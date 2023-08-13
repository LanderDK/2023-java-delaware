package domein;

import java.util.List;

public interface Transportdienst {

	int getId();

	String getNaam();

	String getAdres();

	List<String> getEmailContactPersonen();

	List<String> getTelefoonNrContactPersonen();

	Status getStatus();

	Barcode getBarcode();

}