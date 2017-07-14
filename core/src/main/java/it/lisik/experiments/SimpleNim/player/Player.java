package it.lisik.experiments.SimpleNim.player;

import it.lisik.experiments.SimpleNim.game.Game;
import it.lisik.experiments.SimpleNim.Token;
import it.lisik.experiments.SimpleNim.game.TurnPlayedEventListener;

import java.util.Collection;

public interface Player extends TurnPlayedEventListener {
    Game getCurrentGame();
    String getName();
    Collection<Token> getGatheredTokens();
    boolean isPlayersTurn();
    Collection<Token> makeMove(int amount);

    void setCurrentGame(Game game);
}
