package ie.gmit.sw;

/*
 * The PolyPanel is a custom control and can be added to 
 * a container, i.e. any subtype of Pane. 
 */

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
public class PolyPanel extends HBox{ //OCP. We could have subtyped any Panel or even a control
	private Polygon p = new Polygon(); //Favour composition over inheritance

	public PolyPanel() {
		draw(); //Paint ourselves
		this.getChildren().add(p); //Add the polygon as a child node
		this.setOnMouseClicked(e -> draw()); //Plan an observer on the control to call draw() on click events.
	}
	
	private void draw() { //Re-draw the polygon with a random stroke and fill
		p.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        p.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
		
		for (int i = 0; i < 6; i++){
			p.getPoints().addAll(new Double[]{
					(100 + 50 * Math.cos(i * 2 * Math.PI / 6)),
					(100 + 50 * Math.sin(i * 2 * Math.PI / 6))
			});
		}
	}
}