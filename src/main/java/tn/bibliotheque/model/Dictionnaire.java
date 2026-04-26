package tn.bibliotheque.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Dictionnaire")
public class Dictionnaire extends Document {

	@Column(name="auteurDico")
    private String auteur;
	
	@Column(name="nbrMots")
    private int nbrMots;
	
	@Column(name="lgSource")
    private String lgSource;
	
	@Column(name="lgCible")
    private String lgCible;

    // Constructors
    public Dictionnaire() {
        super();
    }

    public Dictionnaire(int id, int numDoc, String nomDoc, String datePub, boolean disponible,
                        int nbExemplaire, String auteur, int nbrMots,
                        String lgSource, String lgCible) {
        super(id, numDoc, nomDoc, datePub, disponible, nbExemplaire,"Dictionnaire");
        this.auteur = auteur;
        this.nbrMots = nbrMots;
        this.lgSource = lgSource;
        this.lgCible = lgCible;
    }

    // Getters
    public String getAuteur() { return auteur; }
    public int getNbrMots() { return nbrMots; }
    public String getLgSource() { return lgSource; }
    public String getLgCible() { return lgCible; }

    // Setters
    public void setAuteur(String auteur) { this.auteur = auteur; }
    public void setNbrMots(int nbrMots) { this.nbrMots = nbrMots; }
    public void setLgSource(String lgSource) { this.lgSource = lgSource; }
    public void setLgCible(String lgCible) { this.lgCible = lgCible; }

    @Override
    public String toString() {
        return "Dictionnaire{auteur='" + auteur + "', nbrMots=" + nbrMots +
               ", lgSource='" + lgSource + "', lgCible='" + lgCible +
               "', " + super.toString() + "}";
    }

    @Override
    public void afficherInfo() {
        System.out.println("=== Informations Dictionnaire ===");
        System.out.println("Titre       : " + getNomDoc());
        System.out.println("Auteur      : " + auteur);
        System.out.println("Nb mots     : " + nbrMots);
        System.out.println("Langue src  : " + lgSource);
        System.out.println("Langue cible: " + lgCible);
        System.out.println("Date pub    : " + getDatePub());
        System.out.println("Disponible  : " + isDisponible());
        System.out.println("Exemplaires : " + getNbExemplaire());
    }
}