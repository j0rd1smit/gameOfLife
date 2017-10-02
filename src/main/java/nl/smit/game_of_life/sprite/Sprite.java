package nl.smit.game_of_life.sprite;

import java.awt.Graphics;

/**
 * Specifies the required functionality for a {@link Sprite}.
 *
 * @author Jordi Smit.
 */
public interface Sprite {

    /**
     * Draws the sprite on the provided graphics context.
     *
     * @param graphics The graphics context to draw.
     * @param x        The destination x coordinate to start drawing.
     * @param y        The destination y coordinate to start drawing.
     * @param width    The width of the destination draw area.
     * @param height   The height of the destination draw area.
     */
    void draw(Graphics graphics, int x, int y, int width, int height);

    /**
     * Returns the width of this sprite.
     *
     * @return The width in pixels.
     */
    int getWidth();

    /**
     * Returns the height of this sprite.
     *
     * @return The height of this sprite in pixels.
     */
    int getHeight();
}