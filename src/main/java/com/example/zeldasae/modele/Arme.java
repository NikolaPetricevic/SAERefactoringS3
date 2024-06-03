package com.example.zeldasae.modele;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyEvent;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.util.ArrayList;

//A REMETTRE ABSTRACT
public class Arme extends Item{

    private int degats;
    private HitBox hitBox;
    private double delaiRecuperation;

    //POUR PLUS TARD : DONNER UNE HITBOX SPECIALE A LA CREATION D'UNE ARME AU LIEU DE METTRE int large, int haut, int x, int y
    public Arme(String nom, int degats, int posSlotItems, double delaiRecuperation, int large, int haut, int x, int y) {
        super(nom, posSlotItems);
        this.degats = degats;
        this.hitBox = new HitBox(large, haut, new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
        this.delaiRecuperation = delaiRecuperation;
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

    public void checkCoupTouche(ArrayList<Ennemi> ennemis) {
       for (Ennemi e : ennemis) {
           if (e.getHitBox().estDedansHitbox(this.hitBox)) {
               e.perdreVie(this.degats);
               System.out.println("Pv de l'ennemi : " + e.getPv());
           }
       }
    }

    public void deplacerProjectile(ArrayList<Ennemi> ennemis) {
        this.getHitBox().setX(this.getX() + 15);
        checkCoupTouche(ennemis);
    }

    public int getDegats() {
        return this.degats;
    }
}
