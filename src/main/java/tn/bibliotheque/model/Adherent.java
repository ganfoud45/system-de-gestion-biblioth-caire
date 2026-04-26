package tn.bibliotheque.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Adherent")


public class Adherent extends Utilisateur {
	@Column(name="num_abonnement")
    private int numAbonnement;

    // Constructors
    public Adherent() {
        super();
    }

    public Adherent(int id, String cin, String nom, String prenom,
                    String dateNaissance, String email, int numTel,
                    String motDePasse, int numAbonnement) {
        super(id, cin, nom, prenom, dateNaissance, email, numTel, motDePasse);
        this.numAbonnement = numAbonnement;
    }

    // Getter
    public int getNumAbonnement() { return numAbonnement; }

    // Setter
    public void setNumAbonnement(int numAbonnement) { this.numAbonnement = numAbonnement; }

    @Override
    public String toString() {
        return "Adherent{numAbonnement=" + numAbonnement + ", " + super.toString() + "}";
    }

    @Override
    public void afficherInfo() {
        System.out.println("=== Informations Adhérent ===");
        System.out.println("Numéro d'abonnement : " + numAbonnement);
        System.out.println("Nom complet         : " + getNom() + " " + getPrenom());
        System.out.println("CIN                 : " + getCin());
        System.out.println("Email               : " + getEmail());
        System.out.println("Téléphone           : " + getNumTel());
        System.out.println("Date de naissance   : " + getDateNaissance());
    }
}