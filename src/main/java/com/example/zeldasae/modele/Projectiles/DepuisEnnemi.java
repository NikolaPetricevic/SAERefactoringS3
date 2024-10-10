package com.example.zeldasae.modele.Projectiles;

import com.example.zeldasae.modele.HitBox;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.entities.Ennemi;

public class DepuisEnnemi implements ProjectileTouché{


    @Override
    public void checkCoupTouche(Monde map, Projectile p) {
        if (p.getHitBox().estDedansHitbox(map.getJoueur().getHitBox()) && !p.isObstacleTouche()) {
            p.setObstacleTouche(true);
            map.getJoueur().perdreVie(p.getDegats());
        }
    }
}
