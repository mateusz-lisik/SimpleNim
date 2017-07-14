package it.lisik.experiments.SimpleNim;

import it.lisik.experiments.SimpleNim.game.Game;

public class Token {
    private final Game game;

    public Token(Game game) {

        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
