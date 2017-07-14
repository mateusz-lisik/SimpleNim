package it.lisik.experiments.SimpleNim.game;

import it.lisik.experiments.SimpleNim.IllegalAmountException;
import it.lisik.experiments.SimpleNim.Token;
import it.lisik.experiments.SimpleNim.player.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
    private final GameplaySettings gameplaySettings;
    private Player playerOne;
    private Player playerTwo;
    private Collection<Token> tokens;
    private Player currentTurn;
    private Collection<GameEndedListener> gameEndedListeners = new HashSet<>();
    private Collection<TurnPlayedEventListener> turnPlayedListeners = new HashSet<>();

    /**
     * Creates standard 12 token game between two players
     *
     * @param playerOne first player
     * @param playerTwo second player
     */
    public Game(Player playerOne, Player playerTwo) {
        this(playerOne, playerTwo, 12);
    }

    /**
     * Creates game with customizable amount of tokens
     *
     * @param playerOne   first player
     * @param playerTwo   second player
     * @param tokenAmount amounts of token to create
     */
    public Game(Player playerOne, Player playerTwo, int tokenAmount) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        this.playerOne.setCurrentGame(this);
        this.playerTwo.setCurrentGame(this);

        this.currentTurn = playerOne;

        if (tokenAmount < 3) throw new IllegalAmountException();
        this.tokens = createTokens(tokenAmount);

        turnPlayedListeners.add(playerOne);
        turnPlayedListeners.add(playerTwo);
        gameplaySettings = new GameplaySettings();
    }

    private Collection<Token> createTokens(int amount) {
        final Game game = this;

        return Stream.generate(() -> new Token(game)).limit(amount)
                .collect(Collectors.toList());
    }

    public Collection<Token> playTurn(Player player, int amount) {
        if (isGameFinished()) throw new GameAlreadyFinishedException();
        if (getPlayerTurn() != player) throw new InvalidPlayerTurnException();
        if (!getGameplaySettings().getLegalMove().contains(amount)) throw new IllegalAmountException();

        final List<Token> takenTokens = tokens.stream().limit(amount)
                .collect(Collectors.toList());
        tokens.removeAll(takenTokens);

        if (getTokensLeft() == 0) endGame();
        else finishTurn(takenTokens);

        return takenTokens;
    }

    public boolean isGameFinished() {
        return tokens.size() == 0;
    }

    public Player getPlayerTurn() {
        return currentTurn;
    }

    public GameplaySettings getGameplaySettings() {
        return gameplaySettings;
    }

    private void finishTurn(Collection<Token> takenTokens) {
        final Game game = this;

        turnPlayedListeners.forEach(listener ->
                listener.onTurnPlayed(new TurnPlayedEvent(game, currentTurn, takenTokens))
        );

        if (currentTurn == playerOne)
            currentTurn = playerTwo;
        else currentTurn = playerOne;
    }

    public int getTokensLeft() {
        return tokens.size();
    }

    /**
     * Ends game, picks winner and notifies listeners
     *
     * @return Winner
     */
    private Player endGame() {
        if (!isGameFinished()) throw new GameNotEndedException();
        Player winner = getPlayerTurn();

        gameEndedListeners.forEach(listener ->
                listener.onGameEnded(new GameEndedEvent(this, winner))
        );

        return winner;
    }

    public boolean addGameEndedListener(GameEndedListener listener) {
        return this.gameEndedListeners.add(listener);
    }
}
