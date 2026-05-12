package tn.bibliotheque.view.adherentsGestion;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import tn.bibliotheque.dao.AdherentDAO;
import tn.bibliotheque.model.Adherent;

import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SupprimerAdherentPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private AdhrentGestionPanel parent;


	/**
	 * Create the panel.
	 */
	public SupprimerAdherentPanel() {
		this.parent = parent;

		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		
		JLabel title = new JLabel("Supprimer Un Adhérent ");
		title.setForeground(new Color(0, 0, 205));
		title.setBackground(new Color(238, 238, 238));
		title.setFont(new Font("Century Schoolbook L", Font.BOLD, 35));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(124, 35, 487, 35);
		add(title);
		
		JLabel Idlbl = new JLabel("Id_adhérent  :");
		Idlbl.setFont(new Font("Century Schoolbook L", Font.BOLD, 25));
		Idlbl.setBounds(124, 170, 205, 34);
		add(Idlbl);
		
		JFormattedTextField IdTf = new JFormattedTextField();
		IdTf.setFont(new Font("Dialog", Font.PLAIN, 20));
		IdTf.setBounds(377, 167, 107, 43);
		add(IdTf);
		
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
		
		//-----------listeners---------------------
		
		confirmerBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String idSaisi = IdTf.getText().trim();

		        if (idSaisi.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Veuillez saisir l'ID de l'adhérent.", "Champ vide", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        try {
		            int idAdherent = Integer.parseInt(idSaisi);
		            AdherentDAO dao = new AdherentDAO();
		            Adherent a = dao.findById(idAdherent);

		            if (a == null) {
		                JOptionPane.showMessageDialog(null, "Aucun adhérent trouvé avec l'ID : " + idAdherent, "Erreur", JOptionPane.ERROR_MESSAGE);
		            } else {
		                int choix = JOptionPane.showConfirmDialog(
		                    null, 
		                    "Voulez-vous vraiment supprimer l'adhérent : " + a.getNom() + " " + a.getPrenom() + " ?",
		                    "Confirmation",
		                    JOptionPane.YES_NO_OPTION
		                );

		                if (choix == JOptionPane.YES_OPTION) {
		                    dao.delete(idAdherent);
		                    JOptionPane.showMessageDialog(null, "Adhérent supprimé avec succès !");
		                    IdTf.setText("");
		                }
		            }

		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "L'ID doit être un nombre entier valide.", "Erreur format", JOptionPane.ERROR_MESSAGE);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la suppression : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
		    }
		});
		annulerBtn.addActionListener(e->{
			parent.cardLayout.show(parent.conteneur, "ACCEUIL");
		});
	
	}	
}
