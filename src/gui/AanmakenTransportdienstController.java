package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.Barcode;
import domein.DomeinController;
import domein.SchermOpties;
import domein.Status;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.event.*;

public class AanmakenTransportdienstController {

	private Stage stage;
	private DomeinController dc;

	@FXML
	private TextField txtNaam;
	@FXML
	private TextField txtAdres;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtTel;
	@FXML
	private TextField txtKarakters;
	@FXML
	private ComboBox<Boolean> cmbCijfers;
	@FXML
	private ComboBox<Status> cmbStatus;
	@FXML
	private TextField txtPrefix;
	@FXML
	private TextField txtVerificatie;
	@FXML
	private Label lblFout1;
	@FXML
	private Label lblFout2;
	
	@FXML
	private TableView<String[]> tblContactpersoon;
	@FXML
	private TableColumn<String[], String> emaiCol;
	@FXML
	private TableColumn<String[], String> telnrCol;


	@FXML
	private int txtCijfers;
	private HoofdSchermSubject subject;

	public AanmakenTransportdienstController(DomeinController dc, HoofdSchermSubject subject) {
		this.dc = dc;
		this.subject = subject;
	}

	@FXML
	private void initialize() {
		
		
	}
	public void updateAanmakenTransportdienst() {
		tblContactpersoon.setPlaceholder(new Label("Er zijn geen contactpersonen gemaakt"));
		lblFout1.setVisible(false);
		lblFout2.setVisible(false);
		cmbCijfers.getItems().addAll(true, false);
		cmbStatus.getItems().addAll(Status.Actief,Status.Inactief);
	}
	
	public void addContactpersoon() {
		try {	
			if(!dc.valideerContactpersoon(txtEmail.getText(), txtTel.getText()).isEmpty()) {
				lblFout1.setText(dc.valideerContactpersoon(txtEmail.getText(), txtTel.getText()));
				lblFout1.setVisible(true);
			}			
			else
			{
				lblFout1.setVisible(false);
				emaiCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));
				telnrCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));
				tblContactpersoon.getItems().addAll(new String[]{txtEmail.getText(), txtTel.getText()});
			}
			
		}
		catch(Exception e) {
			lblFout1.setText(e.getMessage());
			lblFout1.setVisible(true);
		}
		
	}
	
	

	public void btnVoegToe() throws IOException {
		List<String> emails = new ArrayList<>();
		for (int i = 0; i < tblContactpersoon.getItems().size(); i++) {
		    String email = emaiCol.getCellData(i);
		    emails.add(email);
		}
		
		List<String> telnrs = new ArrayList<>();
		for (int i = 0; i < tblContactpersoon.getItems().size(); i++) {
		    String telnr = telnrCol.getCellData(i);
		    System.out.println(telnr);
		    telnrs.add(telnr);
		}
		
	
		
		try {
			Barcode barcode = new Barcode(Integer.parseInt(txtKarakters.getText()), (Boolean) cmbCijfers.getValue(),
					txtPrefix.getText());
			dc.voegTransportdienstToe(new TransportdienstDTO(txtNaam.getText(), txtAdres.getText(),
					 emails , telnrs, cmbStatus.getValue(), barcode));
			subject.update(SchermOpties.RaadpleegTransportdiensten);
			lblFout2.setVisible(false);
		} catch (Exception e) {
			lblFout2.setText(e.getMessage());
			lblFout2.setVisible(true);
		}
	}

}