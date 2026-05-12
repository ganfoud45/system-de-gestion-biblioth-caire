package tn.bibliotheque.dao;

import tn.bibliotheque.model.Adherent;
import tn.bibliotheque.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class AdherentDAO implements IDAO<Adherent>{
	
	public  void create(Adherent a)
	{
		Session s=HibernateUtil.getSessionFactory().openSession();
		Transaction tx=null;
		try {
			tx=s.beginTransaction();
			s.merge(a);
			tx.commit();
			System.out.println("Insertion réussie dans la base !");
		}catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			s.close();
		}
	}
	
	public  void update(Adherent a)
	{
		Session s=HibernateUtil.getSessionFactory().openSession();
		Transaction tx=null;
		try {
			tx=s.beginTransaction();
			s.merge(a);
			tx.commit();
		}catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			s.close();
		}
	}
	
	public void delete(int id) {
	    Session s = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = null;
	    try {
	        tx = s.beginTransaction();
	        
	        Adherent a = s.get(Adherent.class, id);
	        if (a != null) {
	            String hqlUpdate = "UPDATE Document d SET d.disponible = true " +
	                               "WHERE d.id IN (SELECT p.document.id FROM Pret p WHERE p.adherent.id = :id)";
	            
	            s.createQuery(hqlUpdate)
	             .setParameter("id", id)
	             .executeUpdate();

	            s.createQuery("DELETE FROM Pret p WHERE p.adherent.id = :id")
	             .setParameter("id", id)
	             .executeUpdate();
	            
	            s.remove(a);
	            
	            tx.commit();
	            System.out.println("Adhérent supprimé et livres remis en disponibilité.");
	        }
	    } catch (Exception e) {
	        if (tx != null) tx.rollback();
	        e.printStackTrace();
	    } finally {
	        s.close();
	    }
	}
	
	
	public List<Adherent> getAll() {
		Session s=HibernateUtil.getSessionFactory().openSession();
		try {
			return s.createQuery("From Adherent", Adherent.class).list();
		}finally {
			s.close();
		}
	}
	
	public Adherent findById(int id) {
		Session s=HibernateUtil.getSessionFactory().openSession();
		try {
			return s.get(Adherent.class, id);
		}finally {
			s.close();
		}
	}

	
	// les méthodes de recherche 
	
	//recherche par nom et prenom ; list 
	public List<Adherent> rechercherParNomPrenom(String nom, String prenom) {
	    Session s = HibernateUtil.getSessionFactory().openSession();
	    List<Adherent> resultats = new java.util.ArrayList<>();
	    try {
	        String hql = "FROM Adherent a WHERE LOWER(a.nom) LIKE :nom " +
	                     "AND LOWER(a.prenom) LIKE :prenom";

	        resultats = s.createQuery(hql, Adherent.class)
	                .setParameter("nom", "%" + nom.toLowerCase() + "%")
	                .setParameter("prenom", "%" + prenom.toLowerCase() + "%")
	                .list();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        s.close();
	    }
	    return resultats;
	}
	
	
	//recherche par cin ; resultat unique
	public Adherent rechercherParCin(String cin) {
		Session s=HibernateUtil.getSessionFactory().openSession();
		Adherent a=new Adherent();
		try {
			a= s.createQuery(
					"FROM Adherent a WHERE a.cin =:cin",Adherent.class)
					.setParameter("cin",cin).uniqueResult();
					
		}catch (Exception e) {
	        e.printStackTrace();
	        
	    } finally {
	        s.close();
	    }
		return a;
	}
	
	//recherche par email
	public Adherent rechercherParEmail(String email) {
		Session s=HibernateUtil.getSessionFactory().openSession();
		Adherent a=new Adherent();
		try {
			a= s.createQuery(
					"FROM Adherent a WHERE a.email=:email",Adherent.class)
					.setParameter("email",email).uniqueResult();
		}catch (Exception e) {
	        e.printStackTrace();
	        
	    } finally {
	        s.close();
	    }
		return a;
	}
	
	
	
	
	
	
}
