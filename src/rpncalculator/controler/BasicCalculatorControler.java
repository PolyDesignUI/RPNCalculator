package rpncalculator.controler;

import rpncalculator.model.Calculatrice;
import rpncalculator.view.BasicCalculatorView;
import rpncalculator.view.BeginnerCalculatorView;
import rpncalculator.view.TutorialView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Stack;


public class BasicCalculatorControler {

    public static final int OPERAND_ADD = 1;
    public static final int OPERAND_SUB = 2;
    public static final int OPERAND_MUL = 3;
    public static final int OPERAND_DIV = 4;
    public static final int OPERAND_ENTER = 5;
    public static final int OPERAND_CLEAR = 6;

    private Calculatrice theCalculatrice;
    private BasicCalculatorView theBasicView;
    private BeginnerCalculatorView theBeginnerView;
    private TutorialView theTutorialView;
    private StringBuffer currentNumber = new StringBuffer();

    private boolean calcControl = true;

    public BasicCalculatorControler(){


        this.theCalculatrice= new Calculatrice() ;
        this.theBasicView= new BasicCalculatorView(this);
        this.theBeginnerView=new BeginnerCalculatorView();
        this.theTutorialView=new TutorialView();
        theBasicView.createGUI();


    }

    public void performDigit(Character e){


        currentNumber.append(e);
        String str = currentNumber.toString();
        double value = Double.parseDouble(str);
        theBasicView.setValueDisplayed(value);

    }

    public void performOperand(int e){
        double result;
        Iterator<Double> itr;

        double value = Double.parseDouble(currentNumber.toString());
        theCalculatrice.empiler(value);
        if(calcControl){

        if(e==OPERAND_ADD){theCalculatrice.ajouter();} //addition
        if(e==OPERAND_SUB){theCalculatrice.soustraire();} //subtraction
        if(e==OPERAND_MUL){theCalculatrice.multiplier();} //multiplication
        if(e==OPERAND_DIV){theCalculatrice.diviser();} //division
        if(e==OPERAND_ENTER){}  //enter
        if(e==OPERAND_CLEAR){theCalculatrice.reinitialiser();} //clear
            value= theCalculatrice.obtenirResultat();
            currentNumber = new StringBuffer(Double.toString(value));
            theBasicView.setValueDisplayed(value);
        }
        else{


            if(e==OPERAND_ADD){theCalculatrice.ajouter();} //addition
            if(e==OPERAND_SUB){theCalculatrice.soustraire();} //subtraction
            if(e==OPERAND_MUL){theCalculatrice.multiplier();} //multiplication
            if(e==OPERAND_DIV){theCalculatrice.diviser();} //division
            if(e==OPERAND_ENTER){ itr=theCalculatrice.lirePile(); result=itr.next(); theCalculatrice.empiler(result);}  //enter
            if(e==OPERAND_CLEAR){theCalculatrice.reinitialiser();} //clear

            Stack<Double> pile = theCalculatrice.getPile();
            theBeginnerView.setValuesDisplayed(pile);
        }

    }

    public void openBeginnerCalculator(){
        calcControl=false;
        theBasicView.close();
        theBeginnerView.createGUI();
        //theTutorialView.createGUI();


    }

    public void openBasicCalculator(){
        calcControl=true;
        theBeginnerView.close();
        theTutorialView.close();
        theBasicView.createGUI();


    }



}
