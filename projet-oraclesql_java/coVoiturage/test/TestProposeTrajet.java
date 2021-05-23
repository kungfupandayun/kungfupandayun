

import JDBC.*;
import static Modele.EnergieUtilisee.*;
import static Modele.Etat_Tronçon_Conducteur.*;
import Modele.*;
import java.util.*;

/**
 * Test propose un trajet 
 */
public class TestProposeTrajet {
        public static void main(String[] args) throws Exception {
            
        Compte c= new Compte("poe" ,"helloitsme");
        
        
        final Vehicule v= new Vehicule("ABC1") ;
      
      
        
        //PossedeVehicule p=new PossedeVehicule(c,v);

        Trajet t=new Trajet(2,"2028-12-02", "11:21");

        t.ajouteTronçon( "Strasbourg", 48.5734,7.7521,
			 "Nantes",47.2184,1.5536, 120,
			 Etat_Tronçon_Conducteur.EnAttente);
        
        t.ajouteTronçon("Nantes",47.2184,1.5536,
                         "Paris", 48.8566,2.3522,280,
			 Etat_Tronçon_Conducteur.EnAttente);
        
        t.ajouteTronçon( "Paris", 48.8566,2.3522,
                        "Bruxelles",50.8503,4.3517,300,
			 Etat_Tronçon_Conducteur.EnAttente);
        
        ProposeTrajet propose=new ProposeTrajet(c,v,t) ;
        
        final Compte c2= new Compte("poe1", "helloitsme");
       
        
        final Vehicule v2= new Vehicule("ABC12") ;
      
        
       // PossedeVehicule p1=new PossedeVehicule(c2,v2);
        
        Trajet t2=new Trajet(4,"2028-12-02", "11:21");
        
        t2.ajouteTronçon( "Strasbourg", 48.5734,7.7521,
			 "Lille",50.6292,3.0573, 120,
			 Etat_Tronçon_Conducteur.EnAttente);
        
        t2.ajouteTronçon("Lille",50.6292,3.0573,
                        "Paris", 48.8566,2.3522,300,
			 Etat_Tronçon_Conducteur.EnAttente);
        
        t2.ajouteTronçon("Paris", 48.8566,2.3522,
                        "Bruxelles",50.8503,4.3517,300,
			 Etat_Tronçon_Conducteur.EnAttente);
        
        
        ProposeTrajet propose2=new ProposeTrajet(c2 , v2, t2) ;
        
    }
}
