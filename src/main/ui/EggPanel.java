package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

// Represents the panel for displaying time.
public class EggPanel extends JPanel {
    EggThread eggThread;
    JFrame timerFrame;
    JLabel timeLabel;

    // EFFECTS: constructs a frame to display timer
    public EggPanel(EggThread eggThread) {

    }

    // MODIFIES: this
    // EFFECTS: update the displaying time according to the thread.
    private void updateTimeLabel() {

    }

    // EFFECTS: get the display text for timer
    private String getEggTimeText() {
        return eggThread.getEgg().getDisplayName() + " " + eggThread.getEgg().getRemainingTimeInMinute();
    }
}
