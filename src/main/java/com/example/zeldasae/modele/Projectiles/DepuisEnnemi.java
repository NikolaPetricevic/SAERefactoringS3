package com.example.zeldasae.modele.Projectiles;

import com.example.zeldasae.modele.HitBox;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.entities.Ennemi;

public class DepuisEnnemi implements ProjectileTouché{

    @Override
    public void checkCoupTouche(Projectile p) {
        if (p.getHitBox().estDedansHitbox(Monde.getInstance().getJoueur().getHitBox()) && !p.isObstacleTouche()) {
            p.setObstacleTouche(true);
            Monde.getInstance().getJoueur().perdreVie(p.getDegats());
        }
    }
}
