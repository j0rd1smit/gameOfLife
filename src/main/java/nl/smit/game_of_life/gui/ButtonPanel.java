package nl.smit.game_of_life.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Map;

/**
 * A panel containing a button for every registered action.
 *
 * @author Jordi Smit, 2-10-2017.
 */
public class ButtonPanel extends JPanel {


    /**
     * Create a new button panel with a button for every action.
     *
     * @param buttons The map of caption - action for each button.
     * @param parent  The parent frame, used to return window focus.
     */
    public ButtonPanel(final Map<String, Action> buttons, final JFrame parent) {

        for (final String caption : buttons.keySet()) {
            JButton button = new JButton(caption);
            button.addActionListener(e -> {
                buttons.get(caption).preformAction();
                parent.requestFocusInWindow();
            });
            add(button);
        }

    }
}
