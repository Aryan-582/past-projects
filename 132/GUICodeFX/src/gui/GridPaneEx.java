package gui;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class GridPaneEx extends Application {
	@Override /* Method in Application class */
	public void start(Stage primaryStage) {
		int sceneWidth = 300, sceneHeight = 200;
		int verSpaceBetweenNodes = 8, horSpaceBetweenNodes = 8;
		int paneBorderTop = 20, paneBorderRight = 20;
		int paneBorderBottom = 20, paneBorderLeft = 20;
		
		/* Setting pane properties */
		GridPane pane = new GridPane();
		pane.setHgap(horSpaceBetweenNodes);
		pane.setVgap(verSpaceBetweenNodes);
		pane.setPadding(new Insets(paneBorderTop, paneBorderRight, 
					    paneBorderBottom, paneBorderLeft));
		
		/* Adding GUI elements to the pane */
		Label creditsLabel = new Label("Credits: ");
		TextField credits = new TextField();
		pane.add(creditsLabel, 0, 0);
		pane.add(credits, 1, 0);
				
		Label costPerCreditLabel = new Label("Cost Per Credit: ");
		TextField costPerCredit = new TextField();
		pane.add(costPerCreditLabel, 0, 1);
		pane.add(costPerCredit, 1, 1);

		Label totalLabel = new Label("Total: ");
		TextField total = new TextField();
		pane.add(totalLabel, 0, 2);
		pane.add(total, 1, 2);

		Button button = new Button("Compute");
		pane.add(button, 0, 3);
		
		/* Display the stage */
		Scene scene = new Scene(pane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Payment Computation");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
