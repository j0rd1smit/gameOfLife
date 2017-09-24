package nl.smit.game_of_life.board;

/**
 * [Class explanation]
 *
 * @author Jordi Smit, 23-9-2017.
 */
public class BoardFactory {

    public Board board(int width, int height) {
        Square[][] grid = createGrid(width, height);
        linkNeigbours(grid);

        return new Board(grid);
    }

    private void linkNeigbours(Square[][] grid) {
        //TODO
    }

    private Square[][] createGrid(int width, int heigth) {
        Square[][] grid = new Square[width][heigth];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < heigth; h++) {
                grid[w][h] = new Square();
            }
        }

        return grid;
    }

}
