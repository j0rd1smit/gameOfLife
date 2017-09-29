package nl.smit.game_of_life.board;

import lombok.Getter;

/**
 * Enums of the moveable direction on the {@link Board}.
 *
 * @author Jordi Smit, 22-9-2017.
 */
public enum Direction {

    NORTH(0, -1),
    NORTH_EAST(1, -1),
    EAST(1, 0),
    SOUTH_EAST(1, 1),
    SOUTH(0, 1),
    SOUTH_WEST(-1, 1),
    WEST(-1, 0),
    NORTH_WEST(-1, -1);


    /**
     * The x component of the direction.
     */
    @Getter
    private final int deltaX;
    /**
     * The y component of the direction.
     */
    @Getter
    private final int deltaY;

    /**
     * The default constructor.
     *
     * @param deltaX The x component of the direction.
     * @param deltaY The y component of the direction.
     */
    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }
}
