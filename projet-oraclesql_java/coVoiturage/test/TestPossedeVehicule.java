
import JDBC.JDBC;
import Modele.*;
import static Modele.EnergieUtilisee.Essence;


/**
 * Test inserer le relation entre un vehicule et un utilisateur
 */
public class TestPossedeVehicule{
    public static void main(String[] args) throws Exception {        
       
        final Compte c= new Compte("poe","helloitsme");
    
        final Vehicule v= new Vehicule("ABC9") ;
       
        PossedeVehicule p=new PossedeVehicule(c,v);
    }
}
