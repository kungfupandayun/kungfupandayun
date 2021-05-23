import Modele.AbstractRechercheParcours;
import Modele.Compte;
import Modele.RechercheParcoursSansDest;


/**
 * Generer une liste de suggestions parcours.
 */
public class TestListSuggestionParcours {
     public static void main(String[] args) throws Exception {            
        Compte c= new Compte("poe" ,"helloitsme");    
       AbstractRechercheParcours r= new RechercheParcoursSansDest("Strasbourg", 48.5734,7.7521,"2028-12-02",c);
        r.printList();
     }
}
