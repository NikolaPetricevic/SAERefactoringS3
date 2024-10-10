package com.example.zeldasae.modele;

import java.util.ArrayList;

public class Terrain {

    private ArrayList<Integer> map;
    private int rows;
    private int columns;

    public Terrain(int rows, int columns){
        this.map = new ArrayList<>();
        this.rows = rows;
        this.columns = columns;
    }

    public ArrayList<Integer> getMap() {
        return map;
    }
    public void setMap(ArrayList<Integer> map) {
        this.map = map;
    }
    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }

    public int changeCoo(int x, int y){
        return (x/30) + ((y/30)*rows);
    }

    public boolean poussable(int coo) {
        int[] casesPoussable = {1118};
        return test(coo, casesPoussable);
    }

    //retourne vrai si la coordoner entreée en param respect la condition
    public boolean test(int coordoner, int[] casesTestees){
        if (coordoner < map.size() && coordoner >=0) {
            for (int caseTestee : casesTestees) {
                if (this.map.get(coordoner) == caseTestee)
                    return true;
            }
        }
        return false;
    }

    public boolean vide(int coo){
        int[] casesVides = {0, 1205, 686, 687, 631, 632, 688, 630, 683, 625, 245, 246, 247, 601, 604};
        return test(coo, casesVides);
    }

    public boolean isBrouillard(int coo){
        int[] casesBrouillard = {1205};
        return test(coo, casesBrouillard);
    }

    public boolean destructible(int coo){
        int[] casesDestructible = {514, 1079};
        return test(coo, casesDestructible);
    }

    public boolean isBuisson(int coo){
        return this.map.get(coo) == 1079;
    }

    public boolean isCactus(int coo){
        if (coo < map.size() && coo >=0)
            return this.map.get(coo) == 403;
        return false;
    }

    public void setCoo(int coo, int valeur){
        if (coo < map.size() && coo >=0)
            this.map.set(coo, valeur);

    }

}