package tn.bibliotheque.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.persistence.*;

@Entity
@Table(name="Pret")

public class Pret {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
    private int id;
	
	@ManyToOne
	@JoinColumn(name="adherent_id")
    private Adherent adherent;
	
	@ManyToOne
	@JoinColumn(name="document_id")
    private Document document;
	
	@Column(name="datePret")
    private LocalDate datePret;
	
	@Column(name="dateRetourPrevue")
    private LocalDate dateRetourPrevue;
	
	@Column(name="statut")
    private String statut; // "EN_COURS", "RETOURNE", "EN_RETARD"

    // Constructeurs
    public Pret() {}

    public Pret(int id, Adherent adherent, Document document,
                LocalDate datePret, LocalDate dateRetourPrevue, String statut) {
        this.id = id;
        this.adherent = adherent;
        this.document = document;
        this.datePret = datePret;
        this.dateRetourPrevue = dateRetourPrevue;
        this.statut = statut;
    }

    // Getters
    public int getId() { return id; }
    public Adherent getAdherent() { return adherent; }
    public Document getDocument() { return document; }
    public LocalDate getDatePret() { return datePret; }
    public LocalDate getDateRetourPrevue() { return dateRetourPrevue; }
    public String getStatut() { return statut; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setAdherent(Adherent adherent) { this.adherent = adherent; }
    public void setDocument(Document document) { this.document = document; }
    public void setDatePret(LocalDate datePret) { this.datePret = datePret; }
    public void setDateRetourPrevue(LocalDate dateRetourPrevue) { this.dateRetourPrevue = dateRetourPrevue; }
    public void setStatut(String statut) { this.statut = statut; }

    /**
     * Vérifie si le prêt est en retard.
     * @return true si la date de retour prévue est dépassée et le statut n'est pas "RETOURNE"
     */
    public boolean isRetard() {
        return !statut.equals("RETOURNE") && LocalDate.now().isAfter(dateRetourPrevue);
    }

    /**
     * Clôture le prêt (marque comme retourné et rend le document disponible).
     */
    public void cloturer() {
        this.statut = "RETOURNE";
        if (this.document != null) {
            this.document.setDisponible(true);
        }
        System.out.println("Prêt #" + id + " clôturé avec succès.");
    }

    /**
     * Calcule le nombre de jours de retard.
     * @return nombre de jours de retard, 0 si pas en retard
     */
    public int getNbJoursRetard() {
        if (!isRetard()) return 0;
        return (int) ChronoUnit.DAYS.between(dateRetourPrevue, LocalDate.now());
    }

    @Override
    public String toString() {
        return "Pret{id=" + id +
               ", adherent=" + (adherent != null ? adherent.getNumAbonnement() : "null") +
               ", document=" + (document != null ? document.getNomDoc() : "null") +
               ", datePret=" + datePret +
               ", dateRetourPrevue=" + dateRetourPrevue +
               ", statut='" + statut + "'}";
    }

    public void afficherInfo() {
        System.out.println("=== Informations Prêt ===");
        System.out.println("ID prêt              : " + id);
        System.out.println("Adhérent             : " + (adherent != null ? adherent.getNom() + " " + adherent.getPrenom() : "N/A"));
        System.out.println("Document             : " + (document != null ? document.getNomDoc() : "N/A"));
        System.out.println("Date de prêt         : " + datePret);
        System.out.println("Retour prévu         : " + dateRetourPrevue);
        System.out.println("Statut               : " + statut);
        System.out.println("En retard            : " + isRetard());
        if (isRetard()) {
            System.out.println("Jours de retard      : " + getNbJoursRetard());
        }
    }
}