package com.graph.cs308group8;

import javafx.application.Application;

import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class View extends Application {

    public void start(Stage stage) {
        // Lays out children in top, left, right, bottom, and center positions of the screen.
        BorderPane root = new BorderPane();



        Scene scene = new Scene(root,600, 300);

        stage.setTitle("Graph Game");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }

}