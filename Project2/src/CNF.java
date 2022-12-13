import java.util.ArrayList;

public class CNF {

    private ArrayList<String> non_term = new ArrayList<>();
    private ArrayList<String> terminal = new ArrayList<>();
    private ArrayList<String> rules = new ArrayList<>();
    private String start = "";
    private ArrayList<String> ruleForEach = new ArrayList<>();
    private ArrayList<String> removableTerms = new ArrayList<>();

    CNF() {

    }

    public ArrayList<String> getNon_term() {
        return non_term;
    }

    public void setNon_term(ArrayList<String> non_term) {
        this.non_term = non_term;
    }

    public ArrayList<String> getTerminal() {
        return terminal;
    }

    public void setTerminal(ArrayList<String> terminal) {
        this.terminal = terminal;
    }

    public ArrayList<String> getRules() {
        return rules;
    }

    public void setRules(ArrayList<String> rules) {
        this.rules = rules;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public ArrayList<String> getRemovableTerms() {
        return removableTerms;
    }


    public ArrayList<String> getRuleForEach() {
        return ruleForEach;
    }

    public void setRuleForEach(ArrayList<String> ruleForEach) {
        this.ruleForEach = ruleForEach;
    }

    public void setRemovableTerms(ArrayList<String> removableTerms) {
        this.removableTerms = removableTerms;
    }
}
