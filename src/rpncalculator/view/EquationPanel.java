package rpncalculator.view;


import javax.swing.*;
import java.awt.*;

public class EquationPanel extends JPanel {

    private JLabel equation = new JLabel();
    private JLabel status = new JLabel("");

    public EquationPanel() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }

    private void createGUI(){
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));

        this.add(equation);
        this.add(Box.createHorizontalGlue());
        this.add(status);
    }

    public void setEquation(final String s){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                equation.setText(s);
            }
        });
    }

    private void changeStatus(boolean status){
        if(status){
            this.status.setText("Correct");
            this.status.setForeground(new Color(0,100,0));
        }
        else{
            this.status.setText("Invalid");
            this.status.setForeground(Color.red);
        }
    }

    public void setStatus(final boolean status){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                changeStatus(status);
            }
        });
    }
}
