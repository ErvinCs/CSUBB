package parser;

import exceptions.LexicalError;
import exceptions.SyntaxError;
import grammar.*;
import javafx.util.Pair;
import scanner.auxiliary.*;
import scanner.CodeScanner;
import scanner.ProgramInternalForm;
import scanner.SymbolTable;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PraserLang {
    private First first;
    private Follow follow;
    private Table table;
    private Grammar grammar;

    private LinkedList<String> alpha;
    private LinkedList<String> beta;
    private List<Integer> pi;
    private String result;

    private File file;
    private CodeScanner cs;

    public PraserLang(Grammar grammar) {
        this.grammar = grammar;

        first = new First(grammar);
        first.BuildFirst();
        follow = new Follow(grammar, first);
        follow.BuildFollow();
        table = new Table(first, follow, grammar);
        System.out.println(first.toString());
        System.out.println(follow.toString());
        System.out.println(table.toString());

        alpha = new LinkedList<>();
        beta = new LinkedList<>();
        pi = new ArrayList<>();

        file = new File("res/input/source/input00.txt");
        this.InitAlpha();
        this.InitBeta();
    }

    public PraserLang(Grammar grammar, ProgramInternalForm pif, SymbolTable st) {
        this.grammar = grammar;

        first = new First(grammar);
        first.BuildFirst();
        follow = new Follow(grammar, first);
        follow.BuildFollow();
        table = new Table(first, follow, grammar);
        System.out.println(first.toString());
        System.out.println(follow.toString());
        System.out.println(table.toString());

        alpha = new LinkedList<>();
        beta = new LinkedList<>();
        pi = new ArrayList<>();

        file = new File("res/input/source/input00.txt");
        this.InitAlpha();
        this.InitBeta();
    }

    public void Parse() {
        while (true){
            if (!beta.peekFirst().equals("Eps")) {
//                System.out.println(pi.toString());
//                System.out.println(beta.toString());
//                System.out.println(alpha.toString());
                if (Check(table.getTable().get(new Pair<>(beta.peekFirst(), alpha.peekFirst())), table.getProdNum()) != 0) {
                    String peek = beta.peekFirst();
                    beta.removeFirst();
                    Push(table.getTable().get(new Pair<>(peek, alpha.peekFirst())).getValue());
                    pi.add(table.getTable().get(new Pair<>(peek, alpha.peekFirst())).getKey());
                } else if (table.getTable().get(new Pair<>(beta.peekFirst(), alpha.peekFirst())).getValue().get(0).equals("pop")) {
                    beta.removeFirst();
                    alpha.removeFirst();
                } else if (table.getTable().get(new Pair<>(beta.peekFirst(), alpha.peekFirst())).getValue().get(0).equals("acc")) {
                    result = "acc";
                    break;
                } else {
                    result = "err";
                    break;
                }
            }
            else
                beta.removeFirst();
            ShowStepByStep();
        }
    }

    private int Check(Pair<Integer, List<String>> item, Map<Production, Integer> prodNum)
    {
        for (Map.Entry<Production, Integer> pair : prodNum.entrySet())
            if (pair.getKey().getTo().equals(item.getValue()))
                return pair.getValue();
        return 0;
    }
    private void InitAlpha() {
        try {
            cs = new CodeScanner("res/input/source/input00.txt", "res/output/outputInternal00.txt");
            cs.codify();
        } catch (IOException | LexicalError | SyntaxError ex) {
            System.out.println(ex.toString());
        }
        List<Integer> atoms = new ArrayList<>();
        for(scanner.auxiliary.Pair<Integer, Integer> atom : cs.getPif().getAtoms()) {
            alpha.addLast(atom.getLeft().toString());
        }
        alpha.addLast("$");

//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            String line = br.readLine();
//            while(line != null) {
//                String[] tokens = line.split(" ");
//                for(String token : tokens)
//                    alpha.addLast(token);
//                line = br.readLine();
//            }
//            alpha.addLast("$");
//        } catch (IOException ex) {
//            System.out.println(ex.toString());
//        }
    }

    private void InitBeta() {
        beta.addLast(grammar.getStartingSymbol());
        beta.addLast("$");
    }

    public void ShowResult(){
        if (result.equals("acc")){
            System.out.println("Sequence accepted!");
            pi.forEach(elem -> System.out.print(elem + " "));
            System.out.println();
        }else
            System.out.println("Sequence not accepted!");
    }

    private void ShowStepByStep(){
        this.alpha.forEach(elem -> System.out.print(elem + " "));
        System.out.println();
        this.beta.forEach(elem -> System.out.print(elem + " "));
        System.out.println();
        this.pi.forEach(elem -> System.out.print(elem + " "));
        System.out.println();
    }

    private void Push(List<String> items){
        for (int i = items.size() - 1; i >= 0; i--)
            beta.addFirst(items.get(i));
    }

    public LinkedList<String> getAlpha() {return alpha;}
    public LinkedList<String> getBeta() {return beta;}
    public List<Integer> getPi() {return pi;}
}
