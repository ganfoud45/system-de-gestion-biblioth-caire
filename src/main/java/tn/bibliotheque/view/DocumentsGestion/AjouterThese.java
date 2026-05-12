package tn.bibliotheque.view.DocumentsGestion;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import tn.bibliotheque.dao.DocumentDAO;
import tn.bibliotheque.model.These;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AjouterThese extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField titreTf;
	private JTextField numDocTf;
	private JTextField universiteTf;
	private JTextField domaineTf;
	private JTextField directeurTf;

	/**
	 * Create the panel.
	 */
	public AjouterThese() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		
		
		JLabel title = new JLabel("Ajouter Une Thése ");
		title.setForeground(new Color(0, 0, 205));
		title.setBackground(new Color(238, 238, 238));
		title.setFont(new Font("Century Schoolbook L", Font.BOLD, 35));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(124, 35, 392, 35);
		add(title);
		
		JLabel titrelbl = new JLabel("Titre :");
		titrelbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		titrelbl.setBounds(68, 118, 224, 25);
		add(titrelbl);
		
		JLabel numDoclbl = new JLabel("Numéro de document :");
		numDoclbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		numDoclbl.setBounds(68, 398, 268, 25);
		add(numDoclbl);
		
		JLabel lblDatePublication = new JLabel("Date de publication :");
		lblDatePublication.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		lblDatePublication.setBounds(68, 358, 224, 25);
		add(lblDatePublication);
		
		JLabel universitelbl = new JLabel("Université :");
		universitelbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 19));
		universitelbl.setBounds(68, 238, 238, 25);
		add(universitelbl);
		
		JLabel domainelbl = new JLabel("Domaine de soutenance :");
		domainelbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		domainelbl.setBounds(68, 158, 282, 25);
		add(domainelbl);
		
		JLabel nbreExemplbl=new JLabel("Nombre exemplaire :");
		nbreExemplbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		nbreExemplbl.setBounds(68, 318, 224, 25);
		add(nbreExemplbl);
		
		JLabel anneeStlbl = new JLabel("Année soutenance :");
		anneeStlbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		anneeStlbl.setBounds(68, 278, 238, 25);
		add(anneeStlbl);
		
		JLabel directeurlbl = new JLabel("Directeur :");
		directeurlbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		directeurlbl.setBounds(68, 198, 238, 25);
		add(directeurlbl);
		
		
		titreTf = new JTextField();
		titrelbl.setLabelFor(titreTf);
		titreTf.setFont(new Font("Century Schoolbook L", Font.PLAIN, 18));
		titreTf.setBounds(368, 118, 200, 25);
		add(titreTf);
		titreTf.setColumns(10);
		
		universiteTf = new JTextField();
		universiteTf.setFont(new Font("Century Schoolbook L", Font.PLAIN, 18));
		universiteTf.setColumns(10);
		universiteTf.setBounds(368, 238, 200, 25);
		add(universiteTf);
		
		domaineTf = new JTextField();
		domaineTf.setFont(new Font("Century Schoolbook L", Font.PLAIN, 18));
		domaineTf.setColumns(10);
		domaineTf.setBounds(368, 158, 200, 25);
		add(domaineTf);
		
		directeurTf = new JTextField();
		directeurTf.setFont(new Font("Century Schoolbook L", Font.PLAIN, 18));
		directeurTf.setColumns(10);
		directeurTf.setBounds(368, 198, 200, 25);
		add(directeurTf);
		
		JFormattedTextField datePubTf = new JFormattedTextField();
		lblDatePublication.setLabelFor(datePubTf);
		datePubTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		datePubTf.setBounds(368, 358, 200, 25);
		datePubTf.setToolTipText("Format : YYYY/MM/DD ex: 2020/03/15");
		add(datePubTf);
		
		JFormattedTextField nbreExempTf = new JFormattedTextField();
		nbreExemplbl.setLabelFor(nbreExempTf);
		nbreExempTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		nbreExempTf.setBounds(368, 318, 200, 25);
		add(nbreExempTf);
		
		JFormattedTextField anneeStTf = new JFormattedTextField();
		domainelbl.setLabelFor(anneeStTf);
		anneeStTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		anneeStTf.setBounds(368, 278, 200, 25);
		add(anneeStTf);
		
		JFormattedTextField numDocTf = new JFormattedTextField();
		numDocTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		numDocTf.setBounds(368, 398, 200, 25);
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
		
		//----------------------listeners--------------------------------------------------
		
		
		confirmerBtn.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {

		        try {

		            // ------------------ récupération des champs texte ------------------

		            String titre = titreTf.getText().trim();

		            String domaine = domaineTf.getText().trim();

		            String directeur = directeurTf.getText().trim();

		            String universite = universiteTf.getText().trim();

		            String datePub = datePubTf.getText().trim();

		            // ------------------ vérification champs vides ------------------

		            if (titre.isEmpty()
		                    || domaine.isEmpty()
		                    || directeur.isEmpty()
		                    || universite.isEmpty()
		                    || datePub.isEmpty()
		                    || anneeStTf.getText().trim().isEmpty()
		                    || nbreExempTf.getText().trim().isEmpty()
		                    || numDocTf.getText().trim().isEmpty()) {

		                JOptionPane.showMessageDialog(
		                        null,
		                        "Veuillez remplir tous les champs !"
		                );

		                return;
		            }

		            // ------------------ conversions numériques ------------------

		            int anneeSoutenance =
		                    Integer.parseInt(
		                            anneeStTf.getText().trim()
		                    );

		            int nbreExemp =
		                    Integer.parseInt(
		                            nbreExempTf.getText().trim()
		                    );

		            int numDoc =
		                    Integer.parseInt(
		                            numDocTf.getText().trim()
		                    );

		            // ------------------ création objet ------------------

		            These t = new These();

		            t.setNomDoc(titre);

		            t.setDomaineSoutenance(domaine);

		            t.setDirecteur(directeur);

		            t.setUniversite(universite);

		            t.setAnneeSoutenance(anneeSoutenance);

		            t.setDatePub(datePub);

		            t.setNbExemplaire(nbreExemp);

		            t.setNumDoc(numDoc);

		            t.setDisponible(true);

		            t.setType("these");

		            // ------------------ sauvegarde ------------------

		            DocumentDAO dao = new DocumentDAO();

		            dao.create(t);

		            JOptionPane.showMessageDialog(
		                    null,
		                    "Thèse ajoutée avec succès !"
		            );

		            // ------------------ vider champs ------------------

		            titreTf.setText("");

		            domaineTf.setText("");

		            directeurTf.setText("");

		            universiteTf.setText("");

		            anneeStTf.setText("");

		            datePubTf.setText("");

		            nbreExempTf.setText("");

		            numDocTf.setText("");

		        }

		        catch (NumberFormatException ex) {

		            JOptionPane.showMessageDialog(
		                    null,
		                    "Année, nombre exemplaires et numéro document doivent être numériques."
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
