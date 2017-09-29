package nl.smit.game_of_life.board;

import lombok.AllArgsConstructor;

/**
 * [Class explanation]
 *
 * @author Jordi Smit, 23-9-2017.
 */
@AllArgsConstructor
public class BoardFactory {
    private final GridFactory gridFactory;

    /**
     *
     * @param width The width of the board.
     * @param height The height of the board.
     * @return a new board.
     */
    public Board board(int width, int height) {
        Square[][] grid = gridFactory.createGrid(width, height);
        Board board = new Board(grid);

        linkNeigbours(board);

        return board;
    }

    private void linkNeigbours(Board board) {
        int width = board.getWidth();
        int height = board.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                Square square = board.squareAt(x, y);
                for (Direction dir : Direction.values()) {
                    int dirX = (width + x + dir.getDeltaX()) % width;
                    int dirY = (height + y + dir.getDeltaY()) % height;
                    Square neighbour = board.squareAt(dirX, dirY);
                    square.link(dir, neighbour);
                }
            }
        }
    }



}
