package main;

import java.io.InputStream;

import dataLaag.*;
import domein.DomeinController;
import gui.AanmeldenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.application.*;
import javafx.stage.*;

public class StartGui extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		DataClass data = new DataClass();
		DomeinController dc = new DomeinController();
		//data.aanmaakData();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/Aanmelden.fxml"));
		
		AanmeldenController controller = new AanmeldenController(dc);
		fxmlLoader.setController(controller);
		Parent root = fxmlLoader.load();

		Scene scene = new Scene(root);
		// add css
		String css = this.getClass().getResource("../gui/application.css").toExternalForm();
		scene.getStylesheets().add(css);
		InputStream inputStream = getClass().getResourceAsStream("../resources/smallLogo.png");
		stage.getIcons().add(new Image(inputStream));
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}
}