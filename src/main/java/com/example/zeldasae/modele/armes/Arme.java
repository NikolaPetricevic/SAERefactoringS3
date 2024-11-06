package com.example.zeldasae.modele.armes;

import com.example.zeldasae.modele.HitBox;
import com.example.zeldasae.modele.Item;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.armes.attaques.Attaque;
import com.example.zeldasae.modele.entities.Ennemi;
import com.example.zeldasae.modele.entities.Joueur;
import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public abstract class Arme extends Item {

    private int degats;
    private HitBox hitBox;
    private double delaiRecuperation;
    private Attaque attaque;

    public Arme(String nom, int degats, int posSlotItems, double delaiRecuperation, int large, int haut, int x, int y, Attaque attaque) {
        super(nom, posSlotItems);
        this.degats = degats;
        this.hitBox = new HitBox(large, haut, new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
        this.delaiRecuperation = delaiRecuperation;
        this.attaque = attaque;
    }

    public int getX() {
        return this.getHitBox().getX();
    }

    public int getY() {
        return this.getHitBox().getY();
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public double getDelaiRecuperation() {
        return delaiRecuperation;
    }

    public int getDegats() {
        return this.degats;
    }

    public void setPosMap(int x, int y, KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case LEFT:
                this.hitBox.setX(x-this.hitBox.getLarge());
                this.hitBox.setY(y);
                break;
            case RIGHT:
                this.hitBox.setX(x+30);
                this.hitBox.setY(y);
                break;
            case UP:
                this.hitBox.pivote();
                this.hitBox.setX(x+10);
                this.hitBox.setY(y-this.hitBox.getHaut());
                break;
            case DOWN:
                this.hitBox.pivote();
                this.hitBox.setX(x+10);
                this.hitBox.setY(y+30);
                break;
        }
    }

    public void checkCoupTouche() {
       for (Ennemi e : Monde.getInstance().getListeEnnemis()) {
           if (e.getHitBox().estDedansHitbox(this.hitBox)) {
               infligerDegats(e);
           }
       }
    }

    public void attaquer(KeyEvent keyEvent) {
        this.attaque.executer(keyEvent, this);
    }

    public boolean peutAttaquer() {
        return true;
    }

    @Override
    public void utiliserItem(Joueur j) {
        j.getInv().changerArme(this);
    }

    public void infligerDegats(Ennemi e) {
        e.perdreVie(getDegats());
    }
}
