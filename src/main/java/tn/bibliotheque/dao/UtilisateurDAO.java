package tn.bibliotheque.dao;

import org.hibernate.Session;

import tn.bibliotheque.model.Utilisateur;
import tn.bibliotheque.util.HibernateUtil;

	public class UtilisateurDAO  {
		public Utilisateur findById(int id) {
				try (Session s=HibernateUtil.getSessionFactory().openSession()) {
					return s.get(Utilisateur.class, id);
				}
	}
	//recherche par cin ; resultat unique
		public Utilisateur rechercherParCin(String cin) {
			
			try (Session s=HibernateUtil.getSessionFactory().openSession()) {
				return s.createQuery(
						"FROM Utilisateur a WHERE a.cin =:cin",Utilisateur.class)
						.setParameter("cin",cin).uniqueResult();
						
			}
		}

}
