package com.example.zeldasae.modele;

import java.util.ArrayList;

public enum Direction {

    UP, DOWN, LEFT, RIGHT;

    public static String directionsToString(ArrayList<Direction> directions) {
        String string = "";
        if (directions.contains(UP)) string += "up";
        if (directions.contains(DOWN)) string += "down";
        if (directions.contains(LEFT)) string += "left";
        if (directions.contains(RIGHT)) string += "right";
        return string;
    }

    public static ArrayList<Direction> stringToDirections(String string) {
        ArrayList<Direction> directions = new ArrayList<>();
        if (string.contains("up")) directions.add(Direction.UP);
        if (string.contains("down")) directions.add(Direction.DOWN);
        if (string.contains("left")) directions.add(Direction.LEFT);
        if (string.contains("right")) directions.add(Direction.RIGHT);
        return directions;
    }

}
