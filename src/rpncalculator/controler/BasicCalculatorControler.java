package rpncalculator.controler;

import rpncalculator.model.Calculatrice;
import rpncalculator.view.BasicCalculatorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

// gittest
public class BasicCalculatorControler {

    public Calculatrice theCalculatrice;
    private BasicCalculatorView theBasicView;

    public BasicCalculatorControler(Calculatrice theCalculatrice, BasicCalculatorView theBasicView){

        this.theCalculatrice=theCalculatrice;
        this.theBasicView=theBasicView;



    }

    public void performDigit(double e){

        theCalculatrice.empiler(e);

    }

    public void performOperand(int e){
        double result;
        Iterator<Double> itr;
        if(e==1){theCalculatrice.ajouter(); result= theCalculatrice.obtenirResultat(); theBasicView.setValueDisplayed(result);} //addition
        if(e==2){theCalculatrice.soustraire(); result= theCalculatrice.obtenirResultat(); theBasicView.setValueDisplayed(result);} //subtraction
        if(e==3){theCalculatrice.multiplier(); result= theCalculatrice.obtenirResultat(); theBasicView.setValueDisplayed(result);} //multiplication
        if(e==4){theCalculatrice.diviser(); result= theCalculatrice.obtenirResultat(); theBasicView.setValueDisplayed(result);} //division
        if(e==5){ itr=theCalculatrice.lirePile(); result=itr.next(); theCalculatrice.empiler(result);}  //enter
        if(e==6){theCalculatrice.reinitialiser();} //clear
    }








}
