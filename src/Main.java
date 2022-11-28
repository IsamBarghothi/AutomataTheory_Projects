import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> alphabet = new ArrayList<>();
    public static ArrayList<String> states =  new ArrayList<>();
    public static String startState = "";
    public static ArrayList<String> finalStates =  new ArrayList<>();
    public static ArrayList<String> transitionsNFA =  new ArrayList<>();
    public static ArrayList<String> statesDFA = new ArrayList<>();
    public static ArrayList<String> transitionsDFA = new ArrayList<>();

    public static ArrayList<String> finalStatesDFA =  new ArrayList<>();



    public static void main(String[] args) {
    readFile("NFA1.txt");
    newStateFinder();
    newTransitionDFA();
//  tableTransitionsNFA();
    DFAPrinter();
    }

private static void readFile(String fileName){
        String[] header = {"STATES","START","FINAL","TRANSITIONS","END"};
        int counter = 0;
        try{
            File machineFile = new File( fileName);
            Scanner fileReader = new Scanner(machineFile);
            fileReader.nextLine();
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (!line.equals(header[counter]) ){
                    headerChecker(counter, line);
                }else {
                    counter++;
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
}

    private static void headerChecker(int counter, String line) {
        if (counter == 0){
            alphabet.add(line);
        }
        if (counter == 1) {
            states.add(line);
        }
        if (counter == 2) {
            startState = line;
        }
        if (counter == 3) {
            finalStates.add(line);
        }
        if (counter == 4) {
            transitionsNFA.add(line);
        }
    }

    private static void newStateFinder() {
        //takes transition arraylist indexes 1 by 1 and splits them
        for (String transition : transitionsNFA) {
            String first = transition.substring(0, 3); //takes the input state and alphabet
            String outState = transition.substring(4); // takes the output state
            for (int j = 1; j < transitionsNFA.size(); j++) { //loop that checks the first element with every other element in the  transition array
                String first2 = transitionsNFA.get(j).substring(0, 3); //takes the input state and alphabet for the 2nd element
                String outState2 = transitionsNFA.get(j).substring(4); // takes the output state for the 2nd element
                if (first.equals(first2)) {
                    if (!outState.equals(outState2)) { // for not having repetitive states e.g AA, BB , CC.....
                        String newState = outState + outState2;
                        statesDFA.add(newState);
                    } else {
                        statesDFA.add(outState);
                    }
                }
            }
        }
        System.out.println("States for DFA: " + statesDFA);
        duplicateRemover();
        System.out.println("NO Duplicates in statesDFA: " + statesDFA);

    }

    private static void newTransitionDFA() {
        for (String state : statesDFA) {
            if (state.length() != 1) { //checks if the state is single or a set
                String firstState = state.substring(0, 1); //takes the first state
                String secondState = state.substring(1);  //takes the second state
                newTransactionFinder(firstState);
                newTransactionFinder(secondState);
            } else {
                newTransactionFinder(state);
            }
        }
        System.out.println("Transitions for DFA: " + transitionsDFA);
    }
private static void newTransactionFinder(String state){
    for (String transactions : transitionsNFA) {
        String instateState = transactions.substring(0,3);
        String outState = transactions.substring(4);
        int count = 0;
        for (String transaction : transitionsNFA) {
            String instateState2 = transaction.substring(0,3);
            String outState2 = transaction.substring(4);
            count += getCount(instateState2, instateState); // counts how many times the first half of the transaction happened

        }
        boolean isUnique = !transitionsDFA.contains(transactions);
        boolean isOnce = count == 1; // the input State and its alpha occurs once
        boolean isInput = transactions.indexOf(state) == 0; // if the state we checked is the first index of the transaction meaning it's the input State
        if (isInput && isUnique && isOnce ) { // if it occured once add it, and the state we checked is the input state in the transaction
            transitionsDFA.add(transactions);
        } else if (!isOnce && isUnique ) {
//            String newTransaction = instateState + " " + outState + outState2;
//            transitionsDFA.add(newTransaction);
        }
    }
    for (String finalState : finalStates){
        isFinal(finalState);
    }
}


//    private static void tableTransitionsNFA(){
//        String transitionTable[][] = new String[alphabet.size()][states.size()];
//        for (String transition: transitionsNFA){
//            String outState = transition.substring(4);
//            System.out.println(outState);
//            for(int row = 0; row < states.size();row++){
//                for (int column = 0; column < alphabet.size();column++){
//                    System.out.println("row: " + row);
//                    System.out.println("column: " + column);
//                    transitionTable[row][column] = "1";
//                }
//
//            }
//        }
//        System.out.println(Arrays.deepToString(transitionTable));
//    }

    private static void DFAPrinter(){
        System.out.println("ALPHABET");
        for (String a : alphabet){
            System.out.println(a);
        }
        System.out.println("STATES");
        for (String a : statesDFA){
            System.out.println(a);
        }
        System.out.println("TRANSITIONS");
        for (String a : transitionsDFA){
            System.out.println(a);
        }
    }

    public static void isFinal(String finalState){
        for (String state : statesDFA) {
            if (state.contains(finalState)) {
                finalStatesDFA.add(state);
            }
        }
    }

    private static void duplicateRemover () { //Removes Duplicates from the newState Array
        for (int i =0;i<statesDFA.size();i++) {
            if(statesDFA.get(i).length() > 1) { //if the state has more than 1 alpha (Set)
                String firstState = statesDFA.get(i).substring(0, 1); //Takes the first Alphabet of the string
                String secondState = statesDFA.get(i).substring(1);////Takes the second Alphabet of the string
                for (int j = 1;j < statesDFA.size();j++){ //loops to check each state with the other states in the array
                    String firstState2 = statesDFA.get(j).substring(0, 1);
                    String secondState2 = statesDFA.get(j).substring(1);
                    boolean isDuplicate = firstState.equals(secondState2) && secondState.equals(firstState2);
                    if (isDuplicate){ //Checks if the 2 states are the same e.g (AB, BA) for an example
                        statesDFA.remove(statesDFA.get(j)); //removes that state after checking
                    }
                }
            }else {
                if(!statesDFA.get(i).equals(startState)){
                    for(int j = 1; j <statesDFA.size();j++){
                        if(statesDFA.get(j).length() > 1){
                            String firstState = statesDFA.get(j).substring(0, 1); //Takes the first Alphabet of the string
                            String secondState = statesDFA.get(j).substring(1);////Takes the second Alphabet of the string
                            if(statesDFA.get(i).equals(firstState) || statesDFA.get(i).equals(secondState) ){
                                statesDFA.remove(statesDFA.get(i));
                            }
                        }
                    }
                }
            }
        }
    }
    private static int getCount(String transaction,String instate) {
        int lastIndex = 0;
        int count = 0;
        while (lastIndex != -1) {
            lastIndex = transaction.indexOf(instate, lastIndex);
            if (lastIndex != -1) {
                count++;
                lastIndex += instate.length();
            }
        }
        return count;
    }


}