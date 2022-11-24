package com.practice.video.exceptions.errorTypes;

public class GameIsAlreadyExistException extends RuntimeException{

    public GameIsAlreadyExistException() {
        super("The game with this datas is already exist.");
    }
}
