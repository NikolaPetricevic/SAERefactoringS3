package com.example.zeldasae.modele;

import javafx.beans.property.IntegerProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean checkColision(String direction, int rows, Terrain terrain){
        String[] dString = {"left","right","up","down"};
        for (int i = 0; i < dString.length; i++){
            int nx = getX() + (dLigne[i]*large);
            int ny = getY() + (dColonne[i]*haut);
            if (i < 2 && direction.contains(dString[i])){
                nx += decalage[i];
                for (int j = 0; j < large; j++){
                    if(!terrain.testCoo((nx / large) + ((ny+j)/ haut * rows)))
                        return false;
                }
            }
            else if (i > 1 && direction.contains(dString[i])){
                ny += decalage[i];
                for (int j = 0; j < haut; j++){
                    if(!terrain.testCoo(((nx+j) / large) + (ny/ haut * rows)))
                        return false;
                }
            }
        }
        return true;
    }

    public boolean checkBord(String direction, int column, int rows, int vitesse){
        int X = (getX()/large)%(large*column);
        int Y = (getY()/haut)%(haut*rows);
        int nx;
        int ny;
        if (direction.equals("up")){
            if (getY()-vitesse < 0)
                return false;
        }
        if (direction.equals("down")){
            ny = ((getY()-vitesse)/haut)%(haut*rows);
            if (Y == rows-1 && Y != ny)
                return false;
        }
        if (direction.equals("left")){
            if (getX()-vitesse < 0)
                return false;
        }
        if (direction.equals("right")){
            nx = ((getX()-vitesse)/this.large)%(large*column);
            if (X == column-1 && X != nx)
                return false;
        }

        return true;
    }

    public boolean estDedans(int x, int y){
        return getX() < x && getX()+large > x && getY() < y && getY()+haut > y;
    }

    public boolean estDedansEgal(int x, int y){
        return getX() <= x && getX()+large >= x && getY() <= y && getY()+haut >= y;
    }

    public boolean estDedansHitbox(HitBox h) {
        System.out.println("largeur : " + this.large + " hauteur : " + this.haut);
        boolean check = (this.checkUp(h) || this.checkDown(h) || this.checkLeft(h) || this.checkRight(h));
        return(check);
    }

    public boolean checkUp(HitBox h) {
        for (int i = 0; i < this.large; i++) {
            if (h.estDedansEgal(this.getX()+i, this.getY())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDown(HitBox h) {
        for (int i = 0; i < this.large; i++) {
            if (h.estDedansEgal(this.getX()+i, this.getY()+this.haut)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLeft(HitBox h) {
        for (int i = 0; i < this.haut; i++) {
            if (h.estDedansEgal(this.getX(), this.getY()+i)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkRight(HitBox h) {
        for (int i = 0; i < this.haut; i++) {
            if (h.estDedansEgal(this.getX()+this.large, this.getY()+i)) {
                return true;
            }
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



}
