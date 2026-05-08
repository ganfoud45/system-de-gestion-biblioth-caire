package tn.bibliotheque.view.adherent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import tn.bibliotheque.dao.DocumentDAO;
import tn.bibliotheque.model.Document;

public class PanelRechercherDocument extends JPanel {

    private JTable table;
    private JTextField txtRecherche;
    private JComboBox<String> comboType;
    private DocumentDAO docDAO = new DocumentDAO();

    public PanelRechercherDocument() {
        setLayout(new BorderLayout());

        // --- BARRE DE RECHERCHE (en haut) ---
        JPanel panelRecherche = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblTitre = new JLabel("Rechercher :");
        lblTitre.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelRecherche.add(lblTitre);

        txtRecherche = new JTextField(20);
        panelRecherche.add(txtRecherche);

        comboType = new JComboBox<>(new String[]{"Par Titre", "Par Auteur", "Par Mots-clés", "Par ISBN"});
        panelRecherche.add(comboType);

        JButton btnChercher = new JButton("Chercher");
        btnChercher.setBackground(new Color(102, 51, 204));
        btnChercher.setForeground(Color.WHITE);
        panelRecherche.add(btnChercher);

        JButton btnReset = new JButton("Tout afficher");
        panelRecherche.add(btnReset);

        add(panelRecherche, BorderLayout.NORTH);

        // --- TABLEAU DE RÉSULTATS ---
        table = new JTable();
        table.setRowHeight(22);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- ACTIONS ---
        btnChercher.addActionListener(e -> effectuerRecherche());
        btnReset.addActionListener(e -> afficherTous());
        txtRecherche.addActionListener(e -> effectuerRecherche()); // Touche Entrée

        afficherTous();
    }

    private void effectuerRecherche() {
        String terme = txtRecherche.getText().trim();
        if (terme.isEmpty()) {
            afficherTous();
            return;
        }
        List<Document> resultats;
        
        String typeRecherche = (String) comboType.getSelectedItem();

        switch (typeRecherche) {
            case "Par Auteur":
                resultats = docDAO.rechercherParAuteur(terme);
                break;
            case "Par Mots-clés":
                resultats = docDAO.rechercherParMotsCle(terme);
                break;
            case "Par ISBN":
                Document d = docDAO.rechercherParISBN(terme);
                resultats = (d != null) ? List.of(d) : List.of();
                break;
            default: // Par Titre
                resultats = docDAO.rechercherParNom(terme);
                break;
        }

        afficherResultats(resultats);
    }

    private void afficherTous() {
        afficherResultats(docDAO.getAll());
    }

    private void afficherResultats(List<Document> docs) {
        String[] entetes = {"ID", "Titre", "Type", "Date Publication", "Exemplaires"};
        DefaultTableModel model = new DefaultTableModel(entetes, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

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

        if (docs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucun document trouvé.");
        }
    }
}