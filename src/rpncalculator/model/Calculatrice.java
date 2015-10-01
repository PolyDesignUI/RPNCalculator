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

    /**
     * Buffer permettant de stocker l'ensemble des chiffres inséré afin de composer le nombre final
     */
    private StringBuffer numberBuffer = new StringBuffer();

    /**
     * Constructeur par defaut
     */
    public Calculatrice(){
        // Actualisation des réponses aux exercices.
        // Lire : http://www.calculator.org/rpn.aspx
        // Rq : on ne doit pas presser Enter entre un chiffre et une opération, exemple :
        exercices.add(new Exercice(1,"1 + 1", "1.0 E 1.0 +")); // et pas 1.0 E 1.0 E +
        exercices.add(new Exercice(2,"(3 + 1) / 2", "3.0 E 1.0 + E 2.0 /"));
        exercices.add(new Exercice(3,"(3 / 4) x (5 / 6)", "3.0 E 4.0 / E 5.0 E 6.0 / x"));
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

    //this method gives the stack
    public Stack<Double> getPile() {return pile; }

    /**
     * Retourne la taille de la pile
     * @return un entier compris entre 0 et 4
     */
    public int getSize(){
        return pile.size();
    }

    /**
     * Cette methtode permet de reinitialiser la calculatrice
     */
    public void reinitialiser(){
        pile.clear();
        sequence.clear();
        numberBuffer = new StringBuffer();

        // Notification à la vue d'un changement
        this.setChanged();
        this.notifyObservers();
    }
	
    /**
     * Cette methode permet d'empiler la valeur contenu dans le buffer sur la pile.
     */
    public void enter(){
        if(pile.size() == TAILLE_MAX){
            pile.remove(0);
        }

        if(numberBuffer.length() == 0){  // Buffer vide, on emplie 0 ou la dernière valeur de la pile
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

        // Réinitialisation du bugger
        numberBuffer = new StringBuffer();

        // Notification à la vue d'un changement
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Ajout d'un digit pour composer le nombre final
     *
     * @param e caractère compris entre 0 et 9
     */
    public void addDigit(char e){

        numberBuffer.append(e);

        // Si la pile n'est pas vide on supprime la dernière valeur pour ajouter le nombre actualisé
        if(pile.size() > 0){
            pile.pop();
        }

        if(pile.size() == TAILLE_MAX){
            pile.remove(0);
        }

        // Ajout du nouveau nombre comprenant le digit ajouté
        pile.push(Double.parseDouble(numberBuffer.toString()));

        // Notification à la vue d'un changement
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
        // Ajout automatique à la séquence du nombre inséré
        if(numberBuffer.length() > 0){
            sequence.add(pile.peek().toString());
        }

        pile.push(lire() + lire());
        sequence.add("+");

        // Notification à la vue d'un changement
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Cette methode remplace les deux derniers 
     * elements sur la pile par leur difference
     **/
    public void soustraire(){
        // Ajout automatique à la séquence du nombre inséré
        if(numberBuffer.length() > 0) {
            sequence.add(pile.peek().toString());
        }

        Double a = lire();
        Double b = lire();
        pile.push(b - a);
        sequence.add("-");

        // Notification à la vue d'un changement
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Cette methode remplace les deux derniers 
     * elements sur la pile par leur produit
     **/
    public void multiplier(){
        // Ajout automatique à la séquence du nombre inséré
        if(numberBuffer.length() > 0) {
            sequence.add(pile.peek().toString());
        }
        pile.push(lire() * lire());
        sequence.add("x");

        // Notification à la vue d'un changement
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Cette methode remplace les deux derniers 
     * elements sur la pile par leur division
     **/
    public void diviser(){
        // Ajout automatique à la séquence du nombre inséré
        if(numberBuffer.length() > 0) {
            sequence.add(pile.peek().toString());
        }
    	Double a = lire();
    	Double b = lire();
        //TODO : disivion par 0
        if(a==0){throw new IllegalArgumentException("Argument 'divisor' is 0");}
        pile.push(b / a);
        sequence.add("/");

        // Notification à la vue d'un changement
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Cette methode permet d'obtenir le resultat obtenu suite a l'execution
     * de la sequence d'operation prealablement fournie.
     * @return double
     */
    public double obtenirResultat(){
        if(pile.isEmpty())
            return 0;
			
        return pile.peek();
    }

    /**
     * Récupération de l'historique de la séquence
     * @return String séquence complète
     */
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
     * @return Un exercice choisi au hasard et différent de l'ancien
     */
    public Exercice nouvelExercice(){
        pile.clear();
        sequence.clear();
        Exercice last = exEnCours;

        while(last == exEnCours){
            exEnCours = exercices.get(random.nextInt(exercices.size()));
        }

        // Notification à la vue d'un changement
        this.setChanged();
        this.notifyObservers();

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
