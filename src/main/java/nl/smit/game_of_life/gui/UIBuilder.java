package nl.smit.game_of_life.gui;

import nl.smit.game_of_life.board.Board;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * [Class explanation]
 *
 * @author Jordi Smit, 2-10-2017.
 */
public class UIBuilder {
    private static final Random RANDOM = new Random();
    private static final double RANDOM_BOARD_ALIVE_CHANGE = 0.11;
    private static final String
            NEXT_CYCLE_CAPTION = "Next",
            RANDOMIZE_CAPTION = "Randomize",
            START_CAPTION = "Start",
            STOP_CAPTION = "Stop";

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
        addCycleButtons();
        addDefaultButtons();
        addRandomBoardButton();

        return new UI(board, buttons);
    }

    private void addDefaultButtons() {
        buttons.put(NEXT_CYCLE_CAPTION, board::preformStep);
    }

    private void addRandomBoardButton() {
        buttons.put(RANDOMIZE_CAPTION, this::randommizeBoard);
    }

    private void randommizeBoard() {
        board.streamSquares().forEach(square ->
                square.setAlive(RANDOM.nextDouble() < RANDOM_BOARD_ALIVE_CHANGE));
    }

    private void addCycleButtons() {
        buttons.put(STOP_CAPTION, board::stop);
        buttons.put(START_CAPTION, board::start);
    }
}
