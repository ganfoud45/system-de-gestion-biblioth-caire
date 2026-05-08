package tn.bibliotheque.view.DocumentsGestion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SupprimerDocument extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public SupprimerDocument() {
		setBackground(new Color(245, 255, 250));
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
