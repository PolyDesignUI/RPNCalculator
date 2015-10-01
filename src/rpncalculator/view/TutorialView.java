package rpncalculator.view;

import rpncalculator.model.Calculatrice;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class TutorialView implements Observer{

    private final JFrame frame = new JFrame("Tutoriel");
    private final JLabel modeLabel = new JLabel("Mode débutant");
    private JProgressBar progressBar = new JProgressBar();
    private TutorialControlPanel controlPanel = new TutorialControlPanel();
    private EquationPanel equationPanel = new EquationPanel();
    private final JButton resetButton = new JButton("Recommencer");


    public TutorialView() {
        createGUI();
    }

    private void createGUI(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                progressBar.setMinimum(0);
                progressBar.setMaximum(100);

                JPanel content = new JPanel();
                content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
                content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                content.add(modeLabel);
                content.add(progressBar);
                content.add(controlPanel);
                content.add(equationPanel);
                content.add(resetButton);

                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.getContentPane().add(content);
                frame.pack();
                frame.setLocation(300,0);
                frame.setSize(300,300);
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

    /**
     * Value between 0 and 100
     * @param value
     */
    public void setProgression(final int value){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                progressBar.setValue(value);
            }
        });
    }

    public static void main(String[] args) {
        new TutorialView();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Calculatrice){
            final Calculatrice c = (Calculatrice) o;
            controlPanel.setExerciceName("Exercice n°" + c.obtenirExerciceEnCours().obtenirId());
            equationPanel.setEquation(c.obtenirExerciceEnCours().obtenirQuestion());
        }
    }
}
