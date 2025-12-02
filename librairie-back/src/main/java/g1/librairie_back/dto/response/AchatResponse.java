package g1.librairie_back.dto.response;

import java.time.LocalDate;

import g1.librairie_back.model.Achat;

public class AchatResponse {

    private int id;
    private LocalDate dateAchat;
    private double prix;
    private int quantiteAchat;
    private int articleId;
    private int clientId;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDate dateAchat) {
        this.dateAchat = dateAchat;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantiteAchat() {
        return quantiteAchat;
    }

    public void setQuantiteAchat(int quantiteAchat) {
        this.quantiteAchat = quantiteAchat;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public static AchatResponse convert(Achat achat) {
        AchatResponse resp = new AchatResponse();

        resp.setId(achat.getId());
        resp.setDateAchat(achat.getDateAchat());
        resp.setPrix(achat.getPrix());
        resp.setQuantiteAchat(achat.getQuantiteAchat());

        if (achat.getArticle() != null) {
            resp.setArticleId(achat.getArticle().getId());
        }
        if (achat.getClient() != null) {
            resp.setClientId(achat.getClient().getId());
        }

        return resp;
    }
}
