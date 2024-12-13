package grammar;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Grammar {
    private Set<String> terminals;
    private Set<String> nonTerminals;
    private Set<Production> productions;
    private String startingSymbol;

    public Grammar() {
        this.terminals = new HashSet<>();
        this.nonTerminals = new HashSet<>();
        this.productions = new HashSet<>();
        this.startingSymbol = "";
    }

    public Grammar(Set<String> terminals, Set<String> nonTerminals, Set<Production> productions, String startingSymbol) {
        this.terminals = terminals;
        this.nonTerminals = nonTerminals;
        this.productions = productions;
        this.startingSymbol = startingSymbol;
    }

    public Set<Production> nonTerminalProductions(String nonTerminal) throws Exception {
        //throws some wierd error but k
        //if (this.terminals.contains(nonTerminal))
        //    throw new Exception("The given element is a Terminal!");

        //StackOverflowError
//        return this.productions.stream()
//                .filter(elem -> elem.getFrom().equals(nonTerminal))
//                .collect(Collectors.toSet());
        Set<Production> nonTermProds = new HashSet<>();
        for(Production p : this.productions) {
            if (p.getFrom().equals(nonTerminal)) {
                nonTermProds.add(p);
            }
        }

        return nonTermProds;
    }

    public Set<Production> nonTerminalRHSProd(String nonTerminal){
        //StackOverflowError
//        return this.productions.stream()
//                .filter(elem -> !elem.getFrom().equals(nonTerminal) && elem.getTo().contains(nonTerminal))
//                .collect(Collectors.toSet());
        Set<Production> nonTermProds = new HashSet<>();
        for(Production p : this.productions) {
            if (!p.getFrom().equals(nonTerminal) && p.getTo().contains(nonTerminal)) {
                nonTermProds.add(p);
            }
        }

        return nonTermProds;

    }
    //&& !elem.getFrom().equals(elem.getTo().get(elem.getTo().size() - 1))
    /**
     * If there exists any non-terminal N -> Eps and N != S, then the grammar is not regular
     * @return true if the Grammar is regular; false otherwise
     */
    public boolean isRegular() {
        /*
        Daca nici unul nu implica Epsilon atunci ii bine cat timp se gata intr-o litera mica i.e. terminal.
        */
        for(Production p : productions) {
            if (p.impliesEps() && !p.getFrom().equals(this.startingSymbol))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder()
                .append("Terminals: ").append(terminals.toString()).append("\n")
                .append("Non-terminals: ").append(nonTerminals.toString()).append("\n")
                .append("Productions: ").append("\n");
        productions.forEach(elem -> stringBuilder.append(elem.toString()));
        return stringBuilder.append("Starting Symbol: ").append(startingSymbol).append("\n").toString();
    }

    public String getStartingSymbol() {
        return startingSymbol;
    }

    public Set<String> getTerminals() {
        return terminals;
    }

    public Set<String> getNonTerminals() {
        return nonTerminals;
    }

    public Set<Production> getProductions() {
        return productions;
    }

    public void setStartingSymbol(String startingSymbol) {
        this.startingSymbol = startingSymbol;
    }

    public void addTerminal(String terminal){
        this.terminals.add(terminal);
    }

    public void addNonTerminal(String nonTerminal){
        this.nonTerminals.add(nonTerminal);
    }

    public void addProduction(Production production){
        this.productions.add(production);
    }
}
