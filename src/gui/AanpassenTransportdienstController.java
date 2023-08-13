package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AanpassenTransportdienstController {

	private DomeinController dc;
	private HoofdSchermSubject subject;
	private Transportdienst transportdienst;
	private ObservableList<Transportdienst> data;
	private ObservableList<Barcode> dataBarcode;
	private ObservableList<String> dataEmailList;
	private ObservableList<String> dataTelNrList;

	@FXML
	private Button btnAddContactpersoon;
	@FXML
	private Button btnBewaar;
	@FXML
	private Button btnVerwijderContactpersoon;
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

	public AanpassenTransportdienstController(DomeinController dc, HoofdSchermSubject subject) {
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
	
	public void updateAanpassenTransportdienst() {
		vulLabels();
		vulTabellen();
	}

	private void vulTabellen() {
		// TODO Auto-generated method stub
		emaiCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));
		telnrCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));
		List<String[]> contactPersons = new ArrayList<>();
		for (int i = 0; i < dataEmailList.size(); i++) {
			String email = dataEmailList.get(i);
			String phone = dataTelNrList.get(i);
			contactPersons.add(new String[] { email, phone });
		}
		tblContactpersoon.getItems().addAll(contactPersons);
	}

	private void vulLabels() {
		lblFout1.setVisible(false);
		lblFout2.setVisible(false);
		tblContactpersoon.setPlaceholder(new Label("Er zijn geen contactpersonen gevonden"));
		txtNaam.setText(transportdienst.getNaam());
		txtAdres.setText(transportdienst.getAdres());
		txtKarakters.setText(Integer.toString(transportdienst.getBarcode().getAantalKarakters()));
		txtPrefix.setText(transportdienst.getBarcode().getVastPrefixDeel());
		cmbCijfers.getItems().addAll(true, false);
		cmbCijfers.getSelectionModel().select(transportdienst.getBarcode().getEnkelCijfers());
		cmbStatus.getItems().addAll(Status.Actief, Status.Inactief);
		cmbStatus.getSelectionModel().select(transportdienst.getStatus());;
	}
	@FXML
	private void btnVerwijderContactpersoon(ActionEvent event) throws IOException {
		int selectedItem = tblContactpersoon.getSelectionModel().getSelectedIndex();
//		if (selectedItem != null) {
			System.out.println(selectedItem);
			
			dataTelNrList.remove(selectedItem);
			dataEmailList.remove(selectedItem);
			
			tblContactpersoon.getItems().clear();
			List<String[]> contactPersons = new ArrayList<>();
			for (int i = 0; i < dataEmailList.size(); i++) {
				String email = dataEmailList.get(i);
				String phone = dataTelNrList.get(i);
				contactPersons.add(new String[] { email, phone });
			}
			tblContactpersoon.getItems().addAll(contactPersons);
//		}
	}
	@FXML
	private void btnVoegToe(ActionEvent event) throws IOException {
		
	}

	@FXML
	private void addContactpersoon(ActionEvent event) {
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
	@FXML
	private void btnBewaarTransportdienst(ActionEvent event) throws IOException {
		List<String> emails = new ArrayList<>();
		for (int i = 0; i < tblContactpersoon.getItems().size(); i++) {
		    String email = emaiCol.getCellData(i);
		    emails.add(email);
		}
		
		List<String> telnrs = new ArrayList<>();
		for (int i = 0; i < tblContactpersoon.getItems().size(); i++) {
		    String telnr = telnrCol.getCellData(i);
		    telnrs.add(telnr);
		}
		System.out.println(txtNaam.getText() + txtAdres.getText()+
				 emails + telnrs+ cmbStatus.getValue());
		try {
			Barcode barcode = new Barcode(Integer.parseInt(txtKarakters.getText()), (Boolean) cmbCijfers.getValue(),
					txtPrefix.getText());

			dc.voegTotTransportdienstToe(new TransportdienstDTO(transportdienst.getId(),txtNaam.getText(), txtAdres.getText(),
					 emails , telnrs, cmbStatus.getValue(), barcode));
			
			subject.update(SchermOpties.RaadpleegTransportdiensten);
			lblFout2.setVisible(false);
		} catch (Exception e) {
			System.out.println("rip");
			lblFout2.setText(e.getMessage());
			lblFout2.setVisible(true);
		}
	}

}
