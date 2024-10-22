package com.example.zeldasae.modele.entities;

import com.example.zeldasae.Algo.BFS;

public class Skeleton extends Ennemi {

    public Skeleton(int x, int y, int column, int rows, BFS bfs) {
        super(x, y, 30, 30, column, rows, 7, bfs);
    }

    @Override
    public boolean agir() {
        int x = (this.getX()/ 30) % (30 * this.getColumn());
        int y = (this.getY() / 30) % (30 * this.getRows());
        int distance = this.getBfs().distanceMouvement(new int[]{x, y});
        if (distance < 30)
            return super.agir();
        return false;
    }
}