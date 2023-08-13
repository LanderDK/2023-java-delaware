package testen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Aankoper;
import domein.BedrijfsDoos;
import domein.Status;
import domein.Type;

class DoosTest {

	private BedrijfsDoos doos;
	
	@BeforeEach
	void setUp() throws Exception {
		 doos = new BedrijfsDoos
				("Kartonnen Doos S" , Type.Standaard, "48,5x40x6,8cm", 0.67, Status.Actief);
	}

	@Test
	public void correcteNaam() throws Exception {
		String cNaam = "DoosTest";
		doos.setNaam(cNaam);
		Assertions.assertEquals(cNaam, doos.getNaam());
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings= { "        ", "a", "A", "3" })
	public void foutieveNaamGeeftError(String naam) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			doos.setNaam(naam);
		});
	}
	
	@Test
	public void TypeDoosNullGeefError() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> doos.setType(null));
	}
	
	@ParameterizedTest
	@ValueSource(strings= { "48,5x40x6,8cm", "40,5x36x5,8cm", "30,5x21x4,8cm" })
	public void setAfmetingenCorrecteWaardenGeenError(String afmetingen) {
		doos.setAfmetingen(afmetingen);
		Assertions.assertEquals(afmetingen, doos.getAfmetingen());
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings= { "        ", "a", "A", "gdsksfdlfg", "fsdf sdf ddf cm" })
	public void setAfmetingenFoutieveWaardenGeeftError(String afmetingen) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> doos.setAfmetingen(afmetingen));
	}
	
	@ParameterizedTest
	@ValueSource(doubles = {0.65, 20.00, 0.30, 6.11})
	public void CorrectePrijsGeenError(double price) {
		doos.setPrijs(price);
		Assertions.assertEquals(price, doos.getPrijs());
	}
	
	@ParameterizedTest
	@ValueSource(doubles = {0, 0.00, 1.33333})
	public void foutievePrijsGeeftError(double price) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> doos.setPrijs(price));
	}
	
	@Test
	public void correcteStatusGeenError() {
		doos.setStatus(Status.Actief);
		Assertions.assertEquals(Status.Actief, doos.getStatus());
	}

	@Test
	public void foutieveStatusGeeftError() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> doos.setStatus(null));
	}
	
	@Test
	public void aanmakenDoosFoutieveGegevensError() {
		Assertions.assertThrows(IllegalArgumentException.class, () ->  new BedrijfsDoos("Kartonnen Doos L" , Type.Standaard, "40,5x36x5,8cm", 0.90, null));
	}
		
}
