package grammar;

import java.util.ArrayList;
import java.util.List;

public class Production {
    private String from;
    private List<String> to;

    public Production() {
        this.from = "";
        this.to = new ArrayList<>();
    }

    public Production(String from, List<String> to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public List<String> getTo() {
        return to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void addTo(String to) {
        this.to.add(to);
    }

    public boolean impliesEps() {
        for(String symbol : to) {
            if (symbol.equals("Eps"))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String output = from + "->";
        for(String symbol : to) {
            output += symbol + " ";
        }
        output += "\n";
        return output;
    }
}
