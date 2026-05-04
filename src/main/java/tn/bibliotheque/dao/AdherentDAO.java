package tn.bibliotheque.dao;

import tn.bibliotheque.model.Adherent;
import tn.bibliotheque.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class AdherentDAO implements IDAO<Adherent>{
	
	public  void create(Adherent a)
	{
		Transaction tx=null;
		try(Session s=HibernateUtil.getSessionFactory().openSession()) {
			tx=s.beginTransaction();
			s.persist(a);
			tx.commit();
		}catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
	}
	
	public  void update(Adherent a)
	{
		Transaction tx=null;
		try (Session s=HibernateUtil.getSessionFactory().openSession()) {
			tx=s.beginTransaction();
			s.merge(a);
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
	}
	
	public void delete(int numAbo)
	{
		Transaction tx=null;
		try(Session s=HibernateUtil.getSessionFactory().openSession()) {
			tx=s.beginTransaction();
			Adherent a=s.get(Adherent.class, numAbo);
			if (a!=null) s.remove(a);
			tx.commit();
					
		}catch (Exception e) {
			if (tx !=null) tx.rollback();
			e.printStackTrace();
		}
	}
	
	
	public List<Adherent> getAll() {
		try (Session s=HibernateUtil.getSessionFactory().openSession()) {
			return s.createQuery("From Adherent", Adherent.class).list();
		}
	}
	
	public Adherent findById(int id) {
		try (Session s=HibernateUtil.getSessionFactory().openSession()) {
			return s.get(Adherent.class, id);
		}
	}

	
	// les méthodes de recherche 
	
	//recherche par nom et prenom ; list 
	public  List<Adherent> rechercherParNomPrenom(String nom,String prenom) {
		try (Session s=HibernateUtil.getSessionFactory().openSession()) {
			return s.createQuery(
					"FROM Adherent a WHERE LOWER(a.nom) LIKE LOWER(:nom)" +
					"AND LOWER(a.prenom) LIKE LOWER(:prenom)",Adherent.class
			).setParameter("nom","%" + nom + "%" ).setParameter("prenom","%" + prenom + "%").list();
		}
	}
	
	//recherche par cin ; resultat unique
	public Adherent rechercherParCin(String cin) {
		
		try (Session s=HibernateUtil.getSessionFactory().openSession()) {
			return s.createQuery(
					"FROM Adherent a WHERE a.cin =:cin",Adherent.class)
					.setParameter("cin",cin).uniqueResult();
					
		}
	}
	
	//recherche par email
	public Adherent rechercherParEmail(String email) {
		try (Session s=HibernateUtil.getSessionFactory().openSession()) {
			return s.createQuery(
					"FROM Adherent a WHERE a.email=:email",Adherent.class)
					.setParameter("email",email).uniqueResult();
		}
	}
	
	
	
	
	
	
}
