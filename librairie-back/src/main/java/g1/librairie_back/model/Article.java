package g1.librairie_back.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Article")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_article",columnDefinition = "ENUM('Ouvrage','Papeterie')")
public abstract class Article {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;
	
	@Column(name="libelle", columnDefinition="VARCHAR(50)", nullable=false)
	protected String libelle;
	
	@Column(name="prix", columnDefinition="DECIMAL(5,2)", nullable=false)
	protected double prix;
	
	@Column(name="stock")
	protected int stock;

	@OneToMany(mappedBy = "article")
	protected List<Suivi> suivi;

	@OneToMany(mappedBy = "article")
	protected List<Review> review = new ArrayList();

	@OneToMany(mappedBy = "article")
	protected List<Achat> achat = new ArrayList();

	
	public Article() {}
}
