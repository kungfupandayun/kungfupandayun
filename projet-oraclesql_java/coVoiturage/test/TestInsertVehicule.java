

import JDBC.*;
import Modele.*;
import static Modele.EnergieUtilisee.Essence;


/**
 * Inserer un vehicule simple.
 */
public class TestInsertVehicule {
        public static void main(String[] args) throws Exception {
        Vehicule v= new Vehicule("ABC123", "Lambogini","Aventado", 
                    Essence, 8,2) ;
    }
}
