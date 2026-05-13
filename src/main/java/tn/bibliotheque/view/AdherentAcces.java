package tn.bibliotheque.view;

import tn.bibliotheque.view.DocumentsGestion.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import tn.bibliotheque.view.adherent.PanelListeDocuments;
import tn.bibliotheque.view.adherent.PanelMesEmprunts;
import tn.bibliotheque.view.adherent.PanelRechercherDocument;
import tn.bibliotheque.dao.DocumentDAO;
import tn.bibliotheque.model.Adherent;
import tn.bibliotheque.model.Dictionnaire;
import tn.bibliotheque.model.Document;
import tn.bibliotheque.model.Livre;
import tn.bibliotheque.model.Revue;
import tn.bibliotheque.model.These;
import tn.bibliotheque.view.adherent.PanelEmprunterDocument;

public class AdherentAcces extends JFrame {

    private Adherent adherentConnecte;
    private static final long serialVersionUID = 1L;
    private CardLayout cl;

    AdherentAcces(Adherent adherent) {
        this.adherentConnecte = adherent;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setTitle("Espace Adhérent");

        // ================================================================
        // PANNEAU PRINCIPAL : BorderLayout
        // ================================================================
        JPanel mainPane = new JPanel(new BorderLayout());
        mainPane.setBorder(new EmptyBorder(8, 8, 8, 8));
        setContentPane(mainPane);

        // ================================================================
        // NORTH : barre d'en-tête (bonjour | date | déconnexion)
        // ================================================================
        JPanel panelNorth = new JPanel(new BorderLayout(10, 0));
        panelNorth.setBorder(new EmptyBorder(0, 0, 8, 0));

        JLabel lblBonjour = new JLabel("Bonjour, " + adherent.getNom() + " " + adherent.getPrenom() + " !");
        lblBonjour.setForeground(new Color(102, 51, 204));
        lblBonjour.setFont(new Font("Serif", Font.BOLD, 20));
        panelNorth.add(lblBonjour, BorderLayout.WEST);

        String dateAujourdhui = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
        JLabel lblDate = new JLabel("Aujourd'hui : " + dateAujourdhui, SwingConstants.CENTER);
        lblDate.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDate.setForeground(new Color(0, 0, 139));
        panelNorth.add(lblDate, BorderLayout.CENTER);

        JButton btnDeconnexion = new JButton("Déconnexion");
        btnDeconnexion.setFont(new Font("Serif", Font.BOLD, 13));
        btnDeconnexion.setForeground(Color.RED);
        btnDeconnexion.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> new Authentification().setVisible(true));
        });
        panelNorth.add(btnDeconnexion, BorderLayout.EAST);

        mainPane.add(panelNorth, BorderLayout.NORTH);

        // ================================================================
        // CENTER : panneau de contenu (CardLayout)
        // ================================================================
        JPanel panelAction = new JPanel();
        cl = new CardLayout();
        panelAction.setLayout(cl);

        // --- Instanciation des panneaux de contenu ---
        AfficherDictionnaires panelAfficherDict = new AfficherDictionnaires(false);
        AfficherLivres        panelAfficherLiv  = new AfficherLivres(false);
        AfficherRevue         panelAfficherRev  = new AfficherRevue(false);
        AfficherTheses        panelAfficherThes = new AfficherTheses(false);
        PanelRechercherDocument pnlRecherche    = new PanelRechercherDocument();
        PanelListeDocuments     pnlListe        = new PanelListeDocuments();
        PanelEmprunterDocument  pnlEmprunter    = new PanelEmprunterDocument(adherent);
        PanelMesEmprunts        pnlEmprunts     = new PanelMesEmprunts(adherentConnecte);

        panelAction.add(pnlListe,          "pageListe");
        panelAction.add(panelAfficherDict, "AFFICHERDICT");
        panelAction.add(panelAfficherLiv,  "AFFICHERLIV");
        panelAction.add(panelAfficherRev,  "AFFICHERREV");
        panelAction.add(panelAfficherThes, "AFFICHERTHES");
        panelAction.add(pnlRecherche,      "pageRecherche");
        panelAction.add(pnlEmprunter,      "pageEmprunter");
        panelAction.add(pnlEmprunts,       "pageEmprunts");

        mainPane.add(panelAction, BorderLayout.CENTER);

        // ================================================================
        // WEST : menu de navigation
        // ================================================================
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
        panelMenu.setBorder(new EmptyBorder(10, 0, 10, 10));
        panelMenu.setPreferredSize(new Dimension(185, 0));

        // --- Bouton Afficher documents ---
        JButton btnAfficherDocument = creerBoutonMenu("Afficher Documents");
        btnAfficherDocument.addActionListener(e -> cl.show(panelAction, "pageListe"));

        // --- Bouton Rechercher (popup) ---
        JButton btnRechercher = creerBoutonMenu("Rechercher document");

        JPopupMenu popupRechercher = new JPopupMenu();
        JMenuItem mntmParId = new JMenuItem("Par Id");
        popupRechercher.add(mntmParId);

        JMenu RechercherLivre = new JMenu("Livre");
        JMenuItem mntmParMotsClé = new JMenuItem("Par mots clés");
        JMenuItem mntmParAuteur  = new JMenuItem("Par auteur");
        JMenuItem mntmParISBN    = new JMenuItem("Par ISBN");
        RechercherLivre.add(mntmParMotsClé);
        RechercherLivre.add(mntmParAuteur);
        RechercherLivre.add(mntmParISBN);
        popupRechercher.add(RechercherLivre);

        JMenuItem RechercherDict  = new JMenuItem("Dictionnaire");
        JMenuItem RechercherRevue = new JMenuItem("Revue");
        JMenuItem RechercherThese = new JMenuItem("Thèse");
        popupRechercher.add(RechercherDict);
        popupRechercher.add(RechercherRevue);
        popupRechercher.add(RechercherThese);

        btnRechercher.addActionListener(e ->
            popupRechercher.show(btnRechercher, 0, btnRechercher.getHeight())
        );

        // --- Bouton Emprunter ---
        JButton btnEmprunter = creerBoutonMenu("Emprunter un Document");
        btnEmprunter.addActionListener(e -> cl.show(panelAction, "pageEmprunter"));

        // --- Bouton Mes Emprunts ---
        JButton btnEmprunts = creerBoutonMenu("Mes Emprunts");
        btnEmprunts.addActionListener(e -> cl.show(panelAction, "pageEmprunts"));

        // Ajout dans le menu avec espacement
        panelMenu.add(Box.createVerticalStrut(10));
        panelMenu.add(btnAfficherDocument);
        panelMenu.add(Box.createVerticalStrut(10));
        panelMenu.add(btnRechercher);
        panelMenu.add(Box.createVerticalStrut(10));
        panelMenu.add(btnEmprunter);
        panelMenu.add(Box.createVerticalStrut(10));
        panelMenu.add(btnEmprunts);
        panelMenu.add(Box.createVerticalGlue()); // pousse tout vers le haut

        mainPane.add(panelMenu, BorderLayout.WEST);

        // ================================================================
        // Afficher la page par défaut
        // ================================================================
        cl.show(panelAction, "pageListe");

        // ================================================================
        // LISTENERS DES ITEMS DE RECHERCHE
        // ================================================================
        mntmParId.addActionListener(e -> {
            String idText = JOptionPane.showInputDialog("Entrer l'id du document");
            if (idText == null || idText.trim().isEmpty()) return;
            try {
                int id = Integer.parseInt(idText.trim());
                DocumentDAO dao = new DocumentDAO();
                Document doc = dao.findById(id);
                if (doc instanceof Livre) {
                    panelAfficherLiv.remplirTable(Collections.singletonList(doc));
                    cl.show(panelAction, "AFFICHERLIV");
                } else if (doc instanceof Dictionnaire) {
                    panelAfficherDict.remplirTable(Collections.singletonList(doc));
                    cl.show(panelAction, "AFFICHERDICT");
                } else if (doc instanceof Revue) {
                    panelAfficherRev.remplirTable(Collections.singletonList(doc));
                    cl.show(panelAction, "AFFICHERREV");
                } else if (doc instanceof These) {
                    panelAfficherThes.remplirTable(Collections.singletonList(doc));
                    cl.show(panelAction, "AFFICHERTHES");
                } else {
                    JOptionPane.showMessageDialog(null, "Aucun document trouvé");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Id invalide");
            }
        });

        mntmParMotsClé.addActionListener(e -> {
            String mots = JOptionPane.showInputDialog("Entrer les mots clés :");
            if (mots == null || mots.trim().isEmpty()) return;
            List<Document> res = new DocumentDAO().rechercherParMotsCle(mots);
            if (res != null && !res.isEmpty()) {
                panelAfficherLiv.remplirTable(res);
                cl.show(panelAction, "AFFICHERLIV");
            } else {
                JOptionPane.showMessageDialog(null, "Aucun document trouvé.");
            }
        });

        mntmParAuteur.addActionListener(e -> {
            String auteur = JOptionPane.showInputDialog("Entrer l'auteur :");
            if (auteur == null || auteur.trim().isEmpty()) return;
            List<Document> res = new DocumentDAO().rechercherParAuteur(auteur);
            if (res != null && !res.isEmpty()) {
                panelAfficherLiv.remplirTable(res);
                cl.show(panelAction, "AFFICHERLIV");
            } else {
                JOptionPane.showMessageDialog(null, "Aucun document trouvé.");
            }
        });

        mntmParISBN.addActionListener(e -> {
            String isbn = JOptionPane.showInputDialog("Entrer l'ISBN :");
            if (isbn == null || isbn.trim().isEmpty()) return;
            Document res = new DocumentDAO().rechercherParISBN(isbn);
            if (res != null) {
                panelAfficherLiv.remplirTable(Collections.singletonList(res));
                cl.show(panelAction, "AFFICHERLIV");
            } else {
                JOptionPane.showMessageDialog(null, "Aucun document trouvé.");
            }
        });

        RechercherDict.addActionListener(e -> {
            String lgSource = JOptionPane.showInputDialog("Entrer la langue source");
            String lgCible  = JOptionPane.showInputDialog("Entrer la langue cible");
            if (lgSource == null || lgCible == null) return;
            List<Document> resultat = new DocumentDAO().rechercherParLangue(lgSource, lgCible);
            if (!resultat.isEmpty()) {
                panelAfficherDict.remplirTable(resultat);
                cl.show(panelAction, "AFFICHERDICT");
            } else {
                JOptionPane.showMessageDialog(null, "Aucun dictionnaire trouvé");
            }
        });

        RechercherRevue.addActionListener(e -> {
            String issn = JOptionPane.showInputDialog("Entrer ISSN");
            if (issn == null || issn.trim().isEmpty()) return;
            Document res = new DocumentDAO().rechercherParISSN(issn);
            if (res != null) {
                panelAfficherRev.remplirTable(Collections.singletonList(res));
                cl.show(panelAction, "AFFICHERREV");
            } else {
                JOptionPane.showMessageDialog(null, "Aucune revue trouvée");
            }
        });

        RechercherThese.addActionListener(e -> {
            String domaine = JOptionPane.showInputDialog("Entrer domaine de soutenance");
            if (domaine == null || domaine.trim().isEmpty()) return;
            List<Document> resultat = new DocumentDAO().rechercherParDomaineSoutenance(domaine);
            if (!resultat.isEmpty()) {
                panelAfficherThes.remplirTable(resultat);
                cl.show(panelAction, "AFFICHERTHES");
            } else {
                JOptionPane.showMessageDialog(null, "Aucune thèse trouvée");
            }
        });
    }

    /**
     * Crée un bouton de menu avec un style uniforme et une largeur maximale.
     */
    private JButton creerBoutonMenu(String texte) {
        JButton btn = new JButton(texte);
        btn.setFont(new Font("Serif", Font.BOLD, 12));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }
}