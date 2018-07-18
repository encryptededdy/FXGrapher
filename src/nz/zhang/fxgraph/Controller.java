package nz.zhang.fxgraph;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nz.zhang.graph.Graph;
import nz.zhang.graph.Node;


public class Controller {
    @FXML
    private Pane mainPane;

    @FXML
    void loadGraph(ActionEvent event) {
        // sample graph
        Node a = new Node("A", 10);
        Node b = new Node("B", 3);
        Node c = new Node("C", 4);
        Node d = new Node("D", 7);
        Node e = new Node("E", 9);
        Node f = new Node("F", 3);
        Node g = new Node("G", 4);

        a.addChild(b, 1);
        a.addChild(c, 2);
        b.addChild(d, 0);
        b.addChild(e, 1);
        c.addChild(f, 3);
        d.addChild(g, 4);
        e.addChild(g, 2);
        f.addChild(g, 0);

        Graph graph = new Graph(a);

        GraphDrawer drawer = new GraphDrawer(mainPane, graph);
        drawer.drawGraph();

        System.out.println("Draw complete!");
    }

}
