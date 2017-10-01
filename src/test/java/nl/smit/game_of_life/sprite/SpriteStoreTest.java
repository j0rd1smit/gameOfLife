package nl.smit.game_of_life.sprite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests the implementation of
 *
 * @author Jordi Smit, 1-10-2017.
 */
@SuppressWarnings("PMD.TooManyMethods")
class SpriteStoreTest {
    /**
     * The default values.
     */
    private static final String
            ALIVE_SPRITE_RESOURCE = "/sprite/alive.png",
            DEAD_SPRITE_RESOURCE = "/sprite/dead.png";

    /**
     * The object under testing.
     */
    private SpriteStore spriteStore;

    /**
     * Sets up the testing objects.
     */
    @BeforeEach
    void setUpSpriteStoreTest() {
        spriteStore = new SpriteStore();
    }

    /**
     * Verify that the alive sprite can be found.
     */
    @Test
    void getAliveSprite() {
        assertThat(spriteStore.getSprite(ALIVE_SPRITE_RESOURCE)).isNotNull();
    }

    /**
     * Verify that the alive sprite can be found.
     */
    @Test
    void getDeadSprite() {
        assertThat(spriteStore.getSprite(DEAD_SPRITE_RESOURCE)).isNotNull();
    }
}