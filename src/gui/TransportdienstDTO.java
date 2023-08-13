package gui;

import java.util.List;

import domein.Barcode;
import domein.Status;

public class TransportdienstDTO {
	
	public String naam;
	public String adres;
	public List<String> emailContactPersonen;
	public List<String> telefoonNrContactPersonen;
	public Status status;
	public Barcode barcode;
	public int id;
	
	public TransportdienstDTO(String naam, String adres, List<String> emailContactPersonen,
			List<String> telefoonNrContactPersonen, Status status, Barcode barcode) {
		this.naam = naam;
		this.adres = adres;
		this.emailContactPersonen = emailContactPersonen;
		this.telefoonNrContactPersonen = telefoonNrContactPersonen;
		this.status = status;
		this.barcode = barcode;
		
	}
	public TransportdienstDTO(int id, String naam, String adres, List<String> emailContactPersonen,
			List<String> telefoonNrContactPersonen, Status status, Barcode barcode) {
		this.naam = naam;
		this.adres = adres;
		this.emailContactPersonen = emailContactPersonen;
		this.telefoonNrContactPersonen = telefoonNrContactPersonen;
		this.status = status;
		this.barcode = barcode;
		this.id = id;
		
	}
}
