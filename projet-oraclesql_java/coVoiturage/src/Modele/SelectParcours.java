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

public class SelectParcours implements JDBC {
	
	private Compte compte;
	private HashSet<ParcoursInfos> parcours;
	
	public SelectParcours(Compte compte) {
		this.compte=compte;
		this.parcours=new HashSet<ParcoursInfos>();
        this.appelJDBC();
	}

	 @Override
	 public void appelJDBC() {
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {
            String SQL="SELECT t1.id_parcours,t1.DATEHEURE_DÉPART from Parcours t1"
            		+" INNER JOIN utilisateurparcours t2 ON t1.id_parcours=t2.id_parcours "
            		+ " where email=?";
            PreparedStatement stmt=conn.prepareStatement(SQL);
            stmt.setString(1, compte.getEmail());
            
            ResultSet rset = stmt.executeQuery();   
            while(rset.next()) {
            	String id_parcours = rset.getString(1);
            	String date_depart = rset.getDate(2).toString();
            	String heure_depart = rset.getTime(2).toString();
            	//System.out.println(id_parcours);
            	ParcoursInfos p = new ParcoursInfos(id_parcours,date_depart,heure_depart);
            	parcours.add(p);
            }
            rset.close();
            stmt.close();
            conn.commit();
        }
        catch (SQLException e) {
            System.err.println("[SelectParcours]SelectParcours n'est pas effectué!");
            e.printStackTrace();
        }
	 }
	 
	 public HashSet<ParcoursInfos> getParcours(){
		 return parcours;
	 }
	 
	 
}
