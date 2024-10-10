package com.example.zeldasae.modele.collectibles.Useable;

import com.example.zeldasae.modele.armes.Bombe;
import com.example.zeldasae.modele.collectibles.Collectible;
import com.example.zeldasae.modele.entities.Joueur;

public class UseBombe implements UseItem {
    @Override
    public void utiliserItem(Joueur j, Collectible c) {
        Bombe b = new Bombe();
        j.getInv().changerArme(b);
    }
}
