import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class TypingSpeedController {
    private TypingSpeedModel model;
    private TypingSpeedView view;
    private Timer timer;

    public TypingSpeedController(TypingSpeedModel model, TypingSpeedView view) {
        this.model = model;
        this.view = view;

        view.startButton.addActionListener(e -> startTest());
        view.exitButton.addActionListener(e -> System.exit(0));
        view.inputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (model.isTestStarted()) {
                    checkInput();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        timer = new Timer(1000, new ActionListener() {
            int remainingTime = 60;

            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime--;
                view.setTimerLabel("Час: " + remainingTime / 60 + ":" + String.format("%02d", remainingTime % 60));
                if (remainingTime <= 0) {
                    endTest();
                }
            }
        });
    }

    private void startTest() {
        model.startTest();
        view.setTextArea(model.getCurrentText());
        view.enableInputField(true);
        view.enableStartButton(false);
        view.inputField.requestFocus();
        view.inputField.setText("");
        timer.start();
    }

    private void endTest() {
        model.endTest();
        view.enableInputField(false);
        timer.stop();
        String userInput = view.getInputText();
        int errors = model.calculateErrors(userInput);
        double wpm = model.calculateWPM(userInput);
        view.setResultLabel("Помилок: " + errors + " | Кількість слов в хвилину: " + wpm);
        view.enableStartButton(false);
        view.enableExitButton(true);
    }

    private void checkInput() {
        String userInput = view.getInputText();
        if (userInput.length() <= model.getCurrentText().length()) {
            String currentSubText = model.getCurrentText().substring(0, userInput.length());
            if (!userInput.equals(currentSubText)) {
                view.inputField.setForeground(Color.RED);
            } else {
                view.inputField.setForeground(Color.BLACK);
                if (userInput.equals(model.getCurrentText())) {
                    endTest();
                }
            }
        }
    }
}
