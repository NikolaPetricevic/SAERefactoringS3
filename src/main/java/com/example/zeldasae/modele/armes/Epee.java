package com.example.zeldasae.modele.armes;

import com.example.zeldasae.modele.armes.attaques.AttaqueDeBase;

public class Epee extends Arme {

    public Epee() {
        super("Epee", 2, 2, 0.5, new AttaqueDeBase());
        this.setHitBox(40, 30);
    }

}