package tn.bibliotheque.view.DocumentsGestion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import tn.bibliotheque.dao.DocumentDAO;
import tn.bibliotheque.model.Document;
import tn.bibliotheque.model.Livre;
import tn.bibliotheque.model.Revue;

public class AfficherRevue extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	/**
	 * Create the panel.
	 */
	public AfficherRevue() {
		setBackground(new Color(245, 255, 250));
		setLayout(null);
		
		JLabel title = new JLabel("Liste des Revues ");
		title.setForeground(new Color(0, 0, 205));
		title.setBackground(new Color(238, 238, 238));
		title.setFont(new Font("Century Schoolbook L", Font.BOLD, 35));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(101, 30, 487, 35);
		add(title);
		
		
		table = new JTable();
		table.setFont(new Font("Dialog", Font.PLAIN, 13));
		table.setBackground(new Color(255, 255, 255));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				 "Id", "ISSN", "titre", "periodicité", "numRevue","datePub","numDoc","nbExmp","disponibilité"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		chargerTable();
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 77, 666, 365);
		add(scrollPane);
		
		JButton enregistrerBtn = new JButton("Enregistrer");
		enregistrerBtn.setFont(new Font("Dialog", Font.BOLD, 16));
		enregistrerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		enregistrerBtn.setBounds(499, 484, 144, 43);
		add(enregistrerBtn);
		
		JButton actualiserBtn = new JButton("Actualiser");
		actualiserBtn.setFont(new Font("Dialog", Font.BOLD, 16));
		actualiserBtn.setBounds(328, 484, 144, 43);
		add(actualiserBtn);
		
		
		//--------------listeners-------------------
		enregistrerBtn.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            try {
	                DocumentDAO dao = new DocumentDAO();
	                DefaultTableModel model = (DefaultTableModel) table.getModel();
	                int rowCount = model.getRowCount();

	                for (int i = 0; i < rowCount; i++) {
	                    int id = (int) model.getValueAt(i, 0);
	                    Document a = dao.findById(id);

	                    if (a != null) {
	                    	Revue b=(Revue ) a;
	                        b.setIssn(model.getValueAt(i, 1).toString());
	                        b.setNomDoc(model.getValueAt(i, 2).toString());
	                        b.setPeriodicite(model.getValueAt(i, 3).toString());
	                        b.setNumero(Integer.parseInt(model.getValueAt(i, 4).toString()));
	                        b.setDatePub(model.getValueAt(i,5).toString());
	                        b.setNumDoc(Integer.parseInt(model.getValueAt(i, 6).toString()));
	                        b.setNbExemplaire(Integer.parseInt(model.getValueAt(i, 7).toString()));
	                        dao.update(a);
	                    }
	                }

	                JOptionPane.showMessageDialog(null, "Toutes les modifications ont été enregistrées avec succès !");
	                
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement : " + ex.getMessage(), 
	                                              "Erreur", JOptionPane.ERROR_MESSAGE);
	                ex.printStackTrace();
	            }
	        }
	    });
		chargerTable() ;	
		actualiserBtn.addActionListener(e -> chargerTable());

	}
	
	//méthodes
	
	public void remplirTable(List<Document> liste) { //---> filtrage
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0); // On vide la table

	    if (liste != null) {
	        for (Document doc : liste) {
	        	if (doc instanceof Revue)  {
	                Revue liv = (Revue) doc;
	                model.addRow(new Object[] {
	                    liv.getId(),
	                    liv.getIssn(),
	                    liv.getNomDoc(),
	                    liv.getPeriodicite(),
	                    liv.getNumero(),
	                    liv.getDatePub(),
	                    liv.getNumDoc(),
	                    liv.getNbExemplaire(),
	                    liv.isDisponible() ? "Oui" : "Non" // Plus lisible qu'un boolean
	                });
	            }
	        }
	    }
	}

	public void chargerTable() {
	    DocumentDAO dao = new DocumentDAO();
	    List<Document> listeRevues = dao.rechercherParType("Revue");
	  	remplirTable(listeRevues);
	}
}
