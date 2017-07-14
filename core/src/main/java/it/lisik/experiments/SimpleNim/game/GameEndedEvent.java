package it.lisik.experiments.SimpleNim.game;

import it.lisik.experiments.SimpleNim.player.Player;

public class GameEndedEvent {
    private final Game game;
    private final Player winner;

    public GameEndedEvent(Game game, Player winner) {
        this.game = game;
        this.winner = winner;
    }

    public Game getGame() {
        return game;
    }

    public Player getWinner() {
        return winner;
    }
}
