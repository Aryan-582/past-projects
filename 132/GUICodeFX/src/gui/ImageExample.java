package gui;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.image.*;

public class ImageExample extends Application {
	private Image[] images;
	private int current = 0, position = 0;

	@Override
	public void start(Stage primaryStage) {
		int sceneWidth = 300, sceneHeight = 250;

		images = new Image[3];
		images[0] = new Image("gui/henson.jpg");
		images[1] = new Image("gui/history.jpg");
		images[2] = new Image("gui/birdStadium.jpg");

		ImageView imageView = new ImageView(images[0]);
		imageView.setFitHeight(sceneHeight - 50);
		imageView.setFitWidth(sceneWidth - 50);

		/* Adding elements */
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(imageView);
		BorderPane.setAlignment(imageView, Pos.CENTER);
		BorderPane.setMargin(imageView, new Insets(3, 3, 3, 3));

		Button nextButton = new Button("Next");
		nextButton.setOnAction(e -> imageView.setImage(images[++current % images.length]));
		nextButton.setPrefSize(sceneWidth / 3, sceneHeight / 10);

		Button rotateButton = new Button("Rotate");
		rotateButton.setOnAction(e -> imageView.setRotate((position += 45) % 360));
		rotateButton.setPrefSize(sceneWidth / 3, sceneHeight / 10);

		FlowPane pane = new FlowPane();
		pane.setHgap(sceneWidth / 3);
		pane.getChildren().addAll(nextButton, rotateButton);
		borderPane.setBottom(pane);

		/* Display the stage */
		Scene scene = new Scene(borderPane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Image Display");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
