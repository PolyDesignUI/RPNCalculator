package rpncalculator.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayPanel extends JPanel implements ActionListener{

    private JTextField displayField;
    private JButton trashButton;

    public DisplayPanel() {
        super();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }

    private void createGUI() {
        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setFont(new Font("SansSerif", Font.BOLD, 20));
        displayField.setText("0");
        displayField.setHorizontalAlignment(JTextField.RIGHT);

        trashButton = new JButton("D");
        trashButton.addActionListener(this);

        this.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.ipady = 40;
        this.add(displayField,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.ipady = 40;
        this.add(trashButton,gridBagConstraints);
    }

    public void setValueDisplayed(final int value){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                displayField.setText(Integer.toString(value));
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO call observer
    }
}
