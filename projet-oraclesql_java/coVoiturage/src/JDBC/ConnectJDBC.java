package JDBC;

import Vue.Vue;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Commencer une session.
 */

public class ConnectJDBC implements JDBC {
    
    static final String URL = "jdbc:oracle:thin:@oracle1.ensimag.fr:1521:oracle1";
    static final String USERNAME = "poe";                            
    static final String PASSWD = "poe";  
    static public Connection conn ;

    /*
        Commencer la session
    */
    public ConnectJDBC(){
        try {
        // Chargement du driver Oracle
        System.out.print("Loading Oracle driver... "); 
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        System.out.println("loaded");

        // Etablissement de la connection
        System.out.print("Connecting to the database... "); 
        conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
        System.out.println("connected");

        // Demarrage de la transaction (implicite)
        conn.setAutoCommit(false);
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        }
        catch (SQLException e) {
            System.err.println("Erreur connexion!");
	}
    }

    @Override
    public void appelJDBC() {
        try {
            System.out.println("Fin de Session");
                conn.close();
               } catch (SQLException ex) {
                   Logger.getLogger(Vue.class.getName()).log(Level.SEVERE, null, ex);
               }
    }
    
    

}
