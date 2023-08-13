package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import domein.Bestelling;
import domein.DomeinController;
import domein.Doos;
import domein.Product;
import domein.ProductBestelling;
import domein.SchermOpties;
import domein.Status;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.beans.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;

import java.time.*;
import java.util.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.scene.layout.*;
import javafx.event.*;

public class RaadpleegBestellingController implements PropertyChangeListener {

	private DomeinController dc;

	// labels
	@FXML
	private Label lblEmail;
	@FXML
	private Label lblAankoper;
	@FXML
	private Label lblTotPrijs;
	@FXML
	private Label lblTitleOrderBestelling;

	// tabellen
	@FXML
	private TableView<Bestelling> tblBestellingGegevens;
	@FXML
	private TableColumn<Bestelling, Integer> orderIdCol;
	@FXML
	private TableColumn<Bestelling, String> naamKlantCol;
	@FXML
	private TableColumn<Bestelling, LocalDate> datumCol;
	@FXML
	private TableColumn<Bestelling, String> doosCol;
	@FXML
	private TableColumn<Bestelling, String> leveradresCol;
	@FXML
	private TableColumn<Bestelling, Enum<Status>> statusCol;

	// productlijst
	@FXML
	private TableColumn<Product, String> productNaamCol;
	@FXML
	private TableColumn<Product, Double> productPrijsCol;
	@FXML
	private TableColumn<Product, Integer> productAantalCol;
	@FXML
	private TableColumn<Product, Double> productTotaalPrijsCol;
	@FXML
	private Button btnVoegTransportToe;
	@FXML
	private Label lblTypeTransportdienst;
	@FXML
	private Label lblTrackTraceCode;
	@FXML
	private Button btnVeranderBestelling;

	@FXML
	private TableView<ProductBestelling> tblProductLijst;

	private Bestelling bestelling;
	private List<ProductBestelling> product;
	private ObservableList<Bestelling> data;
	private ObservableList<ProductBestelling> productdata;
	private double totaal;

	private HoofdSchermSubject subject;

	public RaadpleegBestellingController(DomeinController dc, HoofdSchermSubject subject) {
		this.dc = dc;
		this.subject = subject;
	}

	@FXML
	private void initialize() {
		tblProductLijst.setPlaceholder(new Label("Er zijn geen producten"));
	}

	public void updateData() {
		bestelling = dc.geefHuidigeBestelling();
		data = FXCollections.observableArrayList(bestelling);

		product = dc.geefAlleProductenVanBestelling(bestelling);
		productdata = FXCollections.observableArrayList(product);
		totaal = dc.getTotaal(bestelling);
	}

	public void updateRaadpleegBestelling() {
		checkStatus();
		vulLabels();
		vulTabellen();
	}

	private void checkStatus() {
		if (bestelling.getStatus() == Status.Geplaatst) {
			btnVoegTransportToe.setVisible(true);
			btnVeranderBestelling.setVisible(false);
			lblTypeTransportdienst.setText("N/A");
			lblTrackTraceCode.setText("N/A");
		} else if (bestelling.getTransportdienst() != null) {
			btnVoegTransportToe.setVisible(false);
			btnVeranderBestelling.setVisible(true);
			lblTypeTransportdienst.setText(bestelling.getTransportdienst().getNaam());
			lblTrackTraceCode.setText(bestelling.getTrackAndTrace());
		}
	}

	private void vulTabellen() {
		// bestelling details
		orderIdCol.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
		naamKlantCol.setCellValueFactory(new PropertyValueFactory<>("Klantnaam"));
		datumCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
		doosCol.setCellValueFactory(new Callback<CellDataFeatures<Bestelling, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Bestelling, String> data) {
                return new SimpleStringProperty(dc.getTypeOfDoos().toString());
            }
        });
		leveradresCol.setCellValueFactory(new PropertyValueFactory<>("LeverAdres"));
		statusCol.setCellValueFactory(new PropertyValueFactory<>("Status"));
		// product details

//	 productTotaalPrijs.setCellValueFactory(new PropertyValueFactory<>("Status"));
		tblBestellingGegevens.setItems(data);
		productNaamCol.setCellValueFactory(new PropertyValueFactory<>("Productnaam"));
		productPrijsCol.setCellValueFactory(new PropertyValueFactory<>("PrijsProduct"));
		productAantalCol.setCellValueFactory(new PropertyValueFactory<>("aantal"));
		productTotaalPrijsCol.setCellValueFactory(new PropertyValueFactory<>("ProductTotaal"));
		tblProductLijst.setItems(productdata);
		lblTotPrijs.setText(String.format("%.2f", totaal));

	}

	private void vulLabels() {
		lblTitleOrderBestelling.setText("Order #" + bestelling.getOrderId());
		lblAankoper.setText(bestelling.getKlant().getAankoper().get(0).getNaam());
		lblEmail.setText(bestelling.getKlant().getAankoper().get(0).getEmail());
		lblTotPrijs.setText(String.valueOf(bestelling.getTotalePrijs()));

	}

	public void buttonVeranderBestelling(ActionEvent event) throws IOException {
		subject.update(SchermOpties.AanpassenBestelling);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

	public void voegTransportdienstToe() throws IOException {
		subject.update(SchermOpties.maakActiefTransportdienst);
	}
}