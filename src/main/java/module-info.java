module com.graph.cs308group8 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.graph.cs308group8 to javafx.fxml;
    exports com.graph.cs308group8;
}