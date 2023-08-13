package gui;

import java.util.Date;
import java.util.List;

import domein.Aankoper;
import domein.Bestelling;
import domein.DomeinController;
import domein.Klant;
import domein.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class RaadpleegKlantController {

	// Alles van klasse "Object" is eigenlijk van klasse "Barcode"

	private Stage stage;
	private DomeinController dc;
	private HoofdScherm hs;

	// Labels
	@FXML
	private Label naamKlantLbl;
	@FXML
	private Label naamKlantLbl2;
	@FXML
	private Label emailLbl;
	@FXML
	private Label adresLbl;
	@FXML
	private Label telefoonnrLbl;

	@FXML
	private Circle statuscircle;

	@FXML
	private ImageView imgLogo;

	@FXML
	private TableView<Aankoper> tblAankopers;
	@FXML
	private TableColumn<String[], String> naamCol;
	@FXML
	private TableColumn<String[], String> emailCol;

	// Tabel & Cols
	@FXML
	private TableView<Bestelling> bestellingTbl;
	@FXML
	private TableColumn<Bestelling, Integer> idCol;
	@FXML
	private TableColumn<Bestelling, Date> datumCol;
	@FXML
	private TableColumn<Bestelling, Status> statusCol;

	private Klant klant;
	private ObservableList<Klant> data;
	private List<Aankoper> aankopers;
	private List<Bestelling> bestellingen;
	private ObservableList<Aankoper> aankoperData;
	private ObservableList<Bestelling> bestellingData;
	private HoofdSchermSubject subject;

	public RaadpleegKlantController(DomeinController dc, HoofdSchermSubject subject) {
		this.dc = dc;
		this.subject = subject;
	}

	@FXML
	private void initialize() {
		updateRaadpleegKlant();
	}
	public void updateData() {
		klant = dc.geefHuidigeKlant();
		data = FXCollections.observableArrayList(klant);
		aankopers = klant.getAankoper();
		aankoperData = FXCollections.observableArrayList(aankopers);
		bestellingen = dc.geefAlleBestellingenVanKlant(klant);
		bestellingData = FXCollections.observableArrayList(bestellingen);
	}
	public void updateRaadpleegKlant() {
		vulLabels();
		vulTabellen();
	}

	private void vulTabellen() {
		imgLogo.setImage(klant.getLogo());
		naamCol.setCellValueFactory(new PropertyValueFactory<>("naam"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		tblAankopers.setItems(aankoperData);

		idCol.setCellValueFactory(new PropertyValueFactory<>("orderId"));
		datumCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

		bestellingTbl.setItems(bestellingData);
	}

	private void vulLabels() {
		
		naamKlantLbl.setText(klant.getNaam());
		naamKlantLbl2.setText(klant.getNaam());
		emailLbl.setText(klant.getBedrijfsEmail());
		adresLbl.setText(klant.getAdres());
		telefoonnrLbl.setText(klant.getGsmNummer());
	}

}
