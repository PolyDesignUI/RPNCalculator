package rpncalculator.view;

import javax.swing.*;

public class TutorialView {

    private JFrame frame = new JFrame("Tutoriel");


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
