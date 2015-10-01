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
        createGUI();
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

                frame.setFocusable(true);
                frame.addKeyListener(keyboard);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.getContentPane().add(content);
                frame.pack();
                frame.setSize(300, 300);
            }
        });
    }

    public void close(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(false);
            }
        });
    }

    public void show(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Calculatrice){
            Calculatrice c = (Calculatrice) o;
            display.setValueDisplayed(c.obtenirResultat(),c.getSequence());
        }
    }
}
