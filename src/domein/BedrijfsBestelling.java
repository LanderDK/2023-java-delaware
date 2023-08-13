package domein;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.persistence.*;

@Entity
@NamedQueries({ @NamedQuery(name = "BedrijfsBestelling.findAll", query = "SELECT b FROM BedrijfsBestelling b"),
		@NamedQuery(name = "BedrijfsBestelling.findByOrderId", query = "SELECT b FROM BedrijfsBestelling b WHERE b.orderId = :orderId"),
		@NamedQuery(name = "BedrijfsBestelling.findGefilterde", query = "SELECT b FROM BedrijfsBestelling b WHERE b.status = :status"),
		@NamedQuery(name = "BedrijfsBestelling.findByDoos", query = "SELECT b FROM BedrijfsBestelling b WHERE b.doos = :doos"),
		@NamedQuery(name = "BedrijfsBestelling.updateBedrijfsBestellingDoos", query = "UPDATE BedrijfsBestelling b SET b.doos = :smallid WHERE b.doos = :id") })

public class BedrijfsBestelling implements Serializable, Bestelling {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	private BedrijfsKlant klant;
	@Column(name = "\"date\"")
	private LocalDateTime date;
	private String leverAdres;
	private Status status;
	private double totalePrijs;
	private BedrijfsDoos doos;
	private List<BedrijfsProduct> productenLijst;
	private BedrijfsTransportdienst transportdienst;
	private String trackAndTrace;

	private PropertyChangeSupport subject;

	public BedrijfsBestelling(BedrijfsKlant klant, LocalDateTime date, String leverAdres, Status status, BedrijfsDoos doos,
			BedrijfsTransportdienst transportdienst, String trackAndTrace) {
		subject = new PropertyChangeSupport(this);

		setKlant(klant);
		setDate(date);
		setLeverAdres(leverAdres);
		setStatus(status);
		setDoos(doos);
		setTransportdienst(transportdienst);
		setTrackAndTrace(trackAndTrace);
		productenLijst = new ArrayList<BedrijfsProduct>();
	}

	protected BedrijfsBestelling() {

	}

	public void productToevoegen(BedrijfsProduct product) {
		productenLijst.add(product);
	}

	@Override
	public List<BedrijfsProduct> geefProductLijst() {
		return productenLijst;
	}

	@Override
	public BedrijfsTransportdienst getTransportdienst() {
		return transportdienst;
	}

	public void setTransportdienst(BedrijfsTransportdienst transportdienst) {
		this.transportdienst = transportdienst;
	}

	@Override
	public int getOrderId() {
		return orderId;
	}

	@Override
	public BedrijfsKlant getKlant() {
		return klant;
	}

	@Override
	public String getKlantnaam() {
		return klant.getNaam();
	}

	public void setKlant(BedrijfsKlant klant) {
		this.klant = klant;
	}

	@Override
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		if (date.isAfter(LocalDateTime.now())) {
			throw new IllegalArgumentException("Datum kan niet in de toekomst liggen.");
		}
		this.date = date;
	}

	@Override
	public String getLeverAdres() {
		return leverAdres;
	}

	public void setLeverAdres(String leverAdres) {
		if (leverAdres == null || leverAdres.isBlank() || !leverAdres.matches("^([a-zA-Z]+)\\s\\d+\\s\\d+\\s([a-zA-Z]+)")) {
			throw new IllegalArgumentException("Ongeldig leverAdres");
		}
		this.leverAdres = leverAdres;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public double getTotalePrijs() {
		return totalePrijs;
	}

	@Override
	public BedrijfsDoos getDoos() {
		return doos;
	}

	public void setDoos(BedrijfsDoos doos) {
		this.doos = doos;
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		subject.addPropertyChangeListener(pcl);
		pcl.propertyChange(new PropertyChangeEvent(pcl, "status", status, status));
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		subject.removePropertyChangeListener(pcl);
	}

	@Override
	public String getTrackAndTrace() {
		return trackAndTrace;
	}

	public void setTrackAndTrace(String trackAndTrace) {
		this.trackAndTrace = trackAndTrace;
	}

}