package tn.bibliotheque.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tn.bibliotheque.view.prets.PanelAjouterPret;
import tn.bibliotheque.view.prets.PanelAnnulerPret;
import tn.bibliotheque.view.prets.PanelListePrets;
import tn.bibliotheque.view.prets.PanelModifierPret;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.SwingUtilities;

public class GestionPrets extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CardLayout cl; 

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionPrets frame = new GestionPrets();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GestionPrets() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1088, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// --- LE PANNEAU CONTENEUR (CARDLAYOUT) ---
		JPanel panelAction = new JPanel();
		panelAction.setBounds(188, 74, 862, 326);
		contentPane.add(panelAction);
		
		cl = new CardLayout(0, 0); // Initialisation du CardLayout
		panelAction.setLayout(cl);
		
		// --- AJOUT DES PAGES (CARTES) ---
		
		// 1. Page Ajout (Ta classe personnalisée)
		PanelAjouterPret pnlAjout = new PanelAjouterPret();
		panelAction.add(pnlAjout, "pageAjout");
		
		// 2. Page Annulation
		PanelAnnulerPret pnlAnnulation = new PanelAnnulerPret(); 
		panelAction.add(pnlAnnulation, "pageAnnuler");
		
		// 3. Page Liste
		PanelListePrets pnlListe = new PanelListePrets(); 
		panelAction.add(pnlListe, "pageListe");
		
		// 4. Page Modification
		JPanel pnlModif = new PanelModifierPret(); 
		pnlModif.setBackground(Color.WHITE); 
		panelAction.add(pnlModif, "pageModif");
		pnlModif.setLayout(null);
		
		// --- LABELS ET TITRES ---
		JLabel lblWelcome = new JLabel("Bonjour:");
		lblWelcome.setForeground(new Color(0, 0, 204));
		lblWelcome.setFont(new Font("Serif", Font.BOLD, 18));
		lblWelcome.setBounds(37, 23, 134, 33);
		contentPane.add(lblWelcome);
		
		JLabel lblNomUtilisateur = new JLabel("Gestionnaire de Bibliothèque");
		lblNomUtilisateur.setForeground(new Color(102, 0, 255));
		lblNomUtilisateur.setFont(new Font("Serif", Font.BOLD, 18));
		lblNomUtilisateur.setBounds(198, 23, 385, 33);
		contentPane.add(lblNomUtilisateur);
		
		// --- PANNEAU DU MENU GAUCHE ---
		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(10, 74, 172, 326);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		// BOUTON AJOUTER
		JButton btnajouterP = new JButton("Ajouter Pret");
		btnajouterP.setFont(new Font("Serif", Font.BOLD, 12));
		btnajouterP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelAction, "pageAjout"); // Affiche la carte "pageAjout"
			}
		});
		btnajouterP.setBounds(0, 39, 172, 40);
		panelMenu.add(btnajouterP);
		
		// BOUTON ANNULER
		JButton btnannulerP = new JButton("Retourner Pret");
		btnannulerP.setFont(new Font("Serif", Font.BOLD, 12));
		btnannulerP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelAction, "pageAnnuler");
			}
		});
		btnannulerP.setBounds(0, 109, 172, 40);
		panelMenu.add(btnannulerP);
		
		// BOUTON MODIFIER
		JButton btnmodifierP = new JButton("Modifier Pret");
		btnmodifierP.setFont(new Font("Serif", Font.BOLD, 12));
		btnmodifierP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelAction, "pageModif");
			}
		});
		btnmodifierP.setBounds(0, 180, 172, 40);
		panelMenu.add(btnmodifierP);
		
		// BOUTON VOIR LISTE
		JButton btnvoirHP = new JButton("Consulter les prets");
		btnvoirHP.setFont(new Font("Serif", Font.BOLD, 12));
		btnvoirHP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelAction, "pageListe");
			}
		});
		btnvoirHP.setBounds(0, 246, 172, 40);
		panelMenu.add(btnvoirHP);
		
		JButton back = new JButton("Retournner");
		back.setFont(new Font("Serif", Font.BOLD, 13));
		back.setBounds(576, 28, 122, 27);
		contentPane.add(back);
		back.addActionListener(e->{
			dispose();
        	SwingUtilities.invokeLater(() -> {
            	BibliothecaireAcces dash = new BibliothecaireAcces();
                dash.setVisible(true);
		});
		});
	}
}