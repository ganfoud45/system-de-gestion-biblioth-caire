package tn.bibliotheque.dao;

import tn.bibliotheque.model.Pret;
import tn.bibliotheque.model.Adherent;
import tn.bibliotheque.model.Document;
import tn.bibliotheque.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PretDAO implements IDAO<Pret> {

    // Liens vers les autres DAOs pour faire les recherches intermédiaires
    private AdherentDAO adherentDAO = new AdherentDAO();
    private DocumentDAO documentDAO = new DocumentDAO();


    // =========================================================================
    // MÉTHODES IDAO
    // =========================================================================

    // Ajouter un prêt et réduire le nombre d'exemplaires du document
    public void create(Pret p) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            
            // Ré-attacher l'adhérent et le document à la session courante
            Adherent adherent = s.get(Adherent.class, p.getAdherent().getId());
            Document document = s.get(Document.class, p.getDocument().getId());
            
            p.setAdherent(adherent);
            p.setDocument(document);
            
            s.persist(p);
            
            // Réduire le stock
            document.setNbExemplaire(document.getNbExemplaire() - 1);
            s.merge(document);
            
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw e; 
        }
    }

    // Modifier un prêt existant
    public void update(Pret p) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.merge(p);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Supprimer un prêt par son id et restituer l'exemplaire au document
    public void delete(int id) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            Pret p = s.get(Pret.class, id);
            if (p != null) {
                p.getDocument().setNbExemplaire(p.getDocument().getNbExemplaire() + 1);
                s.merge(p.getDocument());
                s.remove(p);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Trouver un prêt par son id
    public Pret findById(int id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Pret.class, id);
        }
    }

    // Retourner tous les prêts
    public List<Pret> getAll() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("FROM Pret", Pret.class).list();
        }
    }


    // =========================================================================
    // RECHERCHE VIA L'ADHÉRENT
    // adherentDAO cherche l'objet Adherent, puis on cherche ses prêts
    // =========================================================================

    // Prêts d'un adhérent trouvé par nom et prénom
    public List<Pret> findByNomAdherent(String nom, String prenom) {
        List<Adherent> resultats = adherentDAO.rechercherParNomPrenom(nom, prenom);
        if (resultats.isEmpty()) return new ArrayList<>();
        return findByAdherent(resultats.get(0));
    }

    // Prêts d'un adhérent trouvé par son CIN
    public List<Pret> findByCinAdherent(String cin) {
        Adherent a = adherentDAO.rechercherParCin(cin);
        if (a == null) return new ArrayList<>();
        return findByAdherent(a);
    }

    // Prêts d'un adhérent trouvé par son email
    public List<Pret> findByEmailAdherent(String email) {
        Adherent a = adherentDAO.rechercherParEmail(email);
        if (a == null) return new ArrayList<>();
        return findByAdherent(a);
    }

    // Méthode interne : chercher les prêts d'un objet Adherent avec son document
    public List<Pret> findByAdherent(Adherent a) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                    "SELECT p FROM Pret p JOIN FETCH p.document WHERE p.adherent = :adh",
                    Pret.class)
                    .setParameter("adh", a)
                    .list();
        }
    }


    // =========================================================================
    // RECHERCHE VIA LE DOCUMENT
    // documentDAO cherche l'objet Document, puis on cherche ses prêts
    // =========================================================================

    // Prêts d'un document trouvé par son titre
    public List<Pret> findByNomDocument(String nom) {
        List<Document> resultats = documentDAO.rechercherParNom(nom);
        if (resultats.isEmpty()) return new ArrayList<>();
        return findByDocument(resultats.get(0));
    }

    // Prêts d'un livre trouvé par son ISBN
    public List<Pret> findByISBN(String isbn) {
        Document d = documentDAO.rechercherParISBN(isbn);
        if (d == null) return new ArrayList<>();
        return findByDocument(d);
    }

    // Prêts d'une revue trouvée par son ISSN
    public List<Pret> findByISSN(String issn) {
        Document d = documentDAO.rechercherParISSN(issn);
        if (d == null) return new ArrayList<>();
        return findByDocument(d);
    }

    // Prêts de tous les livres d'un même auteur
    public List<Pret> findByAuteur(String auteur) {
        List<Document> documents = documentDAO.rechercherParAuteur(auteur);
        if (documents.isEmpty()) return new ArrayList<>();
        List<Pret> tousLesPrets = new ArrayList<>();
        for (Document d : documents) {
            tousLesPrets.addAll(findByDocument(d));
        }
        return tousLesPrets;
    }

    // Méthode interne : chercher les prêts d'un objet Document avec son adhérent
    public List<Pret> findByDocument(Document d) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                    "SELECT p FROM Pret p JOIN FETCH p.adherent WHERE p.document = :doc",
                    Pret.class)
                    .setParameter("doc", d)
                    .list();
        }
    }


    // =========================================================================
    // RECHERCHE DIRECTE SUR LES ATTRIBUTS DU PRÊT
    // =========================================================================

    // Chercher par statut : "EN_COURS", "CLOTURE", "EN_RETARD"
    public List<Pret> findByStatut(String statut) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                    "SELECT p FROM Pret p " +
                    "JOIN FETCH p.adherent JOIN FETCH p.document " +
                    "WHERE LOWER(p.statut) = LOWER(:statut)",
                    Pret.class)
                    .setParameter("statut", statut)
                    .list();
        }
    }

    // Chercher par date de prêt exacte
    public List<Pret> findByDatePret(Date datePret) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                    "SELECT p FROM Pret p " +
                    "JOIN FETCH p.adherent JOIN FETCH p.document " +
                    "WHERE p.datePret = :datePret",
                    Pret.class)
                    .setParameter("datePret", datePret)
                    .list();
        }
    }

    // Chercher les prêts effectués entre deux dates
    public List<Pret> findByPeriode(Date dateDebut, Date dateFin) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                    "SELECT p FROM Pret p " +
                    "JOIN FETCH p.adherent JOIN FETCH p.document " +
                    "WHERE p.datePret BETWEEN :dateDebut AND :dateFin",
                    Pret.class)
                    .setParameter("dateDebut", dateDebut)
                    .setParameter("dateFin", dateFin)
                    .list();
        }
    }

    // Tous les prêts dont la date de retour est dépassée et non clôturés
    public List<Pret> findEnRetard() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                    "SELECT p FROM Pret p " +
                    "JOIN FETCH p.adherent JOIN FETCH p.document " +
                    "WHERE p.dateRetourPrevue < :today " +
                    "AND LOWER(p.statut) != 'cloture'",
                    Pret.class)
                    .setParameter("today", new Date())
                    .list();
        }
    }

    // Prêts en retard d'un adhérent précis, trouvé par son nom
    public List<Pret> findEnRetardByNomAdherent(String nom, String prenom) {
        List<Adherent> resultats = adherentDAO.rechercherParNomPrenom(nom, prenom);
        if (resultats.isEmpty()) return new ArrayList<>();
        Adherent a = resultats.get(0);
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                    "SELECT p FROM Pret p JOIN FETCH p.document " +
                    "WHERE p.adherent = :adh " +
                    "AND p.dateRetourPrevue < :today " +
                    "AND LOWER(p.statut) != 'cloture'",
                    Pret.class)
                    .setParameter("adh", a)
                    .setParameter("today", new Date())
                    .list();
        }
    }
}
