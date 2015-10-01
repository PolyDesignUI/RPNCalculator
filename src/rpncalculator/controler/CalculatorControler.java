package rpncalculator.controler;

import rpncalculator.model.Calculatrice;
import rpncalculator.view.BasicCalculatorView;
import rpncalculator.view.BeginnerCalculatorView;

import java.util.Iterator;
import java.util.Stack;


public class CalculatorControler {

    private static CalculatorControler instance;

    public enum Mode {BASIC, BEGINNER}

    public enum Operand {
        OPERAND_ADD,
        OPERAND_SUB,
        OPERAND_MUL,
        OPERAND_DIV,
        OPERAND_ENTER,
        OPERAND_CLEAR
        }

    private Calculatrice theCalculatrice;
    private BasicCalculatorView theBasicView;
    private BeginnerCalculatorView beginnerView;

    private Mode mode = Mode.BASIC;

    public synchronized static CalculatorControler getInstance(){
        if(instance == null){
            instance = new CalculatorControler();
        }
        return instance;
    }

    private CalculatorControler(){
        theCalculatrice= new Calculatrice();
        theBasicView= new BasicCalculatorView();
        beginnerView = new BeginnerCalculatorView();
        theCalculatrice.addObserver(theBasicView);
        theCalculatrice.addObserver(beginnerView);
        switchMode(Mode.BASIC);
    }

    public void switchMode(Mode newMode){
        this.mode = newMode;
        if(mode.equals(Mode.BASIC)){
            theBasicView.show();
            beginnerView.close();
        }
        else{
            theBasicView.close();
            beginnerView.show();
        }
    }

    public void performDigit(char e){
        if(e >= '0' && e <= '9'){
            theCalculatrice.addDigit(e);
        }
    }

    public void performOperand(Operand e){
        switch (e){
            case OPERAND_ADD:
                theCalculatrice.ajouter();
                break;
            case OPERAND_SUB:
                theCalculatrice.soustraire();
                break;
            case OPERAND_DIV:
                theCalculatrice.diviser();
                break;
            case OPERAND_MUL:
                theCalculatrice.multiplier();
                break;
            case OPERAND_ENTER:
                theCalculatrice.enter();
                break;
            default:
            case OPERAND_CLEAR:
                theCalculatrice.reinitialiser();
                break;
        }
    }


}
