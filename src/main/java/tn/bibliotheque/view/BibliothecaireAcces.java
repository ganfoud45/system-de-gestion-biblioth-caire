package tn.bibliotheque.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BibliothecaireAcces extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BibliothecaireAcces frame = new BibliothecaireAcces();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BibliothecaireAcces() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBonjour = new JLabel("Bonjour:");
		lblBonjour.setBounds(20, 23, 130, 34);
		lblBonjour.setForeground(new Color(0, 0, 204));
		lblBonjour.setFont(new Font("Serif", Font.BOLD, 18));
		contentPane.add(lblBonjour);
		
		JLabel lblNomUtilisateur = new JLabel("Gestionnaire de Bibliothèque");
		lblNomUtilisateur.setForeground(new Color(102, 0, 255));
		lblNomUtilisateur.setFont(new Font("Serif", Font.BOLD, 18));
		lblNomUtilisateur.setBounds(145, 24, 763, 33);
		contentPane.add(lblNomUtilisateur);
		
		JButton GesDoc = new JButton("Gerer les Documents");
		GesDoc.setForeground(new Color(0, 0, 255));
		GesDoc.setFont(new Font("Serif", Font.BOLD, 20));
		GesDoc.addActionListener(e -> {
		    dispose();
		    SwingUtilities.invokeLater(() ->new GestionDocuments().setVisible(true));});
		GesDoc.setBounds(383, 76, 273, 109);
		contentPane.add(GesDoc);
		
		JButton GesAdh = new JButton("Gerer les Adherents");
		GesAdh.setForeground(Color.BLUE);
		GesAdh.setFont(new Font("Serif", Font.BOLD, 20));
		GesAdh.setBounds(57, 76, 279, 109);
		contentPane.add(GesAdh);
		GesAdh.addActionListener(e -> {
		    dispose();
		    SwingUtilities.invokeLater(() -> new GestionAdherent().setVisible(true));
		});
		
		JButton GesPre = new JButton("Gerer les Prets");
		GesPre.setForeground(Color.BLUE);
		GesPre.setFont(new Font("Serif", Font.BOLD, 20));
		GesPre.setBounds(205, 229, 265, 115);
		contentPane.add(GesPre);
		
		
		GesPre.addActionListener(e -> {
		    dispose();
		    SwingUtilities.invokeLater(() -> new GestionPrets().setVisible(true));
		});
		
		JButton btnDeconnexion = new JButton("Déconnexion");
		btnDeconnexion.setForeground(Color.RED);
		btnDeconnexion.setFont(new Font("Serif", Font.BOLD, 13));
		btnDeconnexion.setBounds(538, 10, 150, 30);
		btnDeconnexion.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> new Authentification().setVisible(true));
        });
		contentPane.add(btnDeconnexion);
	}
}
