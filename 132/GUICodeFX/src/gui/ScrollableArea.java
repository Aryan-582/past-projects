package gui;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class ScrollableArea extends Application {
	private TextArea dataEntryArea, displayArea;
	private Button button;
	
	@Override 
	public void start(Stage primaryStage) {
		int sceneWidth = 300, sceneHeight = 250;
		dataEntryArea = new TextArea();
		dataEntryArea.setPrefSize(sceneWidth, sceneHeight / 3);
		dataEntryArea.setWrapText(true);
		
		displayArea = new TextArea();
		displayArea.setEditable(false);
		displayArea.setWrapText(true);
	
		/* Adding scroll pane to text area */
		ScrollPane scrollPane = new ScrollPane(displayArea);

		/* Adding elements */
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(dataEntryArea);
		borderPane.setCenter(scrollPane);
		
		button = new Button("Turn Text to Uppercase");
		borderPane.setBottom(button);
		BorderPane.setAlignment(button, Pos.CENTER);
		button.setOnAction(e -> displayArea.setText(dataEntryArea.getText().toUpperCase()));
		
		/* Display the stage */
		Scene scene = new Scene(borderPane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Turn Text to Uppercase");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
