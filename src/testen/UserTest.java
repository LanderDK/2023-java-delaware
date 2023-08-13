package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import util.BCrypt;
import domein.*;

public class UserTest {
	
	private BedrijfsUser u1;
	
	@BeforeEach
	public void before() throws Exception {
		u1 = new BedrijfsUser("Magazijnier1", "Casper", "De Bock", "Mclaren123", "Straat 0", "0446555555",
				"0971448997", "casperdebock@gmail.com", "Magazijnier");
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings= { "        ", "a", "A" })
	public void foutieveNaam(String naam) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			u1.setUsernaam(naam);
		});
	}
	
	@Test
	public void correcteNaam(){
		String uNaam = "Lewis Hamilton";
		u1.setUsernaam(uNaam);
		Assertions.assertEquals(uNaam, u1.getUsernaam());
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings= { "        ", "a", "A" })
	public void leegEnEnkelPwGooitException(String pw) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			u1.setPassword(pw);
		});
	}
	
	@Test
	public void correctPw(){
		String pw = "langGenoeg";
		Assertions.assertDoesNotThrow(() -> {
			u1.setPassword(pw);
		});
	}
	
	@Test
	public void PwNietLangGenoegGooitError() {
		String pw = "teKort";
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			u1.setPassword(pw);
		});
	}
	
	@ParameterizedTest
	@ValueSource(strings= { "Admin", "Magazijnier" })
	public void correcteRolWordtCorrectIngesteld(String role) {
		u1.setRole(role);
		Assertions.assertEquals(role, u1.getRole());
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings= { "        ", "a", "A", "GeenGeldigeRol", "Adminn" })
	public void nullEnEmptyRoleGeeftError(String role) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			u1.setRole(role);
		});
	}
	

	
	
	
	
	

	
	
	
	

}
