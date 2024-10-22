package com.example.zeldasae.modele.entities;

import com.example.zeldasae.modele.Direction;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.Terrain;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class DeplacementEntite {

    private Entite e;
    private int vitesse;
    private ArrayList<Direction> deplacement;
    private StringProperty direction;


    public DeplacementEntite(Entite entite, int vitesse, ArrayList<Direction> deplacement) {
        this.e = entite;
        this.vitesse = vitesse;
        this.deplacement = deplacement;
        this.direction = new SimpleStringProperty("down");
    }

    public boolean agir() {
        Monde m = Monde.getInstance();
        if (this.e.verifVivant()) {
            int dx = 0;
            int dy = 0;
            int x = e.getX();
            int y = e.getY();

            if (this.deplacement.contains(Direction.UP) && checkHitBox(Direction.UP, m.getTerrain()))
                if (checkUp(vitesse)) {
                    dy -= vitesse;
                    e.addDirection("up");
                    e.setY(e.getY() + dy);
                }
            if (this.deplacement.contains(Direction.DOWN) && checkHitBox(Direction.DOWN, m.getTerrain()))
                if (checkDown(vitesse)) {
                    dy += vitesse;
                    e.addDirection("down");
                    e.setY(e.getY() + dy);
                }
            if (this.deplacement.contains(Direction.LEFT) && checkHitBox(Direction.LEFT, m.getTerrain()))
                if (checkLeft(vitesse)) {
                    dx -= vitesse;
                    e.addDirection("left");
                    e.setX(e.getX() + dx);
                }
            if (this.deplacement.contains(Direction.RIGHT) && checkHitBox(Direction.RIGHT, m.getTerrain()))
                if (checkRight(vitesse)) {
                    dx += vitesse;
                    e.addDirection("right");
                    e.setX(e.getX() + dx);
                }

            this.e.setBouge(x != e.getX() || y != e.getY());
            return x != e.getX() || y != e.getY();
        }
        return false;
    }

    private boolean checkHitBox(Direction direction, Terrain terrain){
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add(direction);
        if (e.getHitBox().checkColision(directions, e.getRows(), terrain)) {
            return e.getHitBox().checkBord(direction, e.getColumn(),e.getColumn(), this.vitesse);
        }
        if (e.getHitBox().degatBlocs(terrain, directions))
            e.perdreVie(1);
        return false;
    }

    public String getDirection() {
        return direction.get();
    }

    public StringProperty directionProperty() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction.set(direction);
    }

    public ArrayList<Direction> getDeplacement() {
        return deplacement;
    }
    public void setDeplacement(ArrayList<Direction> deplacement) {
        this.deplacement = deplacement;
    }
    public int getVitesse() {
        return vitesse;
    }
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
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
        for (int i = 0; i <= e.getWidth(); i++){
            if (checkColisionEntite(e.getX() + i, e.getY() - decalages))
                colisions.add(Direction.UP);
            if (checkColisionEntite(e.getX() + i, e.getY() + e.getHeight() + decalages))
                colisions.add(Direction.DOWN);
        }
        for (int i = 0; i <= e.getHeight(); i++){
            if (checkColisionEntite(e.getX() + e.getWidth() + decalages, e.getY() + i))
                colisions.add(Direction.RIGHT);
            if (checkColisionEntite(e.getX() - decalages, e.getY() + i))
                colisions.add(Direction.LEFT);
        }
        return colisions;
    }
    public boolean checkColisionEntite(int x, int y){
        for (Ennemi ennemi : Monde.getInstance().getListeEnnemis()){
            if (this.e != ennemi && ennemi.getHitBox().contient(x,y))
                return true;
        }
        return false;
    }
}

