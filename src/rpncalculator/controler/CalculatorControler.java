package rpncalculator.controler;

import rpncalculator.model.Calculatrice;
import rpncalculator.view.BasicCalculatorView;
import rpncalculator.view.BeginnerCalculatorView;
import rpncalculator.view.TutorialView;

/**
 * Class CalculatorControler
 * Classe ayant pour rôle Controler du pattern MVC.
 * Implémente le pattern Singleton afin qu'il soit unique
 */
public class CalculatorControler {

    /**
     * Mode basic = mode normale
     * Mode beginner = mode avec aide et affichage de tutoriels
     */
    public enum Mode {BASIC, BEGINNER}

    /**
     * Différentes constantes représentant les actions possible de la calculatrice
     */
    public enum Operand {
        OPERAND_ADD,
        OPERAND_SUB,
        OPERAND_MUL,
        OPERAND_DIV,
        OPERAND_ENTER,
        OPERAND_CLEAR
    }

    /**
     * Instance unique de la classe
     */
    private static CalculatorControler instance;

    /**
     * Référence au modèle calculatrice
     * @see Calculatrice
     */
    private Calculatrice theCalculatrice;

    /*
        Références aux vues
     */
    private BasicCalculatorView theBasicView;
    private BeginnerCalculatorView beginnerView;
    private TutorialView tutorialView;

    /**
     * Récupérer l'instance du controlleur
     *
     * @return CalculatorControler instance du controlleur
     */
    public synchronized static CalculatorControler getInstance(){
        if(instance == null){
            instance = new CalculatorControler();
        }
        return instance;
    }

    private CalculatorControler(){
        // Model init
        theCalculatrice= new Calculatrice();

        // View init
        theBasicView= new BasicCalculatorView();
        beginnerView = new BeginnerCalculatorView();
        tutorialView = new TutorialView();

        // Config observers
        theCalculatrice.addObserver(theBasicView);
        theCalculatrice.addObserver(beginnerView);
        theCalculatrice.addObserver(tutorialView);

        // Setup the calculatrice
        theCalculatrice.nouvelExercice();
        switchMode(Mode.BASIC);
    }

    /**
     * Basculer du mode normale au mode débutant
     *
     * @see rpncalculator.controler.CalculatorControler.Mode
     * @param newMode nouveau mode
     */
    public void switchMode(Mode newMode){
        if(newMode.equals(Mode.BASIC)){
            theBasicView.show();
            beginnerView.close();
            tutorialView.close();
        }
        else{
            theBasicView.close();
            beginnerView.show();
            tutorialView.show();
        }
    }

    /**
     * Insertion d'un nouveau chiffre
     *
     * @param e char nouveau digit [0-9]
     */
    public void performDigit(char e){
        if(e >= '0' && e <= '9'){
            theCalculatrice.addDigit(e);
        }
    }

    /**
     * Réalisation d'une opération
     * @see rpncalculator.controler.CalculatorControler.Operand
     *
     * @param e Nouvelle opération à réaliser
     */
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

    /**
     * Changement d'exercice de la vue tutoriel
     */
    public void newExercice() {
        theCalculatrice.nouvelExercice();
    }

}
