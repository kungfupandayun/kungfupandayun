package Modele;
import java.util.ArrayList;

/**
 * Classe abstract de Parcours,RechercheParcours.
 * @listSuggestion affecté dans la RechercheParcours et utilisé dans la classe Parcours
 *  pour choisir une suggestion.
 */
public abstract class AbstractRechercheParcours {
        
    protected Compte compte;
    protected Suggestion suggestion;
    protected String villeDépart;
    protected double longitudeDépart;
    protected double latitudeDépart;
    protected String date;
    public static ArrayList<Suggestion> listSuggestion ;

    public void printList() {
        int i = 0;
        for (Suggestion s : listSuggestion) {
            System.out.println("============= Suggestion " + ++i + " =============");
            System.out.println(s.toString());
        }
    }
}
