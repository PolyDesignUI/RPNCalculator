package rpncalculator.view;


import javax.swing.*;

public class BasicCalculatorView {

    private JFrame frame = new JFrame("RPN");

    public BasicCalculatorView() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(new LevelPanel(LevelPanel.STATE_BASIC));
        content.add(new DisplayPanel());
        content.add(new KeyboardPanel());

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
