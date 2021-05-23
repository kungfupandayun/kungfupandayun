

import Exceptions.NoAccountException;
import JDBC.*;
import Modele.Compte;

/**
 * Test Selection d'un Compte.
 */
public class TestSelectCompte {
        public static void main(String[] args) throws NoAccountException {
        Compte c= new Compte("poe","");
        System.out.println(c.toString());
    }
}
