package nz.zhang.fxgraph;

import javafx.beans.binding.Bindings;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import nz.zhang.graph.Graph;
import nz.zhang.graph.Node;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphDrawer {
    private Pane parentPane;
    private VBox vbox = new VBox();
    private Graph graph;
    private Map<Node, GraphNode> visited = new HashMap<>();

    public GraphDrawer(Pane parentPane, Graph graph) {
        this.parentPane = parentPane;
        this.graph = graph;

        // populate pane
        parentPane.getChildren().add(vbox);
    }

    public void drawGraph() {
        HashSet<Node> currentLevel = new HashSet<>();
        currentLevel.add(graph.getTop());
        drawLevel(currentLevel);
        drawLines();
    }

    private void drawLevel(HashSet<Node> currentLevel) {
        HashSet<Node> subLevel = new HashSet<>();
        HBox horizBox = new HBox();
        horizBox.setAlignment(Pos.CENTER);
        for (Node n : currentLevel) {
            if (!visited.containsKey(n)) {
                GraphNode graphNode = new GraphNode(n.toString(), n.getCost());
                horizBox.getChildren().add(graphNode);
                visited.put(n, graphNode);
                subLevel.addAll(n.getChildren().keySet());
            }
        }
        vbox.getChildren().add(horizBox);
        if (subLevel.size() > 0) drawLevel(subLevel);
    }

    private void drawLines() {
        for (Map.Entry<Node, GraphNode> node : visited.entrySet()) {
            for (Map.Entry<Node, Integer> child : node.getKey().getChildren().entrySet()) {
                // Draw a line!
                GraphNode source = node.getValue();
                GraphNode dest = visited.get(child.getKey());
                Line line = new Line();
                line.setStrokeWidth(4);
                line.setFill(Color.BLACK);

                // get bounds relative to ParentPane, where we'll be drawing the lines
                Bounds sourceBounds = parentPane.sceneToLocal(source.localToScene(source.getBoundsInLocal()));
                Bounds destnBounds = parentPane.sceneToLocal(dest.localToScene(dest.getBoundsInLocal()));

                System.out.println(parentPane.layoutBoundsProperty());

                System.out.println("Drew line from "+sourceBounds+" to "+destnBounds);

                // ugly code binding X start, end, Y start, end
                line.startXProperty().bind(Bindings.createDoubleBinding(() -> {
                    return sourceBounds.getMinX() + source.getWidth()/2;
                }, parentPane.layoutBoundsProperty()));

                line.startYProperty().bind(Bindings.createDoubleBinding(() -> {
                    return sourceBounds.getMinY() + source.getHeight()/2;
                }, parentPane.layoutBoundsProperty()));

                line.endXProperty().bind(Bindings.createDoubleBinding(() -> {
                    return destnBounds.getMinX() + dest.getWidth()/2;
                }, parentPane.layoutBoundsProperty()));

                line.endYProperty().bind(Bindings.createDoubleBinding(() -> {
                    return destnBounds.getMinY() + dest.getHeight()/2;
                }, parentPane.layoutBoundsProperty()));

                // TODO: Draw weight... somehow!

                parentPane.getChildren().add(line);
            }
        }
    }
}
