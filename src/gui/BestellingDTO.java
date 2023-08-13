package gui;

import java.time.LocalDateTime;

import domein.Doos;
import domein.Transportdienst;
import domein.Klant;
import domein.Status;

public class BestellingDTO {
	public int orderId;
	public Klant klant;
	public LocalDateTime date;
	public String leverAdres;
	public Status status;
	public Doos doos;
	public Transportdienst transportdienst;
	public String trackAndTrace;
	
	public BestellingDTO(int orderId,Klant klant, LocalDateTime date, String leverAdres, Status status, Doos doos,
			Transportdienst transportdienst) {
		this.orderId = orderId;
		this.klant = klant;
		this.date = date;
		this.leverAdres =leverAdres;
		this.status = status;
		this.doos = doos;
		this.transportdienst = transportdienst;
	}
	public BestellingDTO(int orderId,Klant klant, LocalDateTime date, String leverAdres, Status status, Doos doos,
			Transportdienst transportdienst, String trackandtrace) {
		this.orderId = orderId;
		this.klant = klant;
		this.date = date;
		this.leverAdres =leverAdres;
		this.status = status;
		this.doos = doos;
		this.transportdienst = transportdienst;
		this.trackAndTrace = trackandtrace;
	}
	
}
