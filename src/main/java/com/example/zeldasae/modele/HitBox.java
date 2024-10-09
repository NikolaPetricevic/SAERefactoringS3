package com.example.zeldasae.modele;

import javafx.beans.property.IntegerProperty;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HitBox {

    private int large;
    private int haut;
    private IntegerProperty x;
    private IntegerProperty y;
    private boolean estPivote;
    private static final int[] dLigne = {0, 1, 0, 0};
    private static final int[] dColonne = {0, 0, 0, 1};
    private static final int[] decalage = {-1, 1, -1, 1};

    public HitBox(int l, int h, IntegerProperty x, IntegerProperty y){
        this.large = l;
        this.haut = h;
        this.x = x;
        this.y = y;
        this.estPivote = false;
    }

    public int getY() {
        return y.getValue();
    }
    public int getX() {
        return x.getValue();
    }
    public void setX(int x) {
        this.x.set(x);
    }
    public void setY(int y) {
        this.y.set(y);
    }
    public int getLarge() {
        return large;
    }
    public int getHaut() {
        return haut;
    }
    public IntegerProperty xProperty() {
        return x;
    }
    public IntegerProperty yProperty() {
        return y;
    }

    public boolean checkColision(ArrayList<Direction> directions, int rows, Terrain terrain){
        Direction[] dDirection = {Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN};
        for (int i = 0; i < dDirection.length; i++){
            int nx = getX() + (dLigne[i]*large);
            int ny = getY() + (dColonne[i]*haut);
            if (i < 2 && directions.contains(dDirection[i])){
                nx += decalage[i];
                for (int j = 0; j < large; j++){
                    if(!terrain.vide((nx / 30) + ((ny+j)/ 30 * rows)))
                        return false;
                }
            }
            else if (i > 1 && directions.contains(dDirection[i])){
                ny += decalage[i];
                for (int j = 0; j < haut; j++){
                    if(!terrain.vide(((nx+j) / 30) + (ny/ 30 * rows)))
                        return false;
                }
            }
        }
        return true;
    }

    public boolean checkBord(Direction direction, int column, int rows, int vitesse){
        int X = (getX()/large)%(large*column);
        int Y = (getY()/haut)%(haut*rows);
        int nx;
        int ny;
        if (direction.equals(Direction.UP)){
            if (getY()-vitesse < 0)
                return false;
        }
        if (direction.equals(Direction.DOWN)){
            ny = ((getY()-vitesse)/haut)%(haut*rows);
            if (Y == rows-1 && Y != ny)
                return false;
        }
        if (direction.equals(Direction.LEFT)){
            if (getX()-vitesse < 0)
                return false;
        }
        if (direction.equals(Direction.RIGHT)){
            nx = ((getX()-vitesse)/this.large)%(large*column);
            return X != column - 1 || X == nx;
        }

        return true;
    }

    public boolean contient(int x, int y){
        return getX() < x && getX()+large > x && getY() < y && getY()+haut > y;
    }

    public boolean estDedansEgal(int x, int y){
        return getX() <= x && getX()+large >= x && getY() <= y && getY()+haut >= y;
    }

    public boolean estDedansHitbox(HitBox h) {
        return !checkDirection(h).isEmpty();
    }

    public ArrayList<Direction> checkDirection(HitBox h) {
        ArrayList<Direction> directions = new ArrayList<>();
        for (int i = 0; i < this.large; i++) {
            if (h.estDedansEgal(this.getX()+i, this.getY())) {
                directions.add(Direction.UP);
            }
            if (h.estDedansEgal(this.getX()+i, this.getY()+this.haut)) {
                directions.add(Direction.DOWN);
            }
        }
        for (int i = 0; i < this.haut; i++) {
            if (h.estDedansEgal(this.getX(), this.getY()+i)) {
                directions.add(Direction.LEFT);
            }
            if (h.estDedansEgal(this.getX()+this.large, this.getY()+i)) {
                directions.add(Direction.RIGHT);
            }
        }
        return directions;
    }

    public boolean degatBlocs(Terrain terrain, ArrayList<Direction> directions){
        if (directions.contains(Direction.UP)){
            return terrain.isCactus(terrain.changeCoo(getX(), getY()-30));
        }
        if (directions.contains(Direction.DOWN)){
            return terrain.isCactus(terrain.changeCoo(getX(), getY()+30));
        }
        if (directions.contains(Direction.RIGHT)){
            return terrain.isCactus(terrain.changeCoo(getX()+30, getY()));
        }
        if (directions.contains(Direction.LEFT)){
            return terrain.isCactus(terrain.changeCoo(getX() - 30, getY()));
        }
        return false;
    }

    public void pivote() {
        int pivot;
        pivot = this.large;
        this.large = this.haut;
        this.haut = pivot;
        if (this.estPivote) {
            this.estPivote = false;
        }
        else {
            this.estPivote = true;
        }
    }

    public boolean coffreProximite(Coffre coffre, int distance) {
        int positionX = Math.abs(this.getX() - coffre.getX());
        int positionY = Math.abs(this.getY() - coffre.getY());
        return (positionX <= distance && positionY == 0) || (positionY <= distance && positionX == 0);
    }
}
