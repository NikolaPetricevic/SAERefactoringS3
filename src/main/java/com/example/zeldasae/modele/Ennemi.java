package com.example.zeldasae.modele;

import com.example.zeldasae.Algo.BFS;

import java.util.Arrays;

public class Ennemi extends Entite{

    private BFS bfs;

    public Ennemi(int x, int y, int width, int height, int column, int rows ,BFS bfs) {
        super(x, y, width, height, column, rows);
        this.bfs = bfs;
    }

    /**
     * Méthode qui gère le déplacement d'un Ennemi sur la map en se reposant sur la méthode déplacement() d'Entite
     * @param m le monde contenant le terrain, le joueur et la liste d'ennemis qui est passé en paramètre à la méthode
     *          déplacement() d'Entite
     */
    public boolean deplacement(Monde m) {
        int x = (this.getX()/this.getWidth())%(this.getWidth()*this.getColumn());
        int y = (this.getY()/this.getHeight())%(this.getHeight()*this.getRows());

        int[] src = {x, y};
//        int[] dest = {(xj/this.getWidth())%(this.getWidth()*this.getColumn()), (yj/this.getHeight())%(this.getHeight()*this.getRows())};

        int[] pdeplacement = bfs.prochainMouvement(src);
        System.out.println(Arrays.toString(pdeplacement));
        if (pdeplacement != null) {
            String direction = "";
            donneDirection(x, y, pdeplacement, direction);
            if(!super.deplacement(m)){
                x = ((this.getX()+this.getWidth()-1)/this.getWidth())%(this.getWidth()*this.getColumn());
                y = ((this.getY()+this.getHeight()-1)/this.getHeight())%(this.getHeight()*this.getRows());
                src = new int[]{x, y};
                pdeplacement = bfs.prochainMouvement(src);
                if (pdeplacement != null) {
                    direction = "";
                    donneDirection(x, y, pdeplacement, direction);
                    return super.deplacement(m);
                }
            }
        }
        return false;
    }

    private void donneDirection(int x, int y, int[] pdeplacement, String direction) {
        if (pdeplacement[0] > x)
            direction += "right";
        if (pdeplacement[0] < x)
            direction += "left";
        if (pdeplacement[1] > y)
            direction += "down";
        if (pdeplacement[1] < y)
            direction += "up";
        this.setDirection(direction);
    }

    public boolean checkColisionEntite(Monde m, int x, int y) {
        if (m.getJoueur().getHitBox().contient(x, y)){
            this.attaqueEntite(m.getJoueur());
            return true;
        }
        return super.checkColisionEntite(m, x, y);
    }
}
