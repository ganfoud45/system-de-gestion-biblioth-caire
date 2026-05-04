package tn.bibliotheque.view.adherent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import tn.bibliotheque.dao.DocumentDAO;
import tn.bibliotheque.model.Document;

public class PanelListeDocuments extends JPanel {

    private JTable table;
    private DocumentDAO docDAO = new DocumentDAO();

    public PanelListeDocuments() {
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel("LISTE DES DOCUMENTS DISPONIBLES", SwingConstants.CENTER);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        lbl.setForeground(new Color(102, 51, 204));
        add(lbl, BorderLayout.NORTH);

        table = new JTable();
        table.setRowHeight(22);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnActualiser = new JButton("Actualiser");
        add(btnActualiser, BorderLayout.SOUTH);
        btnActualiser.addActionListener(e -> chargerDonnees());

        chargerDonnees();
    }

    private void chargerDonnees() {
        String[] entetes = {"ID", "Titre", "Type", "Date Publication", "Exemplaires Dispo"};
        DefaultTableModel model = new DefaultTableModel(entetes, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        List<Document> docs = docDAO.getAll();
        for (Document d : docs) {
            model.addRow(new Object[]{
                d.getId(),
                d.getNomDoc(),
                d.getType(),
                d.getDatePub(),
                d.getNbExemplaire()
            });
        }
        table.setModel(model);
    }
}