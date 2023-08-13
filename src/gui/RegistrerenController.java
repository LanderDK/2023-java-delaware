package gui;

import java.io.IOException;

import domein.DomeinController;
import domein.SchermOpties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.event.*;

public class RegistrerenController {

	private Stage stage;
	private DomeinController dc;
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtPaswoord;
	@FXML
	private TextField repeatTxtPaswoord;
	@FXML
	private ComboBox<String> rolComboBox;
	@FXML
	private Label lblFout;
	@FXML
	private TextField txtVoornaam;
	@FXML
	private TextField txtAchternaam;
	@FXML
	private TextField txtAdres;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtGsm;
	@FXML
	private TextField txtTele;
	private HoofdSchermSubject subject;

	public RegistrerenController(DomeinController dc, HoofdSchermSubject subject) {
		this.dc = dc;
		this.subject = subject;
	}

	@FXML
	private void initialize() {
		lblFout.setVisible(false);
		rolComboBox.getItems().addAll("Magazijnier", "Admin");
	}

	public void registreerAan(ActionEvent event) throws IOException {
		if (dc.registreer(new UserDTO(txtUsername.getText(), txtPaswoord.getText(), repeatTxtPaswoord.getText(),
				rolComboBox.getValue(), txtAchternaam.getText(), txtVoornaam.getText(), txtAdres.getText(),
				txtEmail.getText(), txtGsm.getText(), txtTele.getText()))) {
			lblFout.setText("Medewerker werd aangemaakt!");
			lblFout.setVisible(true);
			subject.update(SchermOpties.RaadpleegUsers);
		}else {
			lblFout.setText(dc.getFoutMeldingRegistreer());
			lblFout.setVisible(true);
		}
		
	}}