package nl.smit.game_of_life;

import nl.smit.game_of_life.board.Board;
import nl.smit.game_of_life.board.BoardFactory;
import nl.smit.game_of_life.board.GridFactory;
import nl.smit.game_of_life.gui.UI;
import nl.smit.game_of_life.gui.UIBuilder;
import nl.smit.game_of_life.sprite.SquareSpriteStore;

/**
 * [Class explanation]
 *
 * @author Jordi Smit, 2-10-2017.
 */
public class Launcher {
    private static final int WIDTH = 100, HEIGHT = 75;


    private final SquareSpriteStore squareSpriteStore = new SquareSpriteStore();
    private final GridFactory gridFactory = new GridFactory(squareSpriteStore);
    private final BoardFactory boardFactory = new BoardFactory(gridFactory);
    private final Board board = boardFactory.board(WIDTH, HEIGHT);
    private final UI ui = new UIBuilder(board).build();

    public static void main(String[] args) {
        new Launcher().launch();
    }

    public void launch() {
        board.squareAt(0, 0).setAlive(true);
        ui.start();
    }
}
