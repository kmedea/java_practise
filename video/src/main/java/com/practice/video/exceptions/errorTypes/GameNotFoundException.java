package com.practice.video.exceptions.errorTypes;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException() {
        super("The game not found");
    }
}
