package rpncalculator.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelPanel extends JPanel implements ActionListener {

    public static final int STATE_BASIC = 1;
    public static final int STATE_BEGINNER = 2;

    private JButton beginnerButton;
    private JButton basicButton;

    public LevelPanel(final int state) {
        super();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
                setState(state);
            }
        });
    }

    private void createGUI(){
        beginnerButton = new JButton();
        beginnerButton.setText("Mode débutant");
        beginnerButton.setActionCommand("beginner");
        beginnerButton.addActionListener(this);

        basicButton = new JButton();
        basicButton.setText("Mode normal");
        basicButton.setActionCommand("basic");
        basicButton.addActionListener(this);

        GridLayout layout = new GridLayout(1,2);

        this.setLayout(layout);
        this.add(beginnerButton);
        this.add(basicButton);
    }

    public void setState(final int state){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(state == STATE_BASIC){
                    beginnerButton.setEnabled(true);
                    basicButton.setEnabled(false);
                }
                else{
                    beginnerButton.setEnabled(false);
                    basicButton.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("beginner".equals(e.getActionCommand())){
            // TODO call observer
        }
        else{
            // TODO call observer
        }
    }
}
