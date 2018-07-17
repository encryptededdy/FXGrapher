package nz.zhang.fxgraph;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class GraphNode extends Pane {
    public GraphNode(String name, Integer cost) {
        super();

        Circle circle = new Circle();
        circle.setRadius(40);
        circle.setFill(Color.SKYBLUE);

        Text nameLabel = new Text(name);
        Text costLabel = new Text(cost.toString());
        nameLabel.setFill(Color.WHITE);
        nameLabel.setFont(new Font(30));
        costLabel.setFont(new Font(65));
        costLabel.setOpacity(0.4);
        costLabel.setFill(Color.WHITE);

        StackPane stack = new StackPane();
        stack.getChildren().addAll(circle, costLabel, nameLabel);

        // set view
        getChildren().add(stack);

        setPadding(new Insets(20));
    }
}
