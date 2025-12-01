package g1.librairie_back.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Papeterie")
public class Papeterie extends Article {

	private String type;
	private int page;
	private String marque;

	public Papeterie() {}
}
