package gui;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.event.*;

public class HandlingEvent extends Application {
	/* Notice fields now defined as instance variables */
	/* so inner class can access them */
	private TextField credits, costPerCredit, total;
	
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
		pane.setPadding(new Insets(paneBorderTop, paneBorderRight, paneBorderBottom, paneBorderLeft));

		/* Adding GUI elements to the pane */
		Label creditsLabel = new Label("Credits: ");
		credits = new TextField();
		pane.add(creditsLabel, 0, 0);
		pane.add(credits, 1, 0);

		Label costPerCreditLabel = new Label("Cost Per Credit: ");
		costPerCredit = new TextField();
		pane.add(costPerCreditLabel, 0, 1);
		pane.add(costPerCredit, 1, 1);

		Label totalLabel = new Label("Total: ");
		total = new TextField();
		total.setEditable(false); /* Cannot be changed */
		pane.add(totalLabel, 0, 2);
		pane.add(total, 1, 2);

		Button button = new Button("Compute");
		pane.add(button, 0, 3);
		button.setOnAction(new ButtonHandler());

		/* Display the stage */
		Scene scene = new Scene(pane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Payment Computation");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private class ButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			double creditsValue = Double.parseDouble(credits.getText());
			double costPerCreditValue = Double.parseDouble(costPerCredit.getText());
			total.setText("$" + String.format("%.2f", creditsValue * costPerCreditValue));
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
