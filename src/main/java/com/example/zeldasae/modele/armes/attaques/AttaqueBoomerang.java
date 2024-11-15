package com.example.zeldasae.modele.armes.attaques;

import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.Projectiles.Projectile;
import com.example.zeldasae.modele.Projectiles.ProjectileJoueur;
import com.example.zeldasae.modele.armes.Arme;
import com.example.zeldasae.modele.armes.Boomerang;
import javafx.animation.PauseTransition;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class AttaqueBoomerang implements Attaque{

    @Override
    public void executer(KeyEvent keyEvent, Arme arme) {
//((Cat) animal).meow();

        Monde map = Monde.getInstance();
        Projectile p = creerProjectile(keyEvent, arme);
        map.addProjectile(p);

        map.getJoueur().setPeutDonnerCoup(false);

        PauseTransition pause1 = new PauseTransition(Duration.seconds(map.getJoueur().getInv().getArmeActuelle().getDelaiRecuperation()));
        pause1.setOnFinished(event -> ((Boomerang) arme).supprimerBoomerang(p));
        pause1.play();

        PauseTransition pause2 = new PauseTransition(Duration.seconds(1.25));
        pause2.setOnFinished(event -> ((Boomerang) arme).inverserBoomerang(p));
        pause2.play();
    }

    public Projectile creerProjectile(KeyEvent keyEvent, Arme arme) {
        Projectile boomerang = new ProjectileJoueur(arme.getDegats(), 30, 20, 20, keyEvent, "Boomerang", false);
        boomerang.setPosMap(Monde.getInstance().getJoueur().getHitBox().getX(), Monde.getInstance().getJoueur().getHitBox().getY(), keyEvent.getCode().toString());
        return boomerang;
    }
}
