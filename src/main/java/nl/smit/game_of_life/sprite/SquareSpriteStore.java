package nl.smit.game_of_life.sprite;

import nl.smit.game_of_life.board.Square;

/**
 * A specific store for the {@link Square}s.
 *
 * @author Jordi Smit, 1-10-2017.
 */
public class SquareSpriteStore extends SpriteStore {
    private static final String
            ALIVE_SPRITE_RESOURCE = "/sprite/alive.png",
            DEAD_SPRITE_RESOURCE = "/sprite/dead.png";


    /**
     *
     * @return Alive sprite for a square.
     */
    public Sprite getAliveSprite() {
        return super.getSprite(ALIVE_SPRITE_RESOURCE);
    }

    /**
     *
     * @return Dead sprite for a square.
     */
    public Sprite getDeadSprite() {
        return super.getSprite(DEAD_SPRITE_RESOURCE);
    }
}
