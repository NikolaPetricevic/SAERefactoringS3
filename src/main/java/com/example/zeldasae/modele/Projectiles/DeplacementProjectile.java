package com.example.zeldasae.modele.Projectiles;

import com.example.zeldasae.modele.Direction;

public class DeplacementProjectile {

    private int vitesse;
    private Direction direction;
    private Projectile p;

    public DeplacementProjectile(int vitesse, Direction direction, Projectile p) {
        this.vitesse = vitesse;
        this.direction = direction;
        this.p = p;
    }
    public DeplacementProjectile(int vitesse, Projectile p) {
        this.vitesse = vitesse;
        this.p = p;
    }

    public void seDeplace() {
        switch (this.direction) {
            case UP:
                p.getHitBox().setY(p.getHitBox().getY() - this.vitesse);
                break;
            case DOWN:
                p.getHitBox().setY(p.getHitBox().getY() + this.vitesse);
                break;
            case LEFT:
                p.getHitBox().setX(p.getHitBox().getX() - this.vitesse);
                break;
            case RIGHT:
                p.getHitBox().setX(p.getHitBox().getX() + this.vitesse);
                break;
        }
        this.p.checkCoupTouche();
    }

    public void inverserDirection() {
        direction = direction.directionOpposee();
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
