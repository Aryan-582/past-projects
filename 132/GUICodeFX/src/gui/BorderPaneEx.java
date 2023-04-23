package gui;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class BorderPaneEx extends Application {
	@Override /* Method in Application class */
	public void start(Stage primaryStage) {
		int sceneWidth = 300, sceneHeight = 200;
		int verSpaceBetweenNodes = 8, horSpaceBetweenNodes = 8;
		int paneBorderTop = 20, paneBorderRight = 20;
		int paneBorderBottom = 20, paneBorderLeft = 20;
		
		/* Setting pane properties */
		FlowPane fieldsPane = new FlowPane();
		fieldsPane.setHgap(horSpaceBetweenNodes);
		fieldsPane.setVgap(verSpaceBetweenNodes);
		fieldsPane.setPadding(new Insets(paneBorderTop, paneBorderRight, 
					    paneBorderBottom, paneBorderLeft));
		
		/* Adding GUI elements */
		Label creditsLabel = new Label("Credits: ");
		TextField credits = new TextField();
		fieldsPane.getChildren().addAll(creditsLabel, credits);
		
		Label costPerCreditLabel = new Label("Cost Per Credit: ");
		TextField costPerCredit = new TextField();
		fieldsPane.getChildren().addAll(costPerCreditLabel, costPerCredit);

		Label totalLabel = new Label("Total: ");
		TextField total = new TextField();
		fieldsPane.getChildren().addAll(totalLabel, total);

		/* Adding to BorderPane */
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(fieldsPane);
		Button button = new Button("Compute");
		borderPane.setCenter(button);
		
		/* Display the stage */
		Scene scene = new Scene(borderPane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Payment Computation");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
