package com.example.zeldasae.modele;

import static com.example.zeldasae.Algo.BFS.*;

public class Ennemi extends Entite{

    public Ennemi(int x, int y, String id, int width, int height, int column, int rows) {
        super(x, y, id, width, height, column, rows);
    }

    /**
     * Méthode qui gère le déplacement d'un Ennemi sur la map en se reposant sur la méthode déplacement() d'Entite
     * @param m le monde contenant le terrain, le joueur et la liste d'ennemis qui est passé en paramètre à la méthode
     *          déplacement() d'Entite
     */
    public void deplacement(Monde m, int xj, int yj) {
        int x = (this.getX()/this.getWidth())%(this.getWidth()*this.getColumn());
        int y = (this.getY()/this.getHeight())%(this.getHeight()*this.getRows());
        int[] src = {x, y};
        int[] dest = {(xj/this.getWidth())%(this.getWidth()*this.getColumn()), (yj/this.getHeight())%(this.getHeight()*this.getRows())};
        int[] pdeplacement = prochainMouvement(m.getTerrain().getMap(), m.getTerrain().getMap().size()/getColumn(), getColumn(), src, dest);
        if (pdeplacement != null) {
            if (pdeplacement[0] > x)
                super.deplacement('d', m);
            else if (pdeplacement[0] < x)
                super.deplacement('q', m);
            else if (pdeplacement[1] > y)
                super.deplacement('s', m);
            else if (pdeplacement[1] < y)
                super.deplacement('z', m);
        }
    }
}