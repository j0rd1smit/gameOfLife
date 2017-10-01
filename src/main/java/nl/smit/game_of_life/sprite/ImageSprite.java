package nl.smit.game_of_life.sprite;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.awt.Graphics;
import java.awt.Image;

/**
 * A basic image sprite conisting of a single image.
 *
 * @author Jordi Smit, 1-10-2017.
 */
@AllArgsConstructor
@EqualsAndHashCode
public class ImageSprite implements Sprite {

    private final Image image;


    @Override
    public void draw(Graphics graphics, int x, int y, int width, int height) {
        graphics.drawImage(image, x, y, x + width, y + height, 0, 0,
                image.getWidth(null), image.getHeight(null), null);
    }

    @Override
    public int getWidth() {
        return image.getWidth(null);
    }

    @Override
    public int getHeight() {
        return image.getHeight(null);
    }
}
