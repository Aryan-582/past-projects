package gui;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;

public class Pane extends Application {
	@Override /* Method in Application class */
	public void start(Stage primaryStage) {
		int sceneWidth = 100, sceneHeight = 150;

		Button button = new Button("Fear The Turtle");
		FlowPane pane = new FlowPane();
		pane.getChildren().add(button);

		Scene scene = new Scene(pane, sceneWidth, sceneHeight);
		primaryStage.setTitle("FX Button in Pane");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
