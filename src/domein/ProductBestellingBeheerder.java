package domein;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dataLaag.DataClass;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

public class ProductBestellingBeheerder {

	private List<BedrijfsProductBestelling> bpb;

	public ProductBestellingBeheerder() {
		bpb = new ArrayList<>();
	}

	public void voegProductToe(BedrijfsProductBestelling b) {

		if (!bpb.contains(b)) {
			bpb.add(b);
			DataClass.em.persist(b);
		}
	}

	public List<BedrijfsProductBestelling> getProductOfBestelling(Bestelling bestelling) {
		List<BedrijfsProductBestelling> bpbnieuw = new ArrayList<>();
		if (bpb.isEmpty()) {
			bpbnieuw = DataClass.em.createNamedQuery("BedrijfsProductBestelling.findProductenOfBestelling",
					BedrijfsProductBestelling.class).setParameter("bestelling", bestelling).getResultList();
			bpb = bpbnieuw;
		} else {
			bpbnieuw = bpb.stream().filter(bp -> bp.getBestellingId().getOrderId() == bestelling.getOrderId()).toList();
		}

		return bpbnieuw;
	}
}