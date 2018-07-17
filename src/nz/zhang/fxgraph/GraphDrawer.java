package nz.zhang.fxgraph;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import nz.zhang.graph.Graph;
import nz.zhang.graph.Node;

import java.util.HashSet;
import java.util.Set;

public class GraphDrawer {
    private VBox parentBox;
    private Graph graph;
    private Set<Node> visited = new HashSet<>();

    public GraphDrawer(VBox parentBox, Graph graph) {
        this.parentBox = parentBox;
        this.graph = graph;
    }

    public void drawGraph() {
        HashSet<Node> currentLevel = new HashSet<>();
        currentLevel.add(graph.getTop());
        drawLevel(currentLevel);
    }

    private void drawLevel(HashSet<Node> currentLevel) {
        HashSet<Node> subLevel = new HashSet<>();
        HBox horizBox = new HBox();
        for (Node n : currentLevel) {
            if (!visited.contains(n)) {
                visited.add(n);
                horizBox.getChildren().add(new GraphNode(n.toString(), n.getCost()));
                subLevel.addAll(n.getChildren().keySet());
            }
        }
        parentBox.getChildren().add(horizBox);
        if (subLevel.size() > 0) drawLevel(subLevel);
    }
}
