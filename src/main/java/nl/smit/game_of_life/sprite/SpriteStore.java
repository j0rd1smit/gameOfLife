package nl.smit.game_of_life.sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * A store that contains all the possible sprites.
 *
 * @author Jordi Smit, 1-10-2017.
 */
public class SpriteStore {

    private final Map<String, Sprite> spriteMap;

    public SpriteStore() {
        spriteMap = new HashMap<>();
    }

    public Sprite getSprite(String resource) {
        Sprite sprite = spriteMap.get(resource);

        if (sprite != null) {
            return sprite;
        }

        return loadAndStoreSprite(resource);
    }

    private Sprite loadAndStoreSprite(String resource) {
        Sprite sprite;
        try {
            sprite = loadSpriteFromResource(resource);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        spriteMap.put(resource, sprite);

        return sprite;
    }

    /**
     * Loads a sprite from a resource on the class path.
     *
     * @param resource
     *            The resource path.
     * @return A new sprite for the resource.
     * @throws IOException
     *             When the resource could not be loaded.
     */
    private Sprite loadSpriteFromResource(String resource) throws IOException {
        try (InputStream input = SpriteStore.class.getResourceAsStream(resource)) {
            if (input == null) {
                throw new IOException("Unable to load " + resource + ", resource does not exist.");
            }
            BufferedImage image = ImageIO.read(input);
            return new ImageSprite(image);
        }
    }


}
