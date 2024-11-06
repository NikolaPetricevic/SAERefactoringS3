package com.example.zeldasae.modele.armes;

import com.example.zeldasae.modele.*;
import com.example.zeldasae.modele.Projectiles.Projectile;
import com.example.zeldasae.modele.Projectiles.ProjectileJoueur;
import com.example.zeldasae.modele.armes.attaques.AttaqueArc;
import com.example.zeldasae.modele.collectibles.Fleche;
import javafx.animation.PauseTransition;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class Arc extends Arme {

    private Monde map = Monde.getInstance();

    public Arc() {
        super("Arc", 2, 4, 0.8, 0, 0, 0, 0, new AttaqueArc());
    }

    @Override
    public boolean peutAttaquer() {
        if (map.getJoueur().getInv().getCollectible(new Fleche(0, 0)) != null)
            return map.getJoueur().getInv().getCollectible(new Fleche(0, 0)).getQuantite() > 0;
        else
            return false;
    }

}
