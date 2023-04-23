package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Slider;
import javafx.geometry.Orientation;
import javafx.scene.layout.*;

public class SliderExample extends Application {
	private Image image;

	@Override
	public void start(Stage primaryStage) {
		int sceneWidth = 300, sceneHeight = 250;

		image = new Image("gui/birdStadium.jpg");
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(sceneHeight - 50);
		imageView.setFitWidth(sceneWidth - 50);
		FlowPane pane = new FlowPane();
		pane.getChildren().add(imageView);

		/* Horizontal slider */
		Slider verticalSlider = new Slider();
		verticalSlider.setOrientation(Orientation.VERTICAL);
		verticalSlider.setMin(0);
		verticalSlider.setMax(1);
		verticalSlider.setValue(1);
		verticalSlider.setMajorTickUnit(.1);
		verticalSlider.setShowTickMarks(true);
		verticalSlider.setShowTickLabels(true);
		verticalSlider.valueProperty().addListener(e -> { imageView.setScaleY(verticalSlider.getValue());});
		pane.getChildren().add(verticalSlider);
		
		/* Horizontal slider */
		Slider horizontalSlider = new Slider();
		horizontalSlider.setMin(0);
		horizontalSlider.setMax(1);
		horizontalSlider.setValue(1);
		horizontalSlider.setMajorTickUnit(.1);
		horizontalSlider.setShowTickMarks(true);
		horizontalSlider.setShowTickLabels(true);
		/* Setting the listener */ 
		horizontalSlider.valueProperty().addListener(e -> {imageView.setScaleX(horizontalSlider.getValue());});
		
		/* What would happen if we enable the following statement? */
		// horizontalSlider.valueProperty().addListener(e -> {imageView.setScaleY(horizontalSlider.getValue());}); 
		
		
		/* Main pane */
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(pane);
		borderPane.setBottom(horizontalSlider);		
		
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