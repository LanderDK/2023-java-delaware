package domein;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "BedrijfsProductBestelling.findProductenOfBestelling", query = "SELECT pb FROM BedrijfsProductBestelling pb "
		+ " WHERE pb.bestelling = :bestelling")
public class BedrijfsProductBestelling implements Serializable, ProductBestelling {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private BedrijfsProduct product;
	private BedrijfsBestelling bestelling;
	private int aantal;
	private double productTotaal;

	public BedrijfsProductBestelling(BedrijfsProduct product, BedrijfsBestelling bestelling, int aantal) {
		setProductId(product);
		setBestellingId(bestelling);
		setAantal(aantal);
		berekenSubTot();

	}

	protected BedrijfsProductBestelling() {

	}

	@Override
	public BedrijfsProduct getProductId() {
		return this.product;
	}

	public void setProductId(BedrijfsProduct product) {
		this.product = product;
	}

	@Override
	public BedrijfsBestelling getBestellingId() {
		return this.bestelling;
	}

	public void setBestellingId(BedrijfsBestelling bestelling) {
		this.bestelling = bestelling;
	}

	@Override
	public int getAantal() {
		return this.aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	public void berekenSubTot() {
		double temp = product.getPrijs() * aantal;
		productTotaal = Math.round(temp * 100.0) / 100.0;
	}

	@Override
	public double getProductTotaal() {
		return productTotaal;
	}

	@Override
	public String getProductnaam() {
		return product.getNaam();
	}

	@Override
	public double getPrijsProduct() {
		return product.getPrijs();
	}
}