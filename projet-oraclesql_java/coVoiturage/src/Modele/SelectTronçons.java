package Modele;

import static JDBC.ConnectJDBC.conn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import JDBC.ConnectJDBC;
import JDBC.JDBC;

public class SelectTronçons implements JDBC {
	
	private String id_trajet;
	private HashSet<TronçonInfos> tronçons;
	
	public SelectTronçons(String id_trajet) {
		this.id_trajet=id_trajet;
		this.tronçons=new HashSet<TronçonInfos>();
        this.appelJDBC();
	}

	 @Override
	 public void appelJDBC() {
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {
            String SQL="SELECT id_tronçon,ville_depart,ville_arrivee from Tronçon where id_trajet=?";
            PreparedStatement stmt=conn.prepareStatement(SQL);
            stmt.setString(1, id_trajet);
            
            ResultSet rset = stmt.executeQuery();   
            while(rset.next()) {
            	String id_tronçon = rset.getString(1);
            	String ville_depart = rset.getString(2);
            	String ville_arrivee = rset.getString(3);
            	TronçonInfos t =new TronçonInfos(id_tronçon,id_trajet,ville_depart,ville_arrivee);
            	tronçons.add(t);
            }
            rset.close();
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            System.err.println("[SelectTronçons]SelectTrajets n'est pas effectué!");
            e.printStackTrace();
        }
	 }
	 
	 public HashSet<TronçonInfos> getTronçons(){
		 return tronçons;
	 }
	 
	 
}
