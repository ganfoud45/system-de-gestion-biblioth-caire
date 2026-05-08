package tn.bibliotheque.view.DocumentsGestion;

import tn.bibliotheque.dao.DocumentDAO;
import tn.bibliotheque.model.*;
import tn.bibliotheque.view.Authentification;
import tn.bibliotheque.view.adherentsGestion.AfficherAdherentsPanel;
import tn.bibliotheque.view.adherentsGestion.SupprimerAdherentPanel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.CardLayout;
import java.util.Collections;
import java.util.List;

public class DocumentGestionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
    protected CardLayout cardLayout ;
    protected JPanel conteneur;

	/**
	 * Create the panel.
	 */
	public DocumentGestionPanel() {
		setBackground(new Color(245, 255, 250));
		setLayout(null);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(12, 12, 679, 542);
		splitPane.setDividerLocation(200);
		add(splitPane);
		
		//-------------------------------création de panel acceuil------------------------------------------------
		JPanel Acceuil = new JPanel();
		Acceuil.setLayout(null);
		
		JLabel lblBienvenueDansVotre = new JLabel("Bienvenue dans votre bibliothéque !");
		lblBienvenueDansVotre.setFont(new Font("Lato Hairline", Font.BOLD, 20));
		lblBienvenueDansVotre.setBounds(57, 248, 360, 71);
		Acceuil.add(lblBienvenueDansVotre);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		JLabel lblDateTime = new JLabel(LocalDateTime.now().format(formatter));
		lblDateTime.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 15));
		lblDateTime.setSize(161, 38);
		lblDateTime.setLocation(295, 12);
		Acceuil.add(lblDateTime);

		// Timer Swing qui met à jour le label chaque seconde
		Timer timer = new Timer(1000, e -> {
		    lblDateTime.setText(LocalDateTime.now().format(formatter));
		});
		timer.start();
		
		
		//---------------------------fin création de label d'acceuil ---------------------------------------------
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		splitPane.setLeftComponent(panel);
		 //rechercher adh 
		JMenu RechercherDoc = new JMenu("Rechercher document");
		RechercherDoc.setBounds(0, 28, 187, 35);
		panel.add(RechercherDoc);
		
		JMenuItem mntmParId = new JMenuItem("Par Id");
		RechercherDoc.add(mntmParId);
		
		JMenu RechercherLivre = new JMenu("Livre");
		RechercherLivre.setBounds(0, 28, 187, 35);
		
		JMenuItem mntmParMotsClé = new JMenuItem("Par mots clés");
		RechercherLivre.add(mntmParMotsClé);

		JMenuItem mntmParAuteur = new JMenuItem("Par auteur");
		RechercherLivre.add(mntmParAuteur);
		
		JMenuItem mntmParISBN = new JMenuItem("Par ISBN");
		RechercherLivre.add(mntmParISBN);
		
		RechercherDoc.add(RechercherLivre);
		
		JMenuItem RechercherDict = new JMenuItem("Dictionnaire");
		RechercherDoc.add(RechercherDict);
		
		JMenuItem RechercherRevue = new JMenuItem("Revue");
		RechercherDoc.add(RechercherRevue);
		
		JMenuItem RechercherThese = new JMenuItem("These");
		RechercherDoc.add(RechercherThese);
		
		//---------------------------------afficher doc------------------------------------------------------
		JMenu AfficherDoc = new JMenu("Afficher documents");
		AfficherDoc.setBounds(12, 186, 175, 35);
		
		JMenuItem Dictionnaires = new JMenuItem("Dictionnaires");
		AfficherDoc.add(Dictionnaires);
		
		
		JMenuItem Livres = new JMenuItem("Livres");
		AfficherDoc.add(Livres);

		JMenuItem Revues = new JMenuItem("Revues");
		AfficherDoc.add(Revues);
		
		JMenuItem Theses = new JMenuItem("Theses");
		AfficherDoc.add(Theses);
		
		panel.add(AfficherDoc);

		
		//---------------------------------ajouter doc-------------------------------------------------------
		
		JMenu AjouterDoc = new JMenu("Ajouter documents");
		AjouterDoc.setBounds(12, 233, 175, 35);
		
		JMenuItem DictionnaireItem = new JMenuItem("Dictionnaire");
		AjouterDoc.add(DictionnaireItem);
		
		
		JMenuItem LivreItem = new JMenuItem("Livre");
		AjouterDoc.add(LivreItem);

		JMenuItem RevueItem = new JMenuItem("Revue");
		AjouterDoc.add(RevueItem);
		
		JMenuItem TheseItem = new JMenuItem("These");
		AjouterDoc.add(TheseItem);
		
		panel.add(AjouterDoc);

		
		//---------------------------------supprimer doc------------------------------------------------------
		JButton SupprimerDocBttn = new JButton("Supprimer document");
		SupprimerDocBttn.setFont(new Font("Dialog", Font.BOLD, 11));
		SupprimerDocBttn.setHorizontalAlignment(SwingConstants.LEFT);
		SupprimerDocBttn.setBounds(12, 281, 175, 35);
		panel.add(SupprimerDocBttn);
		
		
		//---------------------------------déconnexion--------------------------------------------------------- 
		JButton deconnexion = new JButton("Déconnexion");
		deconnexion.setBounds(12, 493, 175, 35);
		panel.add(deconnexion);
		
		//--------------------------------création de cardLayout-----------------------------------------------
		JPanel conteneur = new JPanel();
        cardLayout = new CardLayout();
		conteneur.setLayout(cardLayout);
		splitPane.setRightComponent(conteneur);

		
		JPanel AfficherDictionnaires = new AfficherDictionnaires ();
		JPanel AfficherLivres = new AfficherLivres ();
		JPanel AfficherRevues = new AfficherLivres ();
		JPanel AfficherTheses = new AfficherTheses ();
		JPanel AjouterDictionnaire = new AjouterDictionnaire();
		JPanel AjouterLivre = new AjouterLivre();
		JPanel AjouterRevue = new AjouterRevue();
		JPanel AjouterThese = new AjouterThese();

		JPanel SupprimerDocument = new SupprimerDocument();
		
		conteneur.add(Acceuil,"ACCEUIL");
		
		conteneur.add(AfficherDictionnaires,"AFFICHERDICT");
		conteneur.add(AfficherLivres,"AFFICHERLIV");
		conteneur.add(AfficherRevues,"AFFICHERREV");
		conteneur.add(AfficherTheses,"AFFICHERTHES");
		
		conteneur.add(AjouterDictionnaire,"AJOUTERDIC");
		conteneur.add(AjouterLivre,"AJOUTERLIV");
		conteneur.add(AjouterRevue,"AJOUTERREV");
		conteneur.add(AjouterThese,"AJOUTERTHES");
		
		conteneur.add(SupprimerDocument,"SUPPRIMER");
		


		
		//--------------------------------Ajout des listeners-----------------------------------
		Dictionnaires.addActionListener(e -> {
			cardLayout.show(conteneur,"AFFICHERDICT");
		});
		Livres.addActionListener(e -> {
			cardLayout.show(conteneur, "AFFICHERLIV");
		});
		Revues.addActionListener(e -> {
			cardLayout.show(conteneur, "AFFICHERREV");
		});
		Theses.addActionListener(e -> {
			cardLayout.show(conteneur, "AFFICHERTHES");
		});
		
		
		DictionnaireItem.addActionListener(e -> {
			cardLayout.show(conteneur,"AJOUTERDIC");
		});
		LivreItem.addActionListener(e -> {
			cardLayout.show(conteneur, "AJOUTERLIV");
		});
		RevueItem.addActionListener(e -> {
			cardLayout.show(conteneur, "AJOUTERREV");
		});
		TheseItem.addActionListener(e -> {
			cardLayout.show(conteneur, "AJOUTERTHES");
		});
		
		SupprimerDocBttn.addActionListener(e -> {
			cardLayout.show(conteneur, "SUPPRIMER");
		});
		
		mntmParId.addActionListener(e -> {

		    String idText = JOptionPane.showInputDialog(
		            "Entrer l'id du document");

		    if(idText != null && !idText.trim().isEmpty()) {

		        try {

		            int id = Integer.parseInt(idText);
		            DocumentDAO dao = new DocumentDAO();
		            Document doc = dao.findById(id);
		            if(doc != null) {
		                // ---------------- LIVRE ----------------
		                if(doc instanceof Livre) {
		                    AfficherLivres panel1 =new AfficherLivres();
		                    panel1.remplirTable(Collections.singletonList(doc));
		                    cardLayout.show(conteneur, "AFFICHERLIV");
		                }
		                // ---------------- DICTIONNAIRE ----------------
		                else if(doc instanceof Dictionnaire) {
		                    AfficherDictionnaires panel2 = new AfficherDictionnaires();
		                    panel2.remplirTable(Collections.singletonList(doc));
		                    cardLayout.show(conteneur,"AFFICHERDICT");
		                }
		                // ---------------- REVUE ----------------
		                else if(doc instanceof Revue) {
		                    AfficherRevue panel3 = new AfficherRevue();
		                    panel3.remplirTable(Collections.singletonList(doc));
		                    cardLayout.show(conteneur,"AFFICHERREV");
		                }
		                // ---------------- THESE ----------------
		                else if(doc instanceof These) {
		                    AfficherTheses panel4 = new AfficherTheses();
		                    panel4.remplirTable(Collections.singletonList(doc));
		                    cardLayout.show(conteneur,"AFFICHERTHES");
		                }
		            } else {
		                JOptionPane.showMessageDialog( null,"Aucun document trouvé");
		            }

		        } catch(NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null,"Id invalide");
		        }
		    }
		});
		
		mntmParMotsClé.addActionListener(e -> {
		    String mots = JOptionPane.showInputDialog("Entrer les mots clés :");
		    if (mots != null && !mots.trim().isEmpty()) {
		        DocumentDAO dao = new DocumentDAO();
		        List<Document> res = dao.rechercherParMotsCle(mots);
		        
		        if (res != null) {
		        	 AfficherLivres afficherLivre = new AfficherLivres();
		        	 afficherLivre.remplirTable(res);
		                cardLayout.show(conteneur, "AFFICHERLIV");
		                
		        } else {
		            JOptionPane.showMessageDialog(null, "Aucun document trouvé.");
		        }
		    }
		});
		
		mntmParAuteur.addActionListener(e -> {
		    String auteur = JOptionPane.showInputDialog("Entrer l'auteur :");
		    if (auteur != null && !auteur.trim().isEmpty()) {
		        DocumentDAO dao = new DocumentDAO();
		        List<Document> res = dao.rechercherParAuteur(auteur);
		        
		        if (res != null) {
		        	 AfficherLivres afficherLivre = new AfficherLivres();
		        	 afficherLivre.remplirTable(res);
		                cardLayout.show(conteneur, "AFFICHERLIV");
		                
		        } else {
		            JOptionPane.showMessageDialog(null, "Aucun document trouvé.");
		        }
		    }
		});
		
		mntmParISBN.addActionListener(e -> {
		    String isbn = JOptionPane.showInputDialog("Entrer l'ISBN :");
		    if (isbn != null && !isbn.trim().isEmpty()) {
		        DocumentDAO dao = new DocumentDAO();
		        Document res = dao.rechercherParISBN(isbn);
		        
		        if (res != null) {
		        	AfficherLivres panel1 =new AfficherLivres();
                    panel1.remplirTable(Collections.singletonList(res));
                    cardLayout.show(conteneur, "AFFICHERLIV");
		                
		        } else {
		            JOptionPane.showMessageDialog(null, "Aucun document trouvé.");
		        }
		    }
		});
		RechercherDict.addActionListener(e -> {
		    String lgSource = JOptionPane.showInputDialog("Entrer la langue source");
		    
		    String lgCible = JOptionPane.showInputDialog("Entrer la langue cible");

		    if(lgSource != null && lgCible != null) {
		        DocumentDAO dao = new DocumentDAO();
		        List<Document> resultat =dao.rechercherParLangue(lgSource,lgCible);

		        if(!resultat.isEmpty()) {

		            AfficherDictionnaires panel1 =new AfficherDictionnaires();

		            panel1.remplirTable(resultat);

		            cardLayout.show(conteneur,"AFIICHERDIC");

		        } else {

		            JOptionPane.showMessageDialog(null,"Aucun dictionnaire trouvé");
		        }
		    }
		});	
		
		RechercherRevue.addActionListener(e -> {
			
		    String issn = JOptionPane.showInputDialog( "Entrer ISSN");
		    
		    if(issn != null && !issn.trim().isEmpty()) {

		        DocumentDAO dao = new DocumentDAO();

		        Document res =dao.rechercherParISSN(issn);

		        if(res != null) {

		            List<Document> resultat =Collections.singletonList(res);

		            AfficherRevue panel1 =new AfficherRevue();

		            panel1.remplirTable(resultat);

		            cardLayout.show(conteneur,"AFFICHERREV");

		        } else {

		            JOptionPane.showMessageDialog(null,"Aucune revue trouvée");
		        }
		    }
		});
		
		RechercherThese.addActionListener(e -> {

		    String domaine = JOptionPane.showInputDialog("Entrer domaine de soutenance");

		    if(domaine != null &&!domaine.trim().isEmpty()) {

		        DocumentDAO dao = new DocumentDAO();

		        List<Document> resultat =dao.rechercherParDomaineSoutenance(domaine);

		        if(!resultat.isEmpty()) {

		            AfficherTheses panel1 =new AfficherTheses();

		            panel1.remplirTable(resultat);

		            cardLayout.show(conteneur, "AFFICHERTHES");

		        } else {

		            JOptionPane.showMessageDialog(null, "Aucune thèse trouvée");
		        }
		    }
		});
		deconnexion.addActionListener(e->{
			Authentification access = new Authentification ();
			access.setVisible(true);
			
			javax.swing.JFrame currentFrame = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
		    
		    if (currentFrame != null) {
		        currentFrame.dispose(); // Ferme et libère les ressources de la fenêtre actuelle
		    }
		});
	}
}
		    	

