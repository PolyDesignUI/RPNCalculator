package rpncalculator.model;

/**
 * Cette classe represente un exercice
 * @author dakhaa
 */
public class Exercice {
    // La question: le calcul que l'on devrait faire a l'aide de la calculatrice
    private String question;
    // La sequence d'operation attendue comme reponse
    private String sequence;

    private int id;
    
    /**
     * Constructeur par parametre
     * @param question La question
     * @param sequence La sequence attendue
     */
    public Exercice(int id, String question, String sequence){
        this.id = id;
        this.question = question;
        this.sequence = sequence;
    }
    
    /**
     * Methode d'acces a la question
     * @return La question
     */
    public String obtenirQuestion(){
        return question;
    }
    
    /**
     * Methode d'acces a la sequence attendue
     * @return La sequence attendue
     */
    public String obtenirSequence(){
        return sequence;
    }

    public int obtenirId(){
        return id;
    }
}
