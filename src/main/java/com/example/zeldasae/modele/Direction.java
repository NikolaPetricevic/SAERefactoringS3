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
        string = string.toLowerCase();
        if (string.contains("up")) directions.add(Direction.UP);
        if (string.contains("down")) directions.add(Direction.DOWN);
        if (string.contains("left")) directions.add(Direction.LEFT);
        if (string.contains("right")) directions.add(Direction.RIGHT);
        return directions;
    }

    public static Direction stringToDirection(String string) {
        ArrayList<Direction> directions = stringToDirections(string);
        if (!directions.isEmpty()) return directions.get(0);
        return null;
    }

    public Direction directionOpposee() {
        if (this.ordinal() % 2 == 0) return values()[this.ordinal() + 1];
        else return values()[this.ordinal() - 1];
    }

    public boolean isVertical() {
        return this == UP || this == DOWN;
    }

    public boolean isHorizontal() {
        return !isVertical();
    }

}
