package tn.bibliotheque.view;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import tn.bibliotheque.dao.UtilisateurDAO;
import tn.bibliotheque.model.Adherent;
import tn.bibliotheque.model.Utilisateur;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Authentification extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;


	/**
	 * Create the frame.
	 */

	public Authentification() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 907, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("🎉WELCOME TO OUR LIBRARY🎉 ");
		lblNewLabel.setForeground(new Color(102, 51, 204));
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 30));
		lblNewLabel.setBounds(207, 10, 511, 74);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("USERNAME");
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(172, 114, 116, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("PASSWORD");
		lblNewLabel_1_1.setFont(new Font("Serif", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(176, 181, 93, 28);
		contentPane.add(lblNewLabel_1_1);
		
		JTextArea username = new JTextArea();
		username.setBounds(338, 118, 242, 28);
		contentPane.add(username);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(338, 184, 242, 28);
		contentPane.add(passwordField);
		
		
		JButton btnLogin = new JButton("LOG IN");
		btnLogin.setFont(new Font("Serif", Font.BOLD, 15));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.setBounds(497, 243, 121, 46);
		contentPane.add(btnLogin);
		
		JButton btnClose = new JButton("CLOSE");
		btnClose.setFont(new Font("Serif", Font.BOLD, 15));
		btnClose.setBounds(301, 243, 121, 46);
		contentPane.add(btnClose);
		
		JLabel errorMe = new JLabel("");
		errorMe.setVerticalAlignment(SwingConstants.TOP);
		errorMe.setForeground(new Color(255, 0, 51));
		errorMe.setFont(new Font("Serif", Font.BOLD, 15));
		errorMe.setBounds(207, 299, 471, 91);
		contentPane.add(errorMe);
		btnLogin.addActionListener(e -> {
		    String cin = username.getText().trim();
		    String mdp = new String(passwordField.getPassword());
		    
		    Utilisateur user = login(cin, mdp);
		    
		    if (user == null) {
		        errorMe.setText("CIN ou mot de passe incorrect.");
		    } else if (user instanceof Adherent) {
		        dispose();
		        SwingUtilities.invokeLater(() -> {
		            AdherentAcces dash = new AdherentAcces((Adherent) user);
		            dash.setVisible(true);
		        });
		    } else {
		        dispose();
		        SwingUtilities.invokeLater(() -> {
		            BibliothecaireAcces dash = new BibliothecaireAcces();
		            dash.setVisible(true);
		        });
		    }
		});

	}
	public Utilisateur login(String cin, String mdp) {
	    UtilisateurDAO u = new UtilisateurDAO();
	    Utilisateur user = u.rechercherParCin(cin);
	    if (user != null && user.getMotDePasse().equals(mdp)) {
	        return user;
	    }
	    return null;
	}
}
