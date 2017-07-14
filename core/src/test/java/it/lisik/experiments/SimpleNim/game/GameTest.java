package it.lisik.experiments.SimpleNim.game;

import it.lisik.experiments.SimpleNim.IllegalAmountException;
import it.lisik.experiments.SimpleNim.player.ManualPlayer;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.iterableWithSize;

class GameTest {

    @Nested
    class GameSetUp {
        @Test
        void startDefaultGame() {
            final Game game = new Game(new ManualPlayer(), new ManualPlayer());
            MatcherAssert.assertThat(game.getTokensLeft(), is(equalTo(12)));
        }

        @Test
        void startGameWithZeroTokens() {
            Assertions.assertThrows(IllegalAmountException.class, () -> new Game(new ManualPlayer(), new ManualPlayer(), 0));
        }
    }

    @Nested
    class TokenTurn {
        Game game;
        @BeforeEach
        void setUpGame() {
            this.game = new Game(new ManualPlayer(), new ManualPlayer());
        }

        @Test
        void takeLegalAmountOfTokens() {
            MatcherAssert.assertThat(this.game.getPlayerTurn().makeMove(2), is(iterableWithSize(2)));
            MatcherAssert.assertThat(this.game.getTokensLeft(), equalTo(10));
        }

        @Test
        void takeTooMuchTokens() {
            Assertions.assertThrows(IllegalAmountException.class, () -> {
                game.getPlayerTurn().makeMove(5);
            });

        }

        @Test
        void takeZeroTokens() {
            Assertions.assertThrows(IllegalAmountException.class, () -> {
                game.getPlayerTurn().makeMove(0);
            });
        }

        @Test
        void takeNegativeAmountOfTokens() {
            Assertions.assertThrows(IllegalAmountException.class, () -> {
                game.getPlayerTurn().makeMove(-5);
            });

        }
    }



    @Test
    void testGameEndedEvent() {
        final Game game = new Game(new ManualPlayer(), new ManualPlayer(), 3);

        // workaround around final variables inside lambdas
        final boolean[] gameEnded = {false};
        game.addGameEndedListener(event -> gameEnded[0] = true);

        game.getPlayerTurn().makeMove(2);
        game.getPlayerTurn().makeMove(1);

        Assertions.assertTrue(gameEnded[0]);
    }
}