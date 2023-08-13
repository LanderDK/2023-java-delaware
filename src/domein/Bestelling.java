package domein;

import java.util.List;

public interface Bestelling {

	List<BedrijfsProduct> geefProductLijst();

	int getOrderId();

	Klant getKlant();

	String getKlantnaam();

	java.time.LocalDateTime getDate();

	String getLeverAdres();

	Status getStatus();

	double getTotalePrijs();

	BedrijfsDoos getDoos();

	BedrijfsTransportdienst getTransportdienst();

	String getTrackAndTrace();
}