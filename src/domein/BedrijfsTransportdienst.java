package domein;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import domein.BedrijfsProduct;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.*;
import java.time.*;
import java.util.*;
import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "BedrijfsTransportdienst.findAll", query = "SELECT t FROM BedrijfsTransportdienst t"),
		@NamedQuery(name = "BedrijfsTransportdienst.findById", query = "SELECT t FROM BedrijfsTransportdienst t WHERE t.id = :id"),
		@NamedQuery(name = "BedrijfsTransportdienst.findActief", query = "SELECT t FROM BedrijfsTransportdienst t WHERE t.status = :status") })
public class BedrijfsTransportdienst implements Serializable, Transportdienst {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String naam;
	private String adres;
	@ElementCollection
	private List<String> emailContactPersonen;
	@ElementCollection
	private List<String> telefoonNrContactPersonen;
	private Status status;
	private Barcode barcode;

	public BedrijfsTransportdienst(String naam, String adres, List<String> emailContactPersonen,
			List<String> telefoonNrContactPersonen, Status status, Barcode barcode) {
		this.setNaam(naam);
		this.setAdres(adres);
		this.setEmailContactPersonen(emailContactPersonen);
		this.setTelefoonNrContactPersonen(telefoonNrContactPersonen);
		this.setStatus(status);
		this.setBarcode(barcode);
	}

	protected BedrijfsTransportdienst() {

	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		if (naam == null || naam.isEmpty())
			throw new IllegalArgumentException("De opgegeven naam is niet geldig!");
		else if (naam.isBlank() || naam.length() < 2)
			throw new IllegalArgumentException("De opgegeven naam is niet lang genoeg!");
		else
			this.naam = naam;
	}

	@Override
	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		if (adres == null || adres.isBlank() || !adres.matches("^([a-zA-Z]+)\\s\\d+"))
			throw new IllegalArgumentException("Ongeldig adres!");
		else
			this.adres = adres;
	}

	@Override
	public List<String> getEmailContactPersonen() {
		return emailContactPersonen;
	}

	public void setEmailContactPersonen(List<String> emailContactPersonen) {
		if (emailContactPersonen.isEmpty() || emailContactPersonen == null)
			throw new IllegalArgumentException("Gelieve contact personen toe te voegen!");
		this.emailContactPersonen = emailContactPersonen;
	}

	@Override
	public List<String> getTelefoonNrContactPersonen() {
		return telefoonNrContactPersonen;
	}

	public void setTelefoonNrContactPersonen(List<String> telefoonNrContactPersonen) {
		if (telefoonNrContactPersonen.isEmpty() || telefoonNrContactPersonen == null)
			throw new IllegalArgumentException("Gelieve telefoonnummers toe te voegen!");
		this.telefoonNrContactPersonen = telefoonNrContactPersonen;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public Barcode getBarcode() {
		return barcode;
	}

	public void setBarcode(Barcode barcode) {
		this.barcode = barcode;
	}

	public int getAantalKarakters() {
		return barcode.getAantalKarakters();
	}

	public Boolean getEnkelCijfers() {
		return barcode.getEnkelCijfers();
	}

	public String getVastPrefixDeel() {
		return barcode.getVastPrefixDeel();
	}
}