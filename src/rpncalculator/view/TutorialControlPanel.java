package rpncalculator.view;

import rpncalculator.controler.CalculatorControler;
import rpncalculator.model.Exercice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TutorialControlPanel extends JPanel implements ActionListener {

    private JButton next = new JButton(">");
    private JLabel exercice = new JLabel("");

    public TutorialControlPanel() {
        super();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }

    private void createGUI() {
        this.setLayout(new FlowLayout());
        this.add(exercice);
        this.add(next);
        next.addActionListener(this);
    }

    public void setExerciceName(final String s){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                exercice.setText(s);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorControler.getInstance().newExercice();
    }
}
