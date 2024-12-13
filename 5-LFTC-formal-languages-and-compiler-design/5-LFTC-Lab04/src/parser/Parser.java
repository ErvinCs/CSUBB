package parser;


import javafx.util.Pair;
import java.io.File;
import java.util.*;

import grammar.Production;
//import scanner.auxiliary.Pair;


public class Parser {

    private LinkedList<String> alpha;
    private LinkedList<String> beta;
    private List<Integer> pi;
    private String result;

    public Parser()
    {
        alpha = new LinkedList<>();
        beta = new LinkedList<>();
        pi = new ArrayList<>();
    }

    public void InitAlpha(List<String> sequence){
        sequence.forEach(elem -> alpha.addLast(elem));
        alpha.addLast("$");
    }

    public void InitBeta(String startingSymbol){
        beta.addAll(Arrays.asList(startingSymbol,"$"));
    }


    public void Start(Map<Pair<String, String>, Pair<Integer, List<String>>> table, Map<Production, Integer> prodNum){

        while (true){
            if (!beta.peekFirst().equals("Eps")) {
                if (Check(table.get(new Pair<>(beta.peekFirst(), alpha.peekFirst())), prodNum) != 0) {
                    String peek = beta.peekFirst();
                    beta.removeFirst();
                    Push(table.get(new Pair<>(peek, alpha.peekFirst())).getValue());
                    pi.add(table.get(new Pair<>(peek, alpha.peekFirst())).getKey());
                } else if (table.get(new Pair<>(beta.peekFirst(), alpha.peekFirst())).getValue().get(0).equals("pop")) {
                    beta.removeFirst();
                    alpha.removeFirst();
                } else if (table.get(new Pair<>(beta.peekFirst(), alpha.peekFirst())).getValue().get(0).equals("acc")) {
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

    private int Check(Pair<Integer, List<String>> item,Map<Production, Integer> prodNum)
    {
        for (Map.Entry<Production, Integer> pair : prodNum.entrySet())
            if (pair.getKey().getTo().equals(item.getValue()))
                return pair.getValue();
        return 0;
    }

    public LinkedList<String> getAlpha() {return alpha;}
    public LinkedList<String> getBeta() {return beta;}
    public List<Integer> getPi() {return pi;}
}
