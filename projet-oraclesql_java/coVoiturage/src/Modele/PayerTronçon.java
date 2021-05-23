package Modele;

import static JDBC.ConnectJDBC.conn;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Exceptions.NoAccountException;
import Exceptions.NoSoldeException;

import java.sql.ResultSet;

import JDBC.ConnectJDBC;
import JDBC.JDBC;

public class PayerTronçon implements JDBC {
	private String email_conducteur;
	private Compte passager;
	private String id_tronçon;
	private String id_trajet;
	private double cout;
	public PayerTronçon(Compte passager, String id_tronçon, String id_trajet) throws Exception{
		this.passager=passager;
		this.id_tronçon=id_tronçon;
		this.id_trajet=id_trajet;
		this.cout=calculCout();
  		if(this.passager.getSolde()<cout) throw new NoSoldeException();
  		
		this.appelJDBC();
	}
	
	
	@Override
    public void appelJDBC() {
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {	
        		
                    String SQL1="select email from ConducteurVehiculeTrajet "
                            + "WHERE id_trajet=? ";
                    
                    PreparedStatement stmt1 = conn.prepareStatement(SQL1);
                    stmt1.setString(1,id_trajet);
                    ResultSet rset1 = stmt1.executeQuery();  
                    
                    while(rset1.next()) {
                        email_conducteur=rset1.getString(1);

                    }
        			
                    String SQL2="select solde from Utilisateur "
                            + "WHERE email=? ";
                    
                    PreparedStatement stmt2 = conn.prepareStatement(SQL2);
                    stmt2.setString(1,email_conducteur);
                    ResultSet rset2 = stmt2.executeQuery();   
                    double solde_conducteur=0;
                    while(rset2.next()) {
                        solde_conducteur=rset2.getDouble(1);
                    }
                    
                    
        			
                    String SQL3="UPDATE Utilisateur set solde=? "
                                + "WHERE email=? ";

                    PreparedStatement stmt3 = conn.prepareStatement(SQL3);
                    PreparedStatement stmt4 = conn.prepareStatement(SQL3);

                    stmt3.setDouble(1,solde_conducteur+cout);
                    stmt3.setString(2,email_conducteur);

                    stmt4.setDouble(1,passager.getSolde()-cout);
                    stmt4.setString(2,passager.getEmail());

                    // execute the preparedstatement
                    stmt3.execute();
                    stmt4.execute();


                    // Fermeture 
         
                    stmt3.close();
                    stmt4.close();
                    conn.commit();
        		


        } catch (SQLException e) {
                System.err.println("[PayerTronçon]La paiement du tronçon n'est pas enregistrée");
                e.printStackTrace();
        }
	}
	

	public double calculCout() {
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {
            PreparedStatement statement1 = conn.prepareStatement("SELECT immatriculation FROM ConducteurVehiculeTrajet "
          	      + "WHERE id_trajet=?");
          		
          		statement1.setString(1, id_trajet);
          		ResultSet results1 = statement1.executeQuery();
          		String immat1="";
          		 while(results1.next()) {
          				immat1 = results1.getString(1);
          				System.out.println(immat1);
          		 }

          		
          		results1.close();
          		statement1.close();
          		
          		PreparedStatement statement2 = conn.prepareStatement("SELECT energie_utilisee,puiss_fiscale from Vehicule "
          		      + "WHERE IMMATRICULATION=? ");
          		
          		statement2.setString(1, immat1);
          		ResultSet results2 = statement2.executeQuery();
          		EnergieUtilisee energieUtilisee=EnergieUtilisee.valueOf("Essence");
          		int puissFiscale=0;
          		while(results2.next()) {
          			energieUtilisee = EnergieUtilisee.valueOf(results2.getString(1));
          			puissFiscale = results2.getInt(2);
          			System.out.println(energieUtilisee);
          			System.out.println(puissFiscale);
          		}


          		
          		results2.close();
          		statement2.close();

          		PreparedStatement statement3 = conn.prepareStatement("SELECT dist_parcourue from Tronçon "
          			      + "WHERE id_tronçon=? AND id_trajet=? ");
          			
          		statement3.setString(1, id_tronçon);
          		statement3.setString(2, id_trajet);
          		ResultSet results3 = statement3.executeQuery();
          		double distance=0;
          		while(results3.next()) {
          			distance = results3.getDouble(1);
          			System.out.println(distance);
          		}

          		double coeff=0;
          		switch(energieUtilisee){
          		  case Essence:
          		      coeff = 1;
          		      break;
          		  case Diesel :
          		      coeff = 1.5;
          		      break;
          		  case Electrique:
          		      coeff = 0.5;
          		      break;
          		  default:
          		      coeff = 0;
          		}

          		
          		
          		results3.close();
          		statement3.close();
          		conn.commit();
          		double coutTronçon= puissFiscale * coeff * 0.10 * distance * 0.001;
          		System.out.println(coutTronçon);

          			return coutTronçon;

        	}
	        catch (SQLException e) {
	            System.err.println("[CalculCout]CalculCout n'est pas enregistré!");
	            e.printStackTrace();
	            return 0;
	        }
        	
		}
	
		


}
