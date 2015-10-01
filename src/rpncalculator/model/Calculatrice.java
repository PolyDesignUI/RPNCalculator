package rpncalculator.model;

import java.util.*;

/**
 * Cette classe represente une calculatrice RPN avec une pile de taille 4.
 * @author Dany Khalife
 * @date 9/4/2014
 */
public class Calculatrice extends Observable {
    private final int TAILLE_MAX = 4;
    private Stack<Double> pile = new Stack<Double>();
    private ArrayList<Exercice> exercices = new ArrayList<Exercice>();
    private Exercice exEnCours;
    private Random random = new Random();
    private ArrayList<String> sequence = new ArrayList<String>();
    private StringBuffer numberBuffer = new StringBuffer();
    /**
     * Constructeur par defaut
     */
    public Calculatrice(){
        exercices.add(new Exercice("1 + 1", "1.0 E 1.0 E +"));
        exercices.add(new Exercice("(3 + 1) / 2", "3.0 E 1.0 E + 2.0 E /"));
        exercices.add(new Exercice("(3 / 4) * (5 / 6)", "3.0 E 4.0 E / 5.0 E 6.0 E / *"));
    }
    
    /**
     * Cette methode permet d'obtenir un iterateur pour lire la pile
     * Utlisez l'iterateur de cette maniere:
     * Iterator<Double> it = calc.lirePile();
     * while(it.hasNext()){
     * 		Double valeur = it.next();
     *		...	
     * }
     * @return un iterateur sur la pile
     *
     */
    public ListIterator<Double> lirePile(){
        return pile.listIterator();
    }
	
    /**
     * Cette methtode permet de reinitialiser la calculatrice
     */

    //this method gives the stack
    public Stack<Double> getPile() {return pile; }



    public void reinitialiser(){
        pile.clear();
        exEnCours = null;
        sequence.clear();
        numberBuffer = new StringBuffer();

        this.setChanged();
        this.notifyObservers();
    }


	
    /**
     * Cette methode permet d'empiler un operande sur la pile.
     */
    public void enter(){
        if(pile.size() == TAILLE_MAX){
            pile.remove(0);
        }

        if(numberBuffer.length() == 0){
            if(pile.empty()){
                pile.push(0.0);
            }
            else{
                pile.push(pile.peek());
            }
        }
        else{
            pile.push(Double.parseDouble(numberBuffer.toString()));
            sequence.add(pile.peek().toString());
        }

        sequence.add("E");
        numberBuffer = new StringBuffer();

        this.setChanged();
        this.notifyObservers();
    }

    public void addDigit(char e){
        numberBuffer.append(e);
        if(pile.size() > 0){
            pile.pop();
        }
        if(pile.size() == TAILLE_MAX){
            pile.remove(0);
        }
        pile.push(Double.parseDouble(numberBuffer.toString()));
        this.setChanged();
        this.notifyObservers();
    }
	
    /**
     * Cette methode permet de lire une valeur de la pile
     * Si celle-ci est vide, la valeur 0 sera retournee
     **/
    private Double lire(){
        if(pile.size() == 0)
            return 0.0;

        numberBuffer = new StringBuffer();

        return pile.pop();
    }

    /**
     * Cette methode remplace les deux derniers 
     * elements sur la pile par leur somme
     **/
    public void ajouter(){
        sequence.add(pile.peek().toString());
        pile.push(lire() + lire());
        sequence.add("+");
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Cette methode remplace les deux derniers 
     * elements sur la pile par leur difference
     **/
    public void soustraire(){
        sequence.add(pile.peek().toString());
        Double a = lire();
        Double b = lire();
        pile.push(b - a);
        sequence.add("-");
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Cette methode remplace les deux derniers 
     * elements sur la pile par leur produit
     **/
    public void multiplier(){
        sequence.add(pile.peek().toString());
        pile.push(lire() * lire());
        sequence.add("x");
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Cette methode remplace les deux derniers 
     * elements sur la pile par leur division
     **/
    public void diviser(){
        sequence.add(pile.peek().toString());
    	Double a = lire();
    	Double b = lire();
        //TODO : disivion par 0
        if(a==0){throw new IllegalArgumentException("Argument 'divisor' is 0");}
        pile.push(b / a);
        sequence.add("/");
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Cette methode permet d'obtenir le resultat obtenu suite a l'execution
     * de la sequence d'operation prealablement fournie.
     * @return 
     */
    public double obtenirResultat(){
        if(pile.isEmpty())
            return 0;
			
        return pile.peek();
    }

    public String getSequence(){
        return join(sequence, " ");
    }

    /**
     * Methode interne, utilisee pour coller un tableau de chaines de caracteres
     * @param ar Le tableau de chaines caracteres
     * @param sep Le separateur 
     * @return La chaine resultante de la concatenation de toutes les chaines du tableau, separees par le separateur
     */
    private String join(ArrayList<String> ar, String sep){
        if(ar.isEmpty())
            return "";

        StringBuilder sb = new StringBuilder();
        sb.append(ar.get(0));
        
        for(int i=1; i<ar.size(); ++i){
            sb.append(sep).append(ar.get(i));
        }
        
        return sb.toString();
    }
    
    /**
     * Cette methode permet d'obtenir un exercice 
     * Si aucun exercice est en cours, celle-ci assigne un au hasard
     * 
     * @return Un exercice choisi au hasard
     */
    public Exercice nouvelExercice(){
        pile.clear();
        sequence.clear();
        exEnCours = exercices.get(random.nextInt(exercices.size()));        
        return exEnCours;
    }
    
    /**
     * Cette methode permet d'obtenir l'exercice en cours
     * @return L'exercice en cours. Dans le cas ou il n'y en a aucun, null.
     */
    public Exercice obtenirExerciceEnCours(){
        return exEnCours;
    }
    
    /**
     * Cette methode permet de valider si la sequence effectue 
     * @return True si la sequence actuelle correspond a celle attendue pour l'exercice courant, False sinon.
     */
    public boolean validerSequence(){
        if(exEnCours == null)
            return false;
        
        return exEnCours.obtenirSequence().toLowerCase().startsWith(join(sequence, " ").toLowerCase());
    }
    
    /**
     * Cette methode permet d'obtenir le progres du calcul en cours lorsqu'on est en mode d'apprentissage
     * Cette methode n'est pas utile lorsque la sequence en cours est invalide
     * @return Le progres du calcul en pourcentage
     */
    public int obtenirProgresApprentissage(){
        return (int) (100 * Math.min(1.0, (double) join(sequence, " ").length() / (double) exEnCours.obtenirSequence().length()));
    }
}
