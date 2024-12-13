package grammar;

import com.sun.xml.internal.bind.v2.TODO;
import javafx.util.Pair;

import java.util.*;

public class Table {
    private First first;
    private Follow follow;
    private Map<Pair<String,String>,Pair<Integer,List<String>>> table;
    private Map<Production,Integer> prodNum;
    private Grammar grammar;

    public Table(First first, Follow follow,Grammar grammar){
        this.first = first;
        this.follow = follow;
        this.grammar = grammar;
        this.table = new HashMap<>();
        this.prodNum = new HashMap<>();
        NumProductions();
        ConstructTable();
    }

    public Map<Pair<String, String>, Pair<Integer, List<String>>> getTable() { return table; }
    public Map<Production, Integer> getProdNum() { return prodNum; }

    private void NumProductions(){
        final int[] index = {1};
        grammar.getProductions().forEach(elem -> {
            prodNum.put(elem,index[0]);
            index[0]++;});
    }

    private void ConstructTable(){
        List<String> pop = new ArrayList<>();
        pop.add("pop");

        first.getFirsts().forEach((k,v) -> {
            if (grammar.getTerminals().contains(k))
                table.put(new Pair<>(k,k),new Pair<>(0,pop));
            else
                Compute(k,v);
        });
        table.put(new Pair<>("$","$"), new Pair<>(0,new ArrayList<>(Collections.singletonList("acc"))));
    }

    private void Compute(String nonTerminal, Set<String> firstSet){
        firstSet.forEach(elem -> {
            if (elem.equals("Eps"))
                AddFollowToTable(nonTerminal);
            else {
                Production production = FindProduction(nonTerminal, elem);
                assert production != null;
                table.put(new Pair<>(nonTerminal,elem),new Pair<>(prodNum.get(production),production.getTo()));
            }
        });
    }

    private Production FindProduction(String nonTerminal,String symbol){
        try {
            for (Production elem : this.grammar.nonTerminalProductions(nonTerminal)) {
                if (!elem.impliesEps() && Find(symbol, elem))
                    return elem;
            }
        } catch (Exception e) {
            return null;
        }
        return  null;
    }

    private boolean Find(String symbol, Production production){
        for (String elem : production.getTo()){
            if (elem.equals(symbol))
                return true;
            if (this.first.getFirsts().get(elem).contains(symbol))
                return true;
        }
        return false;
    }

    private void AddFollowToTable(String nonTerminal){
        Pair<Production,Integer> prod = findEpsProduction(nonTerminal);
        follow.getFollows().get(nonTerminal).forEach(elem ->{
            if (elem.equals("Eps")) {
                assert prod != null;
                table.put(new Pair<>(nonTerminal,"$"), new Pair<>(prod.getValue(),prod.getKey().getTo()));
            }
            else {
                assert prod != null;
                table.put(new Pair<>(nonTerminal,elem), new Pair<>(prod.getValue(),prod.getKey().getTo()));
            }
        });
    }

    private Pair<Production,Integer> findEpsProduction(String symbol){
        for (Map.Entry<Production,Integer> elem: prodNum.entrySet()) {
            if (elem.getKey().getFrom().equals(symbol) && elem.getKey().getTo().contains("Eps"))
                return new Pair<>(elem.getKey(), elem.getValue());
        }
        return null;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        table.forEach((k,v) -> str.append(k.getKey()).append(", ").append(k.getValue()).append(" = ")
                .append(v.getValue().toString()).append(", ").append(v.getKey()).append("\n"));
        return  str.toString();
    }
}
