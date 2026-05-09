package tn.bibliotheque.view.DocumentsGestion;

import tn.bibliotheque.model.Document;
import tn.bibliotheque.model.Livre;
import tn.bibliotheque.model.Revue;
import tn.bibliotheque.model.Dictionnaire;

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

import tn.bibliotheque.dao.AdherentDAO;
import tn.bibliotheque.dao.DocumentDAO;
import tn.bibliotheque.model.Adherent;
import tn.bibliotheque.model.Dictionnaire;
import tn.bibliotheque.model.Document;

public class AfficherDictionnaires extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table ;
	/**
	 * Create the panel.
	 */
	public AfficherDictionnaires() {
		setBackground(new Color(245, 255, 250));
		setLayout(null);
		
		JLabel title = new JLabel("Liste des Dictionnaires ");
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
				 "Id", "titre", "auteur", "LgSource", "LgCible","datePub", "NbrMots","numDoc","nbrExmp","disponibilité"
			}) {
				@Override
			    public boolean isCellEditable(int row, int column) {
			        // La colonne 0 (Id) n'est pas modifiable, les autres le sont
			        return (column != 0 && column!=8);
			    }}
		);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 77, 666, 365);
		add(scrollPane);
		
		JButton enregistrerBtn = new JButton("Enregistrer");
		enregistrerBtn.setFont(new Font("Dialog", Font.BOLD, 16));
		enregistrerBtn.setBounds(499, 484, 144, 43);
		add(enregistrerBtn);
		
		JButton annulerBtn = new JButton("Actualiser");
		annulerBtn.setFont(new Font("Dialog", Font.BOLD, 16));
		annulerBtn.setBounds(328, 484, 144, 43);
		add(annulerBtn);
		
		//------------listeners--------------------------
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
	                        a.setNomDoc(model.getValueAt(i, 1).toString());
	                        ((Dictionnaire) a).setAuteur(model.getValueAt(i, 2).toString());
	                        ((Dictionnaire) a).setLgSource(model.getValueAt(i, 3).toString());
	                        ((Dictionnaire) a).setLgCible(model.getValueAt(i, 4).toString());
	                        
	                        a.setDatePub(model.getValueAt(i, 5).toString());
	                        ((Dictionnaire) a).setNbrMots(Integer.parseInt(model.getValueAt(i, 6).toString()));
	                        a.setNbExemplaire(Integer.parseInt(model.getValueAt(i, 7).toString()));
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
				
				//----------------------récupérer informations --------------------
	public void remplirTable(List<Document> liste) { //---> filtrage
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0); // On vide la table

	    if (liste != null) {
	        for (Document doc : liste) {
	            if (doc instanceof Dictionnaire) {
	                Dictionnaire a = (Dictionnaire) doc;
	                model.addRow(new Object[] {
	                		a.getId(),
	    	                a.getNomDoc(),
	    	                ((Dictionnaire) a).getAuteur(),
	    	                ((Dictionnaire) a).getLgSource(),
	    	                ((Dictionnaire) a).getLgCible(),
	    	                a.getDatePub(),
	    	                ((Dictionnaire) a).getNbrMots(),
	    	                a.getNumDoc(),
	    	                a.getNbExemplaire(),
	    	                a.isDisponible()? "Oui" : "Non" 
	                });
	            }
	        }
	    }
	}

	public void chargerTable() {
	    DocumentDAO dao = new DocumentDAO();
	    List<Document> listeDico = dao.rechercherParType("Dictionnaire"); 
	    remplirTable(listeDico);
	}		
	


}
