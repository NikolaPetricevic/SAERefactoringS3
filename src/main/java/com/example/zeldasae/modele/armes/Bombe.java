package com.example.zeldasae.modele.armes;

import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.armes.attaques.AttaqueBombe;
import com.example.zeldasae.modele.collectibles.BombeCollectible;
import com.example.zeldasae.modele.entities.Ennemi;
import javafx.animation.PauseTransition;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class Bombe extends Arme {

    public static final double delaiExplosion = 2;

    public Bombe() {
        super("Bombe", 5, 0, 0, 90, 90, 0, 0, new AttaqueBombe());
    }

    @Override
    public boolean peutAttaquer() {
        if(Monde.getInstance().getJoueur().getInv().getCollectible(new BombeCollectible(0, 0)) != null)
            return Monde.getInstance().getJoueur().getInv().getCollectible(new BombeCollectible(0, 0)).getQuantite() > 0;
        else
            return false;
    }

    @Override
    public void setPosMap(int x, int y, KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case LEFT:
                this.getHitBox().setX(x-(this.getHitBox().getLarge()/3)*2);
                this.getHitBox().setY(y-this.getHitBox().getHaut()/3);
                break;
            case RIGHT:
                this.getHitBox().setX(x);
                this.getHitBox().setY(y-getHitBox().getHaut()/3);
                break;
            case UP:
                this.getHitBox().setX(x-this.getHitBox().getLarge()/3);
                this.getHitBox().setY(y-(this.getHitBox().getHaut()/3)*2);
                break;
            case DOWN:
                this.getHitBox().setX(x-this.getHitBox().getLarge()/3);
                this.getHitBox().setY(y);
                break;
        }
    }

    @Override
    public void checkCoupTouche() {
        Monde map = Monde.getInstance();

        for (Ennemi e : map.getListeEnnemis()) {
            if (e.getHitBox().estDedansHitbox(getHitBox())) {
                infligerDegats(e);
                break;
            }
        }
        if (map.getJoueur().getHitBox().estDedansHitbox(getHitBox())) {
            map.getJoueur().perdreVie(getDegats()-2);
        }
    }
}
