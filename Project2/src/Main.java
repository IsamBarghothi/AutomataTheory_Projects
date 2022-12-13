import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
public class Main {
    public static ArrayList<String> rulesForS = new ArrayList<>();
    public static ArrayList<String> rulesForF = new ArrayList<>();

    public static void main(String[] args) {
        CNF cnf1 = new CNF();

        FileReader file1 = new FileReader("G1.txt",cnf1);
        file1.readFile(file1.getFileName());
        converter(cnf1);
        System.out.println("=====For the Second File=====");
        CNF cnf2 = new CNF();
        FileReader file2 = new FileReader("G2.txt",cnf2);
        file2.readFile(file2.getFileName());
        converter(cnf2);
    }

    public static void converter(CNF cnf){
        //Add a new Start variable S0
        newStart(cnf);
//        sortRule(cnf);
        //Remove epsilon
        RemoveEpsilon(cnf);
        ///Remove nullable
        removingNullableRules(cnf);
        removingStartRule(cnf);
        nonTermRemovable(cnf);
    }


    public static void RemoveEpsilon(CNF cnf){
        for (int i=0; i< cnf.getRules().size();i++){ // didn't use enhanced loop because it uses an iterator which gives out an exception ERROR
            String[] parts = cnf.getRules().get(i).split(":");
            String lhs = parts[0];
            String rhs = parts[1];
            if(!lhs.equals(cnf.getStart()) && rhs.equals("e")){
                cnf.getRemovableTerms().add(lhs);
                cnf.getRules().remove(cnf.getRules().get(i));
            }
        }
        System.out.println("----Removing epsilon----");
        System.out.println("Rules after Epsilon Removal: " + cnf.getRules());
    }
    public static void newStart(CNF cnf){
        cnf.getRules().add(0,"S0"+ ":" + cnf.getStart());
        cnf.setStart("S0");
        System.out.println("----Adding S0----");
        System.out.println(cnf.getRules());
    }

    public static void removingNullableRules(CNF cnf){
        for (int i=1; i < cnf.getRules().size();i++){
            String[] parts = cnf.getRules().get(i).split(":");
            String lhs = parts[0];
            String rhs = parts[1];
            for (int j =0; j < cnf.getRemovableTerms().size(); j++){
                if (rhs.contains(cnf.getRemovableTerms().get(j)) && !lhs.equals(cnf.getStart())){
                    String removedTerm = cnf.getRemovableTerms().get(j);
                    String newRule = lhs  + ":" + rhs.replace(removedTerm,"");
                    cnf.getRules().add(newRule);
                }
            }
        }
        System.out.println("----Removing Null Rules----");
        System.out.println("Rules after NUll Removal2: " + cnf.getRules());
    }

    public static void removingStartRule(CNF cnf){
        cnf.getRules().remove(0);
        for (int i = 0; i < cnf.getRules().size();i++){
            String firstChar = cnf.getRules().get(i).substring(0,1);
            String secondChar = cnf.getRules().get(i).substring(1,2);
            if (firstChar.equals("S") && !secondChar.equals("0")){
                String rhs = cnf.getRules().get(i).substring(2);
                String newStartRule = "S0:" + rhs;
                cnf.getRules().add(newStartRule);
            }
        }
        System.out.println("Rules after S0->S Removal: " + cnf.getRules());
    }
    public static void nonTermRemovable(CNF cnf){
        for (int i = 1; i < cnf.getRules().size();i++){
            String[] parts = cnf.getRules().get(i).split(":");
            String lhs = parts[0];
            String rhs = parts[1];
            for (int j=0;j<cnf.getNon_term().size();j++){
                String nonTerm = cnf.getNon_term().get(j);
                if (rhs.equals(nonTerm) && !lhs.equals(nonTerm) && nonTerm.equals("S")){
                    for (int z=0; j < rulesForS.size();z++){
                        String newRhs = rhs.replace(nonTerm,rulesForS.get(z));
                        String newRule = lhs + ":" +newRhs;
                        cnf.getRules().add(newRule);
                    }
                } else if (rhs.equals(nonTerm) && !lhs.equals(nonTerm) && nonTerm.equals("F")) {
                    for (int z=0; j < rulesForF.size();z++){
                        String newRhs = rhs.replace(nonTerm,rulesForF.get(z));
                        String newRule = lhs + ":" +newRhs;
                        cnf.getRules().add(newRule);
                    }
                }
            }
        }
        System.out.println("After nonTerminal Removal:"+ cnf.getRules());
    }

    public static void correctForm(CNF cnf) {
        int newTermCounter = 1;
        for (int i = 1; i < cnf.getRules().size(); i++) {
            String[] parts = cnf.getRules().get(i).split(":");
            String lhs = parts[0];
            String rhs = parts[1];
            if (rhs.length() >= 2) {
                if(rhs.contains("00")){
                    
                }else if(rhs.contains("11")){

                }
            }
        }
    }

    public static void sortRule(CNF cnf){
        for (int i = 0; i < cnf.getRules().size(); i++) {
            String[] parts = cnf.getRules().get(i).split(":");
            String lhs = parts[0];
            String rhs = parts[1];
                if (lhs.equals(cnf.getNon_term().get(0))){
                    rulesForS.add(rhs);
                } else if (lhs.equals(cnf.getNon_term().get(1))) {
                    rulesForF.add(rhs);
                }
        }
        System.out.println(rulesForS);
        System.out.println(rulesForF);
    }
}




