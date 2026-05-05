package tn.bibliotheque.controler;

import tn.bibliotheque.view.Acceuil_bibliothecaire;
import tn.bibliotheque.view.GestionAdherents;

public class AcceuilControleur {

    private Acceuil_bibliothecaire vue;

    public AcceuilControleur(Acceuil_bibliothecaire vue) {
        this.vue = vue;
        initController();
    }

    private void initController() {

        vue.mntmAfficherTous.addActionListener(e -> {
            GestionAdherents gestionVue = new GestionAdherents();
            new AdherentControleur(gestionVue);
            gestionVue.setVisible(true);
        });

        vue.mntmQuitter.addActionListener(e -> {
            vue.dispose();
        });
    }
}