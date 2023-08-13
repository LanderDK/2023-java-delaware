package gui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domein.Barcode;
import domein.DomeinController;
import domein.Doos;
import domein.SchermOpties;
import domein.Status;
import domein.Transportdienst;
import domein.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.shape.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.event.*;

public class RaadpleegDoosController {
	
	private DomeinController dc;

	
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
	private Label lblNaam;

	@FXML
	private int txtCijfers;
	
	private Doos doos;
	private ObservableList<Doos> data;
	private HoofdSchermSubject subject;

	public RaadpleegDoosController(DomeinController dc, HoofdSchermSubject subject) {
		this.dc = dc;
		this.subject = subject;
	}

	@FXML
	private void initialize() {
		lblFout2.setVisible(false);
	}

	public void updateData() {
		doos = dc.geefHuidigeDoos();
		data = FXCollections.observableArrayList(doos);
	}

	public void updateRaadpleegDoos() {
		vulLabels();
		cmbType.getItems().addAll(Type.Standaard, Type.Custom);
		cmbStatus.getItems().addAll(Status.Actief, Status.Inactief);
	}

	private void vulLabels() {
		
		txtNaam.setText(doos.getNaam());
		lblNaam.setText(doos.getNaam());
		cmbType.setValue(doos.getType());
		txtAfmetingen.setText(doos.getAfmetingen());
		
		Double prijs = doos.getPrijs();
		txtPrijs.setText(prijs.toString());
		cmbStatus.setValue(doos.getStatus());
	}
	
	public void deleteDoos() throws IOException {
		if (dc.deleteDoos(doos)) {
			subject.update(SchermOpties.RaadpleegDozen);
			subject.update(SchermOpties.RaadpleegDoos);
		}
	}

	public void updateDoos() throws IOException {
		try {
			if (dc.updateDoos(new DoosDTO(doos.getId(), txtNaam.getText(), cmbType.getValue(), txtAfmetingen.getText(), Double.valueOf(txtPrijs.getText()), cmbStatus.getValue()))) {
				subject.update(SchermOpties.RaadpleegDozen);
				subject.update(SchermOpties.RaadpleegDoos);
			}
		} catch (Exception e) {
			lblFout2.setText(e.getMessage());
			lblFout2.setVisible(true);
		}
		
		
	}

}
