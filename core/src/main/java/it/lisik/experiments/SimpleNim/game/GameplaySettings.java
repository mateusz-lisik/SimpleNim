package it.lisik.experiments.SimpleNim.game;

import org.apache.commons.lang3.Range;

public class GameplaySettings {
    private final Range<Integer> legalMove;

    public GameplaySettings(Range<Integer> legalMove) {
        this.legalMove = legalMove;
    }

    public GameplaySettings() {
        legalMove = Range.between(1, 3);
    }

    public Range<Integer> getLegalMove() {
        return legalMove;
    }
}
