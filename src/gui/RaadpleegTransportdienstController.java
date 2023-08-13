package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.Barcode;
import domein.DomeinController;
import domein.SchermOpties;
import domein.Status;
import domein.Transportdienst;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;

import javafx.beans.property.SimpleStringProperty;

public class RaadpleegTransportdienstController {

	// Alles van klasse "Object" is eigenlijk van klasse "Barcode"

	private DomeinController dc;

	// Labels
	@FXML
	private Label naamTransdienstLbl;
	@FXML
	private Label naamTransdienstLbl2;
	@FXML
	private Label statusLbl;
	@FXML
	private Label adresLbl;
	
	@FXML
	private Circle statuscircle;

	@FXML
	private TableView<String[]> tblContactpersonen;
	@FXML
	private TableColumn<String[], String> emailContactCol;
	@FXML
	private TableColumn<String[], String> telefoonContactCol;

	// Tabel & Cols
	@FXML
	private TableView<Barcode> barcodeGegevensTbl;
	@FXML
	private TableColumn<Barcode, Integer> aantalKaraktersCol;
	@FXML
	private TableColumn<Barcode, Boolean> enkelCijfersCol;
	@FXML
	private TableColumn<Barcode, String> vastPrefixDeelCol;

	@FXML
	private Button btnPasAan;

	private Transportdienst transportdienst;
	private ObservableList<Transportdienst> data;
	private ObservableList<Barcode> dataBarcode;
	private ObservableList<String> dataEmailList;
	private ObservableList<String> dataTelNrList;
	private HoofdSchermSubject subject;

	public RaadpleegTransportdienstController(DomeinController dc, HoofdSchermSubject subject) {
		this.dc = dc;
		this.subject = subject;
	}

	@FXML
	private void initialize() {
	}

	public void updateData() {
		transportdienst = dc.geefHuidigeTransportdienst();
		data = FXCollections.observableArrayList(transportdienst);
		dataBarcode = FXCollections.observableArrayList(transportdienst.getBarcode());
		dataEmailList = FXCollections.observableList(transportdienst.getEmailContactPersonen());
		dataTelNrList = FXCollections.observableList(transportdienst.getTelefoonNrContactPersonen());
	}

	public void updateRaadpleegTransportdienst() {
		vulLabels();
		vulTabellen();
	}

	private void vulTabellen() {
		
		aantalKaraktersCol.setCellValueFactory(new PropertyValueFactory<>("AantalKarakters"));
		enkelCijfersCol.setCellValueFactory(new PropertyValueFactory<>("EnkelCijfers"));
		vastPrefixDeelCol.setCellValueFactory(new PropertyValueFactory<>("VastPrefixDeel"));
		barcodeGegevensTbl.setItems(dataBarcode);

		emailContactCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));
		telefoonContactCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));

		List<String[]> contactPersons = new ArrayList<>();
		for (int i = 0; i < dataEmailList.size(); i++) {
			String email = dataEmailList.get(i);
			String phone = dataTelNrList.get(i);
			contactPersons.add(new String[] { email, phone });
		}
		tblContactpersonen.getItems().addAll(contactPersons);
	}

	private void vulLabels() {
		tblContactpersonen.setPlaceholder(new Label("Er zijn geen contactpersonen gevonden"));
		naamTransdienstLbl.setText(transportdienst.getNaam());
		naamTransdienstLbl2.setText(transportdienst.getNaam());
		adresLbl.setText(transportdienst.getAdres());
		statusLbl.setText((transportdienst.getStatus().toString()));
		if (transportdienst.getStatus() == Status.Actief) {
			statusLbl.setStyle("-fx-text-fill: #00952A;");
			statuscircle.setStyle("-fx-fill: #00952A;");
		} else {
			statusLbl.setStyle("-fx-text-fill: #EF463B;");
			statuscircle.setStyle("-fx-fill: #EF463B;");
		}
	}

	public void updateTransportdienst() throws IOException {
		Status omgekeerd;
		if (transportdienst.getStatus() == Status.Actief) {
			omgekeerd = Status.Inactief;
			statusLbl.setStyle("-fx-text-fill: #00952A;");
			statuscircle.setStyle("-fx-fill: #00952A;");
		} else {
			omgekeerd = Status.Actief;
			statusLbl.setStyle("-fx-text-fill: #EF463B;");
			statuscircle.setStyle("-fx-fill: #EF463B;");
		}

		if (dc.updateTransportdienst(transportdienst.getId(), omgekeerd)) {
			subject.update(SchermOpties.RaadpleegTransportdiensten);
			subject.update(SchermOpties.RaadpleegTransportdienst);
		}

		

	}

	public void btnPasAan(ActionEvent event) throws IOException {
		subject.update(SchermOpties.AanpassenTransportdienst);
		System.out.println("Testing");
	}

}
