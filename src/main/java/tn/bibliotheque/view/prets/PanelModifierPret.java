package tn.bibliotheque.view.prets;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.util.Date;

import tn.bibliotheque.dao.PretDAO;
import tn.bibliotheque.model.Adherent;
import tn.bibliotheque.model.Document;
import tn.bibliotheque.model.Pret;


public class PanelModifierPret extends JPanel {
    private JTextField txtIdRecherche;
    private JTextField txtStatut;
    private Pret pretEnCours;
    private PretDAO pretDAO = new PretDAO();

    public PanelModifierPret() {
        setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblTitre = new JLabel("MODIFIER UN PRÊT");
        lblTitre.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitre.setBounds(50, 20, 250, 30);
        add(lblTitre);

        // --- ÉTAPE 1 : RECHERCHE ---
        JLabel lblId = new JLabel("ID du Prêt à modifier :");
        lblId.setBounds(50, 70, 150, 25);
        add(lblId);

        txtIdRecherche = new JTextField();
        txtIdRecherche.setBounds(200, 70, 100, 25);
        add(txtIdRecherche);

        JButton btnChercher = new JButton("Chercher");
        btnChercher.setBounds(310, 70, 100, 25);
        add(btnChercher);

        // --- ÉTAPE 2 : FORMULAIRE DE MODIFICATION (Masqué au début) ---
        JSeparator separator = new JSeparator();
        separator.setBounds(50, 120, 500, 2);
        add(separator);

        JLabel lblInfo = new JLabel("Statut actuel :");
        lblInfo.setBounds(50, 150, 100, 25);
        add(lblInfo);

        txtStatut = new JTextField();
        txtStatut.setBounds(150, 150, 150, 25);
        add(txtStatut);

        JButton btnEnregistrer = new JButton("Mettre à jour le statut");
        btnEnregistrer.setBounds(150, 210, 180, 35);
        add(btnEnregistrer);

        // ==========================================
        // LOGIQUE DES BOUTONS
        // ==========================================

        // 1. Bouton CHERCHER
        btnChercher.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdRecherche.getText());
                pretEnCours = pretDAO.findById(id);
                
                if (pretEnCours != null) {
                    txtStatut.setText(pretEnCours.getStatut());
                    JOptionPane.showMessageDialog(this, "Prêt trouvé : " + 
                        pretEnCours.getDocument().getNomDoc() + " par " + 
                        pretEnCours.getAdherent().getNom());
                } else {
                    JOptionPane.showMessageDialog(this, "Aucun prêt trouvé avec cet ID.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Veuillez saisir un ID valide.");
            }
        });

        // 2. Bouton ENREGISTRER
        btnEnregistrer.addActionListener(e -> {
            if (pretEnCours == null) {
                JOptionPane.showMessageDialog(this, "Veuillez d'abord chercher un prêt !");
                return;
            }

            // On met à jour l'objet avec la nouvelle valeur du champ
            pretEnCours.setStatut(txtStatut.getText());
            
            // On appelle le DAO pour sauvegarder en base via Hibernate
            pretDAO.update(pretEnCours);

            JOptionPane.showMessageDialog(this, "Mise à jour réussie !");
        });
    }
}