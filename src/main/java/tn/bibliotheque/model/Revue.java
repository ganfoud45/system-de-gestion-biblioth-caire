package tn.bibliotheque.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Revue")
public class Revue extends Document {

	@Column(name="issn")
    private String issn;
	
	@Column(name="numero")
    private int numero;
	
	@Column(name="periodicite")
    private String periodicite;

    // Constructors
    public Revue() {
        super();
    }

    public Revue(int id, int numDoc, String nomDoc, String datePub, boolean disponible,
                 int nbExemplaire, String issn, int numero, String periodicite) {
        super(id, numDoc, nomDoc, datePub, disponible, nbExemplaire,"Revue");
        this.issn = issn;
        this.numero = numero;
        this.periodicite = periodicite;
    }

    // Getters
    public String getIssn() { return issn; }
    public int getNumero() { return numero; }
    public String getPeriodicite() { return periodicite; }

    // Setters
    public void setIssn(String issn) { this.issn = issn; }
    public void setNumero(int numero) { this.numero = numero; }
    public void setPeriodicite(String periodicite) { this.periodicite = periodicite; }

    @Override
    public String toString() {
        return "Revue{issn='" + issn + "', numero=" + numero +
               ", periodicite='" + periodicite + "', " + super.toString() + "}";
    }

    @Override
    public void afficherInfo() {
        System.out.println("=== Informations Revue ===");
        System.out.println("Titre        : " + getNomDoc());
        System.out.println("ISSN         : " + issn);
        System.out.println("Numéro       : " + numero);
        System.out.println("Périodicité  : " + periodicite);
        System.out.println("Date pub     : " + getDatePub());
        System.out.println("Disponible   : " + isDisponible());
        System.out.println("Exemplaires  : " + getNbExemplaire());
    }
}