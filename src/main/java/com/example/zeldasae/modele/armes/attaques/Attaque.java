package com.example.zeldasae.modele.armes.attaques;

import com.example.zeldasae.modele.armes.Arme;
import javafx.scene.input.KeyEvent;

public interface Attaque {

    public void executer(KeyEvent keyEvent, Arme arme);
}
