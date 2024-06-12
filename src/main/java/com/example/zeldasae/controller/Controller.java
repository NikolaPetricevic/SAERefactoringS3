package com.example.zeldasae.controller;

import com.example.zeldasae.Algo.BFS;
import com.example.zeldasae.Vue.VueInventaire;
import com.example.zeldasae.Vue.VueTerrain;
import com.example.zeldasae.modele.*;
import com.example.zeldasae.Vue.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Pane fenetre;
    @FXML
    private Pane boxInventaire;
    @FXML
    private Pane paneEntites;
    @FXML
    private Pane boxCoffre1;
    @FXML
    private Pane boxCoffre2;
    @FXML
    private TilePane mapPane;
    private Monde map;
    private Timeline gameLoop;
    private int temps;
    private Button resetButton;
    private VueArme vueArme;
    private VueCollectible vueCollectible;
    private KeyHandler keyHandler;
    private GestionnaireCoffre gestionnaireCoffre;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetButton = new Button();
        resetButton.setOnAction(actionEvent -> {
            relanceJeu();
            stopfreeze();
        });
        resetButton.setTranslateX(600);
        resetButton.setTranslateY(500);
        resetButton.setText("Lancer");
        resetButton.setDisable(false);
        resetButton.setVisible(true);
        fenetre.getChildren().add(resetButton);
    }

    private void lancementJeu(){
        LoadJSON loadJSON = new LoadJSON("src/main/resources/com/example/zeldasae/assets/map.json");
        this.mapPane.setPrefColumns(loadJSON.getPrefColumns());
        this.mapPane.setPrefRows(loadJSON.getPrefRows());
        this.mapPane.setPrefWidth(this.mapPane.getPrefTileWidth()*this.mapPane.getPrefColumns());
        this.mapPane.setPrefHeight(this.mapPane.getPrefTileHeight()*this.mapPane.getPrefRows());

        BFS bfs =new BFS();

        Joueur joueur = new Joueur(600, 510, (int)mapPane.getPrefTileWidth(), (int)mapPane.getPrefTileHeight(), mapPane.getPrefColumns(), mapPane.getPrefRows(), map);
        VueJoueur vueJoueur = new VueJoueur(joueur, paneEntites);
        this.map = new Monde(joueur, bfs, loadJSON.getPrefRows());
        joueur.pv().addListener(new ObservateurVie(joueur, vueJoueur));

        Pursuer pursuer = new Pursuer(120, 120, (int)mapPane.getPrefTileWidth(), (int)mapPane.getPrefTileHeight(), mapPane.getPrefColumns(),  mapPane.getPrefRows(), bfs, map);
        VuePursuer vuePursuer = new VuePursuer(pursuer, paneEntites);
        pursuer.pv().addListener(new ObservateurVie(pursuer, vuePursuer));
        this.map.addEnnemi(pursuer);

        Boss boss = new Boss(740, 900, (int)mapPane.getPrefTileWidth()*3, (int)mapPane.getPrefTileHeight()*3, mapPane.getPrefColumns(),  mapPane.getPrefRows(), bfs, map);
        this.map.addEnnemi(boss);
        VueBoss vueBoss = new VueBoss(boss, paneEntites);
        boss.pv().addListener(new ObservateurVie(boss, vueBoss));

        VueTerrain vueTerrain = new VueTerrain(this.map, this.mapPane, loadJSON.getMap(), loadJSON.getMap2());
        VueInventaire vueInv = new VueInventaire(this.boxInventaire, this.map.getJoueur());
        this.vueArme = new VueArme(this.map.getJoueur(), this.paneEntites, map, this.mapPane);
        this.vueCollectible = new VueCollectible(paneEntites, map);

        this.map.getJoueur().getInv().getListeItems().addListener(new ObservateurItems(vueInv, this.paneEntites, null));
        this.map.getListeProjectiles().addListener(new ObservateurProjectiles(vueArme));
        this.map.getListeCollectibles().addListener(new ObservateurCollectibles(vueCollectible));

        bfs.lanceAlgo(map, mapPane.getPrefColumns(), mapPane.getPrefRows());

        ObservateurMouvement observateurMouvement = new ObservateurMouvement(this.map, this.mapPane, this.paneEntites);

        this.map.getJoueur().xProperty().addListener(observateurMouvement);
        this.map.getJoueur().yProperty().addListener(observateurMouvement);

        gestionnaireCoffre = new GestionnaireCoffre(this.map, Arrays.asList(boxCoffre1, boxCoffre2), vueInv);
        this.keyHandler = new KeyHandler(this.map, vueInv, vueTerrain, vueArme, vueCollectible, gestionnaireCoffre);


        gestionnaireCoffre.creerCoffreDansMonde();

        for (int i = 0 ; i < gestionnaireCoffre.getCoffreList().size() ; i++)
            gestionnaireCoffre.getCoffreList().get(i).getListeItem().addListener(new ObservateurItems(null, this.paneEntites, this.gestionnaireCoffre.getVueCoffreList().get(i)));

        paneEntites.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
        paneEntites.addEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
        initAnimation();
        paneEntites.getChildren().add(this.map.getJoueur().getVueBarreDeVie());

    }

    private void relanceJeu(){
        clearJeu();
        lancementJeu();
    }

    private void clearJeu(){
        paneEntites.getChildren().clear();
        paneEntites.setTranslateX(0);
        paneEntites.setTranslateY(0);
        mapPane.getChildren().clear();
        mapPane.setTranslateX(0);
        mapPane.setTranslateY(0);
        boxInventaire.getChildren().clear();
        boxInventaire.setTranslateX(0);
        boxInventaire.setTranslateY(0);
        this.map = null;
        resetButton.setDisable(true);
        resetButton.setVisible(false);
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.040),
                // on définit ce qui se passe à chaque frame
                (ev ->{
                    this.map.getJoueur().deplacement(map);

                    if (temps%2==0) {
                        this.map.deplacementEnnemi();
                        this.map.deplacerProjectilesVue();
                    }

                    temps++;
                    if (!map.getJoueur().verifVivant()) {
                        clearJeu();
                        paneEntites.removeEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
                        paneEntites.removeEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
                        resetButton.setDisable(false);
                        resetButton.setVisible(true);
                        gameLoop.stop();
                    }

                    this.vueCollectible.checkCollectiblesRamasses();
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    private void stopfreeze(){
        paneEntites.requestFocus();
        gameLoop.play();
    }

}
