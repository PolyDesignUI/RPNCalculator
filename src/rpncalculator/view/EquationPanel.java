package rpncalculator.view;


import javax.swing.*;
import java.awt.*;

/**
 * Affichage de l'équation dans la vue tutoriel
 */
public class EquationPanel extends JPanel {

    /**
     * Equation en cours
     */
    private JLabel equation = new JLabel();

    /**
     * Statut de l'équation :
     *  - correct : l'utilisateur à réussi à reproduire l'équation
     *  - invalid : il n'a pas encore réussi
     */
    private JLabel status = new JLabel("");

    public EquationPanel() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }

    /**
     * Construction de la vue
     */
    private void createGUI(){
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));

        this.add(equation);
        this.add(Box.createHorizontalGlue());
        this.add(status);
    }

    /**
     * Mise à jour de l'équation courante
     * @param s
     */
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

    /**
     * Mise à jour de la réussite de l'équation courante
     * @param status true = Correct, false = Invalid
     */
    public void setStatus(final boolean status){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                changeStatus(status);
            }
        });
    }
}
