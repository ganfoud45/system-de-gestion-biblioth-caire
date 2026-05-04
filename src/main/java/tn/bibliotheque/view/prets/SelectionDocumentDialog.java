package tn.bibliotheque.view.prets;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import tn.bibliotheque.dao.DocumentDAO;
import tn.bibliotheque.model.Document;

public class SelectionDocumentDialog extends JDialog {
    private JTable table;
    private Document documentSelectionne;
    private DocumentDAO docDAO = new DocumentDAO();

    public SelectionDocumentDialog() {
        setTitle("Sélectionner un Document");
        setBounds(100, 100, 500, 350);
        getContentPane().setLayout(new BorderLayout());

        // 1. Le tableau
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // 2. Charger les données depuis Hibernate
        chargerDonnees();

        // 3. Bouton Valider
        JButton btnValider = new JButton("Valider la sélection");
        getContentPane().add(btnValider, BorderLayout.SOUTH);

        btnValider.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                // On récupère l'objet Document correspondant à la ligne
                List<Document> liste = docDAO.getAll();
                documentSelectionne = liste.get(row);
                dispose(); // Ferme la fenêtre
            }
        });
    }

    private void chargerDonnees() {
        String[] colonnes = {"ID", "Titre", "Auteur", "Exemplaires"};
        DefaultTableModel model = new DefaultTableModel(colonnes, 0);
        
        List<Document> documents = docDAO.getAll();
        for (Document d : documents) {
            Object[] row = {d.getId(), d.getNomDoc(), d.getNbExemplaire()};
            model.addRow(row);
        }
        table.setModel(model);
    }

    public Document getDocumentSelectionne() {
        return documentSelectionne;
    }
}