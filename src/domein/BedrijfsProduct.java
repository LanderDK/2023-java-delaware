package domein;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.*;

@Entity
@NamedQuery(name = "BedrijfsProduct.findByProductId", query = "SELECT p FROM BedrijfsProduct p WHERE p.productId = :productId")
public class BedrijfsProduct implements Serializable, Product {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	private String name;
	private int categorieId;
	private String levertijd;
	private String description;
	private double prijs;
	private int stock;
	private String img;

	public BedrijfsProduct(String naam, String image, String description, double prijs, int stock, String levertijd,
			int categorieId) {
		setNaam(naam);
		setPrijs(prijs);
		setImage(image);
		setDescription(description);
		setStock(stock);
		setLevertijd(levertijd);
		setCategorieId(categorieId);
	}

	private void setCategorieId(int categorieId) {
		this.categorieId = categorieId;
	}

	private void setLevertijd(String levertijd) {
		this.levertijd = levertijd;
	}

	private void setStock(int stock) {
		this.stock = stock;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	private void setImage(String image) {
		this.img = image;
	}

	protected BedrijfsProduct() {

	}

	public void setNaam(String naam) {
		this.name = naam;
	}

	@Override
	public String getNaam() {
		return name;
	}

	@Override
	public double getPrijs() {
		return prijs;
	}

	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}

	@Override
	public int getAantalInVoorraad() {
		return stock;
	}

	public void setAantalInVoorraad(int aantalInVoorraad) {
		this.stock = aantalInVoorraad;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

}