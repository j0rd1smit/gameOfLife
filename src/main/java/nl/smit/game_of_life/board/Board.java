package nl.smit.game_of_life.board;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * A containter class that stores the squares in a grid.
 *
 * @author Jordi Smit, 22-9-2017.
 */
public class Board {

    private final Square[][] grid;
    @Getter(AccessLevel.PUBLIC)
    private final int
            width,
            height;

    /**
     * The default constructor.
     *
     * @param grid The square grid of the board.
     */
    Board(Square[][] grid) {
        this.grid = grid;
        this.width = grid.length;
        this.height = grid[0].length;
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

    public Square squareAt(int x, int y) {
        return grid[x][y];
    }

}
