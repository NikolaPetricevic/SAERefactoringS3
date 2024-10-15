package com.example.zeldasae.modele;

import com.example.zeldasae.modele.entities.Joueur;

public class Charme extends Item{
    public Charme() {
        super("charme", 1);
    }

    public boolean possedeCharme(Inventaire inventaire){
        for (Item i : inventaire.getListeItems()){
            if (i instanceof Charme)
                return true;
        }
        return false;
    }

    @Override
    public void utiliserItem(Joueur j) {

    }
}
