package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.Type;
import domein.Barcode;
import domein.DomeinController;
import domein.SchermOpties;
import domein.Status;
import domein.Transportdienst;
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

public class AanmakenDoosController {

	@FXML
	private TextField txtNaam;
	@FXML
	private ComboBox<Type> cmbType;
	@FXML
	private TextField txtAfmetingen;
	@FXML
	private TextField txtPrijs;
	@FXML
	private ComboBox<Status> cmbStatus;
	@FXML
	private Label lblFout2;

	@FXML
	private int txtCijfers;

	private DomeinController dc;
	private HoofdSchermSubject subject;

	public AanmakenDoosController(DomeinController dc, HoofdSchermSubject subject) {
		this.dc = dc;
		this.subject = subject;
	}

	@FXML
	private void initialize() {
		lblFout2.setVisible(false);
	}
	public void updateAanmakenDozen() {
		cmbType.getItems().addAll(Type.Standaard, Type.Custom);
		cmbStatus.getItems().addAll(Status.Actief, Status.Inactief);
	}
	public void btnVoegToe() throws IOException {

		try {
			dc.voegDoosToe(new DoosDTO(txtNaam.getText(), cmbType.getValue(), txtAfmetingen.getText(),
					Double.parseDouble(txtPrijs.getText()), cmbStatus.getValue()));
			subject.update(SchermOpties.RaadpleegDozen);
			lblFout2.setVisible(false);
			

		} catch (Exception e) {
			lblFout2.setText(e.getMessage());
			lblFout2.setVisible(true);
		}
	}

}