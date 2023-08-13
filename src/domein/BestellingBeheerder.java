package domein;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dataLaag.DataClass;
import gui.BestellingDTO;

import java.time.LocalDateTime;
import java.util.*;

public class BestellingBeheerder {

	private List<BedrijfsBestelling> bestellingen;
	private List<Integer> aantalOpenBestellingen;

	public BestellingBeheerder() {
		bestellingen = new ArrayList<>();
	}

	public void voegBestellingToe(BedrijfsBestelling b) {
		if (!bestellingen.contains(b)) {
			bestellingen.add(b);
			DataClass.em.persist(b);
		}
	}

	public List<BedrijfsBestelling> getBestellingen() {
		if (bestellingen.isEmpty()) {
			bestellingen = DataClass.em.createNamedQuery("BedrijfsBestelling.findAll", BedrijfsBestelling.class)
					.getResultList();
		}
		return bestellingen;
	}

	public List<BedrijfsBestelling> getGefilterdeBestellingen() {
		return bestellingen.stream().filter(b -> b.getStatus() == Status.Geplaatst).toList();
//		return em.createNamedQuery("BedrijfsBestelling.findGefilterde", BedrijfsBestelling.class)
//				.setParameter("status", Status.Geplaatst).getResultList();
	}

	public BedrijfsBestelling getBestellingenMetId(int orderId) {
		return bestellingen.stream().filter(b -> b.getOrderId() == orderId).findFirst().orElse(null);
//		return em.createNamedQuery("BedrijfsBestelling.findByOrderId", BedrijfsBestelling.class)
//				.setParameter("orderId", orderId).getSingleResult();
//		return bestellingen.stream().filter(b -> b.getOrderId() == orderId).findFirst().get();
	}

	public List<BedrijfsBestelling> getBestellingenVanKlant(Klant klant) {
		return bestellingen.stream().filter(b -> b.getKlant().getId() == klant.getId()).toList();
	}

	public int getBestellingenVanKlantCount(Klant klant) {
		List<BedrijfsBestelling> bestelligList = getBestellingenVanKlant(klant);
		return (int) bestelligList.stream().filter(b -> b.getStatus() == Status.Geplaatst).count();
	}

	public boolean updateBestelling(BestellingDTO bestelling) {
		bestellingen.stream().filter(b -> b.getOrderId() == bestelling.orderId).findFirst().ifPresent(b -> {
			b.setDoos((BedrijfsDoos) bestelling.doos);
			b.setDate(bestelling.date);
			b.setKlant((BedrijfsKlant) bestelling.klant);
			b.setLeverAdres(bestelling.leverAdres);
			b.setStatus(bestelling.status);
			b.setTransportdienst((BedrijfsTransportdienst) bestelling.transportdienst);
		});

		try {
			if (DataClass.em.getTransaction().isActive())
				DataClass.em.flush();
			DataClass.em.getTransaction().begin();
			BedrijfsBestelling bestellingdb = DataClass.em.find(BedrijfsBestelling.class, bestelling.orderId);

			bestellingdb.setKlant((BedrijfsKlant) bestelling.klant);
			bestellingdb.setLeverAdres(bestelling.leverAdres);
			bestellingdb.setDate(bestelling.date);
			bestellingdb.setStatus(bestelling.status);
			bestellingdb.setDoos((BedrijfsDoos) bestelling.doos);
			bestellingdb.setTransportdienst((BedrijfsTransportdienst) bestelling.transportdienst);

			DataClass.em.merge(bestellingdb);
			DataClass.em.getTransaction().commit();
			return true;
		} catch (Error e) {
			DataClass.em.getTransaction().rollback();
			System.out.println(e);
			return false;
		}

	}

	public boolean updateBestellingTransportdienst(int orderId, BedrijfsTransportdienst t, String trackAndTrace) {
		bestellingen.stream().filter(b -> b.getOrderId() == orderId).findFirst().ifPresent(b -> {
			b.setStatus(Status.Verwerkt);
			b.setTrackAndTrace(trackAndTrace);
			b.setTransportdienst(t);
		});
		try {
			if (DataClass.em.getTransaction().isActive())
				DataClass.em.flush();
			DataClass.em.getTransaction().begin();
			BedrijfsBestelling bestelling = DataClass.em.find(BedrijfsBestelling.class, orderId);

			bestelling.setTransportdienst(t);
			bestelling.setStatus(Status.Verwerkt);
			bestelling.setTrackAndTrace(trackAndTrace);

			DataClass.em.merge(bestelling);
			DataClass.em.getTransaction().commit();
			return true;
		} catch (Error e) {
			DataClass.em.getTransaction().rollback();
			System.out.println(e);
			return false;
		}

	}
}
