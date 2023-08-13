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


class BestellingTest {
	
	private BedrijfsBestelling bestelling;
	private Aankoper a1;
	private BedrijfsKlant k1;
	private Status s1;

	@BeforeEach
	
	
	public void before() {
		List<Aankoper> aankoperLijst = new ArrayList<Aankoper>();
		List<String> emailContactPersonen = new ArrayList<>();
		List<String> telefoonNrContactPersonen = new ArrayList<>();
		Barcode br1 = new Barcode(4, true, "VOL");
		BedrijfsDoos d2 = new BedrijfsDoos("Kartonnen Doos L" , Type.Standaard, "40,5x36x5,8cm", 0.90, Status.Inactief);
		Aankoper a1 = new Aankoper("a49f400e-b0f6-4aaf-8a56-cf78fe2ad6be","Pol", "pol@mail.com", BCrypt.hashpw("Mclaren123", BCrypt.gensalt(10)), LocalDateTime.now(), "Aankoper", null,"8742136590", 1);
		Aankoper a2 = new Aankoper("141d7f71-622a-4acc-abad-8b301955472d","Jos", "jos@mail.com", BCrypt.hashpw("Mclaren123", BCrypt.gensalt(10)), LocalDateTime.now(), "Aankoper", null,"2468013579", 2);
		Aankoper a3 = new Aankoper("906c6853-0353-45b5-84bf-4261a2a19e20","kevin", "kevin@mail.com", BCrypt.hashpw("Mclaren123", BCrypt.gensalt(10)), LocalDateTime.now(), "Aankoper", null,"5907641823", 1);
		Aankoper a4 = new Aankoper("46908591-3160-4e60-bcd9-a2623fdf9a29","piet", "piet@mail.com", BCrypt.hashpw("Mclaren123", BCrypt.gensalt(10)), LocalDateTime.now(), "Aankoper", null, "9876543210", 2);
		aankoperLijst.add(a1);
		aankoperLijst.add(a2);
		aankoperLijst.add(a3);
		aankoperLijst.add(a4);
		emailContactPersonen.add("contact1@mail.com");
		emailContactPersonen.add("contact2@mail.com");
		emailContactPersonen.add("contact3@mail.com");
		telefoonNrContactPersonen.add("0475124798");
		telefoonNrContactPersonen.add("0456324798");
		telefoonNrContactPersonen.add("0497458738");
		BedrijfsTransportdienst td1 = new BedrijfsTransportdienst("VOLVO Camion", "Stationstraat 1", emailContactPersonen,
				telefoonNrContactPersonen, Status.Actief, br1);
		InputStream streamBlis = getClass().getResourceAsStream("../resources/blissfulbites.png");
		Image blisLogo = new Image(streamBlis);
		k1 = new BedrijfsKlant("Blissful Bites", "BlissfulBites@gmail.com", "BlissfulBites",
				"0487679271", aankoperLijst, "Stratem 5 9000 Gent", blisLogo);
		bestelling = new BedrijfsBestelling(k1, LocalDateTime.now(), "Stationstraat 10 9880 Aalter", Status.Verwerkt, d2,
				td1, "VOL-1234-4");
		
	}
	@Test
	public void fouteDatum() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			bestelling.setDate(LocalDateTime.now().plusDays(2));
		});
	}
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings= { "      ", "   hkjhkjh k   ", "wondelgemsesteenweg" , "1564", "16165 hgfhgdjgf"})
	public void foutiefLeveradres(String adres) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			bestelling.setLeverAdres(adres);
	});
}
	
	@ParameterizedTest
	@ValueSource(strings= { "Stationstraat 13 9880 Aalter", "Stations 13 9880 Aalter", "Stationstraat 13 9880 Beernem", "Stationstraat 133 9880 Aalter", "Stationstraat 13 2000 Aalter"})
	public void correctLeveradres(String adres) {
		Assertions.assertDoesNotThrow(() -> {
			bestelling.setLeverAdres(adres);
	});
}
	
	@Test
	public void getStatusReturntEnum() {
		Assertions.assertTrue(bestelling.getStatus() instanceof Status);
	}
	
	@Test
	public void getDoosReturntDoosObject() {
		Assertions.assertTrue(bestelling.getDoos() instanceof BedrijfsDoos);
	}
	
	@Test
	public void setStatusVeranderdStatus() {
		s1 = Status.Geplaatst;
		bestelling.setStatus(s1);
		Assertions.assertEquals(s1, bestelling.getStatus());
	}
	

	

}
