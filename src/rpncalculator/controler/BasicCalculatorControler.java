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

        if(e==1){theCalculatrice.ajouter();} //addition
        if(e==2){theCalculatrice.soustraire();} //subtraction
        if(e==3){theCalculatrice.multiplier();} //multiplication
        if(e==4){theCalculatrice.diviser();} //division
        if(e==5){ itr=theCalculatrice.lirePile(); result=itr.next(); theCalculatrice.empiler(result);}  //enter
        if(e==6){theCalculatrice.reinitialiser();} //clear
            result= theCalculatrice.obtenirResultat();
            theBasicView.setValueDisplayed(result);
        }
        else{

            if(e==1){theCalculatrice.ajouter();} //addition
            if(e==2){theCalculatrice.soustraire();} //subtraction
            if(e==3){theCalculatrice.multiplier();} //multiplication
            if(e==4){theCalculatrice.diviser();} //division
            if(e==5){ itr=theCalculatrice.lirePile();}  //enter
            if(e==6){theCalculatrice.reinitialiser();} //clear
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
