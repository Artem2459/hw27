import javax.swing.*;
import java.awt.*;

class TypingSpeedView extends JFrame {
    private JTextArea textArea;
    JTextField inputField;
    JButton startButton;
    JButton exitButton;
    private JLabel resultLabel;
    private JLabel timerLabel;

    public TypingSpeedView() {
        setTitle("Typing Speed Test");
        setSize(450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        inputField = new JTextField();
        add(inputField, BorderLayout.SOUTH);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        timerLabel = new JLabel("Час: 1:00");
        topPanel.add(timerLabel);

        startButton = new JButton("Почати тест");
        topPanel.add(startButton);

        exitButton = new JButton("Вийти");
        exitButton.setEnabled(false);
        topPanel.add(exitButton);

        add(topPanel, BorderLayout.NORTH);

        resultLabel = new JLabel();
        add(resultLabel, BorderLayout.EAST);
    }

    public void setTextArea(String text) {
        textArea.setText(text);
    }

    public String getInputText() {
        return inputField.getText();
    }

    public void setResultLabel(String result) {
        resultLabel.setText(result);
    }

    public void setTimerLabel(String time) {
        timerLabel.setText(time);
    }

    public void enableInputField(boolean enabled) {
        inputField.setEnabled(enabled);
    }

    public void enableStartButton(boolean enabled) {
        startButton.setEnabled(enabled);
    }

    public void enableExitButton(boolean enabled) {
        exitButton.setEnabled(enabled);
    }
}
