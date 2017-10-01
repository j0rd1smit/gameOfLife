package nl.smit.game_of_life.board;

import nl.smit.game_of_life.sprite.Sprite;
import nl.smit.game_of_life.sprite.SpriteStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests the implementation of {@link Square}.
 *
 * @author Jordi Smit, 23-9-2017.
 */
@SuppressWarnings("PMD.TooManyMethods")
class SquareTest {
    /**
     * The default values.
     */
    private static final int
            BECOME_ALIVE_VALUE = 3;
    private static final String
            ALIVE_SPRITE_RESOURCE = "/sprite/alive.png",
            DEAD_SPRITE_RESOURCE = "/sprite/dead.png";

    /**
     * Dependencies.
     */
    private List<Square> neighbours;
    private SpriteStore spriteStore;
    private Sprite aliveSprite, deadSprite;


    /**
     * The object under testing.
     */
    private Square square;

    /**
     * Sets up the testing objects.
     */
    @BeforeEach
    void setUpSquareTest() {
        setUpSpriteStoreMock();
        setUpNeighbourMocks();

        square = new Square(spriteStore);
        linkToMockNeighbours();
    }

    private void setUpSpriteStoreMock() {
        aliveSprite = mock(Sprite.class);
        deadSprite = mock(Sprite.class);

        spriteStore = mock(SpriteStore.class);

        when(spriteStore.getSprite(ALIVE_SPRITE_RESOURCE)).thenReturn(aliveSprite);
        when(spriteStore.getSprite(DEAD_SPRITE_RESOURCE)).thenReturn(deadSprite);
    }

    private void setUpNeighbourMocks() {
        Square north = mock(Square.class);
        Square northEast = mock(Square.class);
        Square east = mock(Square.class);
        Square southEast = mock(Square.class);
        Square south = mock(Square.class);
        Square southWest = mock(Square.class);
        Square west = mock(Square.class);
        Square northWest = mock(Square.class);

        neighbours = Arrays.asList(
                north,
                northEast,
                east,
                southEast,
                south,
                southWest,
                west,
                northWest
        );
    }

    private void linkToMockNeighbours() {
        Direction[] directions = Direction.values();
        for (int i = 0; i < neighbours.size(); i++) {
            square.link(directions[i], neighbours.get(i));
        }
    }

    private void makeNeigboursAlive(int amount) {
        assert amount <= neighbours.size();
        for (int i = 0; i < amount; i++) {
            when(neighbours.get(i).isAlive()).thenReturn(true);
        }
    }

    //state changes
    private void aliveState() {
        square.setAlive(true);
    }

    private void deadState() {
        square.setAlive(false);
    }


    //actions
    private void calcNextStateAndgoToNextState() {
        square.prepareNextState();
        square.goToNextCycle();
    }

    //state asserts
    private void assertInAliveState() {
        assertThat(square.isAlive()).isTrue();
    }

    private void assertInDeadState() {
        assertThat(square.isAlive()).isFalse();
    }

    /**
     * Test if the square becomes alive in the next state.
     */
    @Test
    void becomeAliveInNextStateTest() {
        deadState();
        makeNeigboursAlive(BECOME_ALIVE_VALUE);

        calcNextStateAndgoToNextState();

        assertInAliveState();
    }

    /**
     * Test that it remain dead when no neighbour are alive.
     */
    @Test
    void remainsDeadTest() {
        deadState();
        makeNeigboursAlive(BECOME_ALIVE_VALUE - 1);

        calcNextStateAndgoToNextState();

        assertInDeadState();
    }

    /**
     * Boundary test for alive to dead state.
     * @param amountOfNeighbours The amount of living Neigbours.
     */
    @ParameterizedTest
    @CsvSource({
            "1",
            "4",
    })
    void fromAliveToDeadState(int amountOfNeighbours) {
        aliveState();
        makeNeigboursAlive(amountOfNeighbours);

        calcNextStateAndgoToNextState();

        assertInDeadState();
    }


    /**
     * Boundary test for alive to alive state.
     * @param amountOfNeighbours The amount of living Neigbours.
     */
    @ParameterizedTest
    @CsvSource({
            "2",
            "3",
    })
    void fromAliveToAliveState(int amountOfNeighbours) {
        aliveState();
        makeNeigboursAlive(amountOfNeighbours);

        calcNextStateAndgoToNextState();

        assertInAliveState();
    }

    /**
     * Verify that the alive state sprite is returned.
     */
    @Test
    void aliveStateSprite() {
        aliveState();

        assertThat(square.getSprite()).isEqualTo(aliveSprite);
    }

    /**
     * Verify that the dead state sprite is returned.
     */
    @Test
    void deadStateSprite() {
        deadState();

        assertThat(square.getSprite()).isEqualTo(deadSprite);
    }
}