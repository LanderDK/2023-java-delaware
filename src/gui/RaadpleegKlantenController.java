package gui;

import java.io.IOException;
import java.util.List;

import domein.DomeinController;
import domein.Klant;
import domein.SchermOpties;
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
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.event.*;

public class RaadpleegKlantenController {

	private Stage stage;
	private DomeinController dc;
	private List<Klant> searchKlanten;
	private List<Klant> klanten;
	private ObservableList<Klant> data;
	private ObservableList<Klant> klantList;
	@FXML
	private TableView<Klant> klantTable;
	@FXML
	private TableColumn<Klant, String> klantNaamCol;
	@FXML
	private TableColumn<Klant, Integer> openBestellingCol;
	@FXML
	private TextField searchBar;

	@FXML
	private TableColumn<Klant, String> editCol;
	
	HoofdSchermSubject subject;

	public RaadpleegKlantenController(DomeinController dc, HoofdSchermSubject subject) {
		this.dc = dc;
		this.subject = subject;
	}

	@FXML
	private void initialize() {
	}

	public void updateData() {
		klanten = dc.geefAlleKlanten();
		data = FXCollections.observableArrayList(klanten);
		dc.geefOpenBestellingVanKlantCount();
	}

	public void updateRaadpleegKlanten() {
		klantNaamCol.setCellValueFactory(new PropertyValueFactory<>("gebruikersNaam"));
		openBestellingCol.setCellValueFactory(new PropertyValueFactory<>("OpenBestellingCount"));
		klantTable.setItems(data);
	}

	public void searchByName() {
		klantTable.setPlaceholder(new Label("Er zijn geen klanten gevonden die: " + searchBar.getText() + " heten"));
		searchKlanten = dc.geefAlleKlantenMetNaam(searchBar.getText());
		data = FXCollections.observableArrayList(searchKlanten);
		klantTable.setItems(data);
	}

	public void rijKlik() throws IOException {
		klantList = klantTable.getSelectionModel().getSelectedItems();
		dc.setHuidigeKlant(klantList.get(0));
//		dc.setCurrentAankoper(klantList.get(0).getAankoper());
		subject.update(SchermOpties.RaadpleegKlant);

	}
}