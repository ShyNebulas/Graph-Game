import java.util.*;

public class Controller implements ControllerADT {

    private final ViewADT view;
    private final ModelADT model;

    public Controller(ViewADT view, ModelADT model) {
        this.view = view;
        this.model = model;
    }

    //TODO: Implement
    public void getValues() {};

    //TODO: Implement
    public void display() {}

    //TODO: Implement
    public void displayRoute() {}

    //TODO: Implement
    public HashMap<NodeADT, ArrayList<EdgeADT>> compareRoute() { return new HashMap<NodeADT, ArrayList<EdgeADT>>(); }
}
