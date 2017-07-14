package it.lisik.experiments.SimpleNim.game;

public class GameAlreadyFinishedException extends RuntimeException {
    GameAlreadyFinishedException() {
        super("Game was already finished");
    }
}
