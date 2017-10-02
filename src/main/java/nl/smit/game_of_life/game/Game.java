package nl.smit.game_of_life.game;

import nl.smit.game_of_life.board.Board;

/**
 * [Class explanation]
 *
 * @author Jordi Smit, 23-9-2017.
 */
public class Game {

    private final Board board;

    /**
     * The default constructor.
     *
     * @param board The board that the game uses.
     */
    public Game(Board board) {
        this.board = board;
    }

    /**
     * preforms a single step in the game.
     */
    public void preformStep() {
        board.preformStep();
    }


}
