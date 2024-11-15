package com.example.zeldasae.modele.armes.attaques;

import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.armes.Arme;
import javafx.animation.PauseTransition;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class AttaqueDeBase implements Attaque{

    @Override
    public void executer(KeyEvent keyEvent, Arme arme) {
        Monde map = Monde.getInstance();

        arme.setPosMap(map.getJoueur().getX(), map.getJoueur().getY(), keyEvent);
        arme.checkCoupTouche();
        map.getJoueur().setPeutDonnerCoup(false);

        PauseTransition pause = new PauseTransition(Duration.seconds(map.getJoueur().getInv().getArmeActuelle().getDelaiRecuperation()));
        pause.setOnFinished(event -> map.getJoueur().setPeutDonnerCoup(true));
        pause.play();
    }
}
