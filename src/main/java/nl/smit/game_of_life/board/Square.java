package nl.smit.game_of_life.board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import nl.smit.game_of_life.sprite.Sprite;
import nl.smit.game_of_life.sprite.SpriteStore;
import nl.smit.game_of_life.sprite.SquareSpriteStore;

import java.util.EnumMap;
import java.util.Map;

/**
 * A square that can alternates between being alive or dead.
 *
 * @author Jordi Smit, 22-9-2017.
 */
public class Square {
    private static final int
            AMOUNT_OF_NEIGHBOURS = 8,
            REMAIN_ALIVE_LOWER_BOUND = 2,
            REMAIN_ALIVE_UPPER_BOUND = 3,
            BECOME_ALIVE_VALUE = 3;




    /**
     * The default values.
     */
    private static final boolean
            ALIVE_IN_NEXT_CYCLE_DEFAULT = false,
            ALIVE_STARTING_VALUE = false;

    /**
     * The alive properties.
     */
    @Setter(AccessLevel.PACKAGE)
    @Getter(AccessLevel.PACKAGE)
    private boolean alive;
    private boolean aliveInNextCycle;

    /**
     * Sprite related properties.
     */
    private final SquareSpriteStore spriteStore;

    /**
     * The neighbour properties.
     */
    private Map<Direction, Square> neighbours = new EnumMap<>(Direction.class);

    /**
     * The default constructor.
     */
    Square(SquareSpriteStore spriteStore) {
        this.spriteStore = spriteStore;
        this.alive = ALIVE_STARTING_VALUE;
        this.aliveInNextCycle = ALIVE_IN_NEXT_CYCLE_DEFAULT;
    }

    /**
     * Links the square to its neighbours.
     * @param direction The direction of the neighbour.
     * @param neighbour The neighour in that direction.
     */
    void link(Direction direction, Square neighbour) {
        neighbours.put(direction, neighbour);
    }

    /**
     * Prepares square for the next state by check to which state it should go
     * and store this in memory.
     */
    public void prepareNextState() {
        int amountOfLivingNeightbours = countLivingNeighbours();

        if (alive) {
            aliveInNextCycle = stillAliveNextCycle(amountOfLivingNeightbours);
        }
        else {
            aliveInNextCycle = becomesAliveNextCycle(amountOfLivingNeightbours);
        }
    }

    private boolean stillAliveNextCycle(int amountOfLivingNeightbours) {
        return amountOfLivingNeightbours >= REMAIN_ALIVE_LOWER_BOUND
                && amountOfLivingNeightbours <= REMAIN_ALIVE_UPPER_BOUND;
    }

    private boolean becomesAliveNextCycle(int amountOfLivingNeightbours) {
        return amountOfLivingNeightbours == BECOME_ALIVE_VALUE;
    }



    private int countLivingNeighbours() {
        assert neighbours.size() == AMOUNT_OF_NEIGHBOURS;
        int count = 0;
        for (Direction direction : Direction.values()) {
            if (neighbours.get(direction).isAlive()) {
                count++;
            }
        }
        return count;
    }

    /**
     * The square will go to the next cycle based on the values stored in memory.
     */
    public void goToNextCycle() {
        alive = aliveInNextCycle;
        aliveInNextCycle = ALIVE_IN_NEXT_CYCLE_DEFAULT;
    }

    /**
     *
     * @param direction The direction of the neighbour.
     * @return The neighbouring square in the given direction.
     */
    public Square getSquareAt(Direction direction) {
        return neighbours.get(direction);
    }

    /**
     * @return The current sprite based on the current state.
     */
    public Sprite getSprite() {
        if (isAlive()) {
            return this.spriteStore.getAliveSprite();
        }

        return this.spriteStore.getDeadSprite();
    }
}
