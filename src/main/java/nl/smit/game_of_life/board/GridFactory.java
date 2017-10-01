package nl.smit.game_of_life.board;

import lombok.AllArgsConstructor;
import nl.smit.game_of_life.sprite.SquareSpriteStore;

/**
 * [Class explanation]
 *
 * @author Jordi Smit, 29-9-2017.
 */
@AllArgsConstructor
public class GridFactory {
    private final SquareSpriteStore spriteStore;


    public Square[][] createGrid(int width, int heigth) {
        Square[][] grid = new Square[width][heigth];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < heigth; h++) {
                grid[w][h] = new Square(spriteStore);
            }
        }

        return grid;
    }

}
