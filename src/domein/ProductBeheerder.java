package domein;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dataLaag.DataClass;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

public class ProductBeheerder {

	private List<BedrijfsProduct> producten;

	public ProductBeheerder() {
		producten = new ArrayList<>();
	}

	public void voegProductToe(BedrijfsProduct b) {
		if (!producten.contains(b)) {
			producten.add(b);
			DataClass.em.persist(b);
		}
	}

	public BedrijfsProduct getProductMetId(int productId) {
		return producten.stream().filter(p -> p.getProductId() == productId).findFirst().orElse(null);
//		return em.createNamedQuery("BedrijfsProduct.findByProductId", BedrijfsProduct.class)
//                .setParameter("productId", productId)
//                .getSingleResult();
//		return bestellingen.stream().filter(b -> b.getOrderId() == orderId).findFirst().get();
	}

}