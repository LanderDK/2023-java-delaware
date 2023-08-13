package gui;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import domein.DomeinController;
import domein.SchermOpties;
import domein.User;
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

public class RaadpleegUsersController {

	private Stage stage;
	private DomeinController dc;
	
	private ObservableList<User> data;
	private ObservableList<User> userList;

	@FXML
	private TableView<User> userTable;
	@FXML
	private TableColumn<User, String> userNaamCol;
	@FXML
	private TableColumn<User, String> rolCol;

	private HoofdSchermSubject subject;

	public RaadpleegUsersController(DomeinController dc, HoofdSchermSubject subject) {
		this.dc = dc;
		this.subject = subject;
	}

	@FXML
	private void initialize() {
	}
	public void updateData() {
		userList = dc.geefAlleUsers();
		data = FXCollections.observableArrayList(userList);
	}
	public void updateRaadpleegUsers() {
		userTable.setPlaceholder(new Label("Er zijn geen users gevonden"));
		userNaamCol.setCellValueFactory(new PropertyValueFactory<>("usernaam"));
		rolCol.setCellValueFactory(new PropertyValueFactory<>("role"));
		userTable.setItems(data);
	}

	public void rijklik() throws IOException {
		userList = userTable.getSelectionModel().getSelectedItems();
		dc.setHuidigeUser(userList.get(0));
		subject.update(SchermOpties.AanpassenUser);
	}

	public void klikVoegToeKnop() throws IOException {
		subject.update(SchermOpties.Registeren);
	}
}
