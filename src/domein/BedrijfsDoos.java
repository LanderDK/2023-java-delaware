package domein;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.*;

@Entity
@NamedQueries({ @NamedQuery(name = "BedrijfsDoos.findAll", query = "SELECT d FROM BedrijfsDoos d"),
		@NamedQuery(name = "BedrijfsDoos.findById", query = "SELECT d FROM BedrijfsDoos d WHERE d.id = :id"),
		@NamedQuery(name = "BedrijfsDoos.findActief", query = "SELECT d FROM BedrijfsDoos d WHERE d.status = :status"),
		@NamedQuery(name = "BedrijfsDoos.findOtherId", query = "SELECT d FROM BedrijfsDoos d WHERE d.id != :id ORDER BY d.id ASC"),
		@NamedQuery(name = "BedrijfsDoos.deleteById", query = "DELETE FROM BedrijfsDoos d WHERE d.id = :id") })
public class BedrijfsDoos implements Serializable, Doos {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String naam;
	private Type type;
	private String afmetingen;
	private double prijsDoos;
	private Status status;

	public BedrijfsDoos(String naam, Type type, String afmetingen, double prijs, Status status) {
		setNaam(naam);
		setType(type);
		setAfmetingen(afmetingen);
		setPrijs(prijs);
		setStatus(status);
	}

	protected BedrijfsDoos() {

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
		if (naam == null || naam.isBlank())
			throw new IllegalArgumentException("De opgegeven naam is niet geldig!");
		else if (naam.length() < 2)
			throw new IllegalArgumentException("De opgegeven naam is niet lang genoeg!");
		else
			this.naam = naam;
	}

	@Override
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		if (type == null)
			throw new IllegalArgumentException("Ongeldig type!");
		this.type = type;
	}

	@Override
	public String getAfmetingen() {
		return afmetingen;
	}

	public void setAfmetingen(String afmetingen) {
		if (afmetingen == null || !afmetingen.matches("[0-9].+x[0-9].+x[0-9].+cm"))
			throw new IllegalArgumentException("Ongeldige afmetingen!");
		this.afmetingen = afmetingen;
	}

	@Override
	public double getPrijs() {
		return prijsDoos;
	}

	public void setPrijs(double prijs) {
		if (prijs <= 0 || !String.valueOf(prijs).matches("[0-9]+.[0-9][0-9]?"))
			throw new IllegalArgumentException("Ongeldige prijs!");
		this.prijsDoos = prijs;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		if (status == null)
			throw new IllegalArgumentException("Ongeldige status!");
		this.status = status;
	}

}