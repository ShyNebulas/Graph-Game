import java.util.*;

public interface ControllerADT {
    void getValues();
    void display();
    void displayRoute();
    HashMap<NodeADT, ArrayList<EdgeADT>> compareRoute();
}
