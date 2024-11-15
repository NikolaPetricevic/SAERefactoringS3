package com.example.zeldasae.modele.entities;

import com.example.zeldasae.Algo.BFS;
import com.example.zeldasae.modele.Direction;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.Projectiles.ProjectileEnnemi;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.ArrayList;

public class Sentinelle extends Ennemi{

    private boolean peutAttaquer;

    public Sentinelle(int x, int y, int column, int rows, BFS bfs) {
        super(x, y, 30, 30, column, rows, 5, bfs);
        super.setVitesse(0);
        peutAttaquer = true;
    }

    @Override
    public boolean verificationAction(int distance) {
        if (distance < 15 && peutAttaquer)
            attaqueDistance();
        return true;
    }

    private void attaqueDistance(){
        ProjectileEnnemi p = new ProjectileEnnemi(2, 20, 30, 30, "Fleche");

        String direction = changeDirection();
        if (!Direction.stringToDirections(direction).isEmpty()) p.setDirection(Direction.stringToDirection(direction));
        p.setPosMap(this.getX(), this.getY(), direction);
        Monde.getInstance().addProjectile(p);
        this.peutAttaquer = false;
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> this.peutAttaquer = true);
        pause.play();

    }

    private String changeDirection(){
        if (this.getDirection().equals("up"))
            return "UP";
        if (this.getDirection().equals("down"))
            return "DOWN";
        if (this.getDirection().equals("right"))
            return "RIGHT";
        if (this.getDirection().equals("left"))
            return "LEFT";
        return "";
    }
}
