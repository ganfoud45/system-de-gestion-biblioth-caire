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
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;


public class AfficherAdherentsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private AdhrentGestionPanel parent;

	private JTable table;
	
	
	
	
	/**
	 * Create the panel.
	 */
	public AfficherAdherentsPanel() {
		
		this.parent = parent;

		setBackground(new Color(245, 255, 250));
		setLayout(null);
		
		JLabel lblAjouterUnAdhrent = new JLabel("Liste des Adhérents ");
		lblAjouterUnAdhrent.setForeground(new Color(0, 0, 205));
		lblAjouterUnAdhrent.setBackground(new Color(238, 238, 238));
		lblAjouterUnAdhrent.setFont(new Font("Century Schoolbook L", Font.BOLD, 35));
		lblAjouterUnAdhrent.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjouterUnAdhrent.setBounds(101, 30, 487, 35);
		add(lblAjouterUnAdhrent);
		
		
		table = new JTable();
		table.setFont(new Font("Dialog", Font.PLAIN, 13));
		table.setBackground(new Color(255, 255, 255));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				 "Id", "Nom", "Prénom", "Date naissance", "Email", "N_téléphone", "N_abonnement ","Mot de passe"
			}) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		        // La colonne 0 (Id) n'est pas modifiable, les autres le sont
		        return column != 0;
		    }}
		);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		chargerTable();
		
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
	                AdherentDAO dao = new AdherentDAO();
	                DefaultTableModel model = (DefaultTableModel) table.getModel();
	                int rowCount = model.getRowCount();

	                for (int i = 0; i < rowCount; i++) {
	                    int id = (int) model.getValueAt(i, 0);
	                    Adherent a = dao.findById(id);

	                    if (a != null) {
	                        a.setNom(model.getValueAt(i, 1).toString());
	                        a.setPrenom(model.getValueAt(i, 2).toString());
	                        a.setDateNaissance(model.getValueAt(i, 3).toString());
	                        a.setEmail(model.getValueAt(i, 4).toString());
	                        
	                        a.setNumTel(Integer.parseInt(model.getValueAt(i, 5).toString()));
	                        a.setNumAbonnement(Integer.parseInt(model.getValueAt(i, 6).toString()));
	                        a.setMotDePasse(model.getValueAt(i, 7).toString());

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
		
		annulerBtn.addActionListener(e -> chargerTable());
	
	
	}
		
		//----------------------récupérer informations --------------------
		
		public void chargerTable() {

		    AdherentDAO dao = new AdherentDAO();

		    DefaultTableModel model =
		            (DefaultTableModel) table.getModel();

		    model.setRowCount(0);

		    List<Adherent> liste = dao.getAll();

		    for (Adherent a : liste) {

		        model.addRow(new Object[] {

		                a.getId(),
		                a.getNom(),
		                a.getPrenom(),
		                a.getDateNaissance(),
		                a.getEmail(),
		                a.getNumTel(),
		                a.getNumAbonnement(),
		                a.getMotDePasse()
		        });
		    }
		}
		//pour le filtrage
	    public void remplirTable(List<Adherent> liste) {
	    	DefaultTableModel model =(DefaultTableModel) table.getModel();
	        model.setRowCount(0); // On vide la table
	        if (liste != null) {
	            for (Adherent a : liste) {
	                model.addRow(new Object[] {
	                    a.getId(), a.getNom(), a.getPrenom(), a.getDateNaissance(), 
	                    a.getEmail(), a.getNumTel(), a.getNumAbonnement(), a.getMotDePasse()
	                });
	            }
	        }
	    }
	    
		    
		    
		

	
}