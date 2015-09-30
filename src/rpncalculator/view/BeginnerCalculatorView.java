package rpncalculator.view;


import javax.swing.*;
import java.util.Stack;

public class BeginnerCalculatorView {

    private JFrame frame = new JFrame("RPN");

    private LevelPanel level;
    private ComplexDisplayPanel display;
    private KeyboardPanel keyboard;

    public void createGUI(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

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
        });

    }

    public BeginnerCalculatorView(){
        level = new LevelPanel(LevelPanel.STATE_BEGINNER);
        display = new ComplexDisplayPanel();
        keyboard = new KeyboardPanel(null);
    }

    public void setValuesDisplayed(Stack<Double> values){
        display.setValuesDisplayed(values);
    }

    public static void main(String[] args) {
        new BeginnerCalculatorView();
    }

    public void close(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(false);
                frame.dispose();
            }
        });
    }
}
