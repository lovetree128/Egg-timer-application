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
        this.eggThread = eggThread;
        timeLabel = new JLabel(getEggTimeText());
        timerFrame = new JFrame(eggThread.getEgg().getDisplayName());
        add(timeLabel);
        new Timer(1000, e -> updateTimeLabel()).start();
        timerFrame.setSize(200,200);
        timerFrame.add(this);
        timerFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: update the displaying time according to the thread.
    private void updateTimeLabel() {
        timeLabel.setText(getEggTimeText());
        revalidate();
        repaint();
    }

    // EFFECTS: get the display text for timer
    private String getEggTimeText() {
        return eggThread.getEgg().getDisplayName() + " " + eggThread.getEgg().getRemainingTimeInMinute();
    }
}
