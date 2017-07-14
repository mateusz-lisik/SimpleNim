package it.lisik.experiments.SimpleNim.player;

import it.lisik.experiments.SimpleNim.Token;
import it.lisik.experiments.SimpleNim.game.Game;
import it.lisik.experiments.SimpleNim.game.TurnPlayedEvent;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collection;
import java.util.HashSet;

public abstract class BasePlayer implements Player {
    private Game currentGame;
    private String name;
    private Collection<Token> gatheredTokens = new HashSet<>();

    public BasePlayer(String name) {
        this.name = name;
    }

    @Override
    public Game getCurrentGame() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<Token> getGatheredTokens() {
        return null;
    }

    @Override
    public boolean isPlayersTurn() {
        return currentGame.getPlayerTurn() == this;
    }

    @Override
    public Collection<Token> makeMove(int amount) {
        final Collection<Token> takenTokens = currentGame.playTurn(this, amount);
        gatheredTokens.addAll(takenTokens);

        return takenTokens;
    }

    @Override
    public void setCurrentGame(Game game) {
        if (this.currentGame != null && this.currentGame.equals(game)) return;

        this.currentGame = game;
        resetTokens();
    }

    protected void resetTokens() {
        gatheredTokens.clear();
    }

    @Override
    public void onTurnPlayed(TurnPlayedEvent event) {

    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(currentGame)
                .append(name)
                .append(gatheredTokens)
                .toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof BasePlayer)) return false;

        final BasePlayer that = (BasePlayer) o;

        return new EqualsBuilder()
                .append(currentGame, that.currentGame)
                .append(name, that.name)
                .append(gatheredTokens, that.gatheredTokens)
                .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("currentGame", currentGame)
                .append("name", name)
                .append("gatheredTokens", gatheredTokens)
                .toString();
    }
}
