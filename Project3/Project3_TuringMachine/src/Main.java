import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
        public static void main(String[] args) throws IOException {
            String fileName = "input.txt";
            String sCurrentLine;
            List<Transition> transitions = readTransitionsFromFile(fileName);
            List<String> finalStates = readFinalStatesFromFile(fileName);
            TuringMachine turingMachine = new TuringMachine(transitions,finalStates);
            String lastLine = "";
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((sCurrentLine = br.readLine()) != null)
            {
                lastLine = sCurrentLine;
            }

            turingMachine.run(lastLine);


        }
    private static List<String> readFinalStatesFromFile(String fileName) {
        List<String> finalStates = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Read input alphabet
            int inputAlphabetSize = Integer.parseInt(br.readLine().trim());
            br.readLine();

            // Read tape alphabet
            int tapeAlphabetSize = Integer.parseInt(br.readLine().trim());
            br.readLine();

            // Read blank symbol
            String blankSymbol = br.readLine().trim();

            // Read number of states
            int numStates = Integer.parseInt(br.readLine().trim());
            br.readLine();

            // Read start state
            String startState = br.readLine().trim();

            // Read accept state
            String acceptState = br.readLine().trim();
            finalStates.add(acceptState);

            // Read reject state
            String rejectState = br.readLine().trim();
            finalStates.add(rejectState);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return finalStates;
    }
    private static List<Transition> readTransitionsFromFile(String fileName) {
        List<Transition> transitions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Read input alphabet
            int inputAlphabetSize = Integer.parseInt(br.readLine().trim());
            br.readLine();

            // Read tape alphabet
            int tapeAlphabetSize = Integer.parseInt(br.readLine().trim());
            br.readLine();

            // Read blank symbol
            String blankSymbol = br.readLine().trim();

            // Read number of states
            int numStates = Integer.parseInt(br.readLine().trim());
            br.readLine();

            // Read start state
            String startState = br.readLine().trim();

            // Read accept state
            String acceptState = br.readLine().trim();

            // Read reject state
            String rejectState = br.readLine().trim();

            // Read transitions
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length < 5) {
                    continue;
                }
                String currentState = parts[0];
                String readValue = parts[1];
                String nextState = parts[4];
                String writeValue = parts[2];
                Movement movement;
                if (parts[3].equals("R")) {
                    movement = Movement.RIGHT;
                } else if (parts[3].equals("L")) {
                    movement = Movement.LEFT;
                } else {
                    movement = Movement.STAY;
                }
                Transition transition = new Transition(currentState, readValue, nextState, writeValue, movement);
                transitions.add(transition);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transitions;
    }
}



