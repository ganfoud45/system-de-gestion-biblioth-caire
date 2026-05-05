package tn.bibliotheque.test;

import java.awt.EventQueue;

import tn.bibliotheque.view.Authentification;

public class TestApp {

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
}
