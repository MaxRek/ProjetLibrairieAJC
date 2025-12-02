package g1.librairie_back.dto.request;

import g1.librairie_back.model.Article;
import g1.librairie_back.model.Client;

public class CreateSuiviRequest {
	
    private Integer id;
	
	private Article article;
	
	private Client client;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
