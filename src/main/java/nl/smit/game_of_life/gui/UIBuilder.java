package nl.smit.game_of_life.gui;

import nl.smit.game_of_life.board.Board;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * [Class explanation]
 *
 * @author Jordi Smit, 2-10-2017.
 */
public class UIBuilder {
    private static final String NEXT_CYCLE_CAPTION = "Next";

    /**
     * Map of buttons and their actions.
     */
    private final Map<String, Action> buttons;

    private Board board;

    public UIBuilder(Board board) {
        buttons = new LinkedHashMap<>();
        this.board = board;
    }

    public UI build() {
        addDefaultButtons();

        return new UI(board, buttons);
    }

    private void addDefaultButtons() {
        buttons.put(NEXT_CYCLE_CAPTION, board::preformStep);
    }
}
