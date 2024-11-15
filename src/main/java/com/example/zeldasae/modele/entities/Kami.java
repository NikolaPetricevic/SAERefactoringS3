package com.example.zeldasae.modele.entities;

import com.example.zeldasae.Algo.BFS;
import com.example.zeldasae.modele.Monde;

public class Kami extends Ennemi{

    public Kami(int x, int y, int column, int rows, BFS bfs) {
        super(x, y, 30, 30, column, rows, 15, bfs);
    }

    @Override
    public boolean verificationAction(int distance) {
        if (distance == 2) {
            exploser();
            return false;
        }
        return distance < 30;
    }

    public void exploser() {
        explose(Monde.getInstance().getJoueur());
        this.perdreVie(this.getPv());
    }

    private void explose(Joueur joueur){
        joueur.perdreVie(5);
    }

    @Override
    public void attaqueEntite(Entite entite) {

    }
}
