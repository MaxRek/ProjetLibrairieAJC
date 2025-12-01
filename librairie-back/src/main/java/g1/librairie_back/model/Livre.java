package g1.librairie_back.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Ouvrage")
public class Livre extends Article {

	@Embedded
	private Auteur auteur;
	private Genre genre;
	private int annee;
	
	public Livre() {}
	
}
