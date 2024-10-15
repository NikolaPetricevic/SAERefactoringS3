package com.example.zeldasae.modele.Projectiles;

import com.example.zeldasae.modele.Monde;

public class DepuisEnnemi implements GestionnaireProjectile {

    @Override
    public void checkCoupTouche(Projectile p) {
        if (p.getHitBox().estDedansHitbox(Monde.getInstance().getJoueur().getHitBox()) && !p.isObstacleTouche()) {
            p.setObstacleTouche(true);
            Monde.getInstance().getJoueur().perdreVie(p.getDegats());
        }
    }
}
