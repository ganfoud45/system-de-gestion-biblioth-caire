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
import tn.bibliotheque.model.These;

public class AfficherTheses extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	/**
	 * Create the panel.
	 */
	public AfficherTheses() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		JLabel title = new JLabel("Liste des Theses ");
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
				 "Id", "titre", "doamine", "directeur", "université", "annéeSout", "datePub","nbExmp","numDoc","disponibilité"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(26, 87, 697, 365);
		add(scrollPane);
		
		JButton enregistrerBtn = new JButton("Enregistrer");
		enregistrerBtn.setFont(new Font("Dialog", Font.BOLD, 16));
		enregistrerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		enregistrerBtn.setBounds(499, 484, 144, 43);
		add(enregistrerBtn);
		
		JButton annulerBtn = new JButton("Actualiser");
		annulerBtn.setFont(new Font("Dialog", Font.BOLD, 16));
		annulerBtn.setBounds(328, 484, 144, 43);
		add(annulerBtn);
		

	

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
	                    	These b=(These ) a;
	                        b.setNomDoc(model.getValueAt(i, 1).toString());
	                        b.setDomaineSoutenance(model.getValueAt(i, 2).toString());
	                        b.setDirecteur(model.getValueAt(i, 3).toString());
	                        b.setUniversite(model.getValueAt(i, 4).toString());
	                        b.setAnneeSoutenance(Integer.parseInt(model.getValueAt(i, 5).toString()));
	                        b.setDatePub(model.getValueAt(i,6).toString());
	                        b.setNumDoc(Integer.parseInt(model.getValueAt(i, 7).toString()));
	                        b.setNbExemplaire(Integer.parseInt(model.getValueAt(i, 8).toString()));
	                        
	                        dao.update(a);
	                    }
	                }
	
	                JOptionPane.showMessageDialog(null, "Toutes les modifications ont été enregistrées avec succès !");
	                chargerTable();
	                
	                
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement : " + ex.getMessage(), 
	                                              "Erreur", JOptionPane.ERROR_MESSAGE);
	                ex.printStackTrace();
	            }
	        }
	    });
		chargerTable() ;
					
		annulerBtn.addActionListener(e -> chargerTable());

}
		
		//méthodes
		
		public void remplirTable(List<Document> liste) { //---> filtrage
		    DefaultTableModel model = (DefaultTableModel) table.getModel();
		    model.setRowCount(0); // On vide la table

		    if (liste != null) {
		        for (Document doc : liste) {
		            // On vérifie si c'est bien un Livre avant de faire le cast pour éviter les erreurs
		            if (doc instanceof These) {
		            	These b=(These ) doc;
		                model.addRow(new Object[] {
		                    b.getId(),
	                        b.getNomDoc(),
	                        b.getDomaineSoutenance(),
	                        b.getDirecteur(),
	                        b.getUniversite(),
	                        b.getAnneeSoutenance(),
	                        b.getDatePub(),
	                        b.getNumDoc(),
	                        b.getNbExemplaire(),
		                    b.isDisponible() ? "Oui" : "Non" // Plus lisible qu'un boolean
		                });
		            }
		        }
		    }
		}

		public void chargerTable() {
		    DocumentDAO dao = new DocumentDAO();
		    List<Document> listetheses = dao.rechercherParType("these");
		  	remplirTable(listetheses);
		}
}

