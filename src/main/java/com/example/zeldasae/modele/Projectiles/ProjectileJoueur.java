package com.example.zeldasae.modele.Projectiles;

import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.entities.Ennemi;
import javafx.scene.input.KeyEvent;

public class ProjectileJoueur extends Projectile {

    public ProjectileJoueur(int degats, int vitesse, int large, int haut, KeyEvent keyEvent, String type, boolean retireEnnemiTouche) {
        super(degats, vitesse, large, haut, keyEvent, type, retireEnnemiTouche);
    }
}