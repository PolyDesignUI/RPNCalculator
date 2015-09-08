package rpncalculator.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

/**
 * Cette classe represente une calculatrice RPN avec une pile de taille 4.
 * @author Dany Khalife
 * @date 9/4/2014
 */
public class Calculatrice {
    private final int TAILLE_MAX = 4;
    private Stack<Double> pile = new Stack<Double>();
    private ArrayList<Exercice> exercices = new ArrayList<Exercice>();
    private Exercice exEnCours;
    private Random random = new Random();
    private ArrayList<String> sequence = new ArrayList<String>();
    
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
     */
    public Iterator<Double> lirePile(){
        return pile.iterator();
    }
	
    /**
     * Cette methtode permet de reinitialiser la calculatrice
     */
    public void reinitialiser(){
        pile.clear();
        exEnCours = null;
        sequence.clear();
    }
	
    /**
     * Cette methode permet d'empiler un operande sur la pile.
     * @param arg L'operande a ajouter 
     */
    public void empiler(Double arg){
        if(pile.size() == TAILLE_MAX)
            pile.remove(0);

        pile.push(arg);
        sequence.add(arg.toString());
        sequence.add("E");
    }
	
    /**
     * Cette methode permet de lire une valeur de la pile
     * Si celle-ci est vide, la valeur 0 sera retournee
     **/
    private Double lire(){
        if(pile.size() == 0)
            return 0.0;

        return pile.pop();
    }

    /**
     * Cette methode remplace les deux derniers 
     * elements sur la pile par leur somme
     **/
    public void ajouter(){
        pile.push(lire() + lire());
        sequence.add("+");
    }

    /**
     * Cette methode remplace les deux derniers 
     * elements sur la pile par leur difference
     **/
    public void soustraire(){
        Double a = lire();
        Double b = lire();
        pile.push(b - a);
        sequence.add("-");
    }

    /**
     * Cette methode remplace les deux derniers 
     * elements sur la pile par leur produit
     **/
    public void multiplier(){
        pile.push(lire() * lire());
        sequence.add("*");
    }

    /**
     * Cette methode remplace les deux derniers 
     * elements sur la pile par leur division
     **/
    public void diviser(){
    	Double a = lire();
    	Double b = lire();
        //TODO : disivion par 0
        pile.push(b / a);
        sequence.add("/");
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
