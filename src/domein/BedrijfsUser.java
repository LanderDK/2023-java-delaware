package domein;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import util.BCrypt;
import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "\"BedrijfsUser\"")
@NamedQueries({ @NamedQuery(name = "BedrijfsUser.findAll", query = "SELECT u FROM BedrijfsUser u"),
		@NamedQuery(name = "BedrijfsUser.findByName", query = "SELECT u FROM BedrijfsUser u where u.usernaam = :userNaam"),
		@NamedQuery(name = "BedrijfsUser.deleteById", query = "DELETE FROM BedrijfsUser u WHERE u.id = :id") })
public class BedrijfsUser implements Serializable, User {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public void setId(int id) {
		this.id = id;
	}

	private String usernaam;
	private String voorNaam;
	private String achterNaam;
	private String password;
	private String adres;
	private String gsmNummer;
	private String teleNummer;
	private String email;
	private String role;

	public BedrijfsUser(String usernaam, String voorNaam, String achterNaam, String password, String adres,
			String gsmNummer, String teleNummer, String email, String role) {
		setUsernaam(usernaam);
		setPassword(password);
		setRole(role);
		setAchterNaam(achterNaam);
		setVoorNaam(voorNaam);
		setAdres(adres);
		setEmail(email);
		setTeleNummer(teleNummer);
		setGsmNummer(gsmNummer);
	}

	protected BedrijfsUser() {
	}

	@Override
	public String getUsernaam() {
		return usernaam;
	}

	public void setUsernaam(String usernaam) {
		if (usernaam == null || usernaam.isBlank()) {
			throw new IllegalArgumentException("De opgegeven usernaam is niet geldig! Probeer opnieuw.");
		} else if (usernaam.length() < 2) {
			throw new IllegalArgumentException("De opgegeven usernaam is niet lang genoeg! Probeer opnieuw");
		} else {
			this.usernaam = usernaam;
		}
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password == null || password.isBlank()) {
			throw new IllegalArgumentException("Het opgegeven paswoord is niet geldig! Probeer opnieuw.");
		} else if (password.length() < 8) {
			throw new IllegalArgumentException(
					"Het opgegeven paswoord is niet lang genoeg! (8 karakters) Probeer opnieuw.");
		} else if (!password.matches(".*[A-Z].*")) {
			throw new IllegalArgumentException(
					"Het opgegeven wachtwoord moet een hoofdletter bevatten! Probeer opnieuw!.");
		} else {
			this.password = BCrypt.hashpw(password, BCrypt.gensalt(12));
			;
		}
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		if (role == null || role.isBlank())
			throw new IllegalArgumentException("Role kan niet leeg of null zijn!");
		if (!role.equals("Admin") && !role.equals("Magazijnier"))
			throw new IllegalArgumentException("Role kan enkel \"Admin\" of \"Magazijnier\" zijn!");
		this.role = role;
	}

	@Override
	public String getVoorNaam() {
		return voorNaam;
	}

	public void setVoorNaam(String voorNaam) {
		this.voorNaam = voorNaam;
	}

	@Override
	public String getAchterNaam() {
		return achterNaam;
	}

	public void setAchterNaam(String achterNaam) {
		this.achterNaam = achterNaam;
	}

	@Override
	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	@Override
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getGsmNummer() {
		return gsmNummer;
	}

	public void setGsmNummer(String gsmNummer) {
		this.gsmNummer = gsmNummer;
	}

	@Override
	public String getTeleNummer() {
		return teleNummer;
	}

	public void setTeleNummer(String teleNummer) {
		this.teleNummer = teleNummer;
	}
}
