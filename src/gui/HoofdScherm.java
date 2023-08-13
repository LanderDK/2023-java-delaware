package gui;

import java.io.IOException;
import java.io.InputStream;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import domein.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.event.*;

public class HoofdScherm implements HoofdSchermSubject {
	private final DomeinController dc;
	private Stage stage;

	@FXML
	private AnchorPane leftPane;
	@FXML
	private AnchorPane DetailPane;

	@FXML
	private Button btnBestelling;
	@FXML
	private Button btnKlant;
	@FXML
	private Button btnTransportdienst;
	@FXML
	private Button btnDoos;
	@FXML
	private Button btnRegistreer;

	@FXML
	private ImageView imgBestelling;
	@FXML
	private ImageView imgKlant;
	@FXML
	private ImageView imgTransport;
	@FXML
	private ImageView imgDoos;
	@FXML
	private ImageView imgRegistreer;
	@FXML
	private Label lblButton1;
	@FXML
	private Label lblButton2;
	@FXML
	private Label lblButton3;
	@FXML
	private Label lblButton4;
	@FXML
	private Label lblButton5;
	@FXML
	private Label welkomLbl;

	Image rooddoos;
	Image roodklant;
	Image roodtruck;
	Image rooddozen;
	Image roodmagazijnier;
	Image witdoos;
	Image witklanten;
	Image wittruck;
	Image witdozen;
	Image witmagazijnier;

	private final RaadpleegBestellingenController raadpleegBestellingenController;
	private final RaadpleegBestellingController raadpleegBestellingController;
	private final AanpassenBestellingController aanpassenBestellingController;
	private final RaadpleegKlantenController raadpleegKlantenController;
	private final RaadpleegKlantController raadpleegKlantController;
	private final RaadpleegTransportdienstenController raadpleegTransportdienstenController;
	private final RaadpleegTransportdienstController raadpleegTransportdienstController;
	private final AanmakenTransportdienstController aanmakenTransportdienstController;
	private final RaadpleegUsersController raadpleegUsersController;
	private final RaadpleegDozenController raadpleegDozenController;
	private final AanmakenDoosController aanmakenDoosController;
	private final AanpassenUserController aanpassenUserController;
	private final RegistrerenController registrerenController;
	private final AanpassenTransportdienstController aanpassenTransportdienstController;
	private final RaadpleegDoosController raadpleegDoosController;

	public HoofdScherm(DomeinController domeinController) {
		dc = domeinController;

		raadpleegBestellingenController = new RaadpleegBestellingenController(dc, this);
		raadpleegBestellingController = new RaadpleegBestellingController(dc, this);
		aanpassenBestellingController = new AanpassenBestellingController(dc, this);
		raadpleegKlantenController = new RaadpleegKlantenController(dc, this);
		raadpleegTransportdienstenController = new RaadpleegTransportdienstenController(dc, this);
		raadpleegTransportdienstController = new RaadpleegTransportdienstController(dc, this);
		aanmakenTransportdienstController = new AanmakenTransportdienstController(dc, this);
		raadpleegUsersController = new RaadpleegUsersController(dc, this);
		raadpleegDozenController = new RaadpleegDozenController(dc, this);
		raadpleegDoosController = new RaadpleegDoosController(domeinController, this);
		aanmakenDoosController = new AanmakenDoosController(dc, this);
		aanpassenUserController = new AanpassenUserController(dc, this);
		registrerenController = new RegistrerenController(dc, this);
		raadpleegKlantController = new RaadpleegKlantController(dc, this);
		aanpassenTransportdienstController = new AanpassenTransportdienstController(domeinController, this);
	}

	@FXML
	private void initialize() throws IOException {
		welkomLbl.setText("Welkom " + dc.getNameOfUser() + "!");
		checkUserRole();
		setImages();
		maakRaadpleegBestellingen();
	}

	private void checkUserRole() {
		if (dc.getRoleOfUser().equals("Admin")) {
			btnRegistreer.setVisible(true);
		} else {
			btnRegistreer.setVisible(false);
		}

	}

	private void setImages() {
		InputStream inputStream = getClass().getResourceAsStream("../resources/rooddoos.png");
		rooddoos = new Image(inputStream);
		inputStream = getClass().getResourceAsStream("../resources/roodklant.png");
		roodklant = new Image(inputStream);
		inputStream = getClass().getResourceAsStream("../resources/roodtruck.png");
		roodtruck = new Image(inputStream);
		inputStream = getClass().getResourceAsStream("../resources/rooddozen.png");
		rooddozen = new Image(inputStream);
		inputStream = getClass().getResourceAsStream("../resources/roodmagazijnier.png");
		roodmagazijnier = new Image(inputStream);
		inputStream = getClass().getResourceAsStream("../resources/witdoos.png");
		witdoos = new Image(inputStream);
		inputStream = getClass().getResourceAsStream("../resources/witklanten.png");
		witklanten = new Image(inputStream);
		inputStream = getClass().getResourceAsStream("../resources/wittruck.png");
		wittruck = new Image(inputStream);
		inputStream = getClass().getResourceAsStream("../resources/witdozen.png");
		witdozen = new Image(inputStream);
		inputStream = getClass().getResourceAsStream("../resources/witmagazijnier.png");
		witmagazijnier = new Image(inputStream);
	}

	@FXML
	public void maakRaadpleegBestellingen() throws IOException {
		leegAlleKnoppenLayout();
		imgBestelling.setImage(witdoos);
		lblButton1.setStyle("-fx-text-fill: #ffffff;");
		btnBestelling.setStyle("-fx-background-color: #EF463B; ");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RaadpleegBestellingen.fxml"));
		loader.setController(raadpleegBestellingenController);
		Pane view = loader.load();
		raadpleegBestellingenController.updateRaadpleegBestellingen();
		leftPane.getChildren().add(view);

	}

	@FXML
	public void maakRaadpleegBestelling() throws IOException {
		DetailPane.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RaadpleegBestelling.fxml"));
		loader.setController(raadpleegBestellingController);
		Pane view = loader.load();
		DetailPane.getChildren().add(view);

	}

	@FXML
	public void maakAanpassenBestelling() throws IOException {
		DetailPane.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AanpassenBestelling.fxml"));
		loader.setController(aanpassenBestellingController);
		Pane view = loader.load();
		DetailPane.getChildren().add(view);
	}

	@FXML
	public void maakRaadpleegKlanten() throws IOException {
		leegAlleKnoppenLayout();
		imgKlant.setImage(witklanten);
		lblButton2.setStyle("-fx-text-fill: #ffffff;");
		btnKlant.setStyle("-fx-background-color: #EF463B;");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RaadpleegKlanten.fxml"));
		loader.setController(raadpleegKlantenController);
		Pane view = loader.load();
		raadpleegKlantenController.updateData();
		raadpleegKlantenController.updateRaadpleegKlanten();
		leftPane.getChildren().add(view);

	}

	@FXML
	public void maakRaadpleegKlant() throws IOException {
		DetailPane.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RaadpleegKlant.fxml"));
		loader.setController(raadpleegKlantController);
		Pane view = loader.load();
		DetailPane.getChildren().add(view);
	}

	@FXML
	public void maakRaadpleegTransportdiensten() throws IOException {
		raadpleegTransportdienstenController.setGefilterd(false);
		leegAlleKnoppenLayout();
		imgTransport.setImage(wittruck);
		lblButton3.setStyle("-fx-text-fill: #ffffff;");
		btnTransportdienst.setStyle("-fx-background-color: #EF463B;");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RaadpleegTransportdiensten.fxml"));
		loader.setController(raadpleegTransportdienstenController);
		Pane view = loader.load();
		raadpleegTransportdienstenController.updateData();
		raadpleegTransportdienstenController.updateRaadpleegTransportdiensten();
		leftPane.getChildren().add(view);

	}

	@FXML
	public void maakRaadpleegDozen() throws IOException {
		leegAlleKnoppenLayout();
		imgDoos.setImage(witdozen);
		lblButton4.setStyle("-fx-text-fill: #ffffff;");
		btnDoos.setStyle("-fx-background-color: #EF463B;");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RaadpleegDozen.fxml"));
		loader.setController(raadpleegDozenController);
		Pane view = loader.load();
		raadpleegDozenController.updateData();
		raadpleegDozenController.updateRaadpleegDozen();
		leftPane.getChildren().add(view);

	}

	@FXML
	public void maakRaadpleegDoos() throws IOException {
		DetailPane.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RaadpleegDoos.fxml"));
		loader.setController(raadpleegDoosController);
		Pane view = loader.load();
		DetailPane.getChildren().add(view);
	}

	@FXML
	public void maakAanmaakDoos() throws IOException {
		DetailPane.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AanmakenDoos.fxml"));
		loader.setController(aanmakenDoosController);
		Pane view = loader.load();
		DetailPane.getChildren().add(view);
	}

	@FXML
	public void maakRaadpleegTransportdienst() throws IOException {
		raadpleegTransportdienstenController.setGefilterd(false);
		DetailPane.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RaadpleegTransportdienst.fxml"));
		loader.setController(raadpleegTransportdienstController);
		Pane view = loader.load();

		DetailPane.getChildren().add(view);

	}

	@FXML
	public void maakAanmaakTransportdienst() throws IOException {
		raadpleegTransportdienstenController.setGefilterd(false);
		DetailPane.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AanmakenTransportdienst.fxml"));
		loader.setController(aanmakenTransportdienstController);
		Pane view = loader.load();
		DetailPane.getChildren().add(view);

	}

	@FXML
	public void maakActiefTransportdienst() throws IOException {

		raadpleegTransportdienstenController.setGefilterd(true);
		DetailPane.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RaadpleegTransportdiensten.fxml"));
		loader.setController(raadpleegTransportdienstenController);
		Pane view = loader.load();
		raadpleegTransportdienstenController.updateData();
		raadpleegTransportdienstenController.updateRaadpleegTransportdiensten();

		DetailPane.getChildren().add(view);
	}

	@FXML
	public void maakRaadpleegUsers() throws IOException {
		if (dc.getRoleOfUser().equals("Admin")) {
			leegAlleKnoppenLayout();
			imgRegistreer.setImage(witmagazijnier);
			lblButton5.setStyle("-fx-text-fill: #ffffff;");
			btnRegistreer.setStyle("-fx-background-color: #EF463B;");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("RaadpleegUsers.fxml"));
			loader.setController(raadpleegUsersController);
			Pane view = loader.load();
			raadpleegUsersController.updateData();
			raadpleegUsersController.updateRaadpleegUsers();
			leftPane.getChildren().add(view);
		}
	}

	private void maakUserszonderRefreshDetail() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("RaadpleegUsers.fxml"));
		loader.setController(raadpleegUsersController);
		Pane view = loader.load();
		leftPane.getChildren().add(view);

	}

	public void maakAanpassenUser() throws IOException {
		DetailPane.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AanpassenUser.fxml"));
		loader.setController(aanpassenUserController);
		Pane view = loader.load();
		DetailPane.getChildren().add(view);

	}

	@FXML
	public void maakRegistreer() throws IOException {
		DetailPane.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Registreren.fxml"));
		loader.setController(registrerenController);
		Pane view = loader.load();
		DetailPane.getChildren().add(view);

	}

	private void maakAanpassenTransportdienst() throws IOException {
		DetailPane.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AanpassenTransportdienst.fxml"));
		loader.setController(aanpassenTransportdienstController);
		Pane view = loader.load();
		DetailPane.getChildren().add(view);

	}

	public void leegAlleKnoppenLayout() {
		DetailPane.getChildren().clear();
		leftPane.getChildren().clear();
		imgBestelling.setImage(rooddoos);
		btnBestelling.setStyle("-fx-background-color: #ffffff; ");
		lblButton1.setStyle("-fx-text-fill: #EF463B;");
		imgKlant.setImage(roodklant);
		btnKlant.setStyle("-fx-background-color: #ffffff; ");
		lblButton2.setStyle("-fx-text-fill: #EF463B;");
		imgTransport.setImage(roodtruck);
		btnTransportdienst.setStyle("-fx-background-color: #ffffff; ");
		lblButton3.setStyle("-fx-text-fill: #EF463B;");
		imgDoos.setImage(rooddozen);
		btnDoos.setStyle("-fx-background-color: #ffffff; ");
		lblButton4.setStyle("-fx-text-fill: #EF463B;");
		imgRegistreer.setImage(roodmagazijnier);
		btnRegistreer.setStyle("-fx-background-color: #ffffff; ");
		lblButton5.setStyle("-fx-text-fill: #EF463B;");
		;
	}

	@Override
	public void update(SchermOpties optie) {
		try {
			switch (optie) {
			case AanpassenBestelling:
				aanpassenBestellingController.updateData();
				maakAanpassenBestelling();
				aanpassenBestellingController.updateAanpassenBestelling();
				break;
			case RaadpleegDozen:
				raadpleegDozenController.updateData();
				maakRaadpleegDozen();
				raadpleegDozenController.updateRaadpleegDozen();
				break;
			case RaadpleegDoos:
				raadpleegDoosController.updateData();
				maakRaadpleegDoos();
				raadpleegDoosController.updateRaadpleegDoos();
				break;
			case AanpassenUser:
				aanpassenUserController.updateData();
				maakAanpassenUser();
				aanpassenUserController.updateAanpassenUser();
				break;
			case RaadpleegTransportdiensten:
				raadpleegTransportdienstenController.updateData();
				maakRaadpleegTransportdiensten();
				raadpleegTransportdienstenController.updateRaadpleegTransportdiensten();
				break;
			case AanmakenDoos:
				maakAanmaakDoos();
				aanmakenDoosController.updateAanmakenDozen();
				break;
			case RaadpleegBestelling:
				raadpleegBestellingController.updateData();
				maakRaadpleegBestelling();
				raadpleegBestellingController.updateRaadpleegBestelling();
				break;
			case RaadpleegBestellingen:
				raadpleegBestellingenController.updateData();
				maakRaadpleegBestellingen();
				raadpleegBestellingenController.updateRaadpleegBestellingen();
				break;
			case RaadpleegKlant:
				raadpleegKlantController.updateData();
				maakRaadpleegKlant();
				raadpleegKlantController.updateRaadpleegKlant();
				break;
			case RaadpleegKlanten:
				raadpleegKlantenController.updateData();
				maakRaadpleegKlanten();
				raadpleegKlantenController.updateRaadpleegKlanten();
				break;
			case RaadpleegUsers:
				raadpleegUsersController.updateData();
				maakRaadpleegUsers();
				raadpleegUsersController.updateRaadpleegUsers();
				break;
			case Registeren:
				maakRegistreer();
				break;
			case AanmakenTransportdienst:
				maakAanmaakTransportdienst();
				aanmakenTransportdienstController.updateAanmakenTransportdienst();
				break;
			case RaadpleegTransportdienst:
				raadpleegTransportdienstController.updateData();
				maakRaadpleegTransportdienst();
				raadpleegTransportdienstController.updateRaadpleegTransportdienst();
				break;
			case maakActiefTransportdienst:
				maakActiefTransportdienst();
				break;
			case AanpassenTransportdienst:
				aanpassenTransportdienstController.updateData();
				maakAanpassenTransportdienst();
				aanpassenTransportdienstController.updateAanpassenTransportdienst();
				break;
			default:
				break;
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
