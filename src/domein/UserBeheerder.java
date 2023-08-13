package domein;

import javax.persistence.*;

import dataLaag.DataClass;
import gui.UserDTO;
import util.BCrypt;

import java.util.*;
import java.util.stream.Collectors;

public class UserBeheerder {

	private String foutMelding = "Er is geen fout.";

	private List<BedrijfsUser> users;

	public UserBeheerder() {
		users = new ArrayList<>();
	}

	public List<BedrijfsUser> getUserByName(String search) {
		return users.stream().filter(u -> u.getUsernaam().toLowerCase().contains(search.toLowerCase())).toList();
//		search = search + "%";
//		return em.createNamedQuery("BedrijfsUser.findByName",BedrijfsUser.class).setParameter("usernaam", search).getResultList();
	}

	public List<BedrijfsUser> getUsers() {
		if (users.isEmpty()) {
			users = DataClass.em.createNamedQuery("BedrijfsUser.findAll", BedrijfsUser.class).getResultList();
		}
		return users;
	}

	public boolean updateUser(UserDTO userdto) {
		users.stream().filter(u -> u.getId() == userdto.id).findFirst().ifPresent(u -> {
			u.setUsernaam(userdto.usernaam);
			u.setAdres(userdto.adres);
			u.setVoorNaam(userdto.voorNaam);
			u.setAchterNaam(userdto.achterNaam);
			u.setEmail(userdto.email);
			u.setGsmNummer(userdto.gsmNummer);
			u.setTeleNummer(userdto.teleNummer);
			u.setRole(userdto.role);
		});

		try {
			// zoek de bestelling
			if (DataClass.em.getTransaction().isActive())
				DataClass.em.flush();
			DataClass.em.getTransaction().begin();
			BedrijfsUser user = DataClass.em.find(BedrijfsUser.class, userdto.id);

			// de velden updaten
			user.setUsernaam(userdto.usernaam);
			user.setRole(userdto.role);
			user.setVoorNaam(userdto.voorNaam);
			user.setAchterNaam(userdto.achterNaam);
			user.setAdres(userdto.adres);
			user.setEmail(userdto.email);
			user.setGsmNummer(userdto.gsmNummer);
			user.setTeleNummer(userdto.teleNummer);

			DataClass.em.merge(user);
			DataClass.em.getTransaction().commit();
			return true;
		} catch (Error e) {
			DataClass.em.getTransaction().rollback();
			System.out.println(e);
			return false;
		}
	}

	public boolean deleteUser(User user) {
		BedrijfsUser us = (BedrijfsUser) user;
		try {
			if (DataClass.em.getTransaction().isActive())
				DataClass.em.flush();
			DataClass.em.getTransaction().begin();
			DataClass.em.createNamedQuery("BedrijfsUser.deleteById", BedrijfsUser.class).setParameter("id", us.getId())
					.executeUpdate();
			DataClass.em.getTransaction().commit();
			users.remove(us);
			return true;
		} catch (Error e) {
			DataClass.em.getTransaction().rollback();
			System.out.println(e);
			return false;
		}
	}

	public boolean registreer(UserDTO user) {
		BedrijfsUser us = new BedrijfsUser(user.usernaam, user.voorNaam, user.achterNaam, user.password, user.adres,
				user.gsmNummer, user.teleNummer, user.email, user.role);
		if (!users.contains(us)) {
			if ((user.usernaam.isEmpty() || user.usernaam == null) || (user.password.isEmpty() || user.password == null)
					|| (user.repeatPassword.isEmpty() || user.repeatPassword == null)
					|| (user.role == null || user.role.isEmpty()) || (user.voorNaam.isEmpty() || user.voorNaam == null)
					|| (user.achterNaam.isEmpty() || user.achterNaam == null)
					|| (user.adres.isEmpty() || user.adres == null) || (user.email.isEmpty() || user.email == null)
					|| (user.gsmNummer.isEmpty() || user.gsmNummer == null)) {
				setFoutMelding("Niet alle gegevens zijn ingevuld!");
				return false;
			}
			if (!user.password.equals(user.repeatPassword)) {
				setFoutMelding("Passwoorden komen niet overeen!");
				return false;
			}
			try {
				if (DataClass.em.getTransaction().isActive())
					DataClass.em.flush();
				DataClass.em.getTransaction().begin();
				DataClass.em.persist(us);
				DataClass.em.getTransaction().commit();
				users.add(us);
			} catch (Exception e) {
				setFoutMelding(e.getMessage());
				return false;
			}
		}
		return true;
	}

	public String getFoutMelding() {
		return foutMelding;
	}

	public void setFoutMelding(String foutMelding) {
		this.foutMelding = foutMelding;
	}
}