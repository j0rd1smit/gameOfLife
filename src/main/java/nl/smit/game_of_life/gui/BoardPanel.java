package nl.smit.game_of_life.gui;

import nl.smit.game_of_life.board.Board;
import nl.smit.game_of_life.board.Square;
import org.checkerframework.checker.initialization.qual.UnderInitialization;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * [Class explanation]
 *
 * @author Jordi Smit, 1-10-2017.
 */
public class BoardPanel extends JPanel {
    private static final int SQUARE_PIXEL_SIZE = 10;
    private static final Color BACKGROUND_COLOR = Color.lightGray;

    private final Board board;

    /**
     * The default constructor.
     *
     * @param board The board to be used.
     */
    public BoardPanel(Board board) {
        this.board = board;

        int w = board.getWidth() * SQUARE_PIXEL_SIZE;
        int h = board.getHeight() * SQUARE_PIXEL_SIZE;

        Dimension size = new Dimension(w, h);
        setMinimumSize(size);
        setPreferredSize(size);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                procesMouseClick(e.getPoint());
            }
        });
    }

    private void procesMouseClick(@UnderInitialization BoardPanel this, Point point) {
        assert board != null;

        int x = point.x / SQUARE_PIXEL_SIZE;
        int y = point.y / SQUARE_PIXEL_SIZE;

        Square square = board.squareAt(x, y);
        square.setAlive(!square.isAlive());
    }


    @Override
    public void paint(Graphics g) {
        assert g != null;
        render(g, getSize());
    }

    /**
     * Renders the board on the given graphics context to the given dimensions.
     *
     * @param graphics The graphics context to draw on.
     * @param window   The dimensions to scale the rendered board to.
     */
    private void render(Graphics graphics, Dimension window) {
        int cellW = window.width / board.getWidth();
        int cellH = window.height / board.getHeight();

        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0, 0, window.width, window.height);

        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                int cellX = x * cellW;
                int cellY = y * cellH;
                Square square = board.squareAt(x, y);
                render(square, graphics, cellX, cellY, cellW, cellH);
            }
        }
    }

    /**
     * Renders a single square on the given graphics context on the specified
     * rectangle.
     *
     * @param square   The square to render.
     * @param graphics The graphics context to draw on.
     * @param x        The x position to start drawing.
     * @param y        The y position to start drawing.
     * @param width    The width of this square (in pixels.)
     * @param height   The height of this square (in pixels.)
     */
    private void render(Square square, Graphics graphics, int x, int y, int width, int height) {
        square.getSprite().draw(graphics, x, y, width, height);
    }


}
