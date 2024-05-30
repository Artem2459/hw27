import javax.swing.*;

public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            TypingSpeedModel model = new TypingSpeedModel();
            TypingSpeedView view = new TypingSpeedView();
            TypingSpeedController controller = new TypingSpeedController(model, view);
            view.setVisible(true);
        }
    });
}