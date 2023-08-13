package gui;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


import domein.DomeinController;
import domein.Bestelling;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.*;
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;

public class RaadpleegTransportdienstenController {

	private DomeinController dc;
	List<Transportdienst> transportDiensten;
	List<Transportdienst> actiefTransportDiensten;
	ObservableList<Transportdienst> data;
	private ObservableList<Transportdienst> transportdienstList;

	@FXML
	private TableView<Transportdienst> transportdienstTable;
	@FXML
	private TableColumn<Transportdienst, String> transportdienstNaamCol;
	@FXML
	private TableColumn<Transportdienst, String> statusCol;
	@FXML
	private Button btnVoegTransportToe;
	@FXML
	private Button btnSelecteerTransportdienst;
	@FXML
	private Button btnGoBack;

	@FXML
	private TableColumn<Transportdienst, String> editCol;
	private int buttonList;
	private int edit;

	private Boolean isGefilterd = false;
	private HoofdSchermSubject subject;

	public RaadpleegTransportdienstenController(DomeinController dc, HoofdSchermSubject subject) {
		this.dc = dc;
		this.subject = subject;
	}

	@FXML
	private void initialize() {
		
	}

	public void updateData() {
		transportDiensten = dc.geefAlleTransportdiensten();
		actiefTransportDiensten = dc.geefAlleActieveTransportdiensten();
	}

	public void updateRaadpleegTransportdiensten() {
		checkUserRole();
		checkGefiltered();
		transportdienstNaamCol.setCellValueFactory(new PropertyValueFactory<>("naam"));
		statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		transportdienstTable.setItems(data);
	}

	private void checkUserRole() {
		if (dc.getRoleOfUser().equals("Admin")) {
			btnVoegTransportToe.setVisible(true);
		} else {
			btnVoegTransportToe.setVisible(false);
		}
	}

	private void checkGefiltered() {
		if (isGefilterd) {
			btnVoegTransportToe.setVisible(false);
			btnGoBack.setVisible(true);
			if (actiefTransportDiensten.isEmpty()) {
				transportdienstTable.setPlaceholder(new Label("Er zijn geen transportdiensten actief"));
				btnSelecteerTransportdienst.setVisible(false);
			} else {
				btnSelecteerTransportdienst.setVisible(true);
			}

			data = FXCollections.observableArrayList(actiefTransportDiensten);
		} else {
			btnGoBack.setVisible(false);
			transportdienstTable.setPlaceholder(new Label("Er zijn geen transportdiensten"));
			btnVoegTransportToe.setVisible(true);
			btnSelecteerTransportdienst.setVisible(false);
			data = FXCollections.observableArrayList(transportDiensten);
		}
	}

	public void rijKlik() throws IOException {
		transportdienstList = transportdienstTable.getSelectionModel().getSelectedItems();
		dc.setHuidigeTransportdienst(transportdienstList.get(0));
		if (!isGefilterd && dc.getRoleOfUser().equals("Admin"))
			subject.update(SchermOpties.RaadpleegTransportdienst);
			
	}

	public void klikVoegToeKnop() throws IOException {
		subject.update(SchermOpties.AanmakenTransportdienst);
	}

	public void bewaarTransportdienstVoorBestelling() throws IOException {
		Bestelling b = dc.geefHuidigeBestelling();
		dc.updateBestellingTransportdienst(b.getOrderId(), dc.geefHuidigeTransportdienst());
		subject.update(SchermOpties.RaadpleegBestellingen);
		subject.update(SchermOpties.RaadpleegBestelling);
	}

	public void gaTerug() throws IOException {
		subject.update(SchermOpties.RaadpleegBestelling);
	}

	public void setGefilterd(boolean filter) {
		this.isGefilterd = filter;
	}
}
