package tn.bibliotheque.view.DocumentsGestion;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import tn.bibliotheque.dao.DocumentDAO;
import tn.bibliotheque.model.Dictionnaire;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AjouterDictionnaire extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField titreTf;
	private JTextField lsTf;
	private JTextField auteurTf;
	private JTextField lcTf;

	/**
	 * Create the panel.
	 */
	public AjouterDictionnaire() {
		setBackground(new Color(245, 255, 250));
		setLayout(null);
		
		JLabel nbreExemplbl=new JLabel("Nombre exemplaire :");
		nbreExemplbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		nbreExemplbl.setBounds(68, 353, 224, 25);
		add(nbreExemplbl);
		
		JLabel title = new JLabel("Ajouter Un Dictionnaire ");
		title.setForeground(new Color(0, 0, 205));
		title.setBackground(new Color(238, 238, 238));
		title.setFont(new Font("Century Schoolbook L", Font.BOLD, 35));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(68, 12, 500, 111);
		add(title);
		
		JLabel titrelbl = new JLabel("Titre :"); //nomDoc
		titrelbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		titrelbl.setBounds(68, 158, 224, 25);
		add(titrelbl);
		
		JLabel numDoclbl = new JLabel("Numéro de document :");
		numDoclbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		numDoclbl.setBounds(68, 430, 257, 25);
		add(numDoclbl);
		
		JLabel nbMotslbl = new JLabel("Nombre de mots :");
		nbMotslbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		nbMotslbl.setBounds(68, 393, 238, 25);
		add(nbMotslbl);
		
		JLabel lslbl = new JLabel("Langage source:");
		lslbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		lslbl.setBounds(68, 238, 238, 25);
		add(lslbl);
		
		JLabel lclbl = new JLabel("Langage cible :");
		lclbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		lclbl.setBounds(68, 278, 238, 25);
		add(lclbl);
		
		JLabel lblAuteur = new JLabel("Auteur :");
		lblAuteur.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		lblAuteur.setBounds(68, 198, 238, 25);
		add(lblAuteur);
		
		JLabel datePublbl = new JLabel("Date de publication :");
		datePublbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		datePublbl.setBounds(68, 315, 238, 25);
		add(datePublbl);
		
		
		titreTf = new JTextField();
		titrelbl.setLabelFor(titreTf);
		titreTf.setFont(new Font("Century Schoolbook L", Font.PLAIN, 18));
		titreTf.setBounds(368, 158, 200, 25);
		add(titreTf);
		titreTf.setColumns(10);
		
		lsTf = new JTextField();
		lslbl.setLabelFor(lsTf);
		lsTf.setFont(new Font("Century Schoolbook L", Font.PLAIN, 18));
		lsTf.setColumns(10);
		lsTf.setBounds(368, 238, 200, 25);
		add(lsTf);
		
		auteurTf = new JTextField();
		auteurTf.setFont(new Font("Century Schoolbook L", Font.PLAIN, 18));
		auteurTf.setColumns(10);
		auteurTf.setBounds(368, 198, 200, 25);
		add(auteurTf);
		
		lcTf = new JTextField();
		lclbl.setLabelFor(lcTf);
		lcTf.setFont(new Font("Century Schoolbook L", Font.PLAIN, 18));
		lcTf.setColumns(10);
		lcTf.setBounds(368, 278, 200, 25);
		add(lcTf);
		
		JFormattedTextField nbMotsTf = new JFormattedTextField();
		nbMotslbl.setLabelFor(nbMotsTf);
		nbMotslbl.setLabelFor(nbMotsTf);
		nbMotsTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		nbMotsTf.setBounds(368, 393, 200, 25);
		add(nbMotsTf);
		
		JFormattedTextField nbreExempTf = new JFormattedTextField();
		nbreExemplbl.setLabelFor(nbreExempTf);
		nbreExempTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		nbreExempTf.setBounds(368, 353, 200, 25);
		add(nbreExempTf);
		
		JFormattedTextField datePubTf = new JFormattedTextField();
		datePublbl.setLabelFor(datePubTf);
		datePubTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		datePubTf.setBounds(368, 318, 200, 25);
		add(datePubTf);
		JFormattedTextField numDocTf = new JFormattedTextField();
		numDocTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		numDocTf.setBounds(368, 435, 200, 25);
		add(numDocTf);
		
		
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
		
		confirmerBtn.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {

		        try {

		            // ---------------- récupération données ----------------

		            String titre = titreTf.getText().trim();

		            String auteur = auteurTf.getText().trim();

		            String langueSource = lsTf.getText().trim();

		            String langueCible = lcTf.getText().trim();

		            String datePub = datePubTf.getText().trim();

		            // ---------------- vérification champs vides ----------------

		            if (titre.isEmpty()
		                    || auteur.isEmpty()
		                    || langueSource.isEmpty()
		                    || langueCible.isEmpty()
		                    || datePub.isEmpty()
		                    || nbMotsTf.getText().trim().isEmpty()
		                    || nbreExempTf.getText().trim().isEmpty()
		                    || numDocTf.getText().trim().isEmpty()) {

		                JOptionPane.showMessageDialog(
		                        null,
		                        "Veuillez remplir tous les champs"
		                );

		                return;
		            }

		            // ---------------- conversions numériques ----------------

		            int nbMots =
		                    Integer.parseInt(
		                            nbMotsTf.getText().trim()
		                    );

		            int nbExemplaires =
		                    Integer.parseInt(
		                            nbreExempTf.getText().trim()
		                    );

		            int numDoc =
		                    Integer.parseInt(
		                            numDocTf.getText().trim()
		                    );

		            // ---------------- création objet ----------------

		            Dictionnaire d = new Dictionnaire();

		            d.setNomDoc(titre);

		            d.setAuteur(auteur);

		            d.setLgSource(langueSource);

		            d.setLgCible(langueCible);

		            d.setDatePub(datePub);

		            d.setNbrMots(nbMots);

		            d.setNbExemplaire(nbExemplaires);

		            d.setNumDoc(numDoc);

		            d.setDisponible(true);

		            d.setType("dictionnaire");

		            // ---------------- sauvegarde ----------------

		            DocumentDAO dao = new DocumentDAO();

		            dao.create(d);

		            JOptionPane.showMessageDialog(
		                    null,
		                    "Dictionnaire ajouté avec succès !"
		            );

		            // ---------------- vider champs ----------------

		            titreTf.setText("");

		            auteurTf.setText("");

		            lsTf.setText("");

		            lcTf.setText("");

		            datePubTf.setText("");

		            nbMotsTf.setText("");

		            nbreExempTf.setText("");

		            numDocTf.setText("");

		        }

		        catch (NumberFormatException ex) {

		            JOptionPane.showMessageDialog(
		                    null,
		                    "Nombre mots, exemplaires et numéro document doivent être numériques."
		            );
		        }

		        catch (Exception ex) {

		            JOptionPane.showMessageDialog(
		                    null,
		                    "Erreur : " + ex.getMessage()
		            );

		            ex.printStackTrace();
		        }
		    }
		});
		

	}
}
