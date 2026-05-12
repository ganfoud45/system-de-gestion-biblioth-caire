package tn.bibliotheque.view;
import  tn.bibliotheque.view.adherentsGestion.*;
import tn.bibliotheque.view.DocumentsGestion.*;
import tn.bibliotheque.view.prets.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class BibliothecaireView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public BibliothecaireView() {
	    setTitle("Gestion Bibliothèque");
	    setSize(1000, 700);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPane.setLayout(new BorderLayout()); 
	    setContentPane(contentPane);

	    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	    contentPane.add(tabbedPane, BorderLayout.CENTER); 
	    DocumentGestionPanel documentPanel = new DocumentGestionPanel();
	    GestionPretsPanel pretsPanel = new GestionPretsPanel();
	    tabbedPane.addTab("Gestion Documents", documentPanel);
	    tabbedPane.addTab("Gestion Prêts", pretsPanel);
	    
	    	    AdhrentGestionPanel adherentPanel = new AdhrentGestionPanel();
	    	    
	    	    	    tabbedPane.addTab("Gestion Adhérents", adherentPanel);
	}
}
