package rpncalculator.view;


import rpncalculator.controler.CalculatorControler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Panel numérique de la calculatrice
 */
public class KeyboardPanel extends JPanel implements ActionListener, KeyListener {

    /*
        Boutons actions
     */
    private JButton addButton;
    private JButton subButton;
    private JButton mulButton;
    private JButton divButton;

    private JButton enterButton;

    /*
        Boutons numérique 0-9
     */
    private JButton oneButton;
    private JButton twoButton;
    private JButton threeButton;
    private JButton fourButton;
    private JButton fiveButton;
    private JButton sixButton;
    private JButton sevenButton;
    private JButton heigtButton;
    private JButton nineButton;
    private JButton zeroButton;


    public KeyboardPanel() {
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
        addButton = new JButton("+");
        addButton.setActionCommand("add");
        addButton.addActionListener(this);
        subButton = new JButton("-");
        subButton.setActionCommand("sub");
        subButton.addActionListener(this);
        mulButton = new JButton("x");
        mulButton.setActionCommand("mul");
        mulButton.addActionListener(this);
        divButton = new JButton("/");
        divButton.setActionCommand("div");
        divButton.addActionListener(this);

        enterButton = new JButton("Enter");
        enterButton.setActionCommand("enter");
        enterButton.addActionListener(this);

        zeroButton = new JButton("0");
        zeroButton.setActionCommand("0");
        zeroButton.addActionListener(this);
        oneButton = new JButton("1");
        oneButton.setActionCommand("1");
        oneButton.addActionListener(this);
        twoButton = new JButton("2");
        twoButton.setActionCommand("2");
        twoButton.addActionListener(this);
        threeButton = new JButton("3");
        threeButton.setActionCommand("3");
        threeButton.addActionListener(this);
        fourButton = new JButton("4");
        fourButton.setActionCommand("4");
        fourButton.addActionListener(this);
        fiveButton = new JButton("5");
        fiveButton.setActionCommand("5");
        fiveButton.addActionListener(this);
        sixButton = new JButton("6");
        sixButton.setActionCommand("6");
        sixButton.addActionListener(this);
        sevenButton = new JButton("7");
        sevenButton.setActionCommand("7");
        sevenButton.addActionListener(this);
        heigtButton = new JButton("8");
        heigtButton.setActionCommand("8");
        heigtButton.addActionListener(this);
        nineButton = new JButton("9");
        nineButton.setActionCommand("9");
        nineButton.addActionListener(this);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.add(subButton,gridBagConstraints);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.add(addButton,gridBagConstraints);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        this.add(mulButton,gridBagConstraints);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        this.add(divButton,gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        this.add(enterButton,gridBagConstraints);
        gridBagConstraints.gridwidth = 1;

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        this.add(zeroButton,gridBagConstraints);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        this.add(oneButton,gridBagConstraints);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        this.add(twoButton,gridBagConstraints);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        this.add(threeButton,gridBagConstraints);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        this.add(fourButton,gridBagConstraints);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        this.add(fiveButton,gridBagConstraints);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        this.add(sixButton,gridBagConstraints);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        this.add(sevenButton,gridBagConstraints);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        this.add(heigtButton,gridBagConstraints);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        this.add(nineButton,gridBagConstraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if("0".equals(e.getActionCommand())){
            CalculatorControler.getInstance().performDigit('0');
        }
        else if("1".equals(e.getActionCommand())){
            CalculatorControler.getInstance().performDigit('1');
        }
        else if("2".equals(e.getActionCommand())){
            CalculatorControler.getInstance().performDigit('2');
        }
        else if("3".equals(e.getActionCommand())){
            CalculatorControler.getInstance().performDigit('3');
        }
        else if("4".equals(e.getActionCommand())){
            CalculatorControler.getInstance().performDigit('4');
        }
        else if("5".equals(e.getActionCommand())){
            CalculatorControler.getInstance().performDigit('5');
        }
        else if("6".equals(e.getActionCommand())){
            CalculatorControler.getInstance().performDigit('6');
        }
        else if("7".equals(e.getActionCommand())){
            CalculatorControler.getInstance().performDigit('7');
        }
        else if("8".equals(e.getActionCommand())){
            CalculatorControler.getInstance().performDigit('8');
        }
        else if("9".equals(e.getActionCommand())){
            CalculatorControler.getInstance().performDigit('9');
        }
        else if("add".equals(e.getActionCommand())){
            CalculatorControler.getInstance().performOperand(CalculatorControler.Operand.OPERAND_ADD);
        }
        else if("sub".equals(e.getActionCommand())){
            CalculatorControler.getInstance().performOperand(CalculatorControler.Operand.OPERAND_SUB);
        }
        else if("div".equals(e.getActionCommand())){
            CalculatorControler.getInstance().performOperand(CalculatorControler.Operand.OPERAND_DIV);
        }
        else if("mul".equals(e.getActionCommand())){
            CalculatorControler.getInstance().performOperand(CalculatorControler.Operand.OPERAND_MUL);
        }
        else if("enter".equals(e.getActionCommand())){
            CalculatorControler.getInstance().performOperand(CalculatorControler.Operand.OPERAND_ENTER);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Pour gérer la pression des touches à partir du clavier
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode()+" -> " +e.getKeyChar());
        switch (e.getKeyCode()){
            case KeyEvent.VK_0:
                CalculatorControler.getInstance().performDigit('0');
                break;
            case KeyEvent.VK_1:
                CalculatorControler.getInstance().performDigit('1');
                break;
            case KeyEvent.VK_2:
                CalculatorControler.getInstance().performDigit('2');
                break;
            case KeyEvent.VK_3:
                CalculatorControler.getInstance().performDigit('3');
                break;
            case KeyEvent.VK_4:
                CalculatorControler.getInstance().performDigit('4');
                break;
            case KeyEvent.VK_5:
                CalculatorControler.getInstance().performDigit('5');
                break;
            case KeyEvent.VK_6:
                CalculatorControler.getInstance().performDigit('6');
                break;
            case KeyEvent.VK_7:
                CalculatorControler.getInstance().performDigit('7');
                break;
            case KeyEvent.VK_8:
                CalculatorControler.getInstance().performDigit('8');
                break;
            case KeyEvent.VK_9:
                CalculatorControler.getInstance().performDigit('9');
                break;
            case KeyEvent.VK_ENTER:
                CalculatorControler.getInstance().performOperand(CalculatorControler.Operand.OPERAND_ENTER);
                break;
            case KeyEvent.VK_PLUS:
                CalculatorControler.getInstance().performOperand(CalculatorControler.Operand.OPERAND_ADD);
                break;
            case KeyEvent.VK_MINUS:
                CalculatorControler.getInstance().performOperand(CalculatorControler.Operand.OPERAND_SUB);
                break;
            case KeyEvent.VK_MULTIPLY:
                CalculatorControler.getInstance().performOperand(CalculatorControler.Operand.OPERAND_MUL);
                break;
            case KeyEvent.VK_DIVIDE:
                CalculatorControler.getInstance().performOperand(CalculatorControler.Operand.OPERAND_DIV);
                break;
            case KeyEvent.VK_CLEAR:
                CalculatorControler.getInstance().performOperand(CalculatorControler.Operand.OPERAND_CLEAR);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
