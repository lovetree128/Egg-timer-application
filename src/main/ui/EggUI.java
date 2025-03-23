package ui;

import java.util.ArrayList;
import java.util.List;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Egg;

// Represents the main frame of the GUI.
public class EggUI extends JFrame {
    private JPanel buttonPanel;
    private List<EggThread> eggThreads;

    // EFFECTS: constructs a frame with two panels.
    public EggUI() {

    }

    // EFFECTS: creates a button for egg choosing
    public void chooseEggButton() {

    }

    // EFFECTS: creates a child frame and three buttons to choose type
    public void chooseEggType() {

    }

    // EFFECTS: creates a child frame and three buttons to choose doneness
    public void chooseEggDoneness(String label) {

    }

    // EFFECTS: add egg to list of thread and start the threads
    public void addEgg(String label, String doneness) {

    }

    public static void main(String[] args) {
        EggUI ui = new EggUI();
    }
}
