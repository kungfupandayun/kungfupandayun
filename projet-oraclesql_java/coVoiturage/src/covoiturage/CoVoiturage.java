package covoiturage;
import Controleur.TraiteBouton;
import Modele.*;
import Vue.Vue;
import javax.swing.JFrame;
import static JDBC.ConnectJDBC.conn;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
 


public class CoVoiturage {
      public static void main(String[] args){
        Vue vue=new Vue();
        Modele modele=new Modele();
        TraiteBouton t=new TraiteBouton( vue,  modele);

      }
}
