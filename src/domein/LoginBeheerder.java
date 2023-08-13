package domein;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import dataLaag.DataClass;
import util.BCrypt;
import util.JPAUtil;

public class LoginBeheerder {
	private String foutMelding = "Er is geen fout.";
	private BedrijfsUser user;

	public boolean login(String username, String password) {
		if ((username.isEmpty() || username == null) || (password.isEmpty() || password == null)) {
			setFoutMelding("Niet alle gegevens zijn ingevuld!");
			return false;
		}
		try {
			user = DataClass.em.createNamedQuery("BedrijfsUser.findByName", BedrijfsUser.class)
					.setParameter("userNaam", username).getSingleResult();

		} catch (NoResultException ex) {
			setFoutMelding("Username bestaat niet!");
			return false;
		} finally {

		}

		if (!BCrypt.checkpw(password, user.getPassword())) {
			setFoutMelding("Password klopt niet!");
			return false;
		}
		setUser(user);
		return true;
	}

	public String getFoutMelding() {
		return foutMelding;
	}

	public void setFoutMelding(String foutMelding) {
		this.foutMelding = foutMelding;
	}

	public BedrijfsUser getUser() {
		return user;
	}

	public void setUser(BedrijfsUser user) {
		this.user = user;
	}
}