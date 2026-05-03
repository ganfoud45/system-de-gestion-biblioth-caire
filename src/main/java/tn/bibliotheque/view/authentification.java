package tn.bibliotheque.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Authentification extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification frame = new Authentification();
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
	public Authentification() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 907, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("🎉WELCOME TO OUR LIBRARY🎉 ");
		lblNewLabel.setForeground(new Color(102, 51, 204));
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 30));
		lblNewLabel.setBounds(207, 10, 511, 74);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("USERNAME");
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(172, 114, 116, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("PASSWORD");
		lblNewLabel_1_1.setFont(new Font("Serif", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(176, 181, 93, 28);
		contentPane.add(lblNewLabel_1_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(338, 118, 242, 28);
		contentPane.add(textArea);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(338, 184, 242, 28);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("LOG IN");
		btnNewButton.setFont(new Font("Serif", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(497, 243, 121, 46);
		contentPane.add(btnNewButton);
		
		JButton btnClose = new JButton("CLOSE");
		btnClose.setFont(new Font("Serif", Font.BOLD, 15));
		btnClose.setBounds(301, 243, 121, 46);
		contentPane.add(btnClose);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setForeground(new Color(255, 0, 51));
		lblNewLabel_2.setFont(new Font("Serif", Font.BOLD, 15));
		lblNewLabel_2.setBounds(207, 299, 471, 91);
		contentPane.add(lblNewLabel_2);

	}
}
