package com.example.zeldasae.modele.entities;

import com.example.zeldasae.Algo.BFS;
import com.example.zeldasae.modele.Monde;

public class Kami extends Ennemi{

    public Kami(int x, int y, int column, int rows, BFS bfs) {
        super(x, y, 30, 30, column, rows, 15, bfs);
    }

    @Override
    public boolean agir() {
        int x = (this.getX()/ 30) % (30 * this.getColumn());
        int y = (this.getY() / 30) % (30 * this.getRows());
        int distance = this.getBfs().distanceMouvement(new int[]{x, y});
        if (distance < 30) {
            if (distance == 2) {
                explose(Monde.getInstance().getJoueur());
                this.perdreVie(this.getPv());
                return false;
            } else return super.agir();
        }
        return false;
    }

    private void explose(Joueur joueur){
        joueur.perdreVie(5);
    }

    @Override
    public void attaqueEntite(Entite entite) {

    }
}
