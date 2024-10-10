package com.example.zeldasae.modele.Projectiles;

import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.entities.Ennemi;

public class DepuisJoueur implements ProjectileTouché{

    @Override
    public void checkCoupTouche(Monde map, Projectile p) {
        for (Ennemi e : map.getListeEnnemis()) {
            if (e.getHitBox().estDedansHitbox(p.getHitBox()) && !p.isObstacleTouche() && p.isRetireEnnemiTouche()) {
                p.setObstacleTouche(true);
                e.perdreVie(p.getDegats());
            }
            else if(e.getHitBox().estDedansHitbox(p.getHitBox()) && !p.isObstacleTouche()) {
                map.getJoueur().getInv().getArmeActuelle().infligerDegats(e);
            }
        }
    }
}
