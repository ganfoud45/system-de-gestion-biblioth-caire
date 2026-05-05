package tn.bibliotheque.view;
import tn.bibliotheque.controler.AdherentControleur;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.util.List;
import tn.bibliotheque.model.Adherent;
import tn.bibliotheque.dao.AdherentDAO;

public class GestionAdherents extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    // ── Attributs publics ──────────────────────────────────────────
    public JTable table;
    public DefaultTableModel tableModel;
    public JButton btnEnregistrer;
    public JButton btnSupprimer;
    public JButton btnActualiser;
    public JButton btnRetour;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	GestionAdherents vue = new GestionAdherents();
                	new AdherentControleur(vue);
                	vue.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GestionAdherents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 500);
        setTitle("Liste des Adhérents");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        // ── Titre ──────────────────────────────────────────────────
        JLabel lblTitre = new JLabel("Liste des Adhérents", SwingConstants.CENTER);
        lblTitre.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitre.setForeground(new Color(30, 144, 255));
        lblTitre.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        contentPane.add(lblTitre, BorderLayout.NORTH);

        // ── Table ──────────────────────────────────────────────────
        String[] colonnes = {"ID", "Nom", "Prénom", "Email", "Téléphone"};
        tableModel = new DefaultTableModel(colonnes, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return col != 0; // ID non éditable
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(30, 144, 255));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(225, 245, 238));
        table.setGridColor(new Color(220, 220, 220));

        // Largeur colonnes
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // ── Boutons ────────────────────────────────────────────────
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        btnActualiser = new JButton("Actualiser");
        btnActualiser.setBackground(new Color(30, 144, 255));
        btnActualiser.setForeground(Color.WHITE);

        btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setBackground(new Color(40, 167, 69));
        btnEnregistrer.setForeground(Color.WHITE);

        btnSupprimer = new JButton("Supprimer");
        btnSupprimer.setBackground(new Color(220, 53, 69));
        btnSupprimer.setForeground(Color.WHITE);
        
        btnRetour = new JButton("Retour");
        btnRetour.setBackground(new Color(120, 3, 69));
        btnRetour.setForeground(Color.WHITE);

        panelBoutons.add(btnActualiser);
        panelBoutons.add(btnEnregistrer);
        panelBoutons.add(btnSupprimer);
        panelBoutons.add(btnRetour);
        contentPane.add(panelBoutons, BorderLayout.SOUTH);

       
        btnRetour.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> new Authentification().setVisible(true));
        });
        
    }

    // ── Remplir la table ───────────────────────────────────────────
    public void remplirTable(List<Adherent> adherents) {
        tableModel.setRowCount(0);
        for (Adherent a : adherents) {
            tableModel.addRow(new Object[]{
                a.getId(),
                a.getNom(),
                a.getPrenom(),
                a.getEmail(),
                a.getNumTel()
            });
        }
    }

    
}