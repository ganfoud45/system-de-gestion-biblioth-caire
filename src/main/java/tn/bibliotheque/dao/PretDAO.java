package tn.bibliotheque.dao;

import tn.bibliotheque.model.Pret;
import tn.bibliotheque.model.Adherent;
import tn.bibliotheque.model.Document;
import tn.bibliotheque.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Date;
import java.util.List;

public class PretDAO implements IDAO<Pret> {

    // =====================================================================
    // Méthodes IDAO
    // =====================================================================

    // Ajouter un nouveau prêt et réduire le nombre d'exemplaires du document
    public void create(Pret p) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.persist(p);
            p.getDocument().setNbExemplaire(p.getDocument().getNbExemplaire() - 1);
            s.merge(p.getDocument());
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
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

    // =====================================================================
    // Recherche via l'Adhérent
    // On passe l'objet Adherent déjà trouvé par AdherentDAO
    // (par nom, cin, email...)
    // =====================================================================

    // Tous les prêts d'un adhérent avec les détails du document
    public List<Pret> findByAdherent(Adherent a) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                    "SELECT p FROM Pret p JOIN FETCH p.document WHERE p.adherent = :adh",
                    Pret.class)
                    .setParameter("adh", a)
                    .list();
        }
    }

    // =====================================================================
    // Recherche via le Document
    // On passe l'objet Document déjà trouvé par DocumentDAO
    // (par nomDoc, isbn, issn, auteur...)
    // =====================================================================

    // Tous les prêts d'un document avec les détails de l'adhérent
    public List<Pret> findByDocument(Document d) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                    "SELECT p FROM Pret p JOIN FETCH p.adherent WHERE p.document = :doc",
                    Pret.class)
                    .setParameter("doc", d)
                    .list();
        }
    }

    // =====================================================================
    // Recherche directe sur les attributs du Prêt
    // =====================================================================

    // Chercher les prêts par statut : "EN_COURS", "CLOTURE", "EN_RETARD"
    public List<Pret> findByStatut(String statut) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                    "SELECT p FROM Pret p " +
                    "JOIN FETCH p.adherent " +
                    "JOIN FETCH p.document " +
                    "WHERE LOWER(p.statut) = LOWER(:statut)",
                    Pret.class)
                    .setParameter("statut", statut)
                    .list();
        }
    }

    // Chercher les prêts par date de prêt exacte
    public List<Pret> findByDatePret(Date datePret) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                    "SELECT p FROM Pret p " +
                    "JOIN FETCH p.adherent " +
                    "JOIN FETCH p.document " +
                    "WHERE p.datePret = :datePret",
                    Pret.class)
                    .setParameter("datePret", datePret)
                    .list();
        }
    }

    // Chercher les prêts entre deux dates
    public List<Pret> findByPeriode(Date dateDebut, Date dateFin) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                    "SELECT p FROM Pret p " +
                    "JOIN FETCH p.adherent " +
                    "JOIN FETCH p.document " +
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
                    "JOIN FETCH p.adherent " +
                    "JOIN FETCH p.document " +
                    "WHERE p.dateRetourPrevue < :today " +
                    "AND LOWER(p.statut) != 'cloture'",
                    Pret.class)
                    .setParameter("today", new Date())
                    .list();
        }
    }

    // Les prêts en retard pour un adhérent précis
    // Appelée après AdherentDAO.rechercherParNomPrenom() / rechercherParCin() ...
    public List<Pret> findEnRetardByAdherent(Adherent a) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                    "SELECT p FROM Pret p " +
                    "JOIN FETCH p.document " +
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
