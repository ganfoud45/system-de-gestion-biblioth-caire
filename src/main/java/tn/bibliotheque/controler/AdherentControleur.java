package tn.bibliotheque.controler;

import tn.bibliotheque.view.GestionAdherents;
import tn.bibliotheque.dao.AdherentDAO;
import tn.bibliotheque.model.Adherent;

import java.util.List;

public class AdherentControleur {

    private GestionAdherents vue;
    private AdherentDAO dao;

    public AdherentControleur(GestionAdherents vue) {
        this.vue = vue;
        this.dao = new AdherentDAO();

        initController();
        chargerDonnees();
    }

    private void initController() {

        vue.btnActualiser.addActionListener(e -> chargerDonnees());

        vue.btnEnregistrer.addActionListener(e -> enregistrer());

        vue.btnSupprimer.addActionListener(e -> supprimer());

        vue.btnRetour.addActionListener(e -> vue.dispose());
    }

    private void chargerDonnees() {
        List<Adherent> list = dao.getAll();
        vue.remplirTable(list);
    }

    private void enregistrer() {

        if (vue.table.isEditing()) {
            vue.table.getCellEditor().stopCellEditing();
        }

        for (int i = 0; i < vue.tableModel.getRowCount(); i++) {

            String nom = (String) vue.tableModel.getValueAt(i, 1);
            String prenom = (String) vue.tableModel.getValueAt(i, 2);

            if (nom.trim().isEmpty() || prenom.trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(vue,
                        "Nom et prénom obligatoires !");
                return;
            }

            Adherent a = new Adherent();
            a.setId((int) vue.tableModel.getValueAt(i, 0));
            a.setNom(nom);
            a.setPrenom(prenom);
            a.setEmail((String) vue.tableModel.getValueAt(i, 3));
            a.setNumTel((int) vue.tableModel.getValueAt(i, 4));

            dao.update(a);
        }

        chargerDonnees();
    }

    private void supprimer() {

        int row = vue.table.getSelectedRow();

        if (row == -1) {
            javax.swing.JOptionPane.showMessageDialog(vue,
                    "Sélectionnez un adhérent !");
            return;
        }

        int id = (int) vue.tableModel.getValueAt(row, 0);
        dao.delete(id);

        chargerDonnees();
    }
}