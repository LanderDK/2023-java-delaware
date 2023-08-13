package domein;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dataLaag.DataClass;
import gui.DoosDTO;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

public class DoosBeheerder {
	private String foutMelding = "Er is geen fout.";

	private List<BedrijfsDoos> dozen;

	public DoosBeheerder() {
		dozen = new ArrayList<>();
	}

	public void voegDoosToe(DoosDTO doos) {

		BedrijfsDoos d = new BedrijfsDoos(doos.naam, doos.type, doos.afmetingen, doos.prijs, doos.status);
		if (!dozen.contains(d)) {
			if (DataClass.em.getTransaction().isActive())
				DataClass.em.flush();
			DataClass.em.getTransaction().begin();
			DataClass.em.persist(d);
			DataClass.em.getTransaction().commit();
			dozen.add(d);
		}

	}

	public List<BedrijfsDoos> getDozen() {
		if (dozen.isEmpty()) {
			dozen = DataClass.em.createNamedQuery("BedrijfsDoos.findAll", BedrijfsDoos.class).getResultList();
		}
		return dozen;
	}

	public List<BedrijfsDoos> getActiefDozen() {
		return dozen.stream().filter(d -> d.getStatus() == Status.Actief).toList();
//		return em.createNamedQuery("BedrijfsDoos.findActief", BedrijfsDoos.class)
//				.setParameter("status", Status.Actief).getResultList();
	}

	public BedrijfsDoos getDozenMetId(int id) {
		return dozen.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
//		return em.createNamedQuery("BedrijfsDoos.findById", BedrijfsDoos.class)
//				.setParameter("id", id).getSingleResult();
	}

	public boolean updateDoos(DoosDTO doos) {
		System.out.print(doos.id + doos.naam);

		dozen.stream().filter(t -> t.getId() == doos.id).findFirst().ifPresent(t -> {
			System.out.println(t);
			t.setNaam(doos.naam);
			t.setType(doos.type);
			t.setAfmetingen(doos.afmetingen);
			t.setPrijs(doos.prijs);
			t.setStatus(doos.status);
		});
		try {
			if (DataClass.em.getTransaction().isActive())
				DataClass.em.flush();
			DataClass.em.getTransaction().begin();

			BedrijfsDoos bDoos = DataClass.em.find(BedrijfsDoos.class, doos.id);
			bDoos.setNaam(doos.naam);
			bDoos.setType(doos.type);
			bDoos.setAfmetingen(doos.afmetingen);
			bDoos.setPrijs(doos.prijs);
			bDoos.setStatus(doos.status);

			System.out.println(bDoos);

			DataClass.em.merge(bDoos);
			DataClass.em.getTransaction().commit();
			return true;
		} catch (Error e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean deleteDoos(Doos doos) {
		BedrijfsDoos bd = (BedrijfsDoos) doos;
		List<BedrijfsBestelling> bestellingen = new ArrayList<>();
		try {

			List<BedrijfsDoos> smallid;

			if (DataClass.em.getTransaction().isActive())
				DataClass.em.flush();
			DataClass.em.getTransaction().begin();

			smallid = DataClass.em.createNamedQuery("BedrijfsDoos.findOtherId", BedrijfsDoos.class)
					.setParameter("id", bd.getId()).getResultList();

			bestellingen = DataClass.em.createNamedQuery("BedrijfsBestelling.findByDoos", BedrijfsBestelling.class)
					.setParameter("doos", bd).getResultList();

			System.out.println(smallid);
			System.out.println(bestellingen);

			for (BedrijfsBestelling bestelling : bestellingen) {
				bestelling.setDoos(smallid.get(0));
				DataClass.em.merge(bestelling);
			}

			DataClass.em.createNamedQuery("BedrijfsDoos.deleteById", BedrijfsDoos.class).setParameter("id", bd.getId())
					.executeUpdate();

			DataClass.em.getTransaction().commit();
			dozen.remove(bd);
			return true;
		} catch (Error e) {
			DataClass.em.getTransaction().rollback();
			System.out.println(e);
			return false;
		}
	}

	public String getFoutMelding() {
		return foutMelding;
	}

	public void setFoutMelding(String foutMelding) {
		this.foutMelding = foutMelding;
	}
}