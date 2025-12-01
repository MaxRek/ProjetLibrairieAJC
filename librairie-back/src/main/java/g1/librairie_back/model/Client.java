package g1.librairie_back.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("Client")
public class Client extends Compte{
	
	@OneToOne(mappedBy="client")
	protected Panier panier;
	
	@OneToMany(mappedBy = "client")
	protected List<Suivi> suivi = new ArrayList();
	
	@OneToMany(mappedBy = "client")
	protected List<Review> review = new ArrayList();
	
	@OneToMany(mappedBy = "client")
	protected List<Achat> achat = new ArrayList();


	
	
	public Client() {}

	public Client(String nom, String prenom, String email, String password) {
		super(nom, prenom, email, password);
	}

	
}
