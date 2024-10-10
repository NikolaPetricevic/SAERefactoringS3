package com.example.zeldasae.modele.collectibles.Useable;

import com.example.zeldasae.modele.collectibles.Collectible;
import com.example.zeldasae.modele.entities.Joueur;

public class Utilisable extends Collectible {

    private UseItem use;

    public Utilisable(int quantite, int quantite_max, String type, int posSlotItems, int large, int haut, int x, int y) {
        super(quantite, quantite_max, type, posSlotItems, large, haut, x, y);
    }
    public void setuse(UseItem use) {
        this.use = use;
    }
    public UseItem getuse() {
        return use;
    }
    public void utiliserItem(Joueur j){
        if(this.use!=null)
            use.utiliserItem(j,this);
    }

}
