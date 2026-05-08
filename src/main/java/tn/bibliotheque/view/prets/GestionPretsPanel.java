package tn.bibliotheque.view.prets;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

public class GestionPretsPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private CardLayout cl;

    public GestionPretsPanel() {
        setLayout(null);

        // --- LE PANNEAU CONTENEUR (CARDLAYOUT) ---
        JPanel panelAction = new JPanel();
        panelAction.setBounds(188, 74, 862, 326);
        add(panelAction);

        cl = new CardLayout(0, 0);
        panelAction.setLayout(cl);

        // --- AJOUT DES PAGES (CARTES) ---
        PanelAjouterPret pnlAjout = new PanelAjouterPret();
        panelAction.add(pnlAjout, "pageAjout");

        PanelAnnulerPret pnlAnnulation = new PanelAnnulerPret();
        panelAction.add(pnlAnnulation, "pageAnnuler");

        PanelListePrets pnlListe = new PanelListePrets();
        panelAction.add(pnlListe, "pageListe");

        JPanel pnlModif = new PanelModifierPret();
        pnlModif.setBackground(Color.WHITE);
        pnlModif.setLayout(null);
        panelAction.add(pnlModif, "pageModif");

        // --- LABELS ET TITRES ---
        JLabel lblWelcome = new JLabel("Bonjour:");
        lblWelcome.setForeground(new Color(0, 0, 204));
        lblWelcome.setFont(new Font("Serif", Font.BOLD, 18));
        lblWelcome.setBounds(37, 23, 134, 33);
        add(lblWelcome);

        JLabel lblNomUtilisateur = new JLabel("Gestionnaire de Bibliothèque");
        lblNomUtilisateur.setForeground(new Color(102, 0, 255));
        lblNomUtilisateur.setFont(new Font("Serif", Font.BOLD, 18));
        lblNomUtilisateur.setBounds(198, 23, 385, 33);
        add(lblNomUtilisateur);

        // --- PANNEAU DU MENU GAUCHE ---
        JPanel panelMenu = new JPanel();
        panelMenu.setBounds(10, 74, 172, 326);
        panelMenu.setLayout(null);
        add(panelMenu);

        JButton btnajouterP = new JButton("Ajouter Pret");
        btnajouterP.setFont(new Font("Serif", Font.BOLD, 12));
        btnajouterP.addActionListener(e -> cl.show(panelAction, "pageAjout"));
        btnajouterP.setBounds(0, 39, 172, 40);
        panelMenu.add(btnajouterP);

        JButton btnannulerP = new JButton("Retourner Pret");
        btnannulerP.setFont(new Font("Serif", Font.BOLD, 12));
        btnannulerP.addActionListener(e -> cl.show(panelAction, "pageAnnuler"));
        btnannulerP.setBounds(0, 109, 172, 40);
        panelMenu.add(btnannulerP);

        JButton btnmodifierP = new JButton("Modifier Pret");
        btnmodifierP.setFont(new Font("Serif", Font.BOLD, 12));
        btnmodifierP.addActionListener(e -> cl.show(panelAction, "pageModif"));
        btnmodifierP.setBounds(0, 180, 172, 40);
        panelMenu.add(btnmodifierP);

        JButton btnvoirHP = new JButton("Consulter les prets");
        btnvoirHP.setFont(new Font("Serif", Font.BOLD, 12));
        btnvoirHP.addActionListener(e -> cl.show(panelAction, "pageListe"));
        btnvoirHP.setBounds(0, 246, 172, 40);
        panelMenu.add(btnvoirHP);
    }
}