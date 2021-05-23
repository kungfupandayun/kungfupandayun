

import JDBC.*;
import static Modele.Etat_Tronçon_Conducteur.EnAttente;
import Modele.*;

/**
 * Test inserer un trajet et les tronçons sans verifier le contraint utilisateur et vehicule
 */
public class TestInsertTrajetTronçon {
         public static void main(String[] args) throws Exception {
        Trajet t1=new Trajet(4,"03-12-2020", "11:21");

        t1.ajouteTronçon( "Strasbourg", 48.5734,7.7521,
			 "Paris", 48.8566,2.3522, 120,
			Etat_Tronçon_Conducteur.EnAttente);
        
        t1.ajouteTronçon("Paris", 48.8566,2.3522,
                        "Nantes",47.2184,1.5536,300,
			 Etat_Tronçon_Conducteur.EnAttente);
        
        t1.ajouteTronçon("Nantes",47.2184,1.5536,
                        "Bruxelles",50.8503,4.3517,300,
			 Etat_Tronçon_Conducteur.EnAttente);
        
        t1.AddTrajetTronçon();
        
                Trajet t2=new Trajet(4,"03-12-2020", "21:21");

        t2.ajouteTronçon( "Strasbourg", 48.5734,7.7521,
			 "Paris", 48.8566,2.3522, 120,
			 Etat_Tronçon_Conducteur.EnAttente);
        
        t2.ajouteTronçon("Paris", 48.8566,2.3522,
                        "Lille",50.6292,3.0573,300,
			Etat_Tronçon_Conducteur.EnAttente);
        
        t2.ajouteTronçon("Lille",50.6292,3.0573,
                        "Bruxelles",50.8503,4.3517,300,
			 Etat_Tronçon_Conducteur.EnAttente);
        
         t2.AddTrajetTronçon();
    }
}
