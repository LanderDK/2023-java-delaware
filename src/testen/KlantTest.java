package testen;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import javafx.scene.image.Image;
import util.BCrypt;
import domein.*;

public class KlantTest {
	
	private BedrijfsKlant k1;
	private Aankoper a1;
	
	@BeforeEach
	public void before() throws Exception {
		List<Aankoper> aankoperLijst = new ArrayList<Aankoper>();
		Aankoper a1 = new Aankoper("a49f400e-b0f6-4aaf-8a56-cf78fe2ad6be","Pol", "pol@mail.com", BCrypt.hashpw("Mclaren123", BCrypt.gensalt(10)), LocalDateTime.now(), "Aankoper", null,"8742136590", 1);
		Aankoper a2 = new Aankoper("141d7f71-622a-4acc-abad-8b301955472d","Jos", "jos@mail.com", BCrypt.hashpw("Mclaren123", BCrypt.gensalt(10)), LocalDateTime.now(), "Aankoper", null,"2468013579", 2);
		Aankoper a3 = new Aankoper("906c6853-0353-45b5-84bf-4261a2a19e20","kevin", "kevin@mail.com", BCrypt.hashpw("Mclaren123", BCrypt.gensalt(10)), LocalDateTime.now(), "Aankoper", null,"5907641823", 1);
		Aankoper a4 = new Aankoper("46908591-3160-4e60-bcd9-a2623fdf9a29","piet", "piet@mail.com", BCrypt.hashpw("Mclaren123", BCrypt.gensalt(10)), LocalDateTime.now(), "Aankoper", null, "9876543210", 2);
		aankoperLijst.add(a1);
		aankoperLijst.add(a2);
		aankoperLijst.add(a3);
		aankoperLijst.add(a4);
		InputStream streamBlis = getClass().getResourceAsStream("../resources/blissfulbites.png");
		Image blisLogo = new Image(streamBlis);
		k1 = new BedrijfsKlant("Blissful Bites", "BlissfulBites@gmail.com", "BlissfulBites",
				"0487679271", aankoperLijst, "Stratem 5 9000 Gent", blisLogo);
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings= { "        ", "a", "A" })
	public void foutieveNaam(String naam) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			k1.setNaam(naam);
		});
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings= { "        ", "a", "A" })
	public void foutievevoorNaam(String voorNaam) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			k1.setNaam(voorNaam);
		});
	}
	
	@Test
	public void correcteNaam(){
		String cNaam = "KlantTest";
		k1.setNaam(cNaam);
		Assertions.assertEquals(cNaam, k1.getNaam());
	}
	
	@Test
	public void correcteVoornaam() {
		String cNaam = "AankoperTest";
		k1.setNaam(cNaam);
		Assertions.assertEquals(cNaam, k1.getNaam());
	}
	
	@Test
	public void getAdresReturntAdres() {
		Assertions.assertEquals("Stratem 5 9000 Gent", k1.getAdres());
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings= { "      ", "   hkjhkjh k   ", "wondelgemsesteenweg" , "1564", "16165 hgfhgdjgf"})
	public void foutiefLeveradres(String adres) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			k1.setAdres(adres);
		});
	}
	
	@ParameterizedTest
	@ValueSource(strings= { "Stationstraat 13 9880 Aalter", "Stations 13 9880 Aalter", "Stationstraat 13 9880 Beernem", "Stationstraat 133 9880 Aalter", "Stationstraat 13 2000 Aalter"})
	public void correctAdres(String adres) {
		k1.setAdres(adres);
		Assertions.assertEquals(adres, k1.getAdres());
	}
	
}
