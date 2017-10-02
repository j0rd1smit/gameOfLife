package nl.smit.game_of_life.gui;

import nl.smit.game_of_life.board.Board;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * [Class explanation]
 *
 * @author Jordi Smit, 1-10-2017.
 */
public class UI extends JFrame {

    /**
     * The desired frame rate interval for the graphics in milliseconds, 40
     * being 25 fps.
     */
    private static final int FRAME_INTERVAL = 40;

    /**
     * The sub panels.
     */
    private final BoardPanel boardPanel;


    @SuppressWarnings("initialization")
    UI(final Board board, final Map<String, Action> buttons) {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ButtonPanel buttonPanel = new ButtonPanel(buttons, this);
        boardPanel = new BoardPanel(board);

        Container containerPanel = getContentPane();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.add(buttonPanel, BorderLayout.SOUTH);
        containerPanel.add(boardPanel, BorderLayout.CENTER);

        pack();
    }

    /**
     * Starts the "engine", the thread that redraws the interface at set
     * intervals.
     */
    public void start() {
        setVisible(true);
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(this::nextFrame, 0, FRAME_INTERVAL, TimeUnit.MILLISECONDS);

    }

    private void nextFrame() {
        boardPanel.repaint();
    }

}
