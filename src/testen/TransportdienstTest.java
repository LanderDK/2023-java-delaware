package testen;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Barcode;
import domein.BedrijfsTransportdienst;
import domein.Status;

class TransportdienstTest {
	
	private BedrijfsTransportdienst td1;
	private BedrijfsTransportdienst td2;
	private List<String> emailContactPersonen = new ArrayList<>();
	private List<String> telefoonNrContactPersonen = new ArrayList<>();
	private Barcode br1;

	@BeforeEach
	void setUp() throws Exception {
		emailContactPersonen.add("contact1@mail.com");
		emailContactPersonen.add("contact2@mail.com");
		emailContactPersonen.add("contact3@mail.com");
		telefoonNrContactPersonen.add("0475124798");
		telefoonNrContactPersonen.add("0456324798");
		telefoonNrContactPersonen.add("0497458738");
		br1 = new Barcode(4, true, "VOL");
		td1 = new BedrijfsTransportdienst("VOLVO Camion", "Stationstraat 1", emailContactPersonen,
				telefoonNrContactPersonen, Status.Actief, br1);
	}

	@ParameterizedTest
	@ValueSource(strings= { "VOLVO Camion", "Renault Camion", "Ferrari Camion", "Red Bull Stuntwagen", "Alpine F1" })
	public void correcteNaamGeenError(String naam) {
		td1.setNaam(naam);
		Assertions.assertEquals(naam, td1.getNaam());
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings= { "        ", "a", "A" })
	public void foutieveNaamGeeftError(String naam) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			td1.setNaam(naam);
		});
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings= { "      ", "   hkjhkjh k   ", "wondelgemsesteenweg" , "1564", "16165 hgfhgdjgf"})
	public void foutiefLeveradres(String adres) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			td1.setAdres(adres);
		});
	}
	
	@ParameterizedTest
	@ValueSource(strings= { "Stratem 5" , "Stationstraat 56"})
	public void correctAdres(String adres) {
		td1.setAdres(adres);
		Assertions.assertEquals(adres, td1.getAdres());
	}
	
	@Test
	public void emailContactPersonenIsLeegGeeftError() {
		emailContactPersonen.clear();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			td2 = new BedrijfsTransportdienst("VOLVO Camion", "Stationstraat 1", emailContactPersonen,
					telefoonNrContactPersonen, Status.Actief, br1);;
		});
	}
	
	@Test
	public void telefoonNrContactPersonenIsLeegGeeftError() {
		telefoonNrContactPersonen.clear();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			td2 = new BedrijfsTransportdienst("VOLVO Camion", "Stationstraat 1", emailContactPersonen,
					telefoonNrContactPersonen, Status.Actief, br1);;
		});
	}

}
