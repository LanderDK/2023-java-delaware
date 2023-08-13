package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import domein.Bestelling;
import domein.DomeinController;
import domein.Doos;
import domein.Klant;
import domein.SchermOpties;
import domein.Status;
import domein.Transportdienst;
import domein.Type;
import javafx.scene.control.*;

public class AanpassenBestellingController {

	private DomeinController dc;
	private Bestelling bestelling;
	private List<Klant> klantenLijst;
//	private List<Aankoper> aankoperlijst;
	private List<Transportdienst> transportDiensten;
	private List<Status> statussen;
	private List<Type> typeDozen;
	private List<String> klantnamen;
//	private List<String> aankopernamen;
	private List<String> tsNamen;

	@FXML
	private ComboBox<String> naamKlantVeld;
	@FXML
	private Label ordidveld;
	@FXML
	private DatePicker datumGeplaatstVeld;
	@FXML
	private TextField leverAdresVeld;
	@FXML
	private ComboBox<Status> statusVeld;
	@FXML
	private ComboBox<Type> typeDoosVeld;
	@FXML
	private ComboBox<String> transportVeld;
	@FXML
	private Label lblTransportdienst;

	private HoofdSchermSubject subject;

	public AanpassenBestellingController(DomeinController dc, HoofdSchermSubject subject) {
		this.dc = dc;
		this.subject = subject;
	}

	@FXML
	private void initialize() {
	}

	public void updateData() {
		klantnamen = dc.geefAlleKlantenNamen();
		bestelling = dc.geefHuidigeBestelling();
		klantenLijst = dc.geefAlleKlanten();
		transportDiensten = dc.geefAlleActieveTransportdiensten();
		statussen = dc.geefAlleStatussen();
		typeDozen = dc.geefAlleTypeDozen();
		tsNamen = dc.geefAlleActieveTransportdienstNamen();
	}

	public void updateAanpassenBestelling() {
		naamKlantVeld.getItems().addAll(klantnamen);
		statusVeld.getItems().addAll(statussen);
		typeDoosVeld.getItems().addAll(typeDozen);
		if (bestelling.getTransportdienst() == null) {
			transportVeld.setVisible(false);
			lblTransportdienst.setVisible(false);
		} else {
			transportVeld.getItems().addAll(tsNamen);
			lblTransportdienst.setVisible(true);
		}
		vulVelden();
	}

	private void vulVelden() {
		naamKlantVeld.setValue(bestelling.getKlantnaam());
		ordidveld.setText("Order #" + Integer.toString(bestelling.getOrderId()));
//		AankoperVeld.getItems().addAll(aankoperlijst);
		if (bestelling.getTransportdienst() != null)
			transportVeld.setValue(bestelling.getTransportdienst().getNaam());
		leverAdresVeld.setText(bestelling.getLeverAdres());
		
        LocalDate date = bestelling.getDate().toLocalDate();
		datumGeplaatstVeld.setValue(date);
		statusVeld.setValue(bestelling.getStatus());
		typeDoosVeld.setValue(bestelling.getDoos().getType());
	}

	public void updateBestelling() throws IOException {
		Klant nieuweKlant = dc.getKlantByName(naamKlantVeld.getValue(), klantenLijst);
		Transportdienst transportdienst = dc.getTransportdienstByName(transportVeld.getValue(),transportDiensten);
		dc.setDoosType(typeDoosVeld.getValue());
		dc.setHuidigeTransportdienst(transportdienst);
		dc.updateBestellingTransportdienst(bestelling.getOrderId(),transportdienst);
		
		LocalDate selectedDate = datumGeplaatstVeld.getValue();
	    // If you want to use a specific time, you can replace LocalTime.MIDNIGHT with your desired time
	    LocalTime defaultTime = LocalTime.MIDNIGHT;
	    LocalDateTime dateTime = LocalDateTime.of(selectedDate, defaultTime);
		if (dc.updateBestelling(new BestellingDTO(bestelling.getOrderId(), nieuweKlant, dateTime, leverAdresVeld.getText(), 
				statusVeld.getValue(), (Doos) bestelling.getDoos(), transportdienst))) {
			subject.update(SchermOpties.RaadpleegBestellingen);
			subject.update(SchermOpties.RaadpleegBestelling);
		}
	}
}
