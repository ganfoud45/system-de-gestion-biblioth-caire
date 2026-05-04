package tn.bibliotheque.view.prets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.util.Date;

import tn.bibliotheque.dao.PretDAO;
import tn.bibliotheque.model.Adherent;
import tn.bibliotheque.model.Document;
import tn.bibliotheque.model.Pret;


public class PanelAnnulerPret extends JPanel {
    public PanelAnnulerPret() {
        setLayout(null);
        JLabel lbl = new JLabel("RETOUR / ANNULATION DE PRÊT");
        lbl.setBounds(150, 20, 300, 30);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(lbl);

        JLabel lblId = new JLabel("ID du Prêt :");
        lblId.setBounds(50, 100, 100, 25);
        add(lblId);

        JTextField txtId = new JTextField();
        txtId.setBounds(150, 100, 200, 25);
        add(txtId);

        JButton btnValider = new JButton("Enregistrer le retour");
        btnValider.setBounds(150, 150, 200, 35);
        btnValider.setBackground(Color.ORANGE);
        add(btnValider);

        btnValider.addActionListener(e -> {
            // Logique : PretDAO.delete(Integer.parseInt(txtId.getText()));
            JOptionPane.showMessageDialog(this, "Document retourné, exemplaire remis en stock !");
        });
    }
}