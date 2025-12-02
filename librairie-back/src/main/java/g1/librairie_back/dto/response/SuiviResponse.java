package g1.librairie_back.dto.response;

import g1.librairie_back.model.Article;
import g1.librairie_back.model.Client;
import g1.librairie_back.model.Suivi;

public class SuiviResponse {
	private Integer id;
	private Client client;
	private Article article;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public static SuiviResponse convert(Suivi suivi) {
		SuiviResponse resp = new SuiviResponse();
        
        resp.setId(suivi.getId());
        resp.setArticle(suivi.getArticle());
        resp.setClient(suivi.getClient());
        
        return resp;
    }
}
