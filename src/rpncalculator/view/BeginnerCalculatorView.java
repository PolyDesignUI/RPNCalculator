package rpncalculator.view;


import rpncalculator.model.Calculatrice;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Vue de la calculatrice en mode débutant
 */
public class BeginnerCalculatorView  implements Observer {

    /**
     * Fenetre conteneur
     */
    private JFrame frame = new JFrame("RPN");

    /**
     * Affichage du switch entre le mode normale et débutant
     */
    private LevelPanel level;

    /**
     * Affichage de la pile en plus de l'afficheur des résultats de la calculatrice
     */
    private ComplexDisplayPanel display;

    /**
     * Affichage du pavé numérique de la calculatrice
     */
    private KeyboardPanel keyboard;

    /**
     * Méthode permettant la construction de la vue
     */
    private void createGUI(){
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

    public BeginnerCalculatorView(){
        level = new LevelPanel(LevelPanel.STATE_BEGINNER);
        display = new ComplexDisplayPanel();
        keyboard = new KeyboardPanel();
        createGUI();
    }

    /**
     * Ferme la fenetre
     */
    public void close(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(false);
            }
        });
    }

    /**
     * Affiche la fenetre
     */
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
            display.setValuesDisplayed(
                    c.lirePile(),
                    c.getSize(),
                    c.getSequence());
        }
    }
}
