package gui;

public class UserDTO {
	public int id;
	public String usernaam;
	public String password;
	public String repeatPassword;
	public String role;
	public String voorNaam;
	public String achterNaam;
	public String adres;
	public String email;
	public String gsmNummer;
	public String teleNummer;
	public UserDTO(String usernaam, String password,String repeatPassword, String role,String achterNaam,String voorNaam,String adres,String email,String gsmNummer,String teleNummer) {
		this.usernaam = usernaam;
		this.password = password;
		this.repeatPassword = repeatPassword;
		this.voorNaam = voorNaam;
		this.achterNaam = achterNaam;
		this.adres = adres;
		this.email = email;
		this.gsmNummer = gsmNummer;
		this.teleNummer = teleNummer;
		this.role = role;
	}
	public UserDTO(int id2, String usernaam, String role, String voornaam, String achternaam, String adres, String email,
			String gsm, String tele) {
		this.id = id2;
		this.usernaam = usernaam;
		this.voorNaam = voornaam;
		this.achterNaam = achternaam;
		this.adres = adres;
		this.email = email;
		this.role = role;
		this.gsmNummer = gsm;
		this.teleNummer = tele;
	}
}
