package com.example.zeldasae.modele.entities;

import com.example.zeldasae.modele.Direction;
import com.example.zeldasae.modele.HitBox;
import javafx.beans.property.*;

import java.util.ArrayList;

public abstract class Entite {

    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private String id;
    private int width;
    private int height;
    private int column;
    private int rows;
    private HitBox hitBox;
    private static int n = 0;
    private IntegerProperty pv;
    private int pvMax;
    private int degats;
    private boolean bouge;
    private DeplacementEntite de;

    public Entite(int x, int y, int width, int height, int column, int rows, int pvMax) {
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.id = ""+n++;
        this.width = width;
        this.height = height;
        this.column = column;
        this.rows = rows;
        this.hitBox = new HitBox(this.width, this.height, this.xProperty, this.yProperty);
        this.pvMax = pvMax;
        this.pv = new SimpleIntegerProperty(this.pvMax);
        this.degats = 1;
        this.bouge = false;
        this.de = new DeplacementEntite(this,10,new ArrayList<>());
    }

    public Entite(int x, int y, String id, int width, int height, int column, int rows, int pvMax) {
        this(x, y, width, height, column, rows, pvMax);
        this.setId(id);
    }

    public int getX() {
        return this.xProperty.getValue();
    }
    public void setX(int n) {
        this.xProperty.setValue(n);
    }
    public IntegerProperty xProperty() {
        return xProperty;
    }

    public int getY() {
        return yProperty.getValue();
    }
    public void setY(int y) {
        this.yProperty.setValue(y);
    }
    public IntegerProperty yProperty() {
        return yProperty;
    }

    public void setPv(int pv) {
        this.pv.setValue(pv);
    }
    public IntegerProperty pvProperty() {
        return this.pv;
    }
    public StringProperty directionProperty() {
        return de.directionProperty();
    }
    public void setDirection(String direction) {
        this.de.directionProperty().setValue(direction);
    }
    public String getDirection() {
        return de.directionProperty().getValue();
    }
    public void addDirection(String direction){this.de.directionProperty().setValue(this.getDirection()+direction);}
    public void setBouge(boolean bouge) {
        this.bouge = bouge;
    }
    public boolean isBouge() {
        return bouge;
    }
    public String getId() {
        return id;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return height;
    }
    public int getColumn() {
        return column;
    }
    public int getRows() {
        return rows;
    }
    public void setVitesse(int vitesse) {
        this.de.setVitesse(vitesse);
    }
    private void setId(String id) {
        this.id = id;
    }
    public int getPv() {
        return pv.getValue();
    }
    public int getPvMax() {
        return pvMax;
    }
    public int getDegats() {
        return degats;
    }
    public HitBox getHitBox() {
        return hitBox;
    }
    public ArrayList<Direction> getDeplacement() {
        return de.getDeplacement();
    }
    public void setDeplacement(ArrayList<Direction> deplacement) {
        this.de.setDeplacement(deplacement);
    }

    public void perdreVie(int degats) {
        if (degats <= 0)
            degats = 1;
        int npv = this.getPv() - degats;
        if (npv <= 0) {
            setPv(0);
        }
        else setPv(npv);
    }

    public void ajouterVie(int vieRecup) {
            setPv(this.getPv() + vieRecup);
    }


    public void attaqueEntite(Entite entite) {
        if (verifVivant()) {
            System.out.println("attaque");
            entite.perdreVie(this.getDegats());
        }
    }

    public boolean verifVivant() {
        return this.getPv() > 0;
    }
    /**
     * Méthode qui gère le déplacement d'une Entite sur le pane
     * @return true si le déplacement a été effectué sinon false
     */
    public boolean agir() {
        return de.agir();
    }

    protected DeplacementEntite getDeplacementEntite() {
        return de;
    }
}
