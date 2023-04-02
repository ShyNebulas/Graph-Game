package com.graph.cs308group8;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.Node;
import javafx.scene.paint.*;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.text.*;

import java.io.File;
import java.util.*;

public class View extends Application {
    private final double radius = 50;
    private double sceneX, sceneY, layoutX, layoutY;
    private final HashMap<Integer, PairADT<Pane, LinkedHashSet<EdgeADT>>> panes = new HashMap<>();
    private String input;

    private Circle createCircle() {
        Random random = new Random();
        int red = random.nextInt(255), green = random.nextInt(255), blue = random.nextInt(255);

        return new Circle(this.radius, Color.rgb(red, green, blue));
    }

    private Text createCircleText(final NodeADT nodeADT) {
        com.graph.cs308group8.Node node = (com.graph.cs308group8.Node) nodeADT;
        String value = String.valueOf(node.value());

        Text text = new Text(value);
        text.setFont(new Font(32));
        text.setTextOrigin(VPos.CENTER);
        // Centring
        text.setX(text.getX() - text.getLayoutX() - (this.radius / (value.length() > 1 ? 3 : 5)) - (value.length() > 1 ? 2 : 0));
        return text;
    }

    private void setupPane(final Pane pane) {
        pane.setPrefSize(this.radius, this.radius);
        pane.setOnMousePressed(event -> {
            this.sceneX = event.getSceneX();
            this.sceneY = event.getSceneY();
            this.layoutX = pane.getLayoutX();
            this.layoutY = pane.getLayoutY();
        });
    }

    private void translateX(final Pane pane, final Bounds parent, final double offset) {
        // If the panes bounds is within the parent bounds, then you can set the offset value.
        if ((pane.getLayoutX() + offset < parent.getWidth() - pane.getWidth()) && (pane.getLayoutX() + offset > -1)) {
            pane.setTranslateX(offset);
        }
        // Create bounds on the left side of the screen, to avoid warping to the right.
        else if (pane.getLayoutX() + offset < 0) {
            pane.setTranslateX(-pane.getLayoutX());
        }
        // Create bounds at the bottom of the screen, to avoid warping to the top.
        else {
            pane.setTranslateX(parent.getWidth() - pane.getLayoutX() - pane.getWidth());
        }
    }

    private void translateY(final Pane pane, final Bounds parent, final double offset) {
        // If the panes bounds is within the parent bounds, then you can set the offset value.
        if ((pane.getLayoutY() + offset < parent.getHeight() - pane.getHeight()) && (pane.getLayoutY() + offset > -1)) {
            pane.setTranslateY(offset);
        }
        // Create bounds on the left side of the screen, to avoid warping to the right.
        else if (pane.getLayoutY() + offset < 0) {
            pane.setTranslateY(-pane.getLayoutY());
        }
        // Create bounds at the bottom of the screen, to avoid warping to the top.
        else {
            pane.setTranslateY(parent.getHeight() - pane.getLayoutY() - pane.getHeight());
        }
    }

    private Pane createNode(final Scene scene, final NodeADT nodeADT) {
        Circle circle = this.createCircle();
        Text text = this.createCircleText(nodeADT);

        Pane pane = new Pane();
        pane.getChildren().addAll(circle, text);
        this.setupPane(pane);

        EventHandler<MouseEvent> dragEvent = event -> {
            double offsetX = event.getSceneX() - sceneX;
            double offsetY = event.getSceneY() - sceneY;
            Bounds parent = pane.getParent().getLayoutBounds();

            this.translateX(pane, parent, offsetX);
            this.translateY(pane, parent, offsetY);
        };

        pane.setOnMouseDragged(dragEvent);
        pane.setOnMouseReleased(event -> {
            pane.setLayoutX(this.layoutX + pane.getTranslateX());
            pane.setLayoutY(this.layoutY + pane.getTranslateY());
            pane.setTranslateX(0);
            pane.setTranslateY(0);
        });

        // Assigning random start position
        Random random = new Random();
        double xAxis = random.nextDouble(scene.getWidth());
        double yAxis = random.nextDouble(scene.getHeight());
        pane.setLayoutX(xAxis);
        pane.setLayoutY(yAxis);

        return pane;
    }

    private Text createWeightText(final EdgeADT edgeADT) {
        Edge edge = (Edge) edgeADT;

        Text text = new Text(String.valueOf(edge.weight()));
        text.setFont(new Font(24));
        return text;
    }

    private StackPane createWeight(final Line line, final EdgeADT edgeADT) {
        StackPane weight = new StackPane();

        DoubleBinding halfWidth = weight.widthProperty().divide(2);
        DoubleBinding halfHeight = weight.heightProperty().divide(2);
        DoubleBinding halfLengthX = line.endXProperty().subtract(line.startXProperty()).divide(2);
        DoubleBinding halfLengthY = line.endYProperty().subtract(line.startYProperty()).divide(2);
        weight.layoutXProperty().bind(line.startXProperty().add(halfLengthX.subtract(halfWidth)));
        weight.layoutYProperty().bind(line.startYProperty().add(halfLengthY.subtract(halfHeight)));

        weight.getChildren().add(this.createWeightText(edgeADT));

        return weight;
    }

    private Line createLine(final Pane start, final Pane destination) {
        Random random = new Random();
        int red = random.nextInt(255), green = random.nextInt(255), blue = random.nextInt(255);

        Line line = new Line();
        line.setStroke(Color.rgb(red, green, blue));
        line.setStrokeWidth(2);
        line.startXProperty().bind(start.layoutXProperty().add(start.translateXProperty()).add(start.widthProperty().divide(2)));
        line.startYProperty().bind(start.layoutYProperty().add(start.translateYProperty()).add(start.heightProperty().divide(2)));
        line.endXProperty().bind(destination.layoutXProperty().add(destination.translateXProperty()).add(destination.widthProperty().divide(2)));
        line.endYProperty().bind(destination.layoutYProperty().add(destination.translateYProperty()).add(destination.heightProperty().divide(2)));
        return line;
    }

    private Line createEdge(final Pane start, final Pane destination) {
        return this.createLine(start, destination);
    }

    private Pane createTextFields() {
        Pane pane = new Pane();
        TextField textField = new TextField();

        EventHandler<ActionEvent> eventHandler = event -> this.input = textField.getText();
        textField.setOnAction(eventHandler);

        pane.getChildren().add(textField);
        return pane;
    }

    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 1800, 1200);
        ObservableList<Node> nodes = root.getChildren();

        nodes.add(this.createTextFields());

        ModelADT model = new Model(new File("src/main/java/com/graph/cs308group8/data.txt"));

        for(Map.Entry<NodeADT, LinkedHashSet<EdgeADT>> entry : model.getGraph().entrySet()) {
            com.graph.cs308group8.Node node = (com.graph.cs308group8.Node) entry.getKey();

            Pane pane = this.createNode(scene, entry.getKey());
            this.panes.put(node.value(), new Pair<>(pane, entry.getValue()));
            nodes.add(pane);
        }

        for(Map.Entry<NodeADT, LinkedHashSet<EdgeADT>> entry : model.getGraph().entrySet()) {
            com.graph.cs308group8.Node node = (com.graph.cs308group8.Node) entry.getKey();
            Pair<Pane, LinkedHashSet<EdgeADT>> startPair = (Pair<Pane, LinkedHashSet<EdgeADT>>) this.panes.get(node.value());

            for(EdgeADT edgeADT : startPair.value()) {
                Edge edge = (Edge) edgeADT;
                com.graph.cs308group8.Node destination = (com.graph.cs308group8.Node) edge.destination();
                Pair<Pane, LinkedHashSet<EdgeADT>> destinationPair = (Pair<Pane, LinkedHashSet<EdgeADT>>) this.panes.get(destination.value());

                Line line = this.createEdge(startPair.key(), destinationPair.key());

                nodes.addAll(line, this.createWeight(line, edgeADT));
            }
        }

        stage.setTitle("Graph Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}