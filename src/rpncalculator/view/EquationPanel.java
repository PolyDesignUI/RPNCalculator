package rpncalculator.view;


import javax.swing.*;

public class EquationPanel extends JPanel {

    private JLabel equation = new JLabel();
    private JLabel status = new JLabel("X");

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
}
