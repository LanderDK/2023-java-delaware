package domein;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Aankoper")
@NamedQueries({ @NamedQuery(name = "Aankoper.findAll", query = "SELECT a FROM Aankoper a") })
public class Aankoper implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String naam,email,rol,gsmNummer;
	private int klantId;
	private LocalDateTime registerDate;
	private String passwoord;
	@Lob
    private String image;


	public Aankoper(String id,String naam, String email, String passwoord, LocalDateTime registerDate, String rol, String image, String gsmNummer, int klantId) {
		setId(id);
		setNaam(naam);
		setEmail(email);
		setPasswoord(passwoord);
		setRegisterDate(registerDate);
		setRol(rol);
		setImage(image);
		setGsmNummer(gsmNummer);
		setKlantId(klantId);
	}

	private void setId(String id) {
		this.id = id;
		
	}

	protected Aankoper() {
	}

	public String getNaam() {
		return naam;
	}

	// is het volgende nodig: controleren of een cijfer in de naam zin?
	public void setNaam(String naam) {
//		try {
		if (naam == null || naam.isBlank()) {
			throw new IllegalArgumentException("De opgegeven naam is niet geldig! Probeer opnieuw.");
		} else if (naam.length() < 2) {
			throw new IllegalArgumentException("De opgegeven naam is niet lang genoeg! Probeer opnieuw");
		}

		else {
			this.naam = naam;
		}

//		} catch (Exception e) {
//			e.getMessage();
//		}
	}

	public String getEmail() {
		return email;
	}

// NOG CHECKEN OF EMAIL EEN @ BEVAT AL DAN NIET
	public void setEmail(String email) {
		if (email == null || email.isBlank()) {
			throw new IllegalArgumentException("De opgegeven email is niet geldig! Probeer opnieuw.");
		}

		else if (email.length() < 2) {
			throw new IllegalArgumentException("De opgegeven email is niet lang genoeg! Probeer opnieuw");
		}
		if (!email.contains("@")) {
			throw new IllegalArgumentException("Email adres moet een @ bevatten.");
		} else {
			this.email = email;
		}
	}
	// testen of je een aankoper kan aanmaken
	private void setRol(String rol) {
		this.rol = rol;
		
	}

	private void setImage(String image) {
		this.image = image;
		
	}

	private void setRegisterDate(LocalDateTime registerDate) {
		this.registerDate = registerDate;
		
	}

	private void setPasswoord(String passwoord) {
		this.passwoord = passwoord;
		
	}
	
	private void setKlantId(int klantId) {
		this.klantId = klantId;
		
	}
	
	public String getGSM() {
		return gsmNummer;
	}

	public String getGsmNummer() {
		return gsmNummer;
	}

	public void setGsmNummer(String gsmNummer) {
		this.gsmNummer = gsmNummer;
	}
}