package tn.bibliotheque.view.DocumentsGestion;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import tn.bibliotheque.dao.DocumentDAO;
import tn.bibliotheque.model.Livre;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AjouterLivre extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField titreTf;
	private JTextField numDocTf;
	private JTextField isbnTf;
	private JTextField genreTf;
	private JTextField auteurTf;
	private JTextField motsCleTf;

	/**
	 * Create the panel.
	 */
	public AjouterLivre() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		
		
		JLabel title = new JLabel("Ajouter Un Livre ");
		title.setForeground(new Color(0, 0, 205));
		title.setBackground(new Color(238, 238, 238));
		title.setFont(new Font("Century Schoolbook L", Font.BOLD, 35));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(124, 35, 392, 35);
		add(title);
		
		JLabel titrelbl = new JLabel("Titre :");
		titrelbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		titrelbl.setBounds(68, 158, 224, 25);
		add(titrelbl);
		
		JLabel numDoclbl = new JLabel("Numéro de document :");
		numDoclbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		numDoclbl.setBounds(68, 430, 268, 25);
		add(numDoclbl);
		
		JLabel lblDatePublication = new JLabel("Date de publication :");
		lblDatePublication.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		lblDatePublication.setBounds(68, 393, 224, 25);
		add(lblDatePublication);
		
		JLabel isbnlbl = new JLabel("ISBN :");
		isbnlbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 19));
		isbnlbl.setBounds(68, 118, 238, 25);
		add(isbnlbl);
		
		JLabel genrelbl = new JLabel("Genre :");
		genrelbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		genrelbl.setBounds(68, 238, 238, 25);
		add(genrelbl);
		
		JLabel nbreExemplbl=new JLabel("Nombre exemplaire :");
		nbreExemplbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		nbreExemplbl.setBounds(68, 353, 224, 25);
		add(nbreExemplbl);
		
		JLabel nbrePagelbl = new JLabel("Nombre page :");
		nbrePagelbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		nbrePagelbl.setBounds(68, 315, 238, 25);
		add(nbrePagelbl);
		
		JLabel lblAuteur = new JLabel("Auteur :");
		lblAuteur.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		lblAuteur.setBounds(68, 198, 238, 25);
		add(lblAuteur);
		
		JLabel lblMotsCls = new JLabel("Mots clés :");
		lblMotsCls.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		lblMotsCls.setBounds(68, 278, 238, 25);
		add(lblMotsCls);
		
		
		titreTf = new JTextField();
		titrelbl.setLabelFor(titreTf);
		titreTf.setFont(new Font("Century Schoolbook L", Font.PLAIN, 18));
		titreTf.setBounds(368, 158, 200, 25);
		add(titreTf);
		titreTf.setColumns(10);
		
		isbnTf = new JTextField();
		isbnTf.setFont(new Font("Century Schoolbook L", Font.PLAIN, 18));
		isbnTf.setColumns(10);
		isbnTf.setBounds(368, 118, 200, 25);
		add(isbnTf);
		
		genreTf = new JTextField();
		genreTf.setFont(new Font("Century Schoolbook L", Font.PLAIN, 18));
		genreTf.setColumns(10);
		genreTf.setBounds(368, 238, 200, 25);
		add(genreTf);
		
		auteurTf = new JTextField();
		auteurTf.setFont(new Font("Century Schoolbook L", Font.PLAIN, 18));
		auteurTf.setColumns(10);
		auteurTf.setBounds(368, 198, 200, 25);
		add(auteurTf);
		
		motsCleTf = new JTextField();
		motsCleTf.setFont(new Font("Century Schoolbook L", Font.PLAIN, 18));
		motsCleTf.setColumns(10);
		motsCleTf.setBounds(368, 278, 200, 25);
		add(motsCleTf);
		
		JFormattedTextField datePubTf = new JFormattedTextField();
		lblDatePublication.setLabelFor(datePubTf);
		datePubTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		datePubTf.setBounds(368, 393, 200, 25);
		datePubTf.setToolTipText("Format : YYYY/MM/DD ex: 2020/03/15");
		add(datePubTf);
		
		JFormattedTextField nbreExempTf = new JFormattedTextField();
		nbreExemplbl.setLabelFor(nbreExempTf);
		nbreExempTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		nbreExempTf.setBounds(368, 353, 200, 25);
		add(nbreExempTf);
		
		JFormattedTextField nbrePageTf = new JFormattedTextField();
		genrelbl.setLabelFor(nbrePageTf);
		nbrePageTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		nbrePageTf.setBounds(368, 318, 200, 25);
		add(nbrePageTf);
		
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
		
		//---------------listeners--------------
		confirmerBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // 1. Récupération des données textuelles
		            String isbn = isbnTf.getText().trim();
		            String titre = titreTf.getText().trim();
		            String auteur = auteurTf.getText().trim();
		            String genre = genreTf.getText().trim();
		            String motsCles = motsCleTf.getText().trim();
		            String datePub = datePubTf.getText().trim();

		            // 2. Vérification des champs vides
		            if (isbn.isEmpty() || titre.isEmpty() || auteur.isEmpty() || genre.isEmpty() 
		                || motsCles.isEmpty() || datePub.isEmpty() || nbrePageTf.getText().trim().isEmpty() 
		                || nbreExempTf.getText().trim().isEmpty() || numDocTf.getText().trim().isEmpty()) {

		                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
		                return;
		            }

		            // 3. Conversion des champs numériques
		            int nbrePages = Integer.parseInt(nbrePageTf.getText().trim());
		            int nbreExemplaires = Integer.parseInt(nbreExempTf.getText().trim());
		            int numDoc = Integer.parseInt(numDocTf.getText().trim());

		            // 4. Création de l'objet Livre
		            Livre monLivre = new Livre();
		            monLivre.setIsbn(isbn);
		            monLivre.setNomDoc(titre); // ou setTitre selon votre modèle
		            monLivre.setAuteur(auteur);
		            monLivre.setGenre(genre);
		            monLivre.setMotsCle(motsCles);
		            monLivre.setDatePub(datePub);
		            monLivre.setNbrPg(nbrePages);
		            monLivre.setNbExemplaire(nbreExemplaires);
		            monLivre.setNumDoc(numDoc);
		            monLivre.setDisponible(true); // Par défaut disponible à l'ajout
		            monLivre.setType("livre");    // Pour le filtrage par type

		            // 5. Sauvegarde via le DAO
		            DocumentDAO dao = new DocumentDAO();
		            dao.create(monLivre);

		            JOptionPane.showMessageDialog(null, "Livre ajouté avec succès !");

		            // 6. Vider les champs après succès
		            isbnTf.setText("");
		            titreTf.setText("");
		            auteurTf.setText("");
		            genreTf.setText("");
		            motsCleTf.setText("");
		            datePubTf.setText("");
		            nbrePageTf.setText("");
		            nbreExempTf.setText("");
		            numDocTf.setText("");

		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Les champs Pages, Exemplaires et NumDoc doivent être numériques");
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
		            ex.printStackTrace();
		        }
		    }
		});
		
		

	}
}
