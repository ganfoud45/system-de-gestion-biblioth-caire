package tn.bibliotheque.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class GestionDocuments extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionDocuments frame = new GestionDocuments();
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
	public GestionDocuments() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JButton back = new JButton("Retournner");
		back.setFont(new Font("Serif", Font.BOLD, 13));
		back.setBounds(576, 28, 122, 27);
		contentPane.add(back);
		back.addActionListener(e->{
			dispose();
        	SwingUtilities.invokeLater(() -> {
            	BibliothecaireAcces dash = new BibliothecaireAcces();
                dash.setVisible(true);
		});
		});

	}

}
