package rpncalculator.view;


import rpncalculator.controler.CalculatorControler;
import rpncalculator.model.Calculatrice;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class BasicCalculatorView implements Observer{

    private JFrame frame = new JFrame("RPN");

    private LevelPanel level;
    private DisplayPanel display;
    private KeyboardPanel keyboard;

    public BasicCalculatorView() {
        level = new LevelPanel(LevelPanel.STATE_BASIC);
        display = new DisplayPanel();
        keyboard = new KeyboardPanel();
    }

    public void setValueDisplayed(double value){
        display.setValueDisplayed(value);
    }

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
                frame.setSize(300, 300);
                frame.setVisible(true);
            }
        });
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

    public static void main(String[] args) {
        //new BasicCalculatorView();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Calculatrice){
            Calculatrice c = (Calculatrice) o;
            display.setValueDisplayed(c.obtenirResultat());
        }
    }
}
