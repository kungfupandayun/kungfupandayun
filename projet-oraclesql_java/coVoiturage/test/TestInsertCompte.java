import Modele.*;

/**
 * Test creer d'un compte
 */
public class TestInsertCompte {
    
    public static void main(String[] args) throws Exception {
        Compte c= new Compte("hi","its","me","beenwondering", 
                                            "alltheseyears",100);
        System.out.println(c.toString());
    }
}
