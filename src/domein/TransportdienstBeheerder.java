package domein;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dataLaag.DataClass;
import gui.TransportdienstDTO;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

public class TransportdienstBeheerder {

	private String foutMelding = "Er is geen fout.";

	private List<BedrijfsTransportdienst> transportdiensten;

	public TransportdienstBeheerder() {
		transportdiensten = new ArrayList<>();
	}

	public void voegTransportdienstToe(TransportdienstDTO t) {
		BedrijfsTransportdienst transportdienst = new BedrijfsTransportdienst(t.naam, t.adres, t.emailContactPersonen,
				t.telefoonNrContactPersonen, t.status, t.barcode);
		if (!transportdiensten.contains(transportdienst)) {
			transportdiensten.add(transportdienst);
			if (DataClass.em.getTransaction().isActive())
				DataClass.em.flush();
			DataClass.em.getTransaction().begin();
			DataClass.em.persist(transportdienst.getBarcode());
			DataClass.em.persist(transportdienst);
			DataClass.em.getTransaction().commit();
		}
	}

	public List<BedrijfsTransportdienst> getTransportdiensten() {
		if (transportdiensten.isEmpty()) {
			transportdiensten = DataClass.em
					.createNamedQuery("BedrijfsTransportdienst.findAll", BedrijfsTransportdienst.class).getResultList();
		}
		return transportdiensten;
	}

	public List<BedrijfsTransportdienst> getActiefTransportdiensten() {
		if (transportdiensten.isEmpty()) {
			getTransportdiensten();
		}
		return transportdiensten.stream().filter(t -> t.getStatus() == Status.Actief).toList();

//		return em.createNamedQuery("BedrijfsTransportdienst.findActief", BedrijfsTransportdienst.class)
//		.setParameter("status", Status.Actief).getResultList();
	}

	public Transportdienst getTransportdienstenMetId(int id) {
		return transportdiensten.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
//		return em.createNamedQuery("BedrijfsTransportdienst.findById", BedrijfsTransportdienst.class)
//				.setParameter("id", id).getSingleResult();
	}

	public String getFoutMelding() {
		return foutMelding;
	}

	public void setFoutMelding(String foutMelding) {
		this.foutMelding = foutMelding;
	}

	public boolean updateTransportdienst(int id, Status status) {
		transportdiensten.stream().filter(t -> t.getId() == id).findFirst().ifPresent(t -> {
			t.setStatus(status);
		});
		try {
			if (DataClass.em.getTransaction().isActive())
				DataClass.em.flush();
			DataClass.em.getTransaction().begin();

			BedrijfsTransportdienst transport = DataClass.em.find(BedrijfsTransportdienst.class, id);
			transport.setStatus(status);

			DataClass.em.merge(transport);
			DataClass.em.getTransaction().commit();
			return true;
		} catch (Error e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean updateFullTransportdienst(TransportdienstDTO dto) {
		transportdiensten.stream().filter(t -> t.getId() == dto.id).findFirst().ifPresent(t -> {
			t.setStatus(dto.status);
			t.setAdres(dto.adres);
			t.getBarcode().setAantalKarakters(dto.barcode.getAantalKarakters());
			t.getBarcode().setEnkelCijfers(dto.barcode.getEnkelCijfers());
			t.getBarcode().setVastPrefixDeel(dto.barcode.getVastPrefixDeel());
			t.setEmailContactPersonen(dto.emailContactPersonen);
			t.setNaam(dto.naam);
			t.setStatus(dto.status);
			t.setTelefoonNrContactPersonen(dto.telefoonNrContactPersonen);
		});
		try {
			if (DataClass.em.getTransaction().isActive())
				DataClass.em.flush();
			DataClass.em.getTransaction().begin();

			BedrijfsTransportdienst transport = DataClass.em.find(BedrijfsTransportdienst.class, dto.id);
			transport.setStatus(dto.status);
			transport.setStatus(dto.status);
			transport.setAdres(dto.adres);
			transport.getBarcode().setAantalKarakters(dto.barcode.getAantalKarakters());
			transport.getBarcode().setEnkelCijfers(dto.barcode.getEnkelCijfers());
			transport.getBarcode().setVastPrefixDeel(dto.barcode.getVastPrefixDeel());
			transport.setEmailContactPersonen(dto.emailContactPersonen);
			transport.setNaam(dto.naam);
			transport.setStatus(dto.status);
			transport.setTelefoonNrContactPersonen(dto.telefoonNrContactPersonen);

			DataClass.em.merge(transport);
			DataClass.em.getTransaction().commit();
			return true;
		} catch (Error e) {
			System.out.println(e);
			return false;
		}

	}
}