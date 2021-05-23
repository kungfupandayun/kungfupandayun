
package Modele;

import Exceptions.NoAccountException;
import Exceptions.NoVehiculeException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author pojia
 */
public class Modele {
    private Compte compte ;
    private Vehicule vehicule;
    private Parcours parcours;
    private Trajet trajet;
    private PossedeVehicule possedeVeh;
    private ProposeTrajet proposeTraj;
    private AbstractRechercheParcours rechercheParcours;
    private RechargerPorteMonnaie rechargerPorteMonnaie;
    private SelectTrajets selectTrajets;
    private ConfirmerDépart confirmerDépart;
    private ConfirmerArrivée confirmerArrivée;
    private SelectTronçons selectTronçons;
    private AnnulerTronçon annulerTronçon;
    private SelectParcours selectParcours;
    private SelectTronçonsParcours selectTronçonsParcours;
    private ConfirmerMontée confirmerMontée;
    private ConfirmerDescente confirmerDescente;
    private PayerTronçon payerTronçon;

    
    public void setCompte(String email,String nom,String prenom,String ville, 
                                            String MdP,double solde){
        compte=new Compte(email,nom,prenom,ville,MdP,solde);
    }
    
    public void setVehicule(String immatriculation, String marque, String modele, 
                    EnergieUtilisee energieUtilisee, int puissFiscale, int nbPlace) 
    {
        vehicule=new Vehicule(immatriculation,marque,modele,energieUtilisee
                                                            ,puissFiscale,nbPlace);
    }
    
     public void choisirVehicule(String immatriculation) 
    {
        vehicule=new Vehicule(immatriculation);
    }
    
    public void setPossedeVehicule(){
        possedeVeh=new PossedeVehicule(compte,vehicule);
    }
    
    public void loginCompte(String email,String mdp) throws NoAccountException{
        compte=new Compte(email,mdp);
    }
    
    public void setTrajet(int nbPlacesDisponibles,String date, String heure){
       trajet=new Trajet(nbPlacesDisponibles,date, heure);
    }
    
    public void setProposeTrajet(Compte compte , Vehicule vehicule, Trajet trajet ) throws Exception{
        proposeTraj=new ProposeTrajet(compte,vehicule,trajet);
    }
    
    public void setRechargerSolde(Double montant){
    	rechargerPorteMonnaie=new RechargerPorteMonnaie(compte, montant);
    }
    
    public void setConfirmerDépart(String id_tronçon, String id_trajet){
    	confirmerDépart=new ConfirmerDépart(id_tronçon,id_trajet);
    }
    
    public void setConfirmerArrivée(String id_tronçon, String id_trajet){
    	confirmerArrivée=new ConfirmerArrivée(id_tronçon,id_trajet);
    }
    
    public void setAnnulerTronçon(String id_tronçon, String id_trajet){
    	annulerTronçon=new AnnulerTronçon(id_tronçon,id_trajet);
    }
    
    public void setConfirmerMontée(String id_tronçon, String id_trajet, String id_parcours){
    	confirmerMontée=new ConfirmerMontée(id_tronçon,id_trajet,id_parcours);
    }
    
    public void setConfirmerDescente(String id_tronçon, String id_trajet, String id_parcours){
    	confirmerDescente=new ConfirmerDescente(id_tronçon,id_trajet,id_parcours);
    }
    
    public void setPaiementTronçon(String id_tronçon, String id_trajet)throws Exception{
    	payerTronçon=new PayerTronçon(compte,id_tronçon,id_trajet);
    }

    public void setParcours(int selectedSuggestion) throws Exception {
        parcours = new Parcours(compte, selectedSuggestion);
    }
    
    public ArrayList<Suggestion> rechercheParcoursSansDest(String villeDépart, double longitudeDépart,
                                    double latitudeDépart, String date){
        rechercheParcours = new RechercheParcoursSansDest(villeDépart, longitudeDépart, latitudeDépart, date, compte);
        return AbstractRechercheParcours.listSuggestion;
    }

    public ArrayList<Suggestion> rechercheParcoursAvecDest(String villeDépart, double longitudeDépart,
                                                           double latitudeDépart, String date,
                                                           String villeArrivée, double longitudeArrivée,
                                                           double latitudeArrivée){
        rechercheParcours = new RechercheParcoursDest(villeDépart, longitudeDépart, latitudeDépart, date,
                villeArrivée, longitudeArrivée, latitudeArrivée, compte);
        rechercheParcours.printList();

        return AbstractRechercheParcours.listSuggestion;
    }

        
    public Compte getCompte(){
        return compte;
    }
    
    public Vehicule getVehicule(){
        return vehicule;
    }
    
    public Trajet getTrajet(){
        return trajet;
    }
    
    public ArrayList<String> getVehiculesList() throws NoVehiculeException{
        possedeVeh=new PossedeVehicule(compte.getEmail());
        return possedeVeh.getListVehicules();
    }
    
    public HashSet<TrajetInfos> getTrajets(){
    	selectTrajets=new SelectTrajets(compte);
        return selectTrajets.getTrajets();
    }
    
    public HashSet<TronçonInfos> getTronçonsTrajet(String id_trajet){
    	selectTronçons=new SelectTronçons(id_trajet);
        return selectTronçons.getTronçons();
    }
    
    public HashSet<ParcoursInfos> getParcours(){
    	selectParcours=new SelectParcours(compte);
        return selectParcours.getParcours();
    }
    
    public HashSet<TronçonParcoursInfos> getTronçonsParcours(String id_parcours){
    	selectTronçonsParcours=new SelectTronçonsParcours(id_parcours);
        return selectTronçonsParcours.getTronçonsParcours();
    }

    
    
}
