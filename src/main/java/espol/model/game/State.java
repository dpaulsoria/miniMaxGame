package espol.model.game;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class State {
    public State() {}

    public static Collection<State> getActions() {
        List<State> actions = new LinkedList<>();

        // Actions
        return actions;
    }

    public static boolean isTerminal() {
        // Logic
        return false;
    }

    public static double getUtility() {
        // Logic
        return 0;
    }
}
