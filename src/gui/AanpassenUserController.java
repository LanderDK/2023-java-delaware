
package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.DomeinController;
import domein.SchermOpties;
import domein.User;
import javafx.collections.*;
import javafx.scene.control.*;

public class AanpassenUserController {

	private DomeinController dc;
	private User user;
	private ObservableList<User> data;

	@FXML
	private Label lblFout;

	@FXML
	private Label lblusername;

	@FXML
	private TextField txtUsername;
	@FXML
	private ComboBox<String> rolComboBox;
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

	public AanpassenUserController(DomeinController dc, HoofdSchermSubject subject) {
		this.dc = dc;
		this.subject = subject;
	}

	@FXML
	private void initialize() {
	}

	public void updateData() {
		user = dc.geefHuidigeUser();
		data = FXCollections.observableArrayList(user);
	}

	public void updateAanpassenUser() {
		lblFout.setText("");
		rolComboBox.getItems().addAll("Admin", "Magazijnier");
		vulVelden();
	}

	private void vulVelden() {
		txtUsername.setText(user.getUsernaam());
		rolComboBox.setValue(user.getRole());
		lblusername.setText(user.getUsernaam());
		txtVoornaam.setText(user.getVoorNaam());
		txtAchternaam.setText(user.getAchterNaam());
		txtAdres.setText(user.getAdres());
		txtEmail.setText(user.getEmail());
		txtGsm.setText(user.getGsmNummer());
		txtTele.setText(user.getTeleNummer() == null ? "" : user.getTeleNummer());

	}

	public void updateUser() throws IOException {
		if (dc.updateUser(new UserDTO(user.getId(), txtUsername.getText(), rolComboBox.getValue(),
				txtVoornaam.getText(), txtAchternaam.getText(), txtAdres.getText(), txtEmail.getText(),
				txtGsm.getText(), txtTele.getText()))) {
			subject.update(SchermOpties.RaadpleegUsers);
		}
	}
	
	public void deleteUser() throws IOException {
		if (dc.deleteUser(user)) {
			subject.update(SchermOpties.RaadpleegUsers);
		}
	}
}
