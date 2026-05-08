package tn.bibliotheque.view;
import  tn.bibliotheque.view.adherentsGestion.*;
import tn.bibliotheque.view.DocumentsGestion.*;
import tn.bibliotheque.view.prets.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class BibliothecaireView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BibliothecaireView frame = new BibliothecaireView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		AdhrentGestionPanel adherentPanel=new AdhrentGestionPanel();
		DocumentGestionPanel documentPanel=new DocumentGestionPanel();
		
		tabbedPane.addTab("Gestion Adhérents",adherentPanel);
		tabbedPane.addTab("Gestion Documents",documentPanel);
		
		//Gestion prets panel
		
		
	}

}
