package espol.model.game;

import java.util.Comparator;

public class miniMax {

    private miniMax() {}

    public static State miniMaxDecision(State state) {
        return state.getActions().stream()
                .max(Comparator.comparing(miniMax::minValue)).get();
    }

    private static double maxValue(State state) {
        if (state.isTerminal()) return state.getUtility();
        return state.getActions().stream()
                .map(miniMax::minValue)
                .max(Comparator.comparing(Double::valueOf)).get();
    }

    private static double minValue(State state) {
        if (state.isTerminal()) return state.getUtility();
        return state.getActions().stream()
                .map(miniMax::maxValue)
                .min(Comparator.comparing(Double::valueOf)).get();
    }
}
