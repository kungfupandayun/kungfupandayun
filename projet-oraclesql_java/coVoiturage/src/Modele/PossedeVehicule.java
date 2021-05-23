/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Exceptions.NoVehiculeException;
import JDBC.*;
import static JDBC.ConnectJDBC.conn;
import java.sql.*;
import java.util.ArrayList;

/**
 * Enregistrer relation entre un utilisateur et un vehicule.
 */
public class PossedeVehicule implements JDBC{
    private  Compte c;
    private  Vehicule v;
    private  ArrayList<String> vehiculesDB;

    public PossedeVehicule(Compte c,Vehicule v) {
        this.c=c;
        this.v=v;
        this.appelJDBC();
    }
    
    public PossedeVehicule(String email) {
        this.vehiculesDB =  new ArrayList<>();
        vehiculeInDatabase(email);
    }

   @Override
    public void appelJDBC() {
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {
            // Execution des requetes et affichage des résultats
            String SQL="INSERT INTO PossedeVehicule (immatriculation, email) VALUES (?,?)";
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setString(1,v.getImmatriculation());
            stmt.setString(2,c.getEmail());

            // execute the preparedstatement
            stmt.execute();

            // Fermeture
            stmt.close();
            conn.commit();

        } catch (SQLException ex) {
            v.setImmatriculation(null);
            c.setEmail(null);
            System.err.println("[InsertPossedeVehicule]Relation Compte Vehicule n'est pas enregistré!");
        }
    }

    public void vehiculeInDatabase(String email){
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {

            // Creation de la requete
            PreparedStatement stmt = conn.prepareStatement("SELECT * from PossedeVehicule "
                                                        + "WHERE Email LIKE ?");
            stmt.setString(1, email);


            // Affichage du résultat
            ResultSet rset = stmt.executeQuery();




            while (rset.next()){
                vehiculesDB.add(rset.getString(1));
           }
           


            // Fermeture
            rset.close();
            stmt.close();
            conn.commit();
          } catch (SQLException e) {
            System.err.println("[CheckVehiculeBD]Vehicule Check echec !");
            //e.printStackTrace();
        }
    }
    
    public  ArrayList<String> getListVehicules(){
        return vehiculesDB;
    }

}
