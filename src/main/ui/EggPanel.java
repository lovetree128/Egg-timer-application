package ui;

import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
        new Timer(1, e -> updateTimeLabel()).start();
        timerFrame.setSize(400,200);
        timerFrame.add(this);
        timerFrame.setVisible(true);
        closeButton();
    }

    // MODIFIES: this
    // EFFECTS: update the displaying time according to the thread.
    public void updateTimeLabel() {
        if (!eggThread.getRunning()) {
            timerFrame.dispose();
        }
        timeLabel.setText(getEggTimeText());
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: creates a button to close the window
    public void closeButton() {
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eggThread.stopThread();
                timerFrame.dispose();
            }
        });
        add(closeButton);
    }

    // EFFECTS: creates a button to restart the timer
    public void restartButton() {

    }

    // EFFECTS: get the display text for timer
    private String getEggTimeText() {
        return eggThread.getEgg().getRemainingTimeInMinute();
    }

    public EggThread getEggThread() {
        return eggThread;
    }
}
