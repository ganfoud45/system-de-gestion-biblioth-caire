package tn.bibliotheque.dao;

import tn.bibliotheque.model.Document;
import tn.bibliotheque.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class DocumentDAO implements IDAO<Document> {

	 public void create(Document d) {
	        Transaction tx = null;
	        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
	            tx = s.beginTransaction();
	            s.persist(d);
	            tx.commit();
	        } catch (Exception e) {
	            if (tx != null) tx.rollback();
	            e.printStackTrace();
	        }
	    }
	 
	 public void update(Document d) {
	        Transaction tx = null;
	        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
	            tx = s.beginTransaction();
	            s.merge(d);
	            tx.commit();
	        } catch (Exception e) {
	            if (tx != null) tx.rollback();
	            e.printStackTrace();
	        }
	    }
	 
	 public void delete(int id) {
	        Transaction tx = null;
	        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
	            tx = s.beginTransaction();
	            Document d = s.get(Document.class, id);
	            if (d != null) s.remove(d);
	            tx.commit();
	        } catch (Exception e) {
	            if (tx != null) tx.rollback();
	            e.printStackTrace();
	        }
	    }
	 
	 public List<Document> getAll() {
	        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
	            return s.createQuery("FROM Document", Document.class).list();
	        }
	    }

    public Document findById(int id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Document.class, id);
        }
    }
    
    
    //les méthodes de recherche
    
    
    //recherche par nom de document
    public List<Document> rechercherParNom(String nom) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                "FROM Document d WHERE LOWER(d.nomDoc) LIKE LOWER(:nom)",
                Document.class)
                .setParameter("nom", "%" + nom + "%")
                .list();
        }
    }
    
    //recherche par numéro de document
    public Document rechercherParNum(int numDoc) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                "FROM Document d WHERE d.numDoc =: numDoc",
                Document.class)
                .setParameter("numDoc", numDoc)
                .uniqueResult();
        }
    }
    
    //rechercher par type
    public List<Document> rechercherParType(String type) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                "FROM Document d WHERE d.type = :type",
                Document.class)
                .setParameter("type", type)
                .list();
        }
    }
    
    
   //rechercher les livres 
    
    
    //par mots cles
    public List<Document> rechercherParMotsCle(String motsCles) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery(
                "FROM Livre l WHERE LOWER(l.motsCle) LIKE LOWER(:motsCles)",
                Document.class)
                .setParameter("motsCles", "%" + motsCles + "%")
                .list();
        }
    }
    
    
    //par isbn
    public Document rechercherParISBN(String isbn) {
    	try (Session s=HibernateUtil.getSessionFactory().openSession()) {
    		return s.createQuery(
    				"FROM Livre l WHERE LOWER(l.isbn) LIKE LOWER(:isbn)",Document.class)
    				.setParameter("isbn", "%" + isbn + "%")
    				.uniqueResult();
    		
    	}
    }
    
    //par auteur
    public List<Document> rechercherParAuteur(String auteur) {
    	try(Session s=HibernateUtil.getSessionFactory().openSession()) {
    		return s.createQuery(
    				"FROM Livre l WHERE LOWER(l.auteur) LIKE LOWER(:auteur)",Document.class)
    				.setParameter("auteur", "%" + auteur + "%")
    				.list();
    	}
    }
    
    
    //recherche Dictionnaire
    public List<Document> rechercherParLangue(String lgSource,String lgCible){
    	try(Session s=HibernateUtil.getSessionFactory().openSession()) {
    		return s.createQuery(
    				"FROM Dictionnaire d WHERE LOWER(d.lgSource) LIKE LOWER(:lgSource)"
    				+"AND LOWER(d.lgCible) LIKE LOWER(:lgCible)",Document.class
    				).setParameter("lgSource", "%" + lgSource + "%")
    				.setParameter("lgCible", "%" + lgCible + "%")
    				.list();
    	}
    }
	    
    //recherche Revue
    public Document rechercherParISSN(String issn) {
    	try (Session s=HibernateUtil.getSessionFactory().openSession()) {
    		return s.createQuery(
    				"FROM Revue r WHERE LOWER(r.issn) LIKE LOWER(:issn)",Document.class)
    				.setParameter("issn", "%" + issn + "%")
    				.uniqueResult();
    		
    	}
    }
    
    //recherche These
    public List<Document> rechercherParDomaineSoutenance(String domaineSoutenance) {
    	try (Session s=HibernateUtil.getSessionFactory().openSession()) {
    		return s.createQuery(
    				"FROM These t WHERE LOWER(t.domaineSoutenance) LIKE LOWER(:domaineSoutenance)",Document.class)
    				.setParameter("domaineSoutenance", "%" + domaineSoutenance + "%")
    				.list();
    		
    	}
    }
    
    
	    
}
