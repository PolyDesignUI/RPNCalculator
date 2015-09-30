package rpncalculator.view;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class TutorialView {

    private final JFrame frame = new JFrame("Tutoriel");
    private final JLabel modeLabel = new JLabel("Mode débutant",SwingConstants.CENTER);
    private JProgressBar progressBar = new JProgressBar();
    private TutorialControlPanel controlPanel = new TutorialControlPanel();
    private EquationPanel equationPanel = new EquationPanel();
    private JTextPane errorLog = new JTextPane();
    private final JButton resetButton = new JButton("Recommencer");
    private StyledDocument errorDocument;


    public TutorialView() {
        errorDocument = errorLog.getStyledDocument();
    }

    public void createGUI(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                errorLog.setEditable(false);
                errorLog.setAlignmentX(Component.CENTER_ALIGNMENT);
                modeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                progressBar.setMinimum(0);
                progressBar.setMaximum(100);

                JPanel content = new JPanel();
                content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
                content.add(modeLabel);
                content.add(progressBar);
                content.add(controlPanel);
                content.add(equationPanel);
                content.add(errorLog);
                content.add(resetButton);
                content.setAlignmentX(JPanel.CENTER_ALIGNMENT);

                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.getContentPane().add(content);
                frame.pack();
                frame.setSize(300,300);
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

    public void setError(final String error){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    errorDocument.insertString(errorDocument.getLength(),'\n'+error, new SimpleAttributeSet());
                } catch (BadLocationException e) {
                    errorLog.setText(error);
                }
            }
        });
    }

    public static void main(String[] args) {
        new TutorialView();
    }
}
