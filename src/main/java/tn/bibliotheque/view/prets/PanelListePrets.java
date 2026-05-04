package tn.bibliotheque.view.prets;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.util.Date;

import tn.bibliotheque.dao.PretDAO;
import tn.bibliotheque.model.Adherent;
import tn.bibliotheque.model.Document;
import tn.bibliotheque.model.Pret;

public class PanelListePrets extends JPanel {
    private JTable table;
    private PretDAO pretDAO = new PretDAO();

    public PanelListePrets() {
        setLayout(new BorderLayout()); // BorderLayout est mieux pour les tableaux

        JLabel lbl = new JLabel("LISTE DES PRÊTS EN COURS", SwingConstants.CENTER);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lbl, BorderLayout.NORTH);

        table = new JTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnActualiser = new JButton("Actualiser la liste");
        add(btnActualiser, BorderLayout.SOUTH);

        btnActualiser.addActionListener(e -> chargerDonnees());
        
        if (!java.beans.Beans.isDesignTime()) {
            chargerDonnees();
        }
    }

    private void chargerDonnees() {
        String[] entetes = {"ID", "Adhérent", "Document", "Date Prêt", "Statut"};
        DefaultTableModel model = new DefaultTableModel(entetes, 0);
        
        // On récupère les données via Hibernate
        for (Pret p : pretDAO.getAll()) {
            model.addRow(new Object[]{
                p.getId(), 
                p.getAdherent().getNom(), 
                p.getDocument().getNomDoc(), 
                p.getDatePret(),
                p.getStatut()
            });
        }
        table.setModel(model);
    }
    
}