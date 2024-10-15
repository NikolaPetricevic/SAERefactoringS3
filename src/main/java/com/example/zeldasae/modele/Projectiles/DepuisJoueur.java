package com.example.zeldasae.modele.Projectiles;

import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.entities.Ennemi;

public class DepuisJoueur implements GestionnaireProjectile {

    @Override
    public void checkCoupTouche(Projectile p) {
        for (Ennemi e : Monde.getInstance().getListeEnnemis()) {
            if (e.getHitBox().estDedansHitbox(p.getHitBox()) && !p.isObstacleTouche() && p.isRetireEnnemiTouche()) {
                p.setObstacleTouche(true);
                e.perdreVie(p.getDegats());
            }
            else if(e.getHitBox().estDedansHitbox(p.getHitBox()) && !p.isObstacleTouche()) {
                Monde.getInstance().getJoueur().getInv().getArmeActuelle().infligerDegats(e);
            }
        }
    }
}
