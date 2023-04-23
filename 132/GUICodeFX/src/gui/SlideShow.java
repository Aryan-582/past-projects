package gui;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.image.*;
import javafx.animation.*;
import javafx.util.Duration;

public class SlideShow extends Application {
	private Image[] images;
	private int current = 0;

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

		/* Animation */
		Timeline animation = new Timeline(
				new KeyFrame(Duration.millis(1000), e -> imageView.setImage(images[++current % images.length])));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();

		/* Controlling Animation (clicking on image will stop animation) */
		imageView.setOnMouseClicked(e -> {
			if (animation.getStatus() == Animation.Status.PAUSED)
				animation.play();
			else
				animation.pause();
		});

		/* Display the stage */
		Scene scene = new Scene(borderPane, sceneWidth, sceneHeight);
		primaryStage.setTitle("SlideShow");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
