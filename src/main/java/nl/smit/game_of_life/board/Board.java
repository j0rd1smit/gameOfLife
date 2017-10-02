package nl.smit.game_of_life.board;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * A containter class that stores the squares in a grid.
 *
 * @author Jordi Smit, 22-9-2017.
 */
public class Board {
    private static final int INTERVAL = 500;

    private ScheduledExecutorService service;
    @Getter(AccessLevel.PRIVATE)
    private boolean inProgress;

    /**
     * The lock that ensures starting and stopping can't interfere with each
     * other.
     */
    private final Object startStopLock = new Object();
    /**
     * The lock that ensures moves are executed sequential.
     */
    private final Object moveLock = new Object();

    private final Square[][] grid;
    @Getter(AccessLevel.PUBLIC)
    private final int
            width,
            height;

    /**
     * The default constructor.
     *
     * @param grid The square grid of the board.
     */
    Board(Square[][] grid) {
        this.grid = grid;
        this.width = grid.length;
        this.height = grid[0].length;
        this.service = Executors.newSingleThreadScheduledExecutor();
    }

    public Stream<Square> streamSquares() {
        return Arrays.stream(grid).flatMap(Arrays::stream);
    }

    /**
     * Preforms a single cycle step.
     */
    public void preformStep() {
        synchronized (moveLock) {
            streamSquares().forEach(Square::prepareNextState);
            streamSquares().forEach(Square::goToNextCycle);
        }
    }

    public Square squareAt(int x, int y) {
        return grid[x][y];
    }

    public void start() {
        synchronized (startStopLock) {
            if (!isInProgress()) {
                this.service = Executors.newSingleThreadScheduledExecutor();
                service.schedule(new CycleTask(service, INTERVAL, this), 0, TimeUnit.MILLISECONDS);
                this.inProgress = true;
            }
        }
    }

    public void stop() {
        synchronized (startStopLock) {
            if (isInProgress()) {
                service.shutdown();
                this.inProgress = false;
            }
        }
    }




    private static class CycleTask implements Runnable {
        private final Board board;
        private final ScheduledExecutorService service;
        private final long interval;

        CycleTask(ScheduledExecutorService service, long interval, Board board) {
            this.service = service;
            this.interval = interval;
            this.board = board;
        }

        @Override
        public void run() {
            board.preformStep();
            service.schedule(this, interval, TimeUnit.MILLISECONDS);
        }
    }
}
