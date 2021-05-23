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

public class SelectTrajets implements JDBC {
	
	private Compte compte;
	private HashSet<TrajetInfos> trajets;
	
	public SelectTrajets(Compte compte) {
		this.compte=compte;
		this.trajets=new HashSet<TrajetInfos>();
        this.appelJDBC();
	}

	 @Override
	 public void appelJDBC() {
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {
            String SQL="SELECT t1.id_trajet,t2.date_trajet,t2.heure_trajet from ConducteurVehiculeTrajet t1 "
                    + "INNER JOIN Trajet t2 ON t1.id_trajet=t2.id_trajet "
            		+ " where email=?";
            PreparedStatement stmt=conn.prepareStatement(SQL);
            stmt.setString(1, compte.getEmail());
            
            ResultSet rset = stmt.executeQuery();   
            while(rset.next()) {
            	String id_trajet = rset.getString(1);
            	String date = rset.getString(2);
            	String heure = rset.getString(3);
            	TrajetInfos t = new TrajetInfos(id_trajet,date,heure);
            	//System.out.println(id_trajet+" "+date+" "+heure);
            	trajets.add(t);
            }
            rset.close();
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            System.err.println("[SelectTrajets]SelectTrajets n'est pas effectué!");
            e.printStackTrace();
        }
	 }
	 
	 public HashSet<TrajetInfos> getTrajets(){
		 return trajets;
	 }
	 
	 
}
