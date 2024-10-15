package com.example.zeldasae.modele.entities;

import com.example.zeldasae.Algo.BFS;
import com.example.zeldasae.modele.Direction;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.Projectiles.ProjectileEnnemi;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Boss extends Ennemi {

    private final double cooldownAttaqueDistance = 1.5;
    private boolean peutAttaquerDistance;

    public Boss(int x, int y, int width, int height, int column, int rows, BFS bfs) {
        super(x, y, width, height, column, rows, 30, bfs);
        super.setVitesse(5);
        this.peutAttaquerDistance = true;
    }

    @Override
    public boolean deplacement() {
        int x = (this.getX()/ 30) % (30 * this.getColumn());
        int y = (this.getY() / 30) % (30 * this.getRows());
        if (this.getBfs().distanceMouvement(new int[]{x, y}) < 15) {
            return super.deplacement();
        }
        if (this.peutAttaquerDistance && verifVivant()) {
            attaquerDistance();
        }
        return false;
    }

    public void attaquerDistance() {
        ProjectileEnnemi p = new ProjectileEnnemi(2, 20, 30, 30, "ProjectileMagique");
        if(!switchDirection().equals("NULL")) {
            p.setDirection(Direction.stringToDirections(switchDirection()).get(0));
            p.setPosMap(this.getX(), this.getY(), switchDirection());
            Monde.getInstance().addProjectile(p);
            this.peutAttaquerDistance = false;

            PauseTransition pause = new PauseTransition(Duration.seconds(this.cooldownAttaqueDistance));
            pause.setOnFinished(event -> this.peutAttaquerDistance = true);
            pause.play();
        }
    }

    public String switchDirection() {
        int x = Monde.getInstance().getJoueur().getX();
        int y = Monde.getInstance().getJoueur().getY();
        if(x < getX() && y < getY() + 200 && y > getY() - 200) {
            return "LEFT";
        }
        else if(x > getX() && y < getY() + 200 && y > getY() - 200) {
            return "RIGHT";
        }
        else if(y > getY() && x < getX() + 200 && x > getX() - 200) {
            return "DOWN";
        }
        else if(y < getY() && x < getX() + 200 && x > getX() - 200) {
            return "UP";
        }
        else {
            return "NULL";
        }
    }
}
