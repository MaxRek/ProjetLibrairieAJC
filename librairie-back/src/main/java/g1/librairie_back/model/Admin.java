package g1.librairie_back.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends Compte{

	public Admin() {}

	public Admin(String nom, String prenom, String email, String password) {
		super(nom, prenom, email, password);
	}

	
}
