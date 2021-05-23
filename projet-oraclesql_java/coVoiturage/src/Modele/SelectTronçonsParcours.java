package Modele;

import static JDBC.ConnectJDBC.conn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import JDBC.ConnectJDBC;
import JDBC.JDBC;

public class SelectTronçonsParcours implements JDBC {
	
	private String id_parcours;
	private HashSet<TronçonParcoursInfos> tronçons;
	
	public SelectTronçonsParcours(String id_parcours) {
		this.id_parcours=id_parcours;
		this.tronçons=new HashSet<TronçonParcoursInfos>();
        this.appelJDBC();
	}

	 @Override
	 public void appelJDBC() {
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {
            String SQL="SELECT t1.id_tronçon,t1.id_trajet,t2.ville_depart, t2.ville_arrivee from ParcoursTronçon t1 "
            		+ "INNER JOIN Tronçon t2 ON t1.id_trajet=t2.id_trajet AND t1.id_tronçon=t2.id_tronçon "
            		+ "where id_parcours=?";
            PreparedStatement stmt=conn.prepareStatement(SQL);
            stmt.setString(1, id_parcours);
            
            ResultSet rset = stmt.executeQuery();   
            while(rset.next()) {
            	String id_tronçon = rset.getString(1);
            	String id_trajet = rset.getString(2);
            	String villle_départ = rset.getString(3);
            	String ville_arrivée = rset.getString(4);

            	TronçonParcoursInfos paire =new TronçonParcoursInfos(id_tronçon,id_trajet,villle_départ,ville_arrivée);
            	tronçons.add(paire);
            }
            rset.close();
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            System.err.println("[SelectTronçonsParcours]SelectTrajetsParcours n'est pas effectué!");
            e.printStackTrace();
        }
	 }
	 
	 public HashSet<TronçonParcoursInfos> getTronçonsParcours(){
		 return tronçons;
	 }
	 
	 
}
