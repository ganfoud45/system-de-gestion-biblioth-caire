package tn.bibliotheque.view.adherent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import tn.bibliotheque.dao.PretDAO;
import tn.bibliotheque.model.Adherent;
import tn.bibliotheque.model.Pret;

public class PanelMesEmprunts extends JPanel {

    private JTable table;
    private PretDAO pretDAO = new PretDAO();
    private Adherent adherent;

    public PanelMesEmprunts(Adherent adherent) {
        this.adherent = adherent;
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel("MES EMPRUNTS", SwingConstants.CENTER);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        lbl.setForeground(new Color(102, 51, 204));
        add(lbl, BorderLayout.NORTH);

        table = new JTable();
        table.setRowHeight(22);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel bas avec bouton actualiser et statistiques
        JPanel panelBas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JButton btnActualiser = new JButton("Actualiser");
        btnActualiser.addActionListener(e -> chargerDonnees());
        panelBas.add(btnActualiser);
        
        JLabel lblStats = new JLabel("");
        lblStats.setFont(new Font("Tahoma", Font.ITALIC, 12));
        panelBas.add(lblStats);
        
        add(panelBas, BorderLayout.SOUTH);

        if (!java.beans.Beans.isDesignTime()) {
            chargerDonnees();
        }
    }

    private void chargerDonnees() {
        String[] entetes = {"ID Prêt", "Document", "Date Prêt", "Retour Prévu", "Statut"};
        DefaultTableModel model = new DefaultTableModel(entetes, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        try {
            List<Pret> prets = pretDAO.findByAdherent(adherent);
            for (Pret p : prets) {
                // Couleur selon statut
                model.addRow(new Object[]{
                    p.getId(),
                    p.getDocument().getNomDoc(),
                    p.getDatePret(),
                    p.getDateRetourPrevue(),
                    p.getStatut()
                });
            }
            table.setModel(model);
            table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
                @Override
                public java.awt.Component getTableCellRendererComponent(
                        JTable t, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                    super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, col);
                    String statut = (String) t.getValueAt(row, 4);
                    if (!isSelected) {
                        if ("EN_RETARD".equals(statut)) {
                            setBackground(new Color(255, 200, 200)); // Rouge clair
                        } else if ("RETOURNE".equals(statut)) {
                            setBackground(new Color(200, 255, 200)); // Vert clair
                        } else {
                            setBackground(Color.WHITE);
                        }
                    }
                    return this;
                }
            });

          

           

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur de chargement : " + e.getMessage());
        }
    }
}