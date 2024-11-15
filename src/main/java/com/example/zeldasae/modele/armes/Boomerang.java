package com.example.zeldasae.modele.armes;

import com.example.zeldasae.modele.*;
import com.example.zeldasae.modele.Projectiles.Projectile;
import com.example.zeldasae.modele.Projectiles.ProjectileJoueur;
import com.example.zeldasae.modele.armes.attaques.AttaqueBoomerang;
import com.example.zeldasae.modele.entities.Ennemi;
import javafx.animation.PauseTransition;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.ArrayList;

public class Boomerang extends Arme {

    private ArrayList<Ennemi> ennemisTouchesAller;

    public Boomerang() {
        super("Boomerang", 2, 6, 2.5, new AttaqueBoomerang());
        this.ennemisTouchesAller = new ArrayList<>();
        setHitBox(0, 0);
    }

    public void inverserBoomerang(Projectile p) {
        p.getDeplacementProjectile().inverserDirection();
        this.ennemisTouchesAller.clear();
    }

    public void supprimerBoomerang(Projectile p) {
        Monde.getInstance().getJoueur().setPeutDonnerCoup(true);
        p.setObstacleTouche(true);
        this.ennemisTouchesAller.clear();
    }

    public void infligerDegats(Ennemi e) {
        if (!dansEnnemisTouchesAller(e)) {
            e.perdreVie(getDegats());
            this.ennemisTouchesAller.add(e);
        }
    }

    public boolean dansEnnemisTouchesAller(Ennemi e) {
        for (Ennemi ennemi : this.ennemisTouchesAller) {
            if (ennemi == e) {
                return true;
            }
        }
        return false;
    }
}