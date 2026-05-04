package tn.bibliotheque.view.prets;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import tn.bibliotheque.dao.AdherentDAO;
import tn.bibliotheque.model.Adherent;

public class SelectionAdherentDialog extends JDialog {
    private JTable table;
    private Adherent adherentSelectionne;
    private AdherentDAO adhDAO = new AdherentDAO();

    public SelectionAdherentDialog() {
        setTitle("Choisir un Adhérent");
        setModal(true); // Bloque la fenêtre d'ajout tant qu'on n'a pas choisi
        setBounds(150, 150, 550, 400);
        getContentPane().setLayout(new BorderLayout());

        // 1. Création du tableau avec défilement
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // 2. Chargement des adhérents depuis la base de données
        chargerDonnees();

        // 3. Panel de boutons en bas
        JPanel buttonPane = new JPanel();
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnValider = new JButton("Valider la sélection");
        btnValider.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // On récupère la liste complète pour retrouver l'objet
                List<Adherent> liste = adhDAO.getAll(); 
                adherentSelectionne = liste.get(selectedRow);
                dispose(); // Fermer la fenêtre
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne !");
            }
        });
        buttonPane.add(btnValider);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(e -> dispose());
        buttonPane.add(btnAnnuler);
    }

    private void chargerDonnees() {
        // Définition des colonnes du tableau
        String[] colonnes = {"ID", "NOM", "PRÉNOM", "CIN", "EMAIL"};
        DefaultTableModel model = new DefaultTableModel(colonnes, 0);
        
        try {
            List<Adherent> adherents = adhDAO.getAll();
            for (Adherent a : adherents) {
                Object[] ligne = {
                    a.getId(), 
                    a.getNom(), 
                    a.getPrenom(), 
                    a.getCin(), 
                    a.getEmail()
                };
                model.addRow(ligne);
            }
            table.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur de chargement : " + e.getMessage());
        }
    }

    // Le getter utilisé par ton PanelAjouterPret
    public Adherent getAdherentSelectionne() {
        return adherentSelectionne;
    }
}