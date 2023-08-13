package gui;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.*;
import domein.*;
import javafx.scene.control.*;
import javafx.event.*;

public class AanmeldenController {

	private Stage stage;
	private DomeinController dc;
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtPaswoord;
	@FXML
	private Label lblFout;

	public AanmeldenController(DomeinController dc) {
		this.dc = dc;
	}

	@FXML
	private void initialize() {
		lblFout.setVisible(false);
	}

	public void meldAan(ActionEvent event) throws IOException {
		if (dc.login(txtUsername.getText(), txtPaswoord.getText())) {
			switchToHome(event);
		} else {
			lblFout.setText(dc.getFoutMeldingLogin());
			lblFout.setVisible(true);
		}
	}

	public void switchToHome(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ButtonMenu.fxml"));
		loader.setController(new HoofdScherm(dc));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
	}


}