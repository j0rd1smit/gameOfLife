package nl.smit.game_of_life.board;

/**
 * [Class explanation]
 *
 * @author Jordi Smit, 29-9-2017.
 */
public class GridFactory {

    public Square[][] createGrid(int width, int heigth) {
        Square[][] grid = new Square[width][heigth];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < heigth; h++) {
                grid[w][h] = new Square();
            }
        }

        return grid;
    }

}
