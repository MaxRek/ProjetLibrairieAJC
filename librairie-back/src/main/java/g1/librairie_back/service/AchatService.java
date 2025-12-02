package g1.librairie_back.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import g1.librairie_back.dao.IDAOAchat;
import g1.librairie_back.dao.IDAOArticle;
import g1.librairie_back.dao.IDAOCompte;
import g1.librairie_back.model.Achat;
import g1.librairie_back.model.Article;
import g1.librairie_back.model.Client;
import g1.librairie_back.model.Compte;

@Service
public class AchatService {

    @Autowired
    private IDAOAchat daoAchat;

    @Autowired
    private IDAOArticle daoArticle;

    @Autowired
    private IDAOCompte daoCompte;

    public Achat getById(Integer id) {
        Optional<Achat> opt = daoAchat.findById(id);
        return opt.orElse(null);
    }

    public List<Achat> getAll() {
        return daoAchat.findAll();
    }

    public Achat create(Achat achat) {
        if (achat.getId() != null) {
            throw new RuntimeException("Création impossible - id déjà existant");
        }
        if (achat.getDateAchat() == null) {
            achat.setDateAchat(LocalDate.now());
        }
        return daoAchat.save(achat);
    }

    public Achat update(Achat achat) {
        if (achat.getId() == null) {
            throw new RuntimeException("Achat sans id - modification impossible");
        }
        return daoAchat.save(achat);
    }

    public void deleteById(Integer id) {
        daoAchat.deleteById(id);
    }

    public void delete(Achat achat) {
        daoAchat.delete(achat);
    }

    public Achat createFromIds(Integer articleId, Integer clientId, int qte, double prix, LocalDate date) {

        Article article = daoArticle.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article introuvable"));

        Compte compte = daoCompte.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));

        if (!(compte instanceof Client)) {
            throw new RuntimeException("L'id fourni n'est pas valide");
        }

        Client client = (Client) compte;

        Achat achat = new Achat(
                date != null ? date : LocalDate.now(),
                prix,
                qte,
                article,
                client
        );

        return daoAchat.save(achat);
    }
}
