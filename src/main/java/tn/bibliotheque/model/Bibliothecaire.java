package tn.bibliotheque.model;

import javax.persistence.*;

@Entity
@Table(name="Bibliothecaire")


public class Bibliothecaire extends Utilisateur {

	@Column(name="matricule")
    private String matricule;
	@Column(name="grade")
    private String grade;

    // Constructors
    public Bibliothecaire() {
        super();
    }

    public Bibliothecaire(int id, String cin, String nom, String prenom,
                          String dateNaissance, String email, int numTel,
                          String motDePasse, String matricule, String grade) {
        super(id, cin, nom, prenom, dateNaissance, email, numTel, motDePasse);
        this.matricule = matricule;
        this.grade = grade;
    }

    // Getters
    public String getMatricule() { return matricule; }
    public String getGrade() { return grade; }

    // Setters
    public void setMatricule(String matricule) { this.matricule = matricule; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "Bibliothecaire{matricule='" + matricule + "', grade='" + grade +
               "', " + super.toString() + "}";
    }

    @Override
    public void afficherInfo() {
        System.out.println("=== Informations Bibliothécaire ===");
        System.out.println("Matricule   : " + matricule);
        System.out.println("Grade       : " + grade);
        System.out.println("Nom complet : " + getNom() + " " + getPrenom());
        System.out.println("CIN         : " + getCin());
        System.out.println("Email       : " + getEmail());
        System.out.println("Téléphone   : " + getNumTel());
    }
}