package com.example.zeldasae.modele;

import com.example.zeldasae.modele.entities.Ennemi;
import javafx.scene.input.KeyEvent;

public class ProjectileJoueur extends Projectile {

    public ProjectileJoueur(int degats, int vitesse, int large, int haut, KeyEvent keyEvent, String type, boolean retireEnnemiTouche) {
        super(degats, vitesse, large, haut, keyEvent, type, retireEnnemiTouche);
    }

    @Override
    public void checkCoupTouche() {
        for (Ennemi e : Monde.getInstance().getListeEnnemis()) {
                if (e.getHitBox().estDedansHitbox(super.getHitBox()) && !super.isObstacleTouche() && this.isRetireEnnemiTouche()) {
                    super.setObstacleTouche(true);
                    e.perdreVie(getDegats());
                }
                else if(e.getHitBox().estDedansHitbox(super.getHitBox()) && !super.isObstacleTouche()) {
                    Monde.getInstance().getJoueur().getInv().getArmeActuelle().infligerDegats(e);
            }
        }
    }
}