package domein;

import java.io.Serializable;

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
public class Barcode implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int aantalKarakters;
	private Boolean enkelCijfers;
	private String vastPrefixDeel;

	public Barcode(int aantalKarakters, Boolean enkelCijfers, String vastPrefixDeel) {
		setAantalKarakters(aantalKarakters);
		setEnkelCijfers(enkelCijfers);
		setVastPrefixDeel(vastPrefixDeel);
	}

	protected Barcode() {
	}

	public void setAantalKarakters(int aantalKarakters) {
		if (aantalKarakters == 0)
			throw new IllegalArgumentException("Aantal karakters moet groter dan 0 zijn!");
		this.aantalKarakters = aantalKarakters;
	}

	public void setEnkelCijfers(Boolean enkelCijfers) {
		if (enkelCijfers == null)
			throw new IllegalArgumentException("Enkel cijfers moet ingevuld zijn!");
		this.enkelCijfers = enkelCijfers;
	}

	public void setVastPrefixDeel(String vastPrefixDeel) {
		if (vastPrefixDeel.isEmpty() || vastPrefixDeel == null)
			throw new IllegalArgumentException("Vast prefix deel moet ingevuld zijn!");
		else if (vastPrefixDeel.length() <= 1)
			throw new IllegalArgumentException("De opgegeven vaste prefix is niet lang genoeg!");
		else
			this.vastPrefixDeel = vastPrefixDeel;
	}

	public int getAantalKarakters() {
		return aantalKarakters;
	}

	public Boolean getEnkelCijfers() {
		return enkelCijfers;
	}

	public String getVastPrefixDeel() {
		return vastPrefixDeel;
	}
}
