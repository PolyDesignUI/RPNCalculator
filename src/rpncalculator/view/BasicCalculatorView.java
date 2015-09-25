package rpncalculator.view;


import javax.swing.*;

public class BasicCalculatorView {

    private JFrame frame = new JFrame("RPN");

    private LevelPanel level;
    private DisplayPanel display;
    private KeyboardPanel keyboard;

    public BasicCalculatorView() {
        level = new LevelPanel(LevelPanel.STATE_BASIC);
        display = new DisplayPanel();
        keyboard = new KeyboardPanel();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }

    public void setValueDisplayed(double value){
        display.setValueDisplayed(value);
    }

    private void createGUI(){

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(level);
        content.add(display);
        content.add(keyboard);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(content);
        frame.pack();
        frame.setSize(300,300);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new BasicCalculatorView();
    }
}
