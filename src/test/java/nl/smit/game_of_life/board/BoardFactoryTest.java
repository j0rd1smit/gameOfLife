package nl.smit.game_of_life.board;

import nl.smit.game_of_life.sprite.SpriteStore;
import nl.smit.game_of_life.sprite.SquareSpriteStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests the implementation of {@link BoardFactory}.
 *
 * @author Jordi Smit, 29-9-2017.
 */
@SuppressWarnings("PMD.TooManyMethods")
class BoardFactoryTest {
    /**
     * The default values.
     */
    private static final int
            HEIGHT_OF_MULTIPLE_SQUARE_GRID = 2,
            WIDTH_OF_MULTIPLE_SQUARE_GRID = 2,
            HEIGHT_OF_SINGLE_SQUARE_GRID = 1,
            WIDTH_OF_SINGLE_SQUARE_GRID = 1;

    /**
     * Inputs.
     */
    private Square s1, s2, s3, s4;

    /**
     * Dependencies.
     */
    private GridFactory gridFactory;


    /**
     * The object under testing.
     */
    private BoardFactory boardFactory;

    /**
     * Sets up the testing objects.
     */
    @BeforeEach
    void setUpBoardFactoryTest() {
        setUpSquares();
        setUpGridFactoryMock();

        boardFactory = new BoardFactory(gridFactory);
    }

    private void setUpSquares() {
        SquareSpriteStore spriteStore = mock(SquareSpriteStore.class);
        s1 = new Square(spriteStore);
        s2 = new Square(spriteStore);
        s3 = new Square(spriteStore);
        s4 = new Square(spriteStore);
    }

    private void setUpGridFactoryMock() {
        gridFactory = mock(GridFactory.class);

        // s1 s3
        // s2 s4
        when(gridFactory.createGrid(WIDTH_OF_MULTIPLE_SQUARE_GRID, HEIGHT_OF_MULTIPLE_SQUARE_GRID))
                .thenReturn(new Square[][]{{s1, s2}, {s3, s4}});
        // s1
        when(gridFactory.createGrid(WIDTH_OF_SINGLE_SQUARE_GRID, HEIGHT_OF_SINGLE_SQUARE_GRID))
                .thenReturn(new Square[][]{{s1}});

    }

    private Board createSingleSquareGridBoard() {
        return boardFactory.board(WIDTH_OF_SINGLE_SQUARE_GRID, HEIGHT_OF_SINGLE_SQUARE_GRID);
    }

    private Board createMultipleSquareGridBoard() {
        return boardFactory.board(WIDTH_OF_MULTIPLE_SQUARE_GRID, HEIGHT_OF_MULTIPLE_SQUARE_GRID);
    }

    /**
     * Verifies that a single cell is connected to itself on all sides.
     */
    @Test
    void worldIsRound() {

        createSingleSquareGridBoard();

        assertThat(Arrays.stream(Direction.values()).map(s1::getSquareAt)).containsOnly(s1);
    }

    /**
     * Verifies a chain of cells is connected to the east.
     */
    @Test
    void connectedEast() {

        createMultipleSquareGridBoard();

        assertThat(s1.getSquareAt(Direction.EAST)).isEqualTo(s3);
        assertThat(s3.getSquareAt(Direction.EAST)).isEqualTo(s1);
    }

    /**
     * Verifies a chain of cells is connected to the west.
     */
    @Test
    void connectedWest() {

        createMultipleSquareGridBoard();

        assertThat(s1.getSquareAt(Direction.WEST)).isEqualTo(s3);
        assertThat(s3.getSquareAt(Direction.WEST)).isEqualTo(s1);
    }

    /**
     * Verifies a chain of cells is connected to the north.
     */
    @Test
    void connectedNorth() {

        createMultipleSquareGridBoard();

        assertThat(s1.getSquareAt(Direction.NORTH)).isEqualTo(s2);
        assertThat(s2.getSquareAt(Direction.NORTH)).isEqualTo(s1);
    }

    /**
     * Verifies a chain of cells is connected to the south.
     */
    @Test
    void connectedSouth() {

        createMultipleSquareGridBoard();

        assertThat(s1.getSquareAt(Direction.SOUTH)).isEqualTo(s2);
        assertThat(s2.getSquareAt(Direction.SOUTH)).isEqualTo(s1);
    }

    /**
     * Verifies a chain of cells is connected to the NORTH_EAST.
     */
    @Test
    void connectNorthEast() {

        createMultipleSquareGridBoard();

        assertThat(s2.getSquareAt(Direction.NORTH_EAST)).isEqualTo(s3);
        assertThat(s3.getSquareAt(Direction.NORTH_EAST)).isEqualTo(s2);
    }

    /**
     * Verifies a chain of cells is connected to the SOUTH_EAST.
     */
    @Test
    void connectSouthEast() {

        createMultipleSquareGridBoard();

        assertThat(s1.getSquareAt(Direction.SOUTH_EAST)).isEqualTo(s4);
        assertThat(s4.getSquareAt(Direction.SOUTH_EAST)).isEqualTo(s1);
    }

    /**
     * Verifies a chain of cells is connected to the SOUTH_WEST.
     */
    @Test
    void connectSouthWest() {

        createMultipleSquareGridBoard();

        assertThat(s2.getSquareAt(Direction.SOUTH_WEST)).isEqualTo(s3);
        assertThat(s3.getSquareAt(Direction.SOUTH_EAST)).isEqualTo(s2);
    }

    /**
     * Verifies a chain of cells is connected to the SOUTH_EAST.
     */
    @Test
    void connectNorthWest() {

        createMultipleSquareGridBoard();

        assertThat(s1.getSquareAt(Direction.SOUTH_EAST)).isEqualTo(s4);
        assertThat(s4.getSquareAt(Direction.SOUTH_EAST)).isEqualTo(s1);
    }
}