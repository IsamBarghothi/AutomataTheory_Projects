import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TuringMachine {
    private List<String> taping;
    private int tapePointer;
    private String state;
    private List<Transition> transitions;
    private List<String> finalStates;

    public TuringMachine(List<Transition> transitions, List<String> finalStates) {
        this.taping = new ArrayList<>();
        this.tapePointer = 0;
        this.state = "q0";
        this.transitions = transitions;
        this.finalStates = finalStates;
    }

    public void run(String input) {
        taping(input);
        List<String> visitedStates = new ArrayList<>();

        while (true) {
            Transition transition = getTransition();
            if (transition == null) {
                System.out.println("ROUT: " + String.join(" ", visitedStates) + " qR");
                System.out.println("RESULT: rejected");
                return;
            }
            visitedStates.add(state);
            state = transition.getNextState();
            taping(transition.getWriteValue());
            moveTape(transition.getMovement());

            if (finalStates.contains(state)) {
                System.out.println("ROUT: " + String.join(" ", visitedStates) + " " + state);
                System.out.println("RESULT: accepted");
                return;
            }
            if (visitedStates.contains(state)) {
                System.out.println("ROUT: " + String.join(" ", visitedStates) + " " + state);
                System.out.println("RESULT: looped");
                return;
            }
        }
    }

    private Transition getTransition() {
        for (Transition transition : transitions) {
            if (transition.getCurrentState().equals(state) && transition.getReadValue().equals(taping.get(tapePointer))) {
                return transition;
            }
        }
        return null;
    }

    private void moveTape(Movement movement) {
        if (movement == Movement.LEFT) {
            taping("_");
            tapePointer--;
        } else if (movement == Movement.RIGHT) {
            tapePointer++;
            taping("_");
        }
    }

    private void taping(String value) {
        if (tapePointer >= taping.size()) {
            taping.add(value);
        } else {
            taping.set(tapePointer, value);
        }
    }

}
