public class Transition {
    private String currentState;
    private String readValue;
    private String nextState;
    private String writeValue;
    private Movement movement;

    public Transition(String currentState, String readValue, String nextState, String writeValue, Movement movement) {
        this.currentState = currentState;
        this.readValue = readValue;
        this.nextState = nextState;
        this.writeValue = writeValue;
        this.movement = movement;
    }

    public String getCurrentState() {
        return currentState;
    }

    public String getReadValue() {
        return readValue;
    }

    public String getNextState() {
        return nextState;
    }

    public String getWriteValue() {
        return writeValue;
    }

    public Movement getMovement() {
        return movement;
    }
}