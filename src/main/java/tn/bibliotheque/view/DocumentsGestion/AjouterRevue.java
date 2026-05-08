package tn.bibliotheque.view.DocumentsGestion;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import tn.bibliotheque.dao.DocumentDAO;
import tn.bibliotheque.model.Revue;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AjouterRevue extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField titreTf;
	private JTextField issnTf;
	private JTextField periodiciteTf;

	/**
	 * Create the panel.
	 */
	public AjouterRevue() {
		setBackground(new Color(245, 255, 250));
		setLayout(null);
	
		
		JLabel title = new JLabel("Ajouter Un Revue ");
		title.setForeground(new Color(0, 0, 205));
		title.setBackground(new Color(238, 238, 238));
		title.setFont(new Font("Century Schoolbook L", Font.BOLD, 35));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(124, 35, 392, 35);
		add(title);
		
		JLabel issnlbl = new JLabel("ISSN :");
		issnlbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 19));
		issnlbl.setBounds(68, 118, 224, 25);
		add(issnlbl);
		
		JLabel titrelbl = new JLabel("Titre :");
		titrelbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		titrelbl.setBounds(68, 158, 224, 25);
		add(titrelbl);
		
		JLabel periodicitelbl = new JLabel("Périodicité :");
		periodicitelbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		periodicitelbl.setBounds(68, 198, 224, 25);
		add(periodicitelbl);
		
		
		JLabel numRevuelbl = new JLabel("Numéro revue :");
		numRevuelbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		numRevuelbl.setBounds(68, 238, 238, 25);
		add(numRevuelbl);
		
		JLabel nbreExemplbl=new JLabel("Nombre exemplaire :");
		nbreExemplbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		nbreExemplbl.setBounds(68, 278, 224, 25);
		add(nbreExemplbl);
		
		
		JLabel lblDatePublication = new JLabel("Date de publication :");
		lblDatePublication.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		lblDatePublication.setBounds(68, 318, 224, 25);
		add(lblDatePublication);
		
		JLabel numDoclbl = new JLabel("Numéro de document :");
		numDoclbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
		numDoclbl.setBounds(68, 358, 238, 25);
		add(numDoclbl);
		
		
		
		issnTf = new JTextField();
		issnTf.setFont(new Font("Century Schoolbook L", Font.PLAIN, 18));
		issnTf.setColumns(10);
		issnTf.setBounds(368, 118, 200, 25);
		add(issnTf);
		
		titreTf = new JTextField();
		titrelbl.setLabelFor(titreTf);
		titreTf.setFont(new Font("Century Schoolbook L", Font.PLAIN, 18));
		titreTf.setBounds(368, 158, 200, 25);
		add(titreTf);
		titreTf.setColumns(10);
		
		periodiciteTf = new JTextField();
		periodiciteTf.setFont(new Font("Century Schoolbook L", Font.PLAIN, 18));
		periodiciteTf.setColumns(10);
		periodiciteTf.setBounds(368, 198, 200, 25);
		add(periodiciteTf);
		
		
		JFormattedTextField datePubTf = new JFormattedTextField();
		lblDatePublication.setLabelFor(datePubTf);
		datePubTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		datePubTf.setBounds(368, 318, 200, 25);
		add(datePubTf);
		
		JFormattedTextField nbreExempTf = new JFormattedTextField();
		nbreExemplbl.setLabelFor(nbreExempTf);
		nbreExempTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		nbreExempTf.setBounds(368, 278, 200, 25);
		add(nbreExempTf);
		
		JFormattedTextField numRevueTf = new JFormattedTextField();
		numRevueTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		numRevueTf.setBounds(368, 238, 200, 25);
		add(numRevueTf);
		
		JFormattedTextField numDocTf = new JFormattedTextField();
		numDocTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		numDocTf.setBounds(368, 358, 200, 25);
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

		            // ---------------- récupération des données ----------------

		            String issn = issnTf.getText().trim();

		            String titre = titreTf.getText().trim();

		            String periodicite = periodiciteTf.getText().trim();

		            String datePub = datePubTf.getText().trim();

		            // ---------------- vérification champs vides ----------------

		            if (issn.isEmpty()
		                    || titre.isEmpty()
		                    || periodicite.isEmpty()
		                    || datePub.isEmpty()
		                    || numRevueTf.getText().trim().isEmpty()
		                    || nbreExempTf.getText().trim().isEmpty()
		                    || numDocTf.getText().trim().isEmpty()) {

		                JOptionPane.showMessageDialog(
		                        null,
		                        "Veuillez remplir tous les champs"
		                );

		                return;
		            }

		            // ---------------- conversions numériques ----------------

		            int numRevue =
		                    Integer.parseInt(
		                            numRevueTf.getText().trim()
		                    );

		            int nbreExemp =
		                    Integer.parseInt(
		                            nbreExempTf.getText().trim()
		                    );

		            int numDoc =
		                    Integer.parseInt(
		                            numDocTf.getText().trim()
		                    );

		            // ---------------- création objet revue ----------------

		            Revue r = new Revue();

		            r.setIssn(issn);

		            r.setNomDoc(titre);

		            r.setPeriodicite(periodicite);

		            r.setNumero(numRevue);

		            r.setNbExemplaire(nbreExemp);

		            r.setDatePub(datePub);

		            r.setNumDoc(numDoc);

		            r.setDisponible(true);

		            r.setType("revue");

		            // ---------------- sauvegarde ----------------

		            DocumentDAO dao = new DocumentDAO();

		            dao.create(r);

		            JOptionPane.showMessageDialog(
		                    null,
		                    "Revue ajoutée avec succès !"
		            );

		            // ---------------- vider champs ----------------

		            issnTf.setText("");

		            titreTf.setText("");

		            periodiciteTf.setText("");

		            numRevueTf.setText("");

		            nbreExempTf.setText("");

		            datePubTf.setText("");

		            numDocTf.setText("");

		        }

		        catch (NumberFormatException ex) {

		            JOptionPane.showMessageDialog(
		                    null,
		                    "Numéro revue, nombre exemplaires et numéro document doivent être numériques."
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
