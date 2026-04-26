package tn.bibliotheque.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Livre")
public class Livre extends Document {

	@Column(name="isbn")
    private String isbn;
	
	@Column(name="nbrPg")
    private int nbrPg;
	
	@Column(name="genre")
    private String genre;
	
	@Column(name="motsCle")
    private String motsCle;
	
	@Column(name="auteurLivre")
    private String auteur;

    // Constructors
    public Livre() {
        super();
    }

    public Livre(int id, int numDoc, String nomDoc, String datePub, boolean disponible,
                 int nbExemplaire, String isbn, int nbrPg, String genre,
                 String motsCle, String auteur) {
        super(id, numDoc, nomDoc, datePub, disponible, nbExemplaire,"Livre");
        this.isbn = isbn;
        this.nbrPg = nbrPg;
        this.genre = genre;
        this.motsCle = motsCle;
        this.auteur = auteur;
    }

    // Getters
    public String getIsbn() { return isbn; }
    public int getNbrPg() { return nbrPg; }
    public String getGenre() { return genre; }
    public String getMotsCle() { return motsCle; }
    public String getAuteur() { return auteur; }

    // Setters
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setNbrPg(int nbrPg) { this.nbrPg = nbrPg; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setMotsCle(String motsCle) { this.motsCle = motsCle; }
    public void setAuteur(String auteur) { this.auteur = auteur; }

    @Override
    public String toString() {
        return "Livre{isbn='" + isbn + "', nbrPg=" + nbrPg + ", genre='" + genre +
               "', motsCle='" + motsCle + "', auteur='" + auteur +
               "', " + super.toString() + "}";
    }

    @Override
    public void afficherInfo() {
        System.out.println("=== Informations Livre ===");
        System.out.println("Titre       : " + getNomDoc());
        System.out.println("ISBN        : " + isbn);
        System.out.println("Auteur      : " + auteur);
        System.out.println("Genre       : " + genre);
        System.out.println("Nb pages    : " + nbrPg);
        System.out.println("Mots-clés   : " + motsCle);
        System.out.println("Date pub    : " + getDatePub());
        System.out.println("Disponible  : " + isDisponible());
        System.out.println("Exemplaires : " + getNbExemplaire());
    }
}