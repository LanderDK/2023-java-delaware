package domein;

import java.io.Serializable;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import java.io.*;

@Entity
@Table(name = "BedrijfsKlant")
@NamedQuery(name = "BedrijfsKlant.findAll", query = "SELECT k FROM BedrijfsKlant k")
@NamedQuery(name = "BedrijfsKlant.findByName", query = "SELECT k FROM BedrijfsKlant k where k.gebruikersNaam Like :gebruikersnaam")
public class BedrijfsKlant implements Serializable, Klant {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String naam;
	private String bedrijfsEmail;
	private String gebruikersNaam;
	private String gsmNummer;
	private int openBestellingCount;
    
	private String adres;
	@Lob
    private byte[] logo;
	@OneToMany
	private List<Aankoper> aankoper;
	

	public BedrijfsKlant(String bedrijfsNaam, String bedrijfsEmail, String gebruikersNaam, String gsmNummer,
			List<Aankoper> aankoper, String adres,Image bedrijfsLogo) {
		setNaam(bedrijfsNaam);
		setBedrijfsEmail(bedrijfsEmail);
		setGebruikersNaam(gebruikersNaam);
		setGsmNummer(gsmNummer);
		setAankoper(aankoper);
	    setAdres(adres);
	    try {
	        setLogo(bedrijfsLogo);
		} catch (IOException e) {
			System.out.println(e);
		}
	}


	protected BedrijfsKlant() {

	}

	@Override
	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		if (naam == null || naam.isBlank()) {
			throw new IllegalArgumentException("De opgegeven naam is niet geldig! Probeer opnieuw.");
		} else if (naam.length() < 2) {
			throw new IllegalArgumentException("De opgegeven naam is niet lang genoeg! Probeer opnieuw");
		} else {
			this.naam = naam;
		}
	}


	@Override
	@javax.persistence.Transient
	public List<Aankoper> getAankoper() {
		return aankoper;
	}

	private void setAankoper(List<Aankoper> aankoper) {
		this.aankoper = aankoper;
	}

	public void addAankoper(Aankoper aankoper) {
		this.aankoper.add(aankoper);
	}

	@Override
	public String getGebruikersNaam() {
		return gebruikersNaam;
	}

	public void setGebruikersNaam(String gebruikersNaam) {
		this.gebruikersNaam = gebruikersNaam;
	}

	@Override
	public String getBedrijfsEmail() {
		return bedrijfsEmail;
	}

	public void setBedrijfsEmail(String bedrijfsEmail) {
		this.bedrijfsEmail = bedrijfsEmail;
	}

	@Override
	public String getGsmNummer() {
		return gsmNummer;
	}

	public void setGsmNummer(String gsmNummer) {
		this.gsmNummer = gsmNummer;
	}



    // getters and setters for all fields

    public void setLogo(Image image) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        this.logo = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
    }
    @Override
    public Image getLogo() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(logo);
        return new Image(byteArrayInputStream);
    }


	public void setAdres(String adres) {
		if (adres == null || adres.isBlank() || !adres.matches("^([a-zA-Z]+)\\s\\d+\\s\\d+\\s([a-zA-Z]+)")) { // ^[a-zA-Z]+\\s\\d+\\s\\d+\\s[a-zA-Z]+$
			throw new IllegalArgumentException("Ongeldig leverAdres");
		}
		this.adres = adres;
	}


	public String getAdres() {
		return adres;
	}

	@Override
	public int getId() {
		return id;
	}


	public int getOpenBestellingCount() {
		return openBestellingCount;
	}


	public void setOpenBestellingCount(int openBestellingCount) {
		this.openBestellingCount = openBestellingCount;
	}


	@Override
	public String getVoorNaam() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
