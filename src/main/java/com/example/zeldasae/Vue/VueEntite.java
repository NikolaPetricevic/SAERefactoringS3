package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Entite;
import com.example.zeldasae.modele.Joueur;
import com.example.zeldasae.modele.Pursuer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueEntite {

    private Entite entite;
    private Pane paneEntites;
    private String directionImage;

    private ImageView imgEntite;

    public VueEntite(Entite entite, Pane paneEntites) {
        this.directionImage = "right";
        this.entite = entite;
        this.paneEntites = paneEntites;
        creerImageEntite();
    }

    public void creerImageEntite() {
        imgEntite = new ImageView();
        Image image;
        if (this.entite instanceof Joueur) {
            image = new Image("file:src/main/resources/com/example/zeldasae/assets/chevalier/chevalierdroite.png");
        } else if (this.entite instanceof Pursuer){
            image = new Image("file:src/main/resources/com/example/zeldasae/assets/monstre/monstredroite.png");
        }
        else {
            image = new Image("file:src/main/resources/com/example/zeldasae/assets/boss.png");
        }
        imgEntite.setImage(image);

        imgEntite.setId(entite.getId());
        imgEntite.translateXProperty().bind(entite.xProperty());
        imgEntite.translateYProperty().bind(entite.yProperty());

        this.paneEntites.getChildren().add(imgEntite);
    }

    public void changeImage(){
        if (this.entite instanceof Joueur) {
            if (entite.getDirection().contains("up")) {
                imgEntite.setImage(new Image("file:src/main/resources/com/example/zeldasae/assets/chevalier/chevalierhaut.png"));
                directionImage = "up";
            }
            if (entite.getDirection().contains("down")) {
                imgEntite.setImage(new Image("file:src/main/resources/com/example/zeldasae/assets/chevalier/chevalierbas.png"));
                directionImage = "down";
            }
            if (entite.getDirection().contains("right")) {
                imgEntite.setImage(new Image("file:src/main/resources/com/example/zeldasae/assets/chevalier/chevalierdroite.png"));
                directionImage = "right";
            }
            if (entite.getDirection().contains("left")){
                imgEntite.setImage(new Image("file:src/main/resources/com/example/zeldasae/assets/chevalier/chevaliergauche.png"));
                directionImage = "left";
            }
        }
        else if (this.entite instanceof Pursuer){
            if (entite.getDirection().contains("up"))
                imgEntite.setImage(new Image("file:src/main/resources/com/example/zeldasae/assets/monstre/monstrebas.png"));
            if (entite.getDirection().contains("down"))
                imgEntite.setImage(new Image("file:src/main/resources/com/example/zeldasae/assets/monstre/monstrebas.png"));
            if (entite.getDirection().contains("right"))
                imgEntite.setImage(new Image("file:src/main/resources/com/example/zeldasae/assets/monstre/monstredroite.png"));
            if (entite.getDirection().contains("left"))
                imgEntite.setImage(new Image("file:src/main/resources/com/example/zeldasae/assets/monstre/monstredroite.png"));
        }
    }

    public String getDirectionImage() {
        return directionImage;
    }
}
