package tn.bibliotheque.view.adherent;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import tn.bibliotheque.dao.AdherentDAO;
import tn.bibliotheque.dao.DocumentDAO;
import tn.bibliotheque.dao.PretDAO;
import tn.bibliotheque.model.Adherent;
import tn.bibliotheque.model.Document;
import tn.bibliotheque.model.Pret;
import tn.bibliotheque.view.prets.SelectionDocumentDialog;

public class PanelEmprunterDocument extends JPanel {

    private JTextField txtCinAdherent;
    private JTextField txtDocument;
    private Adherent adherentTrouve;
    private Document documentSelectionne;
    private AdherentDAO adhDAO = new AdherentDAO();
    private PretDAO pretDAO = new PretDAO();

    public PanelEmprunterDocument(Adherent adherent) {
        setLayout(null);
        setBackground(Color.WHITE);

        JLabel lblTitre = new JLabel("EMPRUNTER UN DOCUMENT");
        lblTitre.setFont(new Font("Serif", Font.BOLD, 18));
        lblTitre.setForeground(new Color(102, 51, 204));
        lblTitre.setBounds(150, 20, 400, 30);
        add(lblTitre);

        // --- CIN de l'adhérent ---
        JLabel lblCin = new JLabel("Votre CIN :");
        lblCin.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblCin.setBounds(50, 80, 100, 25);
        add(lblCin);

        txtCinAdherent = new JTextField();
        txtCinAdherent.setBounds(160, 80, 200, 25);
        add(txtCinAdherent);

        JButton btnVerifier = new JButton("Vérifier");
        btnVerifier.setBounds(370, 80, 100, 25);
        add(btnVerifier);

        JLabel lblInfoAdherent = new JLabel("");
        lblInfoAdherent.setFont(new Font("Tahoma", Font.ITALIC, 12));
        lblInfoAdherent.setForeground(new Color(0, 150, 0));
        lblInfoAdherent.setBounds(160, 110, 350, 20);
        add(lblInfoAdherent);

        // --- Document ---
        JLabel lblDoc = new JLabel("Document :");
        lblDoc.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblDoc.setBounds(50, 150, 100, 25);
        add(lblDoc);

        txtDocument = new JTextField();
        txtDocument.setEditable(false);
        txtDocument.setBounds(160, 150, 200, 25);
        add(txtDocument);

        JButton btnChoisirDoc = new JButton("Choisir...");
        btnChoisirDoc.setBounds(370, 150, 100, 25);
        add(btnChoisirDoc);

        // --- Bouton Emprunter ---
        JButton btnEmprunter = new JButton("CONFIRMER L'EMPRUNT");
        btnEmprunter.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        
        btnEmprunter.setBounds(180, 220, 220, 40);
        add(btnEmprunter);

        // --- ACTIONS ---
        btnVerifier.addActionListener(e -> {
            String cin = txtCinAdherent.getText().trim();
            if (cin.isEmpty()) {
                lblInfoAdherent.setText("Veuillez saisir votre CIN.");
                return; // On arrête l'exécution ici
            }

            if (!cin.equals(adherent.getCin())) {
                lblInfoAdherent.setForeground(Color.RED);
                lblInfoAdherent.setText("🚫 Ce n'est pas votre CIN.");
                adherentTrouve = null;
                return; // Sécurité : impossible d'aller plus loin
            }
            adherentTrouve = adhDAO.rechercherParCin(cin);

            if (adherentTrouve != null  ) {
                    lblInfoAdherent.setText("✔ " + adherentTrouve.getNom() + " " + adherentTrouve.getPrenom() + " identifié(e).");
                } 
            else {
       
                    lblInfoAdherent.setText("✘ Aucun adhérent trouvé avec ce CIN.");
                    adherentTrouve = null;
                }
            
            

        });

        btnChoisirDoc.addActionListener(e -> {
            SelectionDocumentDialog dialog = new SelectionDocumentDialog();
            dialog.setModal(true);
            dialog.setVisible(true);
            if (dialog.getDocumentSelectionne() != null) {
                documentSelectionne = dialog.getDocumentSelectionne();
                txtDocument.setText(documentSelectionne.getNomDoc());
            }
        });

        btnEmprunter.addActionListener(e -> confirmerEmprunt(adherent));
    }

    private void confirmerEmprunt(Adherent adherent) {
        if (adherentTrouve == null) {
            JOptionPane.showMessageDialog(this, "Veuillez d'abord vérifier votre CIN.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (documentSelectionne == null) {
            JOptionPane.showMessageDialog(this, "Veuillez choisir un document.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (documentSelectionne.getNbExemplaire() <= 0) {
            JOptionPane.showMessageDialog(this, "Ce document n'est plus disponible.", "Stock épuisé", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Confirmer l'emprunt de \"" + documentSelectionne.getNomDoc() + "\" ?",
            "Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Pret pret = new Pret();
                pret.setAdherent(adherentTrouve);
                pret.setDocument(documentSelectionne);
                pret.setDatePret(LocalDate.now());
                pret.setDateRetourPrevue(LocalDate.now().plusWeeks(2)); // 2 semaines par défaut
                pret.setStatut("EN_COURS");

                pretDAO.create(pret);
                JOptionPane.showMessageDialog(this, "Emprunt enregistré avec succès !\nRetour prévu : " + pret.getDateRetourPrevue());

                // Remettre à zéro
                txtCinAdherent.setText("");
                txtDocument.setText("");
                adherentTrouve = null;
                documentSelectionne = null;}

             catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }else {
        	JOptionPane.showMessageDialog(this, "Erreur : Vous n'êtes pas autorisés a ajouter un emprunt \n Veuillez  saisir votre cin ", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}