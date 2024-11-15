package com.example.zeldasae.modele.armes.attaques;

import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.armes.Arme;
import com.example.zeldasae.modele.collectibles.BombeCollectible;
import javafx.animation.PauseTransition;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import static com.example.zeldasae.modele.armes.Bombe.delaiExplosion;

public class AttaqueBombe implements Attaque {

    @Override
    public void executer(KeyEvent keyEvent, Arme arme) {
        Monde map = Monde.getInstance();

        map.getJoueur().getInv().getCollectible(new BombeCollectible(0, 0)).retirer(1);
        arme.setPosMap(map.getJoueur().getX(), map.getJoueur().getY(), keyEvent);

        PauseTransition pause = new PauseTransition(Duration.seconds(delaiExplosion));
        pause.setOnFinished(event -> arme.checkCoupTouche());
        pause.play();

        map.getJoueur().setPeutDonnerCoup(true);
    }
}
