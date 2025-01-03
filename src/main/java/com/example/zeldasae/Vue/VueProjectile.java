package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Direction;
import com.example.zeldasae.modele.Projectiles.Projectile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueProjectile {

    private Pane paneEntites;
    public VueProjectile(Pane paneEntites) {
        this.paneEntites = paneEntites;
    }

    public void creerProjectileJoueurVue(Projectile p) {
        ImageView image = getImageViewProjectile(p);
        image.setId(p.getNom());
        image.translateXProperty().bind(p.getHitBox().xProperty());
        image.translateYProperty().bind(p.getHitBox().yProperty());
        this.paneEntites.getChildren().add(image);
    }

    public void creerProjectileEnnemiVue(Projectile p) {
        ImageView image = getImageViewProjectile(p);
        image.setId(p.getNom());
        image.translateXProperty().bind(p.getHitBox().xProperty());
        image.translateYProperty().bind(p.getHitBox().yProperty());
        this.paneEntites.getChildren().add(image);
    }

    public void supprimerProjectileVue(Projectile p) {
        this.paneEntites.getChildren().remove(this.paneEntites.lookup("#" + p.getNom()));
    }

    private static ImageView getImageViewProjectile(Projectile p) {
        ImageView image;
        switch (p.getType()) {
            case "Fleche":
                image = new ImageView(new Image("file:src/main/resources/com/example/zeldasae/assets/Fleche.png"));
                if (p.getDirection().equals(Direction.RIGHT))
                    image.setRotate(90);
                if (p.getDirection().equals(Direction.LEFT))
                    image.setRotate(270);
                if (p.getDirection().equals(Direction.DOWN))
                    image.setRotate(180);
                image.setFitWidth(20);
                image.setFitHeight(20);
                break;
            case "Boomerang":
                image = new ImageView(new Image("file:src/main/resources/com/example/zeldasae/assets/Boomerang.png"));
                image.setFitWidth(30);
                image.setFitHeight(30);
                break;
            case "ProjectileMagique":
                image = new ImageView(new Image("file:src/main/resources/com/example/zeldasae/assets/projectileMagique.png"));
                image.setFitWidth(30);
                image.setFitHeight(30);
                if (p.getDirection().equals(Direction.RIGHT))
                    image.setRotate(180);
                if (p.getDirection().equals(Direction.UP))
                    image.setRotate(90);
                if (p.getDirection().equals(Direction.DOWN))
                    image.setRotate(270);
                break;
            default:
                image = new ImageView();
                break;
        }
        image.setId(p.getNom());
        return image;
    }
}
