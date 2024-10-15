package com.example.zeldasae.modele.entities;

import com.example.zeldasae.modele.Direction;
import com.example.zeldasae.modele.HitBox;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.Terrain;
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
    private ArrayList<Direction> deplacement;
    private int vitesse;
    private HitBox hitBox;
    private static int n = 0;
    private IntegerProperty pv;
    private int pvMax;
    private int degats;
    private StringProperty direction;
    private boolean bouge;

    public Entite(int x, int y, int width, int height, int column, int rows, int pvMax) {
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.id = ""+n++;
        this.width = width;
        this.height = height;
        this.column = column;
        this.rows = rows;
        this.deplacement = new ArrayList<>();
        this.vitesse = 10;
        this.hitBox = new HitBox(this.width, this.height, this.xProperty, this.yProperty);
        this.pvMax = pvMax;
        this.pv = new SimpleIntegerProperty(this.pvMax);
        this.degats = 1;
        this.direction = new SimpleStringProperty("down");
        this.bouge = false;
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
        return direction;
    }
    public void setDirection(String direction) {
        this.direction.setValue(direction);
    }
    public String getDirection() {
        return direction.getValue();
    }
    public void addDirection(String direction){this.direction.setValue(this.getDirection()+direction);}

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
        this.vitesse = vitesse;
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
        return deplacement;
    }
    public void setDeplacement(ArrayList<Direction> deplacement) {
        this.deplacement = deplacement;
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
        Monde m = Monde.getInstance();
        if (verifVivant()) {
            int dx = 0;
            int dy = 0;
            int x = getX();
            int y = getY();

            if (this.deplacement.contains(Direction.UP) && checkHitBox(Direction.UP, m.getTerrain()))
                if (checkUp(vitesse)) {
                    dy -= vitesse;
                    addDirection("up");
                    setY(getY() + dy);
                }
            if (this.deplacement.contains(Direction.DOWN) && checkHitBox(Direction.DOWN, m.getTerrain()))
                if (checkDown(vitesse)) {
                    dy += vitesse;
                    addDirection("down");
                    setY(getY() + dy);
                }
            if (this.deplacement.contains(Direction.LEFT) && checkHitBox(Direction.LEFT, m.getTerrain()))
                if (checkLeft(vitesse)) {
                    dx -= vitesse;
                    addDirection("left");
                    setX(getX() + dx);
                }
            if (this.deplacement.contains(Direction.RIGHT) && checkHitBox(Direction.RIGHT, m.getTerrain()))
                if (checkRight(vitesse)) {
                    dx += vitesse;
                    addDirection("right");
                    setX(getX() + dx);
                }

            this.setBouge(x != getX() || y != getY());
            return x != getX() || y != getY();
        }
        return false;
    }

    private boolean checkHitBox(Direction direction, Terrain terrain){
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add(direction);
        if (hitBox.checkColision(directions, this.rows, terrain)) {
            return hitBox.checkBord(direction, this.column, this.rows, this.vitesse);
        }
        if (hitBox.degatBlocs(terrain, directions))
            this.perdreVie(1);
        return false;
    }

    public boolean checkColisionEntite(int x, int y){
        for (Ennemi ennemi : Monde.getInstance().getListeEnnemis()){
            if (this != ennemi && ennemi.getHitBox().contient(x,y))
                return true;
        }
        return false;
    }

    public boolean checkUp( int decalages){
        return !detecterColision(decalages).contains(Direction.UP);
    }
    public boolean checkDown(int decalages){
        return !detecterColision(decalages).contains(Direction.DOWN);
    }
    public boolean checkRight(int decalages){
        return !detecterColision(decalages).contains(Direction.RIGHT);
    }
    public boolean checkLeft(int decalages){
        return !detecterColision(decalages).contains(Direction.LEFT);
    }
    public ArrayList<Direction> detecterColision(int decalages){
        ArrayList<Direction> colisions = new ArrayList<>();
        for (int i = 0; i <= width; i++){
            if (checkColisionEntite(getX() + i, getY() - decalages))
                colisions.add(Direction.UP);
            if (checkColisionEntite(getX() + i, getY() + height + decalages))
                colisions.add(Direction.DOWN);
        }
        for (int i = 0; i <= height; i++){
            if (checkColisionEntite(getX() + width + decalages, getY() + i))
                colisions.add(Direction.RIGHT);
            if (checkColisionEntite(getX() - decalages, getY() + i))
                colisions.add(Direction.LEFT);
        }
        return colisions;
    }
}
