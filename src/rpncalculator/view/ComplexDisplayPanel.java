package rpncalculator.view;

import rpncalculator.controler.CalculatorControler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

/**
 * Panel personalisé affichant les valeurs courantes de la pile
 */
public class ComplexDisplayPanel extends JPanel implements ActionListener {

    /**
     * Historique de la séquence de l'opération en cours
     */
    private JLabel operationField;

    /*
        Champs de la pile
     */
    private JTextField xField;
    private JTextField yField;
    private JTextField zField;
    private JTextField tField;

    public ComplexDisplayPanel() {
        super();

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
    private void createGUI() {
        xField = new JTextField();
        xField.setEditable(false);
        xField.setFont(new Font("SansSerif", Font.BOLD, 20));
        xField.setText("0");
        xField.setHorizontalAlignment(JTextField.RIGHT);

        yField = new JTextField();
        yField.setEditable(false);
        yField.setFont(new Font("SansSerif", Font.BOLD, 12));
        yField.setText("0");
        yField.setHorizontalAlignment(JTextField.RIGHT);

        zField = new JTextField();
        zField.setEditable(false);
        zField.setFont(new Font("SansSerif", Font.BOLD, 12));
        zField.setText("0");
        zField.setHorizontalAlignment(JTextField.RIGHT);

        tField = new JTextField();
        tField.setEditable(false);
        tField.setFont(new Font("SansSerif", Font.BOLD, 12));
        tField.setText("0");
        tField.setHorizontalAlignment(JTextField.RIGHT);

        operationField = new JLabel("",SwingConstants.RIGHT);
        operationField.setFont(new Font("SansSerif", Font.BOLD, 12));

        JButton trashButton = new JButton("D");
        trashButton.addActionListener(this);

        this.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        this.add(new JLabel(" X :"),gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        this.add(xField,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 0;
        this.add(trashButton,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 0;
        this.add(new JLabel(" Y :"),gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        this.add(yField,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0;
        this.add(new JLabel(" Z :"),gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        this.add(zField,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0;
        this.add(new JLabel(" T :"),gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        this.add(tField,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 2;
        this.add(operationField,gridBagConstraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorControler.getInstance().performOperand(CalculatorControler.Operand.OPERAND_CLEAR);

    }

    /**
     * Mise à jour de l'affichage de la vue
     *
     * @param it itérateur sur la pile à afficher
     * @param size taille courante de la pile
     * @param operation texte de la séquence à mettre à jour
     */
    public void setValuesDisplayed(final Iterator<Double> it, final int size, final String operation) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                tField.setText((size >= 4 ? Double.toString(it.next()) : "0.0"));
                zField.setText((size >= 3 ? Double.toString(it.next()) : "0.0"));
                yField.setText((size >= 2 ? Double.toString(it.next()) : "0.0"));
                xField.setText((size >= 1 ? Double.toString(it.next()) : "0.0"));
                operationField.setText(operation);
            }
        });
    }
}
