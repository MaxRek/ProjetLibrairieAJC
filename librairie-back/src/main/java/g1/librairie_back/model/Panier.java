package g1.librairie_back.model;

public class Panier {

	private Integer id;
		
	private int quantite;

	/*
	@OneToOne
	@JoinColumn(name="client",nullable = false)
	private Client client;
	
	@OneToOne
	@JoinColumn(name="article",nullable = false)
	private Article article;
	*/
	
	public Panier() {}

	public Panier(int quantite) {
		super();
		this.quantite = quantite;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	@Override
	public String toString() {
		return "Panier [id=" + id + ", quantite=" + quantite + "]";
	}
	
}
