package com.example.zeldasae.modele;

public class ProjectileEnnemi extends Projectile {

    public ProjectileEnnemi(int degats, int vitesse, int large, int haut, String type) {
        super(degats, vitesse, large, haut, type);
    }

    @Override
    public void checkCoupTouche() {
        if (this.getHitBox().estDedansHitbox(Monde.getInstance().getJoueur().getHitBox()) && !super.isObstacleTouche()) {
            super.setObstacleTouche(true);
            Monde.getInstance().getJoueur().perdreVie(getDegats());
        }
    }


}
