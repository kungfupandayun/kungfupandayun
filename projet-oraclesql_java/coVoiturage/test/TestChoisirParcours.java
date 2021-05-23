
import Modele.AbstractRechercheParcours;
import Modele.Compte;
import Modele.Parcours;
import Modele.ProposeTrajet;
import Modele.RechercheParcoursSansDest;
import Modele.Trajet;
import Modele.Vehicule;


/**
 *Choisir parcours parmi une liste de suggetsion.
 */
public class TestChoisirParcours {
            public static void main(String[] args) throws Exception {
        
        //login d'un compte.    
        Compte c= new Compte("poe" ,"helloitsme");
        
        //Génerer une liste de suggestion.
        AbstractRechercheParcours r= new RechercheParcoursSansDest("Nantes",47.2184,1.5536,"03-12-2020",c);
        
        //Choisir un Parcours
        Parcours parcours=new Parcours(c, 0);
        
        //Afficher un parcours
        System.out.print(parcours.toString());
    }

}
