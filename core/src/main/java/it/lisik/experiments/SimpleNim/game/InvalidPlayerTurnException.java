package it.lisik.experiments.SimpleNim.game;

public class InvalidPlayerTurnException extends RuntimeException {
    public InvalidPlayerTurnException() {
        super("It isn't your turn yet");
    }
}
