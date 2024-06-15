package com.example.zeldasae.modele.entities;

import com.example.zeldasae.modele.Coffre;
import com.example.zeldasae.modele.Inventaire;
import com.example.zeldasae.modele.Monde;
import javafx.animation.PauseTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class Joueur extends Entite {

    private Inventaire inv;
    private BooleanProperty peutDonnerCoupProperty;
    private BooleanProperty peutPrendreCoupProperty;

    public Joueur(int x, int y, int column, int rows) {
        super(x, y, "j1", 30, 30, column, rows, 10);
        this.inv = new Inventaire();
        this.peutDonnerCoupProperty = new SimpleBooleanProperty(true);
        this.peutPrendreCoupProperty = new SimpleBooleanProperty(true);
    }

    public Inventaire getInv() {
        return this.inv;
    }

    public boolean getPeutDonnerCoupProperty() {
        return this.peutDonnerCoupProperty.get();
    }
    public void setPeutDonnerCoupProperty(boolean peutDonnerCoupProperty) {
        this.peutDonnerCoupProperty.set(peutDonnerCoupProperty);
    }
    public BooleanProperty peutDonnerCoupProperty() {
        return peutDonnerCoupProperty;
    }

    public boolean getPeutPrendreCoupProperty(){
        return peutPrendreCoupProperty.getValue();
    }
    public void setPeutPrendreCoupProperty(boolean peutPrendreCoupProperty) {
        this.peutPrendreCoupProperty.set(peutPrendreCoupProperty);
    }
    public BooleanProperty peutPrendreCoupPropertyProperty() {
        return peutPrendreCoupProperty;
    }

    @Override
    public void perdreVie(int degats) {
        if (getPeutPrendreCoupProperty()) {
            super.perdreVie(degats);
            setPeutPrendreCoupProperty(false);

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> setPeutPrendreCoupProperty(true));
            pause.play();
        }
    }

    public void attaquer(KeyEvent keyEvent, Monde map) {
        this.setPeutDonnerCoupProperty(false);
        this.getInv().getArmeActuelle().attaquer(keyEvent, map);
    }

    @Override
    public boolean deplacement(Monde m) {
        boolean deplacement = super.deplacement(m);
        if (m.getTerrain().isBrouillard(m.getTerrain().changeCoo(getX(), getY())) && !inv.possedeCharme()) {
            if (this.getPv()/2 != 0)
                this.perdreVie(this.getPv() / 2);
            else this.perdreVie(this.getPv());
            this.setVitesse(1);
        }
        else
            this.setVitesse(10);
        return deplacement;
    }

    public boolean peutOuvrirUnCoffre(Monde monde, Coffre coffre, int tailleBloc) {
        for (Coffre c : monde.getCoffres()) {
            if (coffre.getId() == c.getId() && this.getHitBox().coffreProximite(c, tailleBloc)) {
                return true;
            }
        }
        return false;
    }
}
