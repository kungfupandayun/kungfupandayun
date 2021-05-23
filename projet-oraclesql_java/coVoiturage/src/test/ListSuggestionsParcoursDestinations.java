package test;

import Modele.AbstractRechercheParcours;
import Modele.Compte;
import Modele.RechercheParcoursDest;
import static JDBC.ConnectJDBC.conn;

/**
 *
 * @author pojia
 */
public class ListSuggestionsParcoursDestinations {
    public static void main(String[] args) throws Exception {
        Compte c= new Compte("poe" ,"helloitsme");

        AbstractRechercheParcours r= new Modele.RechercheParcoursDest("Strasbourg", 48.5734,7.7521,"2028-12-02","Bruxelles",50.8503,4.3517,c);
        r.printList();
        conn.close();
    }
}
