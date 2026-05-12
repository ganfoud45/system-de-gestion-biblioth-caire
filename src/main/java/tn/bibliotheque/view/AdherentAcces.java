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
    	AfficherDictionnaires panelAfficherDict = new AfficherDictionnaires();
		AfficherLivres        panelAfficherLiv  = new AfficherLivres();
		AfficherRevue         panelAfficherRev  = new AfficherRevue();
		AfficherTheses        panelAfficherThes = new AfficherTheses();
        PanelRechercherDocument pnlRecherche = new PanelRechercherDocument();
        PanelListeDocuments pnlListe = new PanelListeDocuments();
        panelAction.add(pnlListe, "pageListe");
        panelAction.add(panelAfficherDict,  "AFFICHERDICT");
        panelAction.add(panelAfficherLiv,   "AFFICHERLIV");
		panelAction.add(panelAfficherRev,   "AFFICHERREV");
		panelAction.add(panelAfficherThes,  "AFFICHERTHES");
        panelAction.add(pnlRecherche, "pageRecherche");

        PanelEmprunterDocument pnlEmprunter = new PanelEmprunterDocument();
        panelAction.add(pnlEmprunter, "pageEmprunter");

        // --- MENU GAUCHE ---
        JPanel panelMenu = new JPanel();
        panelMenu.setBounds(10, 70, 172, 340);
        panelMenu.setLayout(null);
        contentPane.add(panelMenu);
        JButton btnRechercher = new JButton("Rechercher document");
        btnRechercher.setFont(new Font("Serif", Font.BOLD, 12));
		btnRechercher.setBounds(0, 115, 172, 44);
		panelMenu.add(btnRechercher);

		JPopupMenu popupRechercher = new JPopupMenu();

		JMenuItem mntmParId = new JMenuItem("Par Id");
		popupRechercher.add(mntmParId);

		JMenu RechercherLivre = new JMenu("Livre");
		JMenuItem mntmParMotsClé = new JMenuItem("Par mots clés");
		JMenuItem mntmParAuteur = new JMenuItem("Par auteur");
		JMenuItem mntmParISBN = new JMenuItem("Par ISBN");
		RechercherLivre.add(mntmParMotsClé);
		RechercherLivre.add(mntmParAuteur);
		RechercherLivre.add(mntmParISBN);
		popupRechercher.add(RechercherLivre);

		JMenuItem RechercherDict = new JMenuItem("Dictionnaire");
		JMenuItem RechercherRevue = new JMenuItem("Revue");
		JMenuItem RechercherThese = new JMenuItem("These");
		popupRechercher.add(RechercherDict);
		popupRechercher.add(RechercherRevue);
		popupRechercher.add(RechercherThese);

		btnRechercher.addActionListener(e ->
		    popupRechercher.show(btnRechercher, 0, btnRechercher.getHeight())
		);

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
        
        JButton btnAfficherDocument = new JButton("Afficher Documents");
        btnAfficherDocument.addActionListener(e->cl.show(panelAction, "pageListe"));

        btnAfficherDocument.setFont(new Font("Serif", Font.BOLD, 12));
        btnAfficherDocument.setBounds(0, 42, 172, 44);
        panelMenu.add(btnAfficherDocument);
        String dateAujourdhui = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
        JLabel lblDate = new JLabel("Aujourd'hui : " + dateAujourdhui, SwingConstants.RIGHT);
        lblDate.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDate.setForeground(new Color(0, 0, 139));
        lblDate.setBounds(431, 20, 300, 30);
        contentPane.add(lblDate);

		mntmParId.addActionListener(e -> {
		    String idText = JOptionPane.showInputDialog("Entrer l'id du document");
		    if (idText != null && !idText.trim().isEmpty()) {
		        try {
		            int id = Integer.parseInt(idText);
		            DocumentDAO dao = new DocumentDAO();
		            Document doc = dao.findById(id);
		            if (doc != null) {
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
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Aucun document trouvé");
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Id invalide");
		        }
		    }
		    else {
		    	if(idText.isEmpty()) {
		    		
		    		cl.show(panelAction, "pageListe");
		    	}
		    }
		});

		mntmParMotsClé.addActionListener(e -> {
		    String mots = JOptionPane.showInputDialog("Entrer les mots clés :");
		    if (mots != null && !mots.trim().isEmpty()) {
		        List<Document> res = new DocumentDAO().rechercherParMotsCle(mots);
		        if (res != null && !res.isEmpty()) {
		            panelAfficherLiv.remplirTable(res);
		            cl.show(panelAction, "AFFICHERLIV");
		        } else {
		            JOptionPane.showMessageDialog(null, "Aucun document trouvé.");
		        }
		    }
		});

		mntmParAuteur.addActionListener(e -> {
		    String auteur = JOptionPane.showInputDialog("Entrer l'auteur :");
		    if (auteur != null && !auteur.trim().isEmpty()) {
		        List<Document> res = new DocumentDAO().rechercherParAuteur(auteur);
		        if (res != null && !res.isEmpty()) {
		            panelAfficherLiv.remplirTable(res);
		            cl.show(panelAction, "AFFICHERLIV");
		        } else {
		            JOptionPane.showMessageDialog(null, "Aucun document trouvé.");
		        }
		    }
		});

		mntmParISBN.addActionListener(e -> {
		    String isbn = JOptionPane.showInputDialog("Entrer l'ISBN :");
		    if (isbn != null && !isbn.trim().isEmpty()) {
		        Document res = new DocumentDAO().rechercherParISBN(isbn);
		        if (res != null) {
		            panelAfficherLiv.remplirTable(Collections.singletonList(res));
		            cl.show(panelAction, "AFFICHERLIV");
		        } else {
		            JOptionPane.showMessageDialog(null, "Aucun document trouvé.");
		        }
		    }
		});

		RechercherDict.addActionListener(e -> {
		    String lgSource = JOptionPane.showInputDialog("Entrer la langue source");
		    String lgCible  = JOptionPane.showInputDialog("Entrer la langue cible");
		    if (lgSource != null && lgCible != null) {
		        List<Document> resultat = new DocumentDAO().rechercherParLangue(lgSource, lgCible);
		        if (!resultat.isEmpty()) {
		            panelAfficherDict.remplirTable(resultat);
		            cl.show(panelAction, "AFFICHERDICT"); 
		        } else {
		            JOptionPane.showMessageDialog(null, "Aucun dictionnaire trouvé");
		        }
		    }
		});

		RechercherRevue.addActionListener(e -> {
		    String issn = JOptionPane.showInputDialog("Entrer ISSN");
		    if (issn != null && !issn.trim().isEmpty()) {
		        Document res = new DocumentDAO().rechercherParISSN(issn);
		        if (res != null) {
		            panelAfficherRev.remplirTable(Collections.singletonList(res));
		            cl.show(panelAction, "AFFICHERREV");
		        } else {
		            JOptionPane.showMessageDialog(null, "Aucune revue trouvée");
		        }
		    }
		});

		RechercherThese.addActionListener(e -> {
		    String domaine = JOptionPane.showInputDialog("Entrer domaine de soutenance");
		    if (domaine != null && !domaine.trim().isEmpty()) {
		        List<Document> resultat = new DocumentDAO().rechercherParDomaineSoutenance(domaine);
		        if (!resultat.isEmpty()) {
		            panelAfficherThes.remplirTable(resultat);
		            cl.show(panelAction, "AFFICHERTHES");
		        } else {
		            JOptionPane.showMessageDialog(null, "Aucune thèse trouvée");
		        }
		    }
		});
    
    }
}