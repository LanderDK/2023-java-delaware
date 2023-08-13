package gui;

import domein.Status;
import domein.Type;

public class DoosDTO {
	
	public int id;
	public String naam;
	public Type type;
	public String afmetingen;
	public double prijs;
	public Status status;
	public DoosDTO(String naam, Type type, String afmetingen, double prijs, Status status) {
		this.naam = naam;
		this.type = type;
		this.afmetingen = afmetingen;
		this.prijs = prijs;
		this.status = status;
	}
	public DoosDTO(int id, String naam2, Type type2, String afmetingen2, double prijs2, Status status2) {
		this.id = id;
		this.naam = naam2;
		this.type = type2;
		this.afmetingen = afmetingen2;
		this.prijs = prijs2;
		this.status = status2;
	}
}
