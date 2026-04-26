package tn.bibliotheque.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("These")
public class These extends Document {

	@Column(name="universite")
    private String universite;
	
	@Column(name="directeur")
    private String directeur;
	
	@Column(name="anneeSoutenance")
    private int anneeSoutenance;
	
	@Column(name="domaineSoutenance")
    private String domaineSoutenance;

    // Constructors
    public These() {
        super();
    }

    public These(int id, int numDoc, String nomDoc, String datePub, boolean disponible,
                 int nbExemplaire, String universite, String directeur,
                 int anneeSoutenance, String domaineSoutenance) {
        super(id, numDoc, nomDoc, datePub, disponible, nbExemplaire,"These");
        this.universite = universite;
        this.directeur = directeur;
        this.anneeSoutenance = anneeSoutenance;
        this.domaineSoutenance = domaineSoutenance;
    }

    // Getters
    public String getUniversite() { return universite; }
    public String getDirecteur() { return directeur; }
    public int getAnneeSoutenance() { return anneeSoutenance; }
    public String getDomaineSoutenance() { return domaineSoutenance; }

    // Setters
    public void setUniversite(String universite) { this.universite = universite; }
    public void setDirecteur(String directeur) { this.directeur = directeur; }
    public void setAnneeSoutenance(int anneeSoutenance) { this.anneeSoutenance = anneeSoutenance; }
    public void setDomaineSoutenance(String domaineSoutenance) { this.domaineSoutenance = domaineSoutenance; }

    @Override
    public String toString() {
        return "These{universite='" + universite + "', directeur='" + directeur +
               "', anneeSoutenance=" + anneeSoutenance +
               ", domaineSoutenance='" + domaineSoutenance +
               "', " + super.toString() + "}";
    }

    @Override
    public void afficherInfo() {
        System.out.println("=== Informations Thèse ===");
        System.out.println("Titre              : " + getNomDoc());
        System.out.println("Université         : " + universite);
        System.out.println("Directeur          : " + directeur);
        System.out.println("Année soutenance   : " + anneeSoutenance);
        System.out.println("Domaine            : " + domaineSoutenance);
        System.out.println("Date pub           : " + getDatePub());
        System.out.println("Disponible         : " + isDisponible());
        System.out.println("Exemplaires        : " + getNbExemplaire());
    }
}