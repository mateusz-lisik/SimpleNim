package it.lisik.experiments.SimpleNim.game;

import it.lisik.experiments.SimpleNim.Token;
import it.lisik.experiments.SimpleNim.game.Game;
import it.lisik.experiments.SimpleNim.player.Player;

import java.util.Collection;

public class TurnPlayedEvent {
    private final Game game;
    private final Player player;
    private final Collection<Token> takenTokens;

    public TurnPlayedEvent(Game game, Player player, Collection<Token> takenTokens) {
        this.game = game;
        this.player = player;
        this.takenTokens = takenTokens;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public Collection<Token> getTakenTokens() {
        return takenTokens;
    }
}
