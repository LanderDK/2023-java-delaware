package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import domein.Bestelling;
import domein.DomeinController;
import domein.SchermOpties;
import domein.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.beans.*;
import javafx.stage.*;
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.*;
import java.time.*;
import javafx.scene.layout.*;

public class RaadpleegBestellingenController implements PropertyChangeListener {

	private Stage stage;
	private DomeinController dc;
	private List<Bestelling> bestellingen;
	private List<Bestelling> gefilterdeBestellingen;
	private ObservableList<Bestelling> bestellingData;

	@FXML
	private TableView<Bestelling> bestellingTable;

	@FXML
	private TableColumn<Bestelling, Integer> orderIdCol;
	@FXML
	private TableColumn<Bestelling, String> naamKlantCol;
	@FXML
	private TableColumn<Bestelling, LocalDate> datumCol;
	@FXML
	private TableColumn<Bestelling, Enum<Status>> statusCol;
	@FXML
	private Label lblFilter;

	private ObservableList<Bestelling> bestellingList;

	private Boolean isGefilterd = false;

	private HoofdSchermSubject subject;

	public RaadpleegBestellingenController(DomeinController dc, HoofdSchermSubject subject) {
		this.dc = dc;
		this.subject = subject;
		updateData();
	}

	@FXML
	private void initialize() {
		bestellingTable.setPlaceholder(new Label("Er zijn geen bestellingen"));
		
	}
	
	public void updateData() {
		bestellingen = dc.geefAlleBestellingen();
		bestellingData = FXCollections.observableArrayList(bestellingen);
	}
	
	public void updateRaadpleegBestellingen() {
		orderIdCol.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
		naamKlantCol.setCellValueFactory(new PropertyValueFactory<>("Klantnaam"));
		datumCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
		statusCol.setCellValueFactory(new PropertyValueFactory<>("Status"));
		bestellingTable.setItems(bestellingData);
	}

	public void rijKlik() throws IOException {
		bestellingList = bestellingTable.getSelectionModel().getSelectedItems();
		dc.setHuidigeBestelling(bestellingList.get(0));
		dc.setCurrentAankoper(bestellingList.get(0).getKlant().getAankoper());
		subject.update(SchermOpties.RaadpleegBestelling);	

	}

	public void filterGeplaatstKlik() throws IOException {
		if (!isGefilterd) {
			gefilterdeBestellingen = dc.geefAlleGefilterdeBestellingen();
			bestellingData = FXCollections.observableArrayList(gefilterdeBestellingen);
			updateRaadpleegBestellingen();
			lblFilter.setText("Filter op Alles");
			isGefilterd = true;
		} else if (isGefilterd) {
			bestellingData = FXCollections.observableArrayList(bestellingen);
			updateRaadpleegBestellingen();
			lblFilter.setText("Filter op \"Geplaatst\"");
			isGefilterd = false;
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}
}