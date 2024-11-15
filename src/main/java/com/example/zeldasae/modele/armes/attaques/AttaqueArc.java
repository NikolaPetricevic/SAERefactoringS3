package com.example.zeldasae.modele.armes.attaques;

import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.Projectiles.Projectile;
import com.example.zeldasae.modele.Projectiles.ProjectileJoueur;
import com.example.zeldasae.modele.armes.Arme;
import com.example.zeldasae.modele.collectibles.Fleche;
import javafx.animation.PauseTransition;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class AttaqueArc implements Attaque {

    @Override
    public void executer(KeyEvent keyEvent, Arme arme) {
        Monde map = Monde.getInstance();
        map.getJoueur().getInv().getCollectible(new Fleche(0, 0)).retirer(1);

        Projectile p = creerProjectile(keyEvent, arme);
        map.addProjectile(p);

        map.getJoueur().setPeutDonnerCoup(false);

        PauseTransition pause = new PauseTransition(Duration.seconds(map.getJoueur().getInv().getArmeActuelle().getDelaiRecuperation()));
        pause.setOnFinished(event -> map.getJoueur().setPeutDonnerCoup(true));
        pause.play();
    }

    public Projectile creerProjectile(KeyEvent keyEvent, Arme arme) {
        Projectile fleche = new ProjectileJoueur(arme.getDegats(), 25, 20, 10, keyEvent, "Fleche", true);
        fleche.setPosMap(Monde.getInstance().getJoueur().getHitBox().getX(), Monde.getInstance().getJoueur().getHitBox().getY(), keyEvent.getCode().toString());
        return fleche;
    }
}
