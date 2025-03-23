package ui;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Egg;
import persistence.JsonReader;
import persistence.JsonWriter;

// Represents the main frame of the GUI.
public class EggUI extends JFrame {
    private static final String FILE_LOCATION = "./data/eggs.json";
    private JPanel buttonPanel;
    private List<EggThread> eggThreads;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs a frame with two panels.
    public EggUI() {
        buttonPanel = new JPanel();
        eggThreads = new ArrayList<>();
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(600, 800);
        setVisible(true);
        chooseEggButton();
        add(buttonPanel);
    }

    // EFFECTS: creates a button for egg choosing
    public void chooseEggButton() {
        JButton eggButton = new JButton("Choose egg");
        eggButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                chooseEggType();
            }

        });
        buttonPanel.add(eggButton);
    }

    // EFFECTS: creates a child frame and three buttons to choose type
    public void chooseEggType() {
        JFrame eggTypFrame = new JFrame();
        eggTypFrame.setLocationRelativeTo(this);
        eggTypFrame.setSize(300, 200);
        JPanel panel = new JPanel();
        List<String> buttonLabels = List.of("boiled", "fried", "scrambled");
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    chooseEggDoneness(label);
                    eggTypFrame.dispose();
                }
            });
            panel.add(button);
        }

        eggTypFrame.add(panel);
        eggTypFrame.setVisible(true);
    }

    // EFFECTS: creates a child frame and three buttons to choose doneness
    public void chooseEggDoneness(String label) {
        JFrame eggDonenessFrame = new JFrame();
        eggDonenessFrame.setLocationRelativeTo(this);
        eggDonenessFrame.setSize(300, 200);
        JPanel panel = new JPanel();
        List<String> buttonLabels = List.of("soft", "medium", "hard");
        for (String doneness : buttonLabels) {
            JButton button = new JButton(doneness);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addEgg(label, doneness);
                    eggDonenessFrame.dispose();
                }
            });
            panel.add(button);
        }
        eggDonenessFrame.add(panel);
        eggDonenessFrame.setVisible(true);
    }

    // EFFECTS: add egg to list of thread and start the threads
    public void addEgg(String label, String doneness) {
        switch (doneness) {
            case "soft":
                eggThreads.add(new EggThread(new Egg(label, 1)));
                break;
            case "medium":
                eggThreads.add(new EggThread(new Egg(label, 2)));
                break;
            case "hard":
                eggThreads.add(new EggThread(new Egg(label, 3)));
                break;
        }
        for (EggThread eggThread : eggThreads) {
            if (!eggThread.getStart()) {
                eggThread.start();
                EggPanel panel = new EggPanel(eggThread);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: reads the timers from the file
    public void readTimer() {

    }

    // EFFECTS: saves the egg timers to file
    public void saveTimer() {

    }

    public static void main(String[] args) {
        EggUI ui = new EggUI();
    }
}
