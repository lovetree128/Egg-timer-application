package ui;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    private List<EggPanel> eggPanels;

    // EFFECTS: constructs a frame with two panels.
    public EggUI() {
        jsonReader = new JsonReader(FILE_LOCATION);
        jsonWriter = new JsonWriter(FILE_LOCATION);
        buttonPanel = new JPanel();
        eggThreads = new ArrayList<>();
        eggPanels = new ArrayList<>();
        buttonPanel.setLayout(new GridLayout(0, 1));
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        chooseEggButton();
        readButton();
        saveButton();
        highlightByType();
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
        startTimer();
    }

    // EFFECTS: creates a button for reading the timers from the file
    public void readButton() {
        JButton eggButton = new JButton("Load eggs from file");
        eggButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                readTimer();
            }

        });
        buttonPanel.add(eggButton);
    }

    // MODIFIES: this
    // EFFECTS: reads the timers from the file
    public void readTimer() {
        List<Egg> eggs = new ArrayList<>();
        try {
            eggs = jsonReader.read();
            for (Egg egg : eggs) {
                eggThreads.add(new EggThread(egg));
                startTimer();
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + FILE_LOCATION);
        }
    }

    // EFFECTS: creates a save button
    public void saveButton() {
        JFrame warningFrame = new JFrame();
        warningFrame.setSize(400, 400);
        warningFrame.setLocationRelativeTo(this);
        JLabel warningText = new JLabel("No eggs to save, add some eggs first");
        warningFrame.add(warningText);
        JButton eggButton = new JButton("Save eggs from file");
        eggButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (eggThreads.isEmpty()) {
                    warningFrame.setVisible(true);
                } else {
                    saveTimer();
                }
            }

        });
        buttonPanel.add(eggButton);
    }

    // EFFECTS: saves the egg timers to file
    public void saveTimer() {
        List<Egg> eggs = new ArrayList<>();
        for (EggThread eggThread : eggThreads) {
            eggs.add(eggThread.getEgg());
            eggThread.stopThread();
        }
        try {
            jsonWriter.open();
            jsonWriter.write(eggs);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + FILE_LOCATION);
        }
    }

    // EFFECTS: start the threads in the list of thread
    public void startTimer() {
        for (EggThread eggThread : eggThreads) {
            if (!eggThread.getStart()) {
                eggThread.start();
                EggPanel panel = new EggPanel(eggThread);
                eggPanels.add(panel);
            }
        }
    }

    // EFFECTS: highlights the timers by type
    public void highlightByType() {
        JButton eggButton = new JButton("Highlight timers by type");
        eggButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                highlightByTypeButton();
            }

        });
        buttonPanel.add(eggButton);
    }

    // EFFECTS: creates a new frame and three buttons for highlighting
    public void highlightByTypeButton() {
        JFrame warningFrame = new JFrame();
        warningFrame.setSize(400, 400);
        warningFrame.setLocationRelativeTo(this);
        JPanel panel = new JPanel();
        List<String> buttonLabels = List.of("boiled", "fried", "scrambled");
        for (String type : buttonLabels) {
            JButton button = new JButton(type);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setToRed(type);
                }
            });
            panel.add(button);
        }
        warningFrame.add(panel);
        warningFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: highlight the chosen timers
    public void setToRed(String type) {
        for (EggPanel eggPanel : eggPanels) {
            if (eggPanel.getEggThread().getEgg().getMethod().equals(type)) {
                eggPanel.setBackground(Color.RED);
            } else {
                eggPanel.setBackground(Color.WHITE);
            }
        }
    }

    // start the GUI
    public static void main(String[] args) {
        EggUI ui = new EggUI();
    }
}
