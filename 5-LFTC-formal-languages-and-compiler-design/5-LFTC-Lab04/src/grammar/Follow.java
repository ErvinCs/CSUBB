package grammar;

import java.util.*;

public class Follow {
    private Grammar grammar;
    private First first;
    private Map<String, Set<String>> follows;

    public Follow(Grammar grammar, First first){
        this.first = first;
        this.grammar = grammar;
        this.follows = new HashMap<>();
    }

    public Map<String, Set<String>> getFollows() {return  this.follows;}

    public void BuildFollow(){
        Set<String> set = new HashSet<>();
        set.add("Eps");
        set.addAll(ComputeFollow(grammar.getStartingSymbol()));
        follows.put(grammar.getStartingSymbol(),set);

        grammar.getNonTerminals().stream()
                .filter(elem -> !elem.equals(grammar.getStartingSymbol()))
                .forEach(elem ->{
           Set<String> follow = ComputeFollow(elem);
           follows.put(elem, follow);
        });
    }

    private Set<String> ComputeFollow(String symbol){
        Set<String> follow = new HashSet<>();

        this.grammar.nonTerminalRHSProd(symbol).forEach(elem ->
           follow.addAll(ProductionFollow(elem,symbol))
        );
        return follow;
    }

    private Set<String> ProductionFollow(Production elems, String symbol){
        Set<String> follow = new HashSet<>();

        for (int i = elems.getTo().indexOf(symbol) + 1; i < elems.getTo().size(); i++){
            //Throws some wierd NullPonterException - hopefully this actually fixes it
            if (this.first.getFirsts().get(elems.getTo().get(i)) != null) {
                follow.addAll(this.first.getFirsts().get(elems.getTo().get(i)));
                if (!follow.contains("Eps"))
                    return follow;
                follow.remove("Eps");
            }
        }

        if (follows.get(elems.getFrom()) != null)
            follow.addAll(follows.get(elems.getFrom()));
        else
            follow.addAll(ComputeFollow(elems.getFrom()));

        return follow;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        follows.forEach((k,v) -> str.append("Follow(").append(k).append(")=").append(v.toString()).append("\n"));
        return str.toString();
    }
}
