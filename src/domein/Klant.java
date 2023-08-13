package domein;

import java.util.List;

import javafx.scene.image.Image;

public interface Klant {

	String getNaam();

	String getVoorNaam();

	List<Aankoper> getAankoper();

	String getGebruikersNaam();

	String getBedrijfsEmail();

	String getGsmNummer();

	String getAdres();

	Image getLogo();

	int getId();

}