package domein;

import javax.persistence.*;

import dataLaag.DataClass;
import util.BCrypt;

import java.util.*;
import java.util.stream.Collectors;

public class KlantBeheerder {

	private List<BedrijfsKlant> klanten;

	public KlantBeheerder() {
		klanten = new ArrayList<>();
	}

	public List<BedrijfsKlant> getKlant() {
		if (klanten.isEmpty()) {
			klanten = DataClass.em.createNamedQuery("BedrijfsKlant.findAll", BedrijfsKlant.class).getResultList();
		}
		return klanten;
	}

	public List<BedrijfsKlant> getKlantByName(String search) {
		return klanten.stream().filter(k -> k.getGebruikersNaam().toLowerCase().contains(search.toLowerCase()))
				.toList();
//		search = search + "%";
//		return em.createNamedQuery("BedrijfsKlant.findByName",BedrijfsKlant.class).setParameter("gebruikersnaam", search).getResultList();
	}

}