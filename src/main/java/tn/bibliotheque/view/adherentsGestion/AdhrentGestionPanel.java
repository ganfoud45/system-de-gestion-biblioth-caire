package tn.bibliotheque.view.adherentsGestion;
import tn.bibliotheque.view.Authentification;
import tn.bibliotheque.dao.AdherentDAO;
import tn.bibliotheque.model.Adherent;
import tn.bibliotheque.view.adherentsGestion.*;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.Collections;
import java.util.List;

public class AdhrentGestionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField prenomTf;
    protected CardLayout cardLayout ;
    protected JPanel conteneur;
    private JTextField nomTf;

	/**
	 * Create the panel.
	 */
	public AdhrentGestionPanel() {
		setBackground(new Color(245, 255, 250));
		setLayout(new BorderLayout());            
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(250);        

		add(splitPane, BorderLayout.CENTER);
		
		//-------------------------------création de panel acceuil------------------------------------------------
		JPanel Acceuil = new JPanel();
		Acceuil.setBackground(new Color(255, 255, 255));
		Acceuil.setLayout(null);
		
		prenomTf = new JTextField();
		prenomTf.setText(" Prenom");
		prenomTf.setBounds(86, 349, 311, 38);
		Acceuil.add(prenomTf);
		prenomTf.setColumns(10);
		
		JLabel lblRechercherUnAdhrent = new JLabel("Rechercher un adhérent");
		lblRechercherUnAdhrent.setFont(new Font("Bitstream Charter", Font.BOLD, 15));
		lblRechercherUnAdhrent.setBounds(86, 244, 201, 38);
		Acceuil.add(lblRechercherUnAdhrent);
		
		JLabel lblVeuillezEntrerLe = new JLabel("Veuillez entrer le nom et le prénom de l'adhérent");
		lblVeuillezEntrerLe.setVerticalAlignment(SwingConstants.TOP);
		lblVeuillezEntrerLe.setFont(new Font("DejaVu Sans Light", Font.BOLD, 12));
		lblVeuillezEntrerLe.setBounds(86, 399, 312, 38);
		Acceuil.add(lblVeuillezEntrerLe);
		
		JLabel lblBienvenueDansVotre = new JLabel("Bienvenue dans votre bibliothéque !");
		lblBienvenueDansVotre.setFont(new Font("Lato Hairline", Font.BOLD, 20));
		lblBienvenueDansVotre.setBounds(58, 174, 360, 71);
		Acceuil.add(lblBienvenueDansVotre);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		JLabel lblDateTime = new JLabel(LocalDateTime.now().format(formatter));
		lblDateTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblDateTime.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 15));
		lblDateTime.setSize(161, 38);
		lblDateTime.setLocation(12, 12);
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
		JPopupMenu popupRecherche = new JPopupMenu();
		JMenuItem mntmParCin = new JMenuItem("Par cin");
		JMenuItem mntmParEmail = new JMenuItem("Par email");
		JMenuItem mntmParNom = new JMenuItem("Par nom et prénom");

		popupRecherche.add(mntmParCin);
		popupRecherche.add(mntmParEmail);
		popupRecherche.add(mntmParNom);


		JButton btnRechercherAdh = new JButton("Rechercher adhérent");
		btnRechercherAdh.setBounds(12, 156, 225, 44);
		btnRechercherAdh.addActionListener(e -> 
		    popupRecherche.show(btnRechercherAdh, 0, btnRechercherAdh.getHeight())
		);
		panel.add(btnRechercherAdh);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(164, 447, 138, 40);
		Acceuil.add(btnRechercher);
		
		//---------------------------------afficher adh------------------------------------------------------
		JButton afficherAdhBttn = new JButton("Afficher adhérents");
		afficherAdhBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		afficherAdhBttn.setBounds(12, 212, 225, 44);
		panel.add(afficherAdhBttn);
		
		//---------------------------------ajouter adh-------------------------------------------------------
		JButton AjouterAdhBttn = new JButton("Ajouter adhérent");
		AjouterAdhBttn.setBounds(12, 266, 225, 44);
		panel.add(AjouterAdhBttn);
		
		//---------------------------------supprimer adh------------------------------------------------------
		JButton SupprimerAdhBttn = new JButton("Supprimer adhérent");
		SupprimerAdhBttn.setFont(new Font("Dialog", Font.BOLD, 12));
		SupprimerAdhBttn.setBounds(12, 320, 225, 44);
		panel.add(SupprimerAdhBttn);
		
		
		//---------------------------------déconnexion--------------------------------------------------------- 
		JButton deconnexion = new JButton("Déconnexion");
		deconnexion.setBounds(12, 493, 225, 44);
		panel.add(deconnexion);
		
		//--------------------------------création de cardLayout-----------------------------------------------
		JPanel conteneur = new JPanel();
        cardLayout = new CardLayout();
		conteneur.setLayout(cardLayout);
		splitPane.setRightComponent(conteneur);

		
		AfficherAdherentsPanel panelAfficher = new AfficherAdherentsPanel();
		JPanel SupprimerAdherents = new SupprimerAdherentPanel();
		JPanel AjouterAdherent    = new AjouterAdherent(this);

		conteneur.add(Acceuil,          "ACCEUIL");
		
		nomTf = new JTextField();
		nomTf.setText("Nom ");
		nomTf.setColumns(10);
		nomTf.setBounds(86, 294, 311, 38);
		Acceuil.add(nomTf);
		conteneur.add(panelAfficher,    "AFFICHER");
		conteneur.add(AjouterAdherent,  "AJOUTER");
		conteneur.add(SupprimerAdherents,"SUPPRIMER");
		
		
		//--------------------------------Ajout des listeners-----------------------------------
		
	
		
		AjouterAdhBttn.addActionListener(e -> {
			cardLayout.show(conteneur,"AJOUTER");
		});
		
		SupprimerAdhBttn.addActionListener(e -> {
			cardLayout.show(conteneur,"SUPPRIMER");
			
		});
		
		afficherAdhBttn.addActionListener(e -> {
		    panelAfficher.chargerTable(); // recharge toute la liste
		    cardLayout.show(conteneur, "AFFICHER");
		});

		mntmParCin.addActionListener(e -> {
		    String cin = JOptionPane.showInputDialog("Entrez le CIN :");
		    if (cin != null && !cin.trim().isEmpty()) {
		        Adherent res = new AdherentDAO().rechercherParCin(cin);
		        if (res != null) {
		            panelAfficher.remplirTable(Collections.singletonList(res)); // ← instance originale
		            cardLayout.show(conteneur, "AFFICHER");
		        } else {
		            JOptionPane.showMessageDialog(null, "Aucun adhérent trouvé.");
		        }
		    }
		});

		mntmParEmail.addActionListener(e -> {
		    String email = JOptionPane.showInputDialog("Entrez l'email :");
		    if (email == null || email.trim().isEmpty()) return;
		    if (!email.contains("@") || !email.contains(".")) {
		        JOptionPane.showMessageDialog(null, "Email invalide");
		        return;
		    }
		    Adherent res = new AdherentDAO().rechercherParEmail(email);
		    if (res != null) {
		        panelAfficher.remplirTable(Collections.singletonList(res)); // ← instance originale
		        cardLayout.show(conteneur, "AFFICHER");
		    } else {
		        JOptionPane.showMessageDialog(null, "Aucun adhérent trouvé.");
		    }
		});
		
		mntmParNom.addActionListener(e -> {
            cardLayout.show(conteneur, "ACCEUIL");
		});

		btnRechercher.addActionListener(e -> {
			String nomSaisi = nomTf.getText().trim();
		    String prenomSaisi = prenomTf.getText().trim();

		    // On vérifie qu'au moins l'un des deux n'est pas vide
		    if (!nomSaisi.isEmpty() || !prenomSaisi.isEmpty()) {
		        
		        List<Adherent> resultats = new AdherentDAO().rechercherParNomPrenom(nomSaisi, prenomSaisi);
		        
		        if (resultats != null && !resultats.isEmpty()) {
		            panelAfficher.remplirTable(resultats);
		            cardLayout.show(conteneur, "AFFICHER");
		            
		            // On vide les champs après la réussite
		            nomTf.setText("");
		            prenomTf.setText("");
		        } else {
		            JOptionPane.showMessageDialog(null, "Aucun adhérent trouvé pour ce nom et prénom.");
		        }
		    } else {
		        JOptionPane.showMessageDialog(null, "Veuillez remplir au moins un champ (Nom ou Prénom) !");
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
		    	

