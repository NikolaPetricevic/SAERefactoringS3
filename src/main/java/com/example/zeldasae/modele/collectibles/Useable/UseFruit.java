package com.example.zeldasae.modele.collectibles.Useable;

import com.example.zeldasae.modele.collectibles.Collectible;
import com.example.zeldasae.modele.entities.Joueur;

public class UseFruit implements UseItem {

    @Override
    public void utiliserItem(Joueur j, Collectible c) {
        if (c.getQuantite() > 0 && j.getPv() + 1 <= j.getPvMax()) {
            c.retirer(1);
            j.ajouterVie(1);
        }
    }
}
