package com.example.zeldasae.modele.collectibles;

import com.example.zeldasae.modele.collectibles.Useable.UseFruit;
import com.example.zeldasae.modele.collectibles.Useable.Utilisable;
import com.example.zeldasae.modele.entities.Joueur;

public class Fruit extends Utilisable {

    private Joueur joueur;

    public Fruit(int x, int y, Joueur joueur) {
        super(0, 10, "Fruit", 5, 30, 30, x, y);
        this.joueur = joueur;
        super.setuse(new UseFruit());
    }
}