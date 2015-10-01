package rpncalculator.view;


import rpncalculator.controler.CalculatorControler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  Panel de controle des modes débutant et normal
 */
public class LevelPanel extends JPanel implements ActionListener {

    private JButton beginnerButton;
    private JButton basicButton;

    public LevelPanel(final CalculatorControler.Mode state) {
        super();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
                setState(state);
            }
        });
    }

    /**
     * Création de la vue
     */
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

    private void setState(final CalculatorControler.Mode state){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(state == CalculatorControler.Mode.BEGINNER){
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
            CalculatorControler.getInstance().switchMode(CalculatorControler.Mode.BEGINNER);
        }
        else{
            CalculatorControler.getInstance().switchMode(CalculatorControler.Mode.BASIC);
        }
    }
}
