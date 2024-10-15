package com.example.zeldasae.modele.Projectiles;

import com.example.zeldasae.modele.HitBox;
import com.example.zeldasae.modele.Terrain;
import com.example.zeldasae.modele.Direction;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyEvent;

public abstract class Projectile {

    private String nom;
    public static int compteur = 0;
    private int degats;
    private HitBox hitBox;
    private boolean obstacleTouche;
    private boolean retireEnnemiTouche;
    private String type;
    private GestionnaireProjectile projectileTouch;
    private DeplacementProjectile dp;
    public Projectile(int degats, int vitesse, int large, int haut, KeyEvent keyEvent, String type, boolean retireEnnemiTouche) { //Constructeur du joueur
        this.nom = "Proj" + compteur;
        this.degats = degats;
        this.hitBox = new HitBox(large, haut, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        this.obstacleTouche = false;
        this.type = type;
        this.retireEnnemiTouche = retireEnnemiTouche;
        this.projectileTouch = new DepuisJoueur();
        this.dp = new DeplacementProjectile(vitesse,Direction.stringToDirection(keyEvent.getCode().toString()),this);
        compteur++;
    }

    public Projectile(int degats, int vitesse, int large, int haut, String type) {
        this.nom = "Proj" + compteur;
        this.degats = degats;
        this.hitBox = new HitBox(large, haut, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        this.obstacleTouche = false;
        this.type = type;
        this.projectileTouch = new DepuisEnnemi();
        this.dp = new DeplacementProjectile(vitesse,this);
        compteur++;
    }

    public int getDegats() {
        return degats;
    }
    public HitBox getHitBox() {
        return this.hitBox;
    }
    public String getNom() {
        return this.nom;
    }
    public Direction getDirection() {
        return this.dp.getDirection();
    }
    public void setDirection(Direction direction) {
        this.dp.setDirection(direction);
    }
    public boolean isObstacleTouche() {
        return this.obstacleTouche;
    }
    public void setObstacleTouche(boolean obstacleTouche) {
        this.obstacleTouche = obstacleTouche;
    }
    public boolean isRetireEnnemiTouche() {
        return retireEnnemiTouche;
    }
    public String getType() {
        return type;
    }
    public DeplacementProjectile getDeplacementProjectile() {
        return this.dp;
    }
    public void setPosMap(int x, int y, String keyEvent) {
        Direction direction = Direction.stringToDirection(keyEvent);
        if (direction.isHorizontal()) {
            this.hitBox.setY(y);
            if (direction == Direction.LEFT) this.hitBox.setX(x-this.hitBox.getLarge());
            else this.hitBox.setX(x+30);
        }
        else {
            this.hitBox.pivote();
            this.hitBox.setX(x+10);
            if (direction == Direction.UP) this.hitBox.setY(y-this.hitBox.getHaut());
            else this.hitBox.setY(y+30);
        }
    }


    public void checkCoupTouche(){
        this.projectileTouch.checkCoupTouche(this);
    }

    public boolean dansMap(Terrain terrain) {
        return terrain.vide((this.getHitBox().getX() / 30) + (this.getHitBox().getY() / 30 * terrain.getRows()));
    }


    public boolean aRetirer(Terrain terrain) {
        return isObstacleTouche() || !dansMap(terrain);
    }
}