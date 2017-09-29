package nl.smit.game_of_life.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InOrder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

/**
 * Tests the implementation of {@link Board}.
 *
 * @author Jordi Smit, 29-9-2017.
 */
@SuppressWarnings("PMD.TooManyMethods")
class BoardTest {
    /**
     * The default values.
     */
    private static final int
            GRID_HEIGHT = 2,
            GRID_WIDTH = 2;

    /**
     * Dependencies.
     */
    private Square[][] grid;


    /**
     * The object under testing.
     */
    private Board board;

    /**
     * Sets up the testing objects.
     */
    @BeforeEach
    void setUpBoardTest() {
        setUpGridMock();

        board = new Board(grid);
    }

    private void setUpGridMock() {
        grid = new Square[][]{
                {mock(Square.class), mock(Square.class)},
                {mock(Square.class), mock(Square.class)}
        };
    }

    /**
     * Verifies the height is set correctly.
     */
    @Test
    void heightTest() {
        assertThat(board.getHeight()).isEqualTo(GRID_HEIGHT);
    }

    /**
     * Verifies the width is set correctly.
     */
    @Test
    void widthTest() {
        assertThat(board.getWidth()).isEqualTo(GRID_WIDTH);
    }

    /**
     * Verifies that the correct square is returned.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    @ParameterizedTest(name = "squareAt({0}, {1})")
    @CsvSource({
            "0,0",
            "1,1",
            "1,0"
    })
    void squareAt(int x, int y) {
        assertThat(board.squareAt(x, y)).isEqualTo(grid[x][y]);
    }

    /**
     * Verifies that the preformStep method call the square methods in the correct order.
     * For all squares in the grid.
     */
    @Test
    void preformStep() {

        board.preformStep();

        verifyCorrectSquancesForASquareAfterPreformStep();
    }

    private void verifyCorrectSquancesForASquareAfterPreformStep() {
        for (int x = 0; x < GRID_WIDTH; x++) {
            for (int y = 0; y < GRID_HEIGHT; y++) {
                InOrder inOrder = inOrder(grid[x][y], grid[x][y]);

                inOrder.verify(grid[x][y]).prepareNextState();
                inOrder.verify(grid[x][y]).goToNextCycle();
            }
        }
    }
}