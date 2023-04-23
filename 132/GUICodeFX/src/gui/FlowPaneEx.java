package gui;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class FlowPaneEx extends Application {
	@Override /* Method in Application class */
	public void start(Stage primaryStage) {
		int sceneWidth = 300, sceneHeight = 200;
		int verSpaceBetweenNodes = 8, horSpaceBetweenNodes = 8;
		int paneBorderTop = 20, paneBorderRight = 20;
		int paneBorderBottom = 20, paneBorderLeft = 20;
		
		/* Setting pane properties */
		FlowPane pane = new FlowPane();
		pane.setHgap(horSpaceBetweenNodes);
		pane.setVgap(verSpaceBetweenNodes);
		pane.setPadding(new Insets(paneBorderTop, paneBorderRight, 
					    paneBorderBottom, paneBorderLeft));
		
		/* Adding GUI elements */
		Label creditsLabel = new Label("Credits: ");
		TextField credits = new TextField();
		pane.getChildren().addAll(creditsLabel, credits);
		
		Label costPerCreditLabel = new Label("Cost Per Credit: ");
		TextField costPerCredit = new TextField();
		pane.getChildren().addAll(costPerCreditLabel, costPerCredit);

		Label totalLabel = new Label("Total: ");
		TextField total = new TextField();
		pane.getChildren().addAll(totalLabel, total);

		Button button = new Button("Compute");
		pane.getChildren().add(button);
		
		/* Display the stage */
		Scene scene = new Scene(pane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Payment Computation");
		primaryStage.setScene(scene);
	
		/* Try resizing the window */
		// primaryStage.setResizable(false); /* prevents stage resizing */
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
