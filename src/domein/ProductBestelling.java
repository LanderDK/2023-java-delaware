package domein;

public interface ProductBestelling {

	BedrijfsProduct getProductId();

	BedrijfsBestelling getBestellingId();

	int getAantal();

	double getProductTotaal();

	String getProductnaam();

	double getPrijsProduct();

}