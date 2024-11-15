package com.example.zeldasae.modele.armes;

import com.example.zeldasae.modele.armes.attaques.Attaque;

public abstract class ArmePic extends Arme {

    private Arme arme;

    public ArmePic(Arme arme) {
        super(arme.getNom(), arme.getDegats(), arme.getPosSlotItems(), arme.getDelaiRecuperation(), arme.getAttaque());
        this.arme = arme;
    }

    @Override
    public int getDegats() {
        return arme.getDegats()+2;
    }
}
