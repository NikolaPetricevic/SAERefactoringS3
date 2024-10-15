package com.example.zeldasae.modele.entities;

import com.example.zeldasae.Algo.BFS;
import com.example.zeldasae.modele.Direction;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.Terrain;

import java.util.ArrayList;

public abstract class Ennemi extends Entite {

    private BFS bfs;

    public Ennemi(int x, int y, int width, int height, int column, int rows, int pvMax ,BFS bfs) {
        super(x, y, width, height, column, rows, pvMax);
        this.bfs = bfs;
    }

    public BFS getBfs() {
        return bfs;
    }

    /**
     * Méthode qui gère le déplacement d'un Ennemi sur la map en se reposant sur la méthode déplacement() d'Entite
     */
    public boolean deplacement() {
        Monde m = Monde.getInstance();
        int width = 0;
        int width2 = this.getWidth();
        int x;
        int y;
        int[] src;
        int[] pdeplacement;

        boolean deplacement = false;

        do {
            if (!deplacement && width <= this.getWidth()) {
                x = ((this.getX() + width )/ 30) % (30 * this.getColumn());
                y = (this.getY() / 30) % (30 * this.getRows());
                src = new int[]{x, y};
                pdeplacement = bfs.prochainMouvement(src);
                if (pdeplacement != null) {
                    metDirection(x, y, pdeplacement, m.getTerrain());
                    deplacement = super.deplacement();
                }
            }
            if(!deplacement && width2 >= 0){
                x = ((this.getX()+width2-1)/30)%(30*this.getColumn());
                y = ((this.getY()+this.getHeight()-1)/30)%(30*this.getRows());
                src = new int[]{x, y};
                pdeplacement = bfs.prochainMouvement(src);
                if (pdeplacement != null) {
                    metDirection(x, y, pdeplacement, m.getTerrain());
                    deplacement = super.deplacement();
                }
            }
            if (width <= this.getWidth())
                width += 30;
            if (width2 >= 0)
                width2 -= 30;
        }while (width <= this.getWidth() && width2 >= 0);

        this.setDirection(Direction.directionsToString(this.getDeplacement()));
        if (this.trouverDirection() != null)
            attaqueEntite(m.getJoueur());
        return deplacement;
    }

    private void metDirection(int x, int y, int[] pdeplacement, Terrain terrain) {
        ArrayList<Direction> direction = new ArrayList<>();
        if (pdeplacement[0] > x && !terrain.isBrouillard(terrain.changeCoo(getX()+getWidth(), getY())))
            direction.add(Direction.RIGHT);
        if (pdeplacement[0] < x && !terrain.isBrouillard(terrain.changeCoo(getX(), getY())))
            direction.add(Direction.LEFT);
        if (pdeplacement[1] > y && !terrain.isBrouillard(terrain.changeCoo(getX(), getY()+getHeight())))
            direction.add(Direction.DOWN);
        if (pdeplacement[1] < y && !terrain.isBrouillard(terrain.changeCoo(getX(), getY())))
            direction.add(Direction.UP);
        this.setDeplacement(direction);
    }

    public boolean checkColisionEntite(int x, int y) {
        if (Monde.getInstance().getJoueur().getHitBox().contient(x, y)){
            return true;
        }
        return super.getDeplacementEntite().checkColisionEntite(x, y);
    }

    private Direction trouverDirection(){
        int decalages = 1;
        Monde m = Monde.getInstance();
        for (int i = 0; i <= super.getWidth(); i++){
            if (m.getJoueur().getHitBox().contient(getX() + i, getY() - decalages))
                return Direction.UP;
            if (m.getJoueur().getHitBox().contient(getX() + i, getY() + super.getHeight() + decalages))
                return Direction.DOWN;
        }
        for (int i = 0; i <= super.getHeight(); i++) {
            if (m.getJoueur().getHitBox().contient(getX() + super.getWidth() + decalages, getY() + i))
                return Direction.RIGHT;
            if (m.getJoueur().getHitBox().contient(getX() - decalages, getY() + i))
                return Direction.LEFT;
        }
        return null;
    }

}
