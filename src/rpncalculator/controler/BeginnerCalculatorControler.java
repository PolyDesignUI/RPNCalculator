package rpncalculator.controler;


import rpncalculator.model.Calculatrice;
import rpncalculator.view.BasicCalculatorView;
import rpncalculator.view.BeginnerCalculatorView;

import java.util.Iterator;

public class BeginnerCalculatorControler{

    public Calculatrice theCalculatrice;
    private BeginnerCalculatorView theBeginnerView;

    public BeginnerCalculatorControler(Calculatrice theCalculatrice, BeginnerCalculatorView theBeginnerView) {
        this.theCalculatrice=theCalculatrice;
        this.theBeginnerView=theBeginnerView;
    }

    public void setTheCalculatrice(Calculatrice calc){

        theCalculatrice = calc;

    }

    public void performDigit(double e){

        theCalculatrice.empiler(e);
        //theBeginnerView.setValueDisplayedOne();

    }

    public void performOperand(int e){
        double result;
        Iterator<Double> itr;
      //  if(e==1){theCalculatrice.ajouter(); result= theCalculatrice.obtenirResultat(); theBeginnerView.setValueDisplayed(result);} //addition
      //  if(e==2){theCalculatrice.soustraire(); result= theCalculatrice.obtenirResultat(); theBeginnerView.setValueDisplayed(result);} //subtraction
        //if(e==3){theCalculatrice.multiplier(); result= theCalculatrice.obtenirResultat(); theBeginnerView.setValueDisplayed(result);} //multiplication
       // if(e==4){theCalculatrice.diviser(); result= theCalculatrice.obtenirResultat(); theBeginnerView.setValueDisplayed(result);} //division
        if(e==5){ itr=theCalculatrice.lirePile(); result=itr.next(); theCalculatrice.empiler(result);}  //enter
        if(e==6){theCalculatrice.reinitialiser();} //clear
    }





}