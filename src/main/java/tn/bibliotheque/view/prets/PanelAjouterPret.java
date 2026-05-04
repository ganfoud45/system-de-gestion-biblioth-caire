package tn.bibliotheque.view.prets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;


import tn.bibliotheque.dao.PretDAO;
import tn.bibliotheque.model.Adherent;
import tn.bibliotheque.model.Document;
import tn.bibliotheque.model.Pret;

public class PanelAjouterPret extends JPanel {
    
    // Composants UI
    private JTextField txtAdherent;
    private JTextField txtDocument;
    
    // Données sélectionnées (on stocke les objets complets)
    private Adherent adherentSelectionne;
    private Document documentSelectionne;
    
    // DAO pour l'insertion en base
    private PretDAO pretDAO = new PretDAO();

    public PanelAjouterPret() {
        // On utilise Absolute Layout à l'intérieur de ce panel
        setLayout(null);
        setBackground(Color.WHITE); // Pour bien le distinguer

        // Titre du formulaire
        JLabel lblTitre = new JLabel("ENREGISTRER UN NOUVEAU PRÊT");
        lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitre.setFont(new Font("Serif", Font.BOLD, 18));
        lblTitre.setBounds(10, 25, 580, 30);
        add(lblTitre);

        // --- PARTIE ADHÉRENT ---
        JLabel lblAdherent = new JLabel("Adhérent :");
        lblAdherent.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblAdherent.setBounds(50, 100, 100, 25);
        add(lblAdherent);

        txtAdherent = new JTextField();
        txtAdherent.setEditable(false); // Sécurité : l'utilisateur ne tape pas au clavier
        txtAdherent.setBounds(150, 100, 250, 25);
        add(txtAdherent);

        JButton btnChoisirAdh = new JButton("Choisir...");
        btnChoisirAdh.setBounds(410, 100, 100, 25);
        add(btnChoisirAdh);

        // --- PARTIE DOCUMENT ---
        JLabel lblDocument = new JLabel("Document :");
        lblDocument.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblDocument.setBounds(50, 160, 100, 25);
        add(lblDocument);

        txtDocument = new JTextField();
        txtDocument.setEditable(false); // Sécurité
        txtDocument.setBounds(150, 160, 250, 25);
        add(txtDocument);

        JButton btnChoisirDoc = new JButton("Choisir...");
        btnChoisirDoc.setBounds(410, 160, 100, 25);
        add(btnChoisirDoc);

        // --- BOUTON DE VALIDATION FINALE ---
        JButton btnEnregistrer = new JButton("VALIDER LE PRÊT");
        btnEnregistrer.setFont(new Font("Tahoma", Font.BOLD, 13));
       
        btnEnregistrer.setBounds(200, 250, 200, 40);
        add(btnEnregistrer);

        // ===========================================================
        // GESTION DES ÉVÉNEMENTS (ACTIONS)
        // ===========================================================

        // Action pour choisir l'Adhérent
        btnChoisirAdh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // On ouvre la JDialog de sélection d'adhérent
                SelectionAdherentDialog dialog = new SelectionAdherentDialog();
                dialog.setModal(true);
                dialog.setVisible(true);
                
                // Après fermeture, on récupère l'objet sélectionné
                if (dialog.getAdherentSelectionne() != null) {
                    adherentSelectionne = dialog.getAdherentSelectionne();
                    txtAdherent.setText(adherentSelectionne.getNom() + " " + adherentSelectionne.getPrenom());
                }
            }
        });

        // Action pour choisir le Document
        btnChoisirDoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // On ouvre la JDialog de sélection de document
                SelectionDocumentDialog dialog = new SelectionDocumentDialog();
                dialog.setModal(true);
                dialog.setVisible(true);
                
                if (dialog.getDocumentSelectionne() != null) {
                    documentSelectionne = dialog.getDocumentSelectionne();
                    txtDocument.setText(documentSelectionne.getNomDoc());
                }
            }
        });

        // Action pour enregistrer en base de données via Hibernate
        btnEnregistrer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enregistrerPret();
            }
        });
    }

    private void enregistrerPret() {
        // 1. Vérification des champs
        if (adherentSelectionne == null || documentSelectionne == null) {
            JOptionPane.showMessageDialog(this, "Erreur : Veuillez sélectionner un adhérent ET un document.", "Champs vides", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Vérification de la disponibilité (Optionnel mais conseillé)
        if (documentSelectionne.getNbExemplaire() <= 0) {
            JOptionPane.showMessageDialog(this, "Ce document n'a plus d'exemplaires disponibles.", "Stock épuisé", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // 3. Création de l'objet Pret
            Pret nouveauPret = new Pret();
            nouveauPret.setAdherent(adherentSelectionne);
            nouveauPret.setDocument(documentSelectionne);
            nouveauPret.setDatePret( LocalDate.now()); // Date du jour
            nouveauPret.setDateRetourPrevue(LocalDate.now().plusWeeks(2));
            nouveauPret.setStatut("EN_COURS");

            // 4. Appel au DAO (Ta méthode create gère Hibernate et le stock -1)
            pretDAO.create(nouveauPret);

            // 5. Succès et remise à zéro
            JOptionPane.showMessageDialog(this, "Le prêt a été enregistré avec succès !");
            viderChamps();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement : " + ex.getMessage());
        }
    }

    private void viderChamps() {
        txtAdherent.setText("");
        txtDocument.setText("");
        adherentSelectionne = null;
        documentSelectionne = null;
    }
}