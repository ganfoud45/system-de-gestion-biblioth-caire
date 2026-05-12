package tn.bibliotheque.view.DocumentsGestion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import tn.bibliotheque.dao.DocumentDAO;
import tn.bibliotheque.model.Document;

public class SupprimerDocument extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public SupprimerDocument() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		
		JLabel title = new JLabel("Supprimer Un Document ");
		title.setForeground(new Color(0, 0, 205));
		title.setBackground(new Color(238, 238, 238));
		title.setFont(new Font("Century Schoolbook L", Font.BOLD, 35));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(124, 35, 487, 35);
		add(title);
		
		JLabel Idlbl = new JLabel("Id_document  :");
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
		        String idSaisi = IdTf.getText().trim();
		        if (idSaisi.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Veuillez saisir l'ID du document.",
		                "Champ vide", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        try {
		            int id = Integer.parseInt(idSaisi);
		            DocumentDAO dao = new DocumentDAO();
		            Document doc = dao.findById(id);
		            if (doc == null) {
		                JOptionPane.showMessageDialog(null, "Aucun document trouvé avec l'ID : " + id,
		                    "Erreur", JOptionPane.ERROR_MESSAGE);
		            } else {
		                int choix = JOptionPane.showConfirmDialog(null,
		                    "Voulez-vous vraiment supprimer : " + doc.getNomDoc() + " ?",
		                    "Confirmation", JOptionPane.YES_NO_OPTION);
		                if (choix == JOptionPane.YES_OPTION) {
		                    dao.delete(id);
		                    JOptionPane.showMessageDialog(null, "Document supprimé avec succès !");
		                    IdTf.setText("");
		                }
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "L'ID doit être un entier valide.",
		                "Erreur format", JOptionPane.ERROR_MESSAGE);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage(),
		                "Erreur", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
		    }
		});
		confirmerBtn.setBounds(499, 484, 144, 43);
		add(confirmerBtn);
		
		JButton annulerBtn = new JButton("annuler");
		annulerBtn.setFont(new Font("Dialog", Font.BOLD, 16));
		annulerBtn.setBounds(328, 484, 144, 43);
		add(annulerBtn);

	}

}
