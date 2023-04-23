package InterestTableCalculation;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InterestTableGUI extends Application {
	private TextField principal, rate;
	private TextArea textArea;
	private Slider yearSlider;

	public void start(Stage primaryStage) {
		int sceneWidth = 600, sceneHeight = 500;
		int verSpaceBetweenNodes = 4, horSpaceBetweenNodes = 4;
		int paneBorderTop = 20, paneBorderRight = 20;
		int paneBorderBottom = 20, paneBorderLeft = 20;

		GridPane pane = new GridPane();
		pane.setHgap(horSpaceBetweenNodes);
		pane.setVgap(verSpaceBetweenNodes);
		pane.setPadding(new Insets(paneBorderTop, paneBorderRight, paneBorderBottom, paneBorderLeft));

		Label principalLabel = new Label("Principal: ");
		principal = new TextField();
		pane.add(principalLabel, 0, 4);
		pane.add(principal, 1, 4);

		Label rateLabel = new Label("Rate(Perecntage): ");
		rate = new TextField();
		pane.add(rateLabel, 0, 5);
		pane.add(rate, 1, 5);

		yearSlider = new Slider(1, 25, 1);
		yearSlider.setOrientation(Orientation.HORIZONTAL);
		yearSlider.setMajorTickUnit(1);
		yearSlider.setShowTickMarks(true);
		yearSlider.setShowTickLabels(true);
		pane.add(yearSlider, 0, 8);

		Button simpleInterest = new Button("SimpleInterest");
		pane.add(simpleInterest, 0, 10);

		Button compoundInterest = new Button("CompoundInterest");
		pane.add(compoundInterest, 0, 12);

		Button bothInterest = new Button("BothInterests");
		pane.add(bothInterest, 0, 14);

		textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setWrapText(true);
		ScrollPane scrollPane = new ScrollPane(textArea);
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		pane.add(scrollPane, 0, 0);

		EventHandler<ActionEvent> simpInterest = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				textArea.clear();
				String string = new String();
				double enteredPrincipal = Double.parseDouble(principal.getText());
				double enteredRate = Double.parseDouble(rate.getText());
				string = InterestTable.simpleInterest(enteredPrincipal, enteredRate, yearSlider.getValue());
				textArea.insertText(0, string);
			}
		};

		simpleInterest.setOnAction(simpInterest);
		compoundInterest.setOnAction(e -> {
			textArea.clear();
			String string = new String();
			double enteredPrincipal = Double.parseDouble(principal.getText());
			double enteredRate = Double.parseDouble(rate.getText());
			string = InterestTable.compoundInterest(enteredPrincipal, enteredRate, yearSlider.getValue());
			textArea.insertText(0, string);
		});

		bothInterest.setOnAction(new BothInterest());
		Scene scene = new Scene(pane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Interest Table Calc");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private class BothInterest implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			textArea.clear();
			String string = new String();
			double enteredPrincipal = Double.parseDouble(principal.getText());
			double enteredRate = Double.parseDouble(rate.getText());
			string = InterestTable.bothInterest(enteredPrincipal, enteredRate, yearSlider.getValue());
			textArea.insertText(0, string);
		}
	}

	public static void main(String[] args) {
		Application.launch(args);

	}
}
