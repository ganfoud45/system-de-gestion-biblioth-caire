package tn.bibliotheque.view;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import tn.bibliotheque.view.adherent.PanelListeDocuments;
import tn.bibliotheque.view.adherent.PanelMesEmprunts;
import tn.bibliotheque.view.adherent.PanelRechercherDocument;
import tn.bibliotheque.model.Adherent;
import tn.bibliotheque.view.adherent.PanelEmprunterDocument;

public class AdherentAcces extends JFrame {
	private Adherent adherentConnecte;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private CardLayout cl;

    AdherentAcces(Adherent adherent) {  
        this.adherentConnecte = adherent;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 450);
        setTitle("Espace Adhérent");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // --- TITRE ---
        JLabel lblBonjour = new JLabel("Bonjour !");
        lblBonjour.setForeground(new Color(102, 51, 204));
        lblBonjour.setFont(new Font("Serif", Font.BOLD, 20));
        lblBonjour.setBounds(20, 15, 400, 35);
        contentPane.add(lblBonjour);

        // --- PANNEAU CONTENU (CardLayout) ---
        JPanel panelAction = new JPanel();
        panelAction.setBounds(188, 70, 780, 340);
        contentPane.add(panelAction);

        cl = new CardLayout();
        panelAction.setLayout(cl);

        // --- LES 3 PAGES ---
        PanelListeDocuments pnlListe = new PanelListeDocuments();
        panelAction.add(pnlListe, "pageListe");

        PanelRechercherDocument pnlRecherche = new PanelRechercherDocument();
        panelAction.add(pnlRecherche, "pageRecherche");

        PanelEmprunterDocument pnlEmprunter = new PanelEmprunterDocument();
        panelAction.add(pnlEmprunter, "pageEmprunter");

        // --- MENU GAUCHE ---
        JPanel panelMenu = new JPanel();
        panelMenu.setBounds(10, 70, 172, 340);
        panelMenu.setLayout(null);
        contentPane.add(panelMenu);

        JButton btnListe = new JButton("Liste des Documents");
        btnListe.setFont(new Font("Serif", Font.BOLD, 12));
        btnListe.setBounds(0, 30, 172, 50);
        btnListe.addActionListener(e -> cl.show(panelAction, "pageListe"));
        panelMenu.add(btnListe);

        JButton btnRecherche = new JButton("Rechercher un Document");
        btnRecherche.setFont(new Font("Serif", Font.BOLD, 12));
        btnRecherche.setBounds(0, 110, 172, 50);
        btnRecherche.addActionListener(e -> cl.show(panelAction, "pageRecherche"));
        panelMenu.add(btnRecherche);

        JButton btnEmprunter = new JButton("Emprunter un Document");
        btnEmprunter.setFont(new Font("Serif", Font.BOLD, 12));
        btnEmprunter.setBounds(0, 190, 172, 50);
        btnEmprunter.addActionListener(e -> cl.show(panelAction, "pageEmprunter"));
        panelMenu.add(btnEmprunter);

        // --- BOUTON DÉCONNEXION ---
        JButton btnDeconnexion = new JButton("Déconnexion");
        btnDeconnexion.setFont(new Font("Serif", Font.BOLD, 13));
        btnDeconnexion.setForeground(Color.RED);
        btnDeconnexion.setBounds(820, 15, 150, 30);
        btnDeconnexion.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> new Authentification().setVisible(true));
        });
        contentPane.add(btnDeconnexion);

        // Afficher la liste par défaut
        cl.show(panelAction, "pageListe");
        lblBonjour.setText("Bonjour, " + adherent.getNom() + " " + adherent.getPrenom() + " !");
          
        PanelMesEmprunts pnlEmprunts = new PanelMesEmprunts(adherentConnecte);
        panelAction.add(pnlEmprunts, "pageEmprunts");
        
     
        JButton btnEmprunts = new JButton("Mes Emprunts");
        btnEmprunts.setFont(new Font("Serif", Font.BOLD, 12));
        btnEmprunts.setBounds(0, 270, 172, 50);
        btnEmprunts.addActionListener(e -> cl.show(panelAction, "pageEmprunts"));
        panelMenu.add(btnEmprunts);
    
    }
}