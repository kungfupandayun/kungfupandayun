import Modele.Compte;
import Modele.*;

/**
 * Test Recharger le solde du compte
 */
public class TestRechargementSolde {
    public static void main(String[] args) throws Exception {
        Compte c= new Compte("souha","its","me","beenwondering", 
                                            "alltheseyears",100);
        System.out.println(c.toString());
        RechargerPorteMonnaie recharge = new RechargerPorteMonnaie(c,20);
        
    }
}
