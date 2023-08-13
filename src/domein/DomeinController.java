package domein;

import domein.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import gui.BestellingDTO;
import gui.DoosDTO;
import gui.RaadpleegBestellingenController;
import gui.TransportdienstDTO;
import gui.UserDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DomeinController {

	private BestellingBeheerder bestellingBeheerder;
	private LoginBeheerder loginBeheerder;
	private TransportdienstBeheerder transportdienstBeheerder;
	private UserBeheerder userBeheerder;
	private KlantBeheerder klantBeheerder;
	private ProductBestellingBeheerder productBestellingBeheerder;
	private DoosBeheerder doosBeheerder;

	private BedrijfsTransportdienst currentTransportdienst;
	private BedrijfsBestelling currentBestelling;
	private BedrijfsKlant currentKlant;
	private BedrijfsUser currentLogedIn;
	private BedrijfsUser currenUser;
	private List<Aankoper> currentAankoper;
	private BedrijfsDoos currentDoos;

	private PropertyChangeSupport subject;
	private BedrijfsProduct bedrijfsProduct;
	private ProductBeheerder productBeheerder;

	public DomeinController() {
		subject = new PropertyChangeSupport(this);

		bestellingBeheerder = new BestellingBeheerder();
		loginBeheerder = new LoginBeheerder();
		transportdienstBeheerder = new TransportdienstBeheerder();
		klantBeheerder = new KlantBeheerder();
		productBestellingBeheerder = new ProductBestellingBeheerder();
		bedrijfsProduct = new BedrijfsProduct();
		productBeheerder = new ProductBeheerder();
		userBeheerder = new UserBeheerder();
		doosBeheerder = new DoosBeheerder();
	}

	// bestellingen
	public ObservableList<Bestelling> geefAlleBestellingen() {
		ObservableList<Bestelling> observableList = FXCollections.observableArrayList();
		List<BedrijfsBestelling> bestellingen = bestellingBeheerder.getBestellingen();
		for (BedrijfsBestelling bestelling : bestellingen) {
			observableList.add((Bestelling) bestelling);
		}
		return observableList;
	}

	public List<Bestelling> geefAlleGefilterdeBestellingen() {
		ObservableList<Bestelling> observableList = FXCollections.observableArrayList();
		List<BedrijfsBestelling> gefilterdeBestellingen = bestellingBeheerder.getGefilterdeBestellingen();
		for (Bestelling bestelling : gefilterdeBestellingen) {
			observableList.add(bestelling);
		}
		return observableList;
	}

	public ObservableList<ProductBestelling> geefAlleProductenVanBestelling(Bestelling bestelling) {
		ObservableList<ProductBestelling> observableList = FXCollections.observableArrayList();
		List<BedrijfsProductBestelling> producten = productBestellingBeheerder.getProductOfBestelling(bestelling);
		for (ProductBestelling product : producten) {
			observableList.add((ProductBestelling) product);
		}
		return observableList;
	}

	public double getTotaal(Bestelling bestelling) {
		double totaal = 0;
		ObservableList<ProductBestelling> observableList = geefAlleProductenVanBestelling(bestelling);
		for (ProductBestelling sub : observableList) {
			totaal += sub.getProductTotaal();
		}
		return totaal;
	}

	public BedrijfsBestelling getBestelling(int id) {
		return bestellingBeheerder.getBestellingenMetId(id);
	}

	// bij interfaces hoef je geen extra parameters toe te voegen
	public Bestelling geefHuidigeBestelling() {
		return currentBestelling;

	}

	public void setHuidigeBestelling(Bestelling bs) {
		currentBestelling = (BedrijfsBestelling) bs;
	}

	// Klanten
	@SuppressWarnings("unchecked")
	public List<Klant> geefAlleKlanten() {

		return (List<Klant>) (Object) klantBeheerder.getKlant();
	}

	public List<String> geefAlleKlantenNamen() {
		List<Klant> klantenLijst = (List<Klant>) (Object) klantBeheerder.getKlant();
		List<String> klantnamen = new ArrayList<String>();
		for (Klant k : klantenLijst) {
			klantnamen.add(k.getNaam());
		}
		return klantnamen;
	}

	public Klant geefKlant() {
		return currentKlant;

	}

	@SuppressWarnings("unchecked")
	public List<Klant> geefAlleKlantenMetNaam(String search) {
		return (List<Klant>) (Object) klantBeheerder.getKlantByName(search);

	}

	// transportdiensten
	@SuppressWarnings("unchecked")
	public List<Transportdienst> geefAlleTransportdiensten() {
		return (List<Transportdienst>) (Object) transportdienstBeheerder.getTransportdiensten();
	}

	@SuppressWarnings("unchecked")
	public List<Transportdienst> geefAlleActieveTransportdiensten() {
		return (List<Transportdienst>) (Object) transportdienstBeheerder.getActiefTransportdiensten();
	}

	public List<String> geefAlleActieveTransportdienstNamen() {
		List<Transportdienst> transportDiensten = geefAlleActieveTransportdiensten();
		List<String> transportdienstNamen = new ArrayList<String>();
		for (Transportdienst transportdienst : transportDiensten) {
			transportdienstNamen.add(transportdienst.getNaam());
		}
		return transportdienstNamen;
	}

	public BedrijfsTransportdienst geefHuidigeTransportdienst() {
		return currentTransportdienst;

	}

	public void setHuidigeTransportdienst(Transportdienst bt) {
		currentTransportdienst = (BedrijfsTransportdienst) bt;
	}

	public boolean login(String username, String paswoord) {
		if (loginBeheerder.login(username, paswoord)) {
			currentLogedIn = loginBeheerder.getUser();
			return true;
		}
		return false;
	}

	public boolean registreer(UserDTO user) {
		if (userBeheerder.registreer(user))
			return true;
		return false;
	}

	public String getNameOfUser() {
		if (currentLogedIn == null || currentLogedIn.getUsernaam() == null)
			return "Bypass knop gebruikt lolol";
		else
			return currentLogedIn.getUsernaam();
	}

	public String getRoleOfUser() {
		if (currentLogedIn == null || currentLogedIn.getRole() == null)
			return "Bypass knop gebruikt lolol";
		else
			return currentLogedIn.getRole();
	}

	public String getFoutMeldingLogin() {
		return loginBeheerder.getFoutMelding();
	}

	public String getFoutMeldingRegistreer() {
		return userBeheerder.getFoutMelding();
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		subject.addPropertyChangeListener(pcl);
		pcl.propertyChange(new PropertyChangeEvent(pcl, "currentBestelling", currentBestelling, currentBestelling));
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		subject.removePropertyChangeListener(pcl);
	}

	public void voegTransportdienstToe(TransportdienstDTO transport) {
		transportdienstBeheerder.voegTransportdienstToe(transport);

	}

	public void voegTotTransportdienstToe(TransportdienstDTO transport) {
		transportdienstBeheerder.updateFullTransportdienst(transport);
	}

	public String getFoutMeldingTransportdienst() {
		return transportdienstBeheerder.getFoutMelding();
	}

	private String generateBarcode(int length, Boolean enkelCijfers) {
		String charsNietEnkelCijfers = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String charsEnkelCijfers = "0123456789";
		Random random = new Random();

		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			if (enkelCijfers)
				sb.append(charsEnkelCijfers.charAt(random.nextInt(charsEnkelCijfers.length())));
			else
				sb.append(charsNietEnkelCijfers.charAt(random.nextInt(charsNietEnkelCijfers.length())));
		}

		return sb.toString();
	}

	public String generateTrackAndTrace() {
		String trackAndTrace;
		if (currentBestelling.getTransportdienst() == null) {
			String prefix = geefHuidigeTransportdienst().getBarcode().getVastPrefixDeel();
			int karakters = geefHuidigeTransportdienst().getBarcode().getAantalKarakters();
			Boolean enkelCijfers = geefHuidigeTransportdienst().getBarcode().getEnkelCijfers();
			int orderId = geefHuidigeBestelling().getOrderId();
			String barcode = generateBarcode(karakters, enkelCijfers);
			trackAndTrace = prefix + "-" + barcode + "-" + orderId;
		} else if (currentTransportdienst.getNaam() != currentBestelling.getTransportdienst().getNaam()) {
			String prefix = geefHuidigeTransportdienst().getBarcode().getVastPrefixDeel();
			int karakters = geefHuidigeTransportdienst().getBarcode().getAantalKarakters();
			Boolean enkelCijfers = geefHuidigeTransportdienst().getBarcode().getEnkelCijfers();
			int orderId = geefHuidigeBestelling().getOrderId();
			String barcode = generateBarcode(karakters, enkelCijfers);
			trackAndTrace = prefix + "-" + barcode + "-" + orderId;
		} else
			trackAndTrace = currentBestelling.getTrackAndTrace();
		return trackAndTrace;
	}

	public boolean updateBestelling(BestellingDTO bestelling) {
		if (bestellingBeheerder.updateBestelling(bestelling)) {
			return true;
		}
		return false;
	}

	public boolean updateBestellingTransportdienst(int orderId, Transportdienst t) {
		if (bestellingBeheerder.updateBestellingTransportdienst(orderId, (BedrijfsTransportdienst) t,
				generateTrackAndTrace())) {
			return true;
		}
		return false;
	}

	public boolean updateTransportdienst(int id, Status status) {
		if (transportdienstBeheerder.updateTransportdienst(id, status)) {
			return true;
		}
		return false;
	}

	public List<Status> geefAlleStatussen() {
		List<Status> statussen = Stream.of(Status.values()).collect(Collectors.toList());
		return statussen;

	}

	public ObservableList<User> geefAlleUsers() {
		ObservableList<User> observableList = FXCollections.observableArrayList();
		List<BedrijfsUser> users = userBeheerder.getUsers();
		for (User user : users) {
			observableList.add(user);
		}
		return observableList;
	}

	public void setHuidigeUser(User user) {
		currenUser = (BedrijfsUser) user;

	}

	public BedrijfsUser geefHuidigeUser() {
		return currenUser;
	}

	public boolean updateUser(UserDTO user) {
		if (userBeheerder.updateUser(user)) {
			return true;
		}
		return false;
	}

	public boolean deleteUser(User user) {
		if (userBeheerder.deleteUser(user)) {
			return true;
		}
		return false;
	}

	public List<Aankoper> getCurrentAankoper() {
		return currentAankoper;
	}

	public void setCurrentAankoper(List<Aankoper> list) {
		this.currentAankoper = list;
	}

	public void setHuidigeDoos(Doos bd) {
		currentDoos = (BedrijfsDoos) bd;
	}

	public Type getTypeOfDoos() {
		return geefHuidigeBestelling().getDoos().getType();
	}

	public BedrijfsDoos geefHuidigeDoos() {
		return currentDoos;

	}

	@SuppressWarnings("unchecked")
	public List<Doos> geefAlleDozen() {
		return (List<Doos>) (Object) doosBeheerder.getDozen();
	}

	@SuppressWarnings("unchecked")
	public List<Doos> geefAlleActieveDozen() {
		return (List<Doos>) (Object) doosBeheerder.getActiefDozen();
	}

	public void voegDoosToe(DoosDTO doos) {
		doosBeheerder.voegDoosToe(doos);
	}

	public boolean updateDoos(DoosDTO doos) {
		if (doosBeheerder.updateDoos(doos)) {
			return true;
		}
		return false;
	}

	public boolean deleteDoos(Doos doos) {
		if (doosBeheerder.deleteDoos(doos)) {
			return true;
		}
		return false;
	}

	public Klant getKlantByName(String naamKlant, List<Klant> klantenLijst) {
		Klant klant = null;
		for (Klant k : klantenLijst) {
			if (k.getNaam().equals(naamKlant)) {
				klant = (Klant) k;

			}
		}
		return klant;
	}

	public Transportdienst getTransportdienstByName(String transportdienstNaam,
			List<Transportdienst> transportDiensten) {
		Transportdienst transportdienst = null;
		for (Transportdienst t : transportDiensten) {
			if (t.getNaam().equals(transportdienstNaam)) {
				transportdienst = (Transportdienst) t;
			}
		}
		return transportdienst;
	}

	public List<Type> geefAlleTypeDozen() {
		List<BedrijfsDoos> dozen = doosBeheerder.getDozen();
		List<Type> typeDozen = new ArrayList<Type>();
		for (BedrijfsDoos doos : dozen)
			typeDozen.add(doos.getType());
		return typeDozen;
	}

	public void setDoosType(Type type) {
		currentBestelling.getDoos().setType(type);
	}

	public void setHuidigeKlant(Klant klant) {
		this.currentKlant = (BedrijfsKlant) klant;

	}

	public Klant geefHuidigeKlant() {
		return this.currentKlant;
	}

	public List<Bestelling> geefAlleBestellingenVanKlant(Klant klant) {
		ObservableList<Bestelling> observableList = FXCollections.observableArrayList();
		List<BedrijfsBestelling> bestellingenVanKlant = bestellingBeheerder.getBestellingenVanKlant(klant);
		for (Bestelling bestelling : bestellingenVanKlant) {
			observableList.add(bestelling);
		}
		return observableList;
	}

	@SuppressWarnings("unchecked")
	public void geefOpenBestellingVanKlantCount() {
		List<BedrijfsKlant> klantList = (List<BedrijfsKlant>) (Object) geefAlleKlanten();
		for (BedrijfsKlant k : klantList) {
			k.setOpenBestellingCount(bestellingBeheerder.getBestellingenVanKlantCount(k));
		}
	}

	public static boolean isEmailGeldig(String email) {
		String pattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		return email.matches(pattern);
	}

	public static boolean isTelefoonnrGeldig(String telefoonnr) {
		String pattern = "^[+]?[0-9]{10,13}$";
		return telefoonnr.matches(pattern);
	}

	public String valideerContactpersoon(String email, String telefoonNr) {
		String output = "";
		if (email.isBlank()) {
			output = "Gelieve het email in te geven.";
		} else if (telefoonNr.isBlank()) {
			output = "Gelieve het telefoonnr in te geven.";
		}
		if (!isTelefoonnrGeldig(telefoonNr) && !isEmailGeldig(email)) {
			output = "Gelieve een correct telefoonnr en een correct emailadres in te geven.";
		} else if (!isTelefoonnrGeldig(telefoonNr)) {
			output = "Gelieve een correct telefoonnr in te geven.";
		} else if (!isEmailGeldig(email)) {
			output = "Gelieve een correct emailadres in te geven.";
		}
		return output;
	}

}