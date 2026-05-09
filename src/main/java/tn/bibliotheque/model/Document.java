package tn.bibliotheque.model;

import javax.persistence.*;

@Entity
@Table(name="Document")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="typeDocument",discriminatorType=DiscriminatorType.STRING)
public abstract class Document {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
    private int id;
	
	@Column(name="numDoc")
    private int numDoc;
	
	@Column(name="nomDoc")
    private String nomDoc;
	
	@Column(name="datePub")
    private String datePub;
	
	@Column(name="disponible")
    private boolean disponible;
	
	@Column(name="nbExemplaire")
    private int nbExemplaire;
	
	@Column(name="typeDocument", insertable=false, updatable=false)
	private String type;

	
    // Constructors
    public Document() {}

    public Document(int id, int numDoc, String nomDoc, String datePub,
                    boolean disponible, int nbExemplaire,String type) {
        this.id = id;
        this.numDoc = numDoc;
        this.nomDoc = nomDoc;
        this.datePub = datePub;
        this.disponible = disponible;
        this.nbExemplaire = nbExemplaire;
        this.type=type;
    }

    // Getters
    public int getId() { return id; }
    public int getNumDoc() { return numDoc; }
    public String getNomDoc() { return nomDoc; }
    public String getDatePub() { return datePub; }
    public boolean isDisponible() { return disponible; }
    public int getNbExemplaire() { return nbExemplaire; }
    public String getType() { return type; }
 


    // Setters
    public void setId(int id) { this.id = id; }
    public void setNumDoc(int numDoc) { this.numDoc = numDoc; }
    public void setNomDoc(String nomDoc) { this.nomDoc = nomDoc; }
    public void setDatePub(String datePub) { this.datePub = datePub; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public void setNbExemplaire(int nbExemplaire) { this.nbExemplaire = nbExemplaire; }
    public void setType(String t) { this.type=t; }


    
    @Override
    public String toString() {
        return "Document{id=" + id + ", numDoc=" + numDoc + ", nomDoc='" + nomDoc +
               "', datePub='" + datePub + "', disponible=" + disponible +
               ", nbExemplaire=" + nbExemplaire + "}";
    }

    public abstract void afficherInfo();
}