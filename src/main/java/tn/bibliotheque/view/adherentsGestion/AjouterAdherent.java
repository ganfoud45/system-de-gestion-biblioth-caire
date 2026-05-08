package tn.bibliotheque.view.adherentsGestion;
import tn.bibliotheque.dao.AdherentDAO;
import tn.bibliotheque.model.Adherent;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AjouterAdherent extends JPanel {

	private static final long serialVersionUID = 1L;
	private AdhrentGestionPanel parent;
	private JTextField nomTf;
	private JTextField prenomTf;
	private JPasswordField mdpTf;

	/**
	 * Create the panel.
	 */
	public AjouterAdherent() {
		this.parent = parent;
		
		setBackground(new Color(245, 255, 250));
		setLayout(null);
		
		JLabel title = new JLabel("Ajouter Un Adhérent ");
		title.setForeground(new Color(0, 0, 205));
		title.setBackground(new Color(238, 238, 238));
		title.setFont(new Font("Century Schoolbook L", Font.BOLD, 35));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(124, 35, 392, 35);
		add(title);
		
		
		JLabel emaillbl=new JLabel("Email :");
		emaillbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		emaillbl.setBounds(68, 238, 118, 25);
		add(emaillbl);
	
		
		JLabel nomlbl = new JLabel("Nom :");
		nomlbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		nomlbl.setBounds(68, 118, 61, 25);
		add(nomlbl);
		
		JLabel Prenomlbl = new JLabel("Prenom :");
		Prenomlbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		Prenomlbl.setBounds(68, 158, 118, 25);
		add(Prenomlbl);
		
		JLabel lblDateDeNaissance = new JLabel("Date de naissance :");
		lblDateDeNaissance.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		lblDateDeNaissance.setBounds(68, 198, 211, 25);
		add(lblDateDeNaissance);
		
		JLabel numlbl = new JLabel("Numéro de Téléphone :");
		numlbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 19));
		numlbl.setBounds(68, 278, 238, 25);
		add(numlbl);
		
		JLabel abonnementlbl = new JLabel("Numéro Abonnement :");
		abonnementlbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		abonnementlbl.setBounds(68, 400, 238, 25);
		add(abonnementlbl);
		
		JLabel mdplbl = new JLabel("Mot de passe  :");
		mdplbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		mdplbl.setBounds(68, 358, 168, 25);
		add(mdplbl);
		
		JLabel lblCin = new JLabel("CIN  :");
		lblCin.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		lblCin.setBounds(68, 315, 168, 25);
		add(lblCin);
		
		nomTf = new JTextField();
		nomlbl.setLabelFor(nomTf);
		nomTf.setFont(new Font("Century Schoolbook L", Font.PLAIN, 18));
		nomTf.setBounds(368, 118, 200, 25);
		add(nomTf);
		nomTf.setColumns(10);
		
		prenomTf = new JTextField();
		Prenomlbl.setLabelFor(prenomTf);
		prenomTf.setFont(new Font("Century Schoolbook L", Font.PLAIN, 18));
		prenomTf.setColumns(10);
		prenomTf.setBounds(368, 158, 200, 25);
		add(prenomTf);
		
		mdpTf = new JPasswordField();
		mdplbl.setLabelFor(mdpTf);
		mdpTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		mdpTf.setBounds(368, 358, 200, 25);
		add(mdpTf);
		
		
		JFormattedTextField dateNaissTf = new JFormattedTextField();
		lblDateDeNaissance.setLabelFor(dateNaissTf);
		dateNaissTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		dateNaissTf.setBounds(368, 198, 200, 25);
		dateNaissTf.setToolTipText("Format : YYYY/MM/DD ex: 2020/03/15");
		add(dateNaissTf);
		
		JFormattedTextField emailTf = new JFormattedTextField();
		emaillbl.setLabelFor(emailTf);
		emailTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		emailTf.setBounds(368, 238, 200, 25);
		add(emailTf);
		
		JFormattedTextField numTf = new JFormattedTextField();
		numlbl.setLabelFor(numTf);
		numTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		numTf.setBounds(368, 278, 200, 25);
		add(numTf);
		
		JFormattedTextField abonnementTf = new JFormattedTextField();
		abonnementlbl.setLabelFor(abonnementTf);
		abonnementTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		abonnementTf.setBounds(368, 399, 200, 25);
		add(abonnementTf);
		
		JButton confirmerBtn = new JButton("confirmer");
		confirmerBtn.setFont(new Font("Dialog", Font.BOLD, 16));
		confirmerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		confirmerBtn.setBounds(499, 484, 144, 43);
		add(confirmerBtn);
		
		JButton annulerBtn = new JButton("annuler");
		annulerBtn.setFont(new Font("Dialog", Font.BOLD, 16));
		annulerBtn.setBounds(328, 484, 144, 43);
		add(annulerBtn);
		
		JFormattedTextField cinTf = new JFormattedTextField();
		cinTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		cinTf.setBounds(368, 320, 200, 25);
		add(cinTf);
		
		
		
		//-----------------listeners-------------------------------
		confirmerBtn.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {

		        try {

		            String nom = nomTf.getText().trim();
		            String prenom = prenomTf.getText().trim();
		            String dateNaissance =dateNaissTf.getText().trim();
		            String email =emailTf.getText().trim();//trim ---> supprimer les espaces 
		            String cin=cinTf.getText().trim();
		            String motDePasse =new String(mdpTf.getPassword());
		            // Vérification champs vides
		            if (nom.isEmpty()
		                    || prenom.isEmpty()
		                    || dateNaissance.isEmpty()
		                    || email.isEmpty()
		                    || motDePasse.isEmpty()
		                    || cin.isEmpty()) {

		                JOptionPane.showMessageDialog(
		                        null,
		                        "Veuillez remplir tous les champs"
		                );

		                return;
		            }

		            // Vérification email simple
		            if (!email.contains("@")
		                    || !email.contains(".")) {

		                JOptionPane.showMessageDialog(
		                        null,
		                        "Email invalide"
		                );

		                return;
		            }

		            // Conversion téléphone
		            int telephone =
		                    Integer.parseInt(
		                            numTf.getText().trim()
		                    );

		            // Conversion abonnement
		            int abonnement =
		                    Integer.parseInt(
		                            abonnementTf.getText().trim()
		                    );

		            // Création objet
		            Adherent a = new Adherent();

		            a.setNom(nom);

		            a.setPrenom(prenom);

		            a.setDateNaissance(dateNaissance);

		            a.setEmail(email);

		            a.setNumTel(telephone);

		            a.setNumAbonnement(abonnement);

		            a.setMotDePasse(motDePasse);

		            // Sauvegarde
		            AdherentDAO dao = new AdherentDAO();

		            dao.create(a);

		            JOptionPane.showMessageDialog(
		                    null,
		                    "Adhérent ajouté avec succès !"
		            );

		            // vider les champs
		            nomTf.setText("");

		            prenomTf.setText("");

		            dateNaissTf.setText("");

		            emailTf.setText("");

		            numTf.setText("");

		            abonnementTf.setText("");

		            mdpTf.setText("");

		        }

		        catch (NumberFormatException ex) {

		            JOptionPane.showMessageDialog(
		                    annulerBtn,
		                    "Téléphone et abonnement doivent être numériques"
		            );
		        }

		        catch (Exception ex) {

		            JOptionPane.showMessageDialog(
		                    annulerBtn,
		                    "Erreur : " + ex.getMessage()
		            );

		            ex.printStackTrace();
		        }
		    }
		});
		annulerBtn.addActionListener(e->{
			parent.cardLayout.show(parent.conteneur, "ACCEUIL");
		});
	}
}
