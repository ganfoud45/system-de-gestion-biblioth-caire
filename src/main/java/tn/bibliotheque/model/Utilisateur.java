package tn.bibliotheque.model;


import javax.persistence.*;

@Entity
@Table(name="Utilisateur")
@Inheritance(strategy=InheritanceType.JOINED)


public abstract class Utilisateur {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
    private int id;
	
	@Column(name="cin",nullable=false)
    private String cin;
	
	@Column(name="nom")
    private String nom;
	@Column(name="prenom")
    private String prenom;
	@Column(name="dateNaissance")
    private String dateNaissance;
	@Column(name="email")
    private String email;
	@Column(name="numTel")
    private int numTel;
	@Column(name="motDePasse")
    private String motDePasse;
	@Column(name="type")
	private String type;

    // Constructors
    public Utilisateur() {}

    public Utilisateur(int id, String cin, String nom, String prenom,
                       String dateNaissance, String email, int numTel,
                       String motDePasse,String type) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.numTel = numTel;
        this.motDePasse = motDePasse;
        this.type=type;
    }

    // Getters
    public int getId() { return id; }
    public String getCin() { return cin; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getDateNaissance() { return dateNaissance; }
    public String getEmail() { return email; }
    public int getNumTel() { return numTel; }
    public String getMotDePasse() { return motDePasse; }
    public String getType() { return type; }


    // Setters
    public void setId(int id) { this.id = id; }
    public void setCin(String cin) { this.cin = cin; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setDateNaissance(String dateNaissance) { this.dateNaissance = dateNaissance; }
    public void setEmail(String email) { this.email = email; }
    public void setNumTel(int numTel) { this.numTel = numTel; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
    public void setType(String type) { this.type = type; }


    @Override
    public String toString() {
        return "Utilisateur{id=" + id + ", cin='" + cin + "', nom='" + nom +
               "', prenom='" + prenom + "', dateNaissance='" + dateNaissance +
               "', email='" + email + "', numTel=" + numTel + "'}";
    }

    public abstract void afficherInfo();
}