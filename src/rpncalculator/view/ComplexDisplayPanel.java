package rpncalculator.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class ComplexDisplayPanel extends JPanel implements ActionListener {

    private JTextField xField;
    private JTextField yField;
    private JTextField zField;
    private JTextField tField;
    private JButton trashButton;

    public ComplexDisplayPanel() {
        super();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }

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

        trashButton = new JButton("D");
        trashButton.addActionListener(this);

        this.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        this.add(new JLabel(" X :"),gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        this.add(xField,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 0;
        this.add(trashButton,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0;
        this.add(new JLabel(" Y :"),gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        this.add(yField,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0;
        this.add(new JLabel(" Z :"),gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        this.add(zField,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0;
        this.add(new JLabel(" T :"),gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        this.add(tField,gridBagConstraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    void setValuesDisplayed(final Stack<Double> values) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                 xField.setText(Double.toString(values.get(0)));
                 yField.setText(Double.toString(values.get(1)));
                 zField.setText(Double.toString(values.get(2)));
                 tField.setText(Double.toString(values.get(3)));
            }
        });
    }
}
