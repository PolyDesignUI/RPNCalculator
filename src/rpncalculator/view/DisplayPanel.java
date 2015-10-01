package rpncalculator.view;


import rpncalculator.controler.CalculatorControler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Simple afficheur de la calculatrice
 */
public class DisplayPanel extends JPanel implements ActionListener{

    /**
     * Historique de la séquence de l'opération en cours
     */
    private JLabel operationField;

    /**
     * Afficheur
     */
    private JTextField displayField;

    public DisplayPanel() {
        super();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }

    /**
     * Création de la vue
     */
    private void createGUI() {
        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setFont(new Font("SansSerif", Font.BOLD, 20));
        displayField.setText("0");
        displayField.setHorizontalAlignment(JTextField.RIGHT);

        JButton trashButton = new JButton("D");
        trashButton.addActionListener(this);

        operationField = new JLabel("",SwingConstants.RIGHT);
        operationField.setFont(new Font("SansSerif", Font.BOLD, 12));

        this.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.ipady = 40;
        this.add(displayField,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.ipady = 40;
        this.add(trashButton,gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 3;
        this.add(operationField,gridBagConstraints);
    }

    /**
     * Mise à jour de l'affichage de la vue
     *
     * @param value résultat de l'opération courante à afficher
     * @param operation texte de la séquence à mettre à jour
     */
    public void setValueDisplayed(final double value, final String operation){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                displayField.setText(Double.toString(value));
                operationField.setText(operation);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorControler.getInstance().performOperand(CalculatorControler.Operand.OPERAND_CLEAR);
    }
}
