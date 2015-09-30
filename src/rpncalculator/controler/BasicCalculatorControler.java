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

    private boolean calcControl = true;

    public BasicCalculatorControler(Calculatrice theCalculatrice, BasicCalculatorView theBasicView,BeginnerCalculatorView theBeginnerView, TutorialView theTutorialView){

        this.theCalculatrice=theCalculatrice;
        this.theBasicView=theBasicView;
        this.theBeginnerView=theBeginnerView;
        this.theTutorialView=theTutorialView;
        theBasicView.createGUI();


    }

    public void performDigit(double e){

        theCalculatrice.empiler(e);

    }

    public void performOperand(int e){
        double result;
        Iterator<Double> itr;
        if(calcControl){

        if(e==OPERAND_ADD){theCalculatrice.ajouter();} //addition
        if(e==OPERAND_SUB){theCalculatrice.soustraire();} //subtraction
        if(e==OPERAND_MUL){theCalculatrice.multiplier();} //multiplication
        if(e==OPERAND_DIV){theCalculatrice.diviser();} //division
        if(e==OPERAND_ENTER){ itr=theCalculatrice.lirePile(); result=itr.next(); theCalculatrice.empiler(result);}  //enter
        if(e==OPERAND_CLEAR){theCalculatrice.reinitialiser();} //clear
            result= theCalculatrice.obtenirResultat();
            theBasicView.setValueDisplayed(result);
        }
        else{

            if(e==OPERAND_ADD){theCalculatrice.ajouter();} //addition
            if(e==OPERAND_SUB){theCalculatrice.soustraire();} //subtraction
            if(e==OPERAND_MUL){theCalculatrice.multiplier();} //multiplication
            if(e==OPERAND_DIV){theCalculatrice.diviser();} //division
            if(e==OPERAND_ENTER){ itr=theCalculatrice.lirePile();}  //enter
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
