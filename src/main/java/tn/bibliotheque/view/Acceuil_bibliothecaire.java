package tn.bibliotheque.view;

import tn.bibliotheque.controler.AcceuilControleur;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Acceuil_bibliothecaire extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    // ── Attributs publics 
    public JMenuItem mntmAjouter;
    public JMenuItem mntmAfficherTous;
    public JMenuItem mntmRechercherAdh;
    public JMenuItem mntmSupprimerAdh;
    public JMenuItem mntmAjouterDoc;
    public JMenuItem mntmAfficherDoc;
    public JMenuItem mntmRechercherDoc;
    public JMenuItem mntmSupprimerDoc;
    public JMenuItem mntmAfficherTous_1;
    public JMenuItem mntmAjouterPret;
    public JMenuItem mntmAnnulerPret;
    public JMenuItem mntmModifierPret;
    public JMenuItem mntmQuitter;


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Acceuil_bibliothecaire vue = new Acceuil_bibliothecaire();
                new AcceuilControleur(vue);
                vue.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Acceuil_bibliothecaire() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 500);
        setTitle("Accueil Bibliothécaire");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null); // layout libre
        setContentPane(contentPane);

        // ── Label Bienvenue ────────────────────────────────────────
        JLabel lblBienvenue = new JLabel("Bienvenue dans votre espace Bibliothécaire", SwingConstants.CENTER);
        lblBienvenue.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
        lblBienvenue.setForeground(new Color(30, 144, 255));
        lblBienvenue.setBounds(50, 150, 650, 50);
        contentPane.add(lblBienvenue);

        // ── Date ───────────────────────────────────────────────────
        String dateAujourdhui = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
        JLabel lblDate = new JLabel("Aujourd'hui : " + dateAujourdhui, SwingConstants.RIGHT);
        lblDate.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDate.setForeground(new Color(0, 0, 139));
        lblDate.setBounds(400, 10, 300, 30);
        contentPane.add(lblDate);

        // ── MenuBar ────────────────────────────────────────────────
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // ===== ADHERENTS =====
        JMenu mnAdherents = new JMenu("Adherents");
        menuBar.add(mnAdherents);

        mntmAjouter = new JMenuItem("Ajouter Adh");
        mnAdherents.add(mntmAjouter);

        mntmAfficherTous = new JMenuItem("Afficher tous");
        mnAdherents.add(mntmAfficherTous);

        mntmRechercherAdh = new JMenuItem("Rechercher Adh");
        mnAdherents.add(mntmRechercherAdh);

        mntmSupprimerAdh = new JMenuItem("Supprimer Adh");
        mnAdherents.add(mntmSupprimerAdh);

        // ===== DOCUMENTS =====
        JMenu mnDocuments = new JMenu("Documents");
        menuBar.add(mnDocuments);

        mntmAjouterDoc = new JMenuItem("Ajouter doc");
        mnDocuments.add(mntmAjouterDoc);

        mntmAfficherDoc = new JMenuItem("Afficher doc");
        mnDocuments.add(mntmAfficherDoc);

        mntmRechercherDoc = new JMenuItem("Rechercher doc");
        mnDocuments.add(mntmRechercherDoc);

        mntmSupprimerDoc = new JMenuItem("Supprimer doc");
        mnDocuments.add(mntmSupprimerDoc);

        // ===== PRETS =====
        JMenu mnPrets = new JMenu("Prets");
        menuBar.add(mnPrets);

        mntmAfficherTous_1 = new JMenuItem("Afficher tous");
        mnPrets.add(mntmAfficherTous_1);

        mntmAjouterPret = new JMenuItem("Ajouter pret");
        mnPrets.add(mntmAjouterPret);

        mntmAnnulerPret = new JMenuItem("Annuler pret");
        mnPrets.add(mntmAnnulerPret);

        mntmModifierPret = new JMenuItem("Modifier pret");
        mnPrets.add(mntmModifierPret);

        // ===== QUITTER =====
        JMenu mnQuitter = new JMenu("Quitter");
        menuBar.add(mnQuitter);

        mntmQuitter = new JMenuItem("Quitter");
        mnQuitter.add(mntmQuitter);
    }
}