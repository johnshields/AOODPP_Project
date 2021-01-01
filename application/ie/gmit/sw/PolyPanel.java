package ie.gmit.sw;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class PolyPanel extends HBox {
    private final Polygon p = new Polygon();

    public PolyPanel() {
        draw();
        this.getChildren().add(p);
        this.setOnMouseClicked(e -> draw());
    }

    private void draw() {
        p.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        p.setStroke(Color.color(Math.random(), Math.random(), Math.random()));

        for (int i = 0; i < 6; i++){
            p.getPoints().addAll((100 + 50 * Math.cos(i * 2 * Math.PI / 6)),
                    (100 + 50 * Math.sin(i * 2 * Math.PI / 6)));
        }
    }
}
