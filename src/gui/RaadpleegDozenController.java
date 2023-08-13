	package gui;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import domein.Bestelling;
import domein.DomeinController;
import domein.Doos;
import domein.SchermOpties;
import domein.Transportdienst;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.*;
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;

public class RaadpleegDozenController{

	private Stage stage;
	private DomeinController dc;
	List<Doos> dozen;
	List<Doos> actiefDozen;
	ObservableList<Doos> data;
	private ObservableList<Doos> doosList;

	@FXML
	private TableView<Doos> doosTable;
	@FXML
	private TableColumn<Doos, String> naamCol;
	@FXML
	private TableColumn<Doos, String> typeCol;
	@FXML
	private TableColumn<Doos, String> afmetingCol;
	@FXML
	private TableColumn<Doos, Double> prijsCol;
	@FXML
	private TableColumn<Doos, String> statusCol;
	@FXML
	private Button btnVoegDoosToe;

	@FXML
	private TableColumn<Transportdienst, String> editCol;
	int buttonList;
	int edit;
	private HoofdSchermSubject subject;

	public RaadpleegDozenController(DomeinController dc, HoofdSchermSubject subject) {
		this.dc = dc;
		this.subject = subject;
	}

	@FXML
	private void initialize() {
	}
	public void updateData() {
		dozen = dc.geefAlleDozen();
		actiefDozen = dc.geefAlleActieveDozen();
	}
	public void updateRaadpleegDozen() {
		data = FXCollections.observableArrayList(dozen);
		naamCol.setCellValueFactory(new PropertyValueFactory<>("naam"));
		typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
		afmetingCol.setCellValueFactory(new PropertyValueFactory<>("afmetingen"));
		prijsCol.setCellValueFactory(new PropertyValueFactory<>("prijs"));
		statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		doosTable.setItems(data);
	}
	public void klikVoegToeKnop() throws IOException {
		subject.update(SchermOpties.AanmakenDoos);
	}

	public void bewaarTransportdienstVoorBestelling() throws IOException {
		Bestelling b = dc.geefHuidigeBestelling();
		dc.updateBestellingTransportdienst(b.getOrderId(), dc.geefHuidigeTransportdienst());
		subject.update(SchermOpties.RaadpleegBestelling);
	}
	
	public void rijKlik() throws IOException {
		doosList = doosTable.getSelectionModel().getSelectedItems();
		dc.setHuidigeDoos(doosList.get(0));
		subject.update(SchermOpties.RaadpleegDoos);
	}
}