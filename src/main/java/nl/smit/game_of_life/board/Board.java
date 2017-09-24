package nl.smit.game_of_life.board;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * A containter class that stores the squares in a grid.
 *
 * @author Jordi Smit, 22-9-2017.
 */
public class Board {

    private final Square[][] grid;

    /**
     * The default constructor.
     * @param grid The square grid of the board.
     */
    Board(Square[][] grid) {
        this.grid = grid;
    }

    private Stream<Square> streamSquares() {
        return Arrays.stream(grid).flatMap(Arrays::stream);
    }

    /**
     * Preforms a single cycle step.
     */
    public void preformStep() {
        streamSquares().forEach(Square::prepareNextState);
        streamSquares().forEach(Square::goToNextCycle);
    }
}
