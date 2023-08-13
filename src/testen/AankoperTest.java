package testen;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Aankoper;
import junit.framework.Assert;
import util.BCrypt;
import domein.*;


public class AankoperTest {

	private Aankoper a1;
	LocalDateTime dateTime = LocalDateTime.parse("2016-03-04 11:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		
		@BeforeEach
		public void before() throws Exception {
		 a1 = new Aankoper("a49f400e-b0f6-4aaf-8a56-cf78fe2ad6be","TestAankoper", "testaankoper@mail.com", BCrypt.hashpw("Mclaren123", BCrypt.gensalt(10)), LocalDateTime.now(), "Aankoper", null,"8742136590", 1);
		}
		
		@ParameterizedTest
		@NullAndEmptySource
		@ValueSource(strings= { "        ", "a", "A" })
		public void foutieveNaam(String naam) {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				a1.setNaam(naam);
			});
		}
		
		@ParameterizedTest
		@NullAndEmptySource
		@ValueSource(strings= { "        ", "a", "A", "project.com" })
		public void foutieveEmail(String email) {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				a1.setEmail(email);
			});
		}
		@ParameterizedTest
		@ValueSource(strings= { "project@student.hogent.be", "project@hotmail.com", "project@outlook.com" })
		public void correctEmail(String email) {
			Assertions.assertDoesNotThrow(() -> {
				a1.setEmail(email);
			});
		}
		@Test
		public void correcteNaam() throws Exception {
			String cNaam = "AankoperTest";
			a1.setNaam(cNaam);
			Assertions.assertEquals(cNaam, a1.getNaam());
		}
		
		
		//door localdatetime werken deze testen niet. Als er een manier is om toch een localtimedate object door te geven als parameter voor een Csv source test zouden deze 2 testen wel werken!
		@CsvSource ({"141d7f71-622a-4acc-abad-8b301955472d","Jos", "jos@mail.com", "$2a$10$OkgM.wH9Stdfy69u.LhnzOTA064fmBJ6U6U5bPb.SJjXA4upbycBG", "Aankoper", "test","2468013579"})
		public void maakNieuweAankoperGeeftError(String id,String naam, String email, String passwoord, String rol, String image, String gsmNummer) {
			LocalDateTime dateTime = LocalDateTime.parse("2016-03-04 11:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
			int klantId = 2;
			Assertions.assertThrows(IllegalArgumentException.class, () -> new Aankoper(id, naam, email, passwoord, dateTime, rol, image, gsmNummer, klantId));
		}

		@CsvSource ({"141d7f71-622a-4acc-abad-8b301955472d","Jos", "jos@mail.com", "$2a$10$OkgM.wH9Stdfy69u.LhnzOTA064fmBJ6U6U5bPb.SJjXA4upbycBG", "Aankoper", "test","2468013579"})
		public void maakNieuweAankoperGeeftGeenError(String id,String naam, String email, String passwoord, String rol, String image, String gsmNummer) {
			LocalDateTime dateTime = LocalDateTime.parse("2016-03-04 11:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
			int klantId = 2;
			Assertions.assertDoesNotThrow(() -> new Aankoper(id, naam, email, passwoord, dateTime, rol, image, gsmNummer, klantId));
		}
		

	
}
