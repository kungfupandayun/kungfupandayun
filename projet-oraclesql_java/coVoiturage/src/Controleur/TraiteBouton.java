
package Controleur;

import Exceptions.NoAccountException;
import Exceptions.NoSoldeException;
import Exceptions.NoTronçonException;
import Exceptions.NoVehiculeException;
import Exceptions.TimeProblemeException;
import static JDBC.ConnectJDBC.conn;
import Modele.*;
import Vue.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JComboBox;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author pojia
 */
public class TraiteBouton {

  // La vue associée à ce contrôleur
  private Vue vue;
  // La modele associe
  private Modele modele;
  private  JComboBox combo;

  private ArrayList<String> vehiculesDB;
  private HashSet<TrajetInfos> trajets;
  private HashSet<TronçonInfos> tronçons_trajet;
  private HashSet<ParcoursInfos> parcours;
  private HashSet<TronçonParcoursInfos> tronçons_parcours;
  private JLabel l=new JLabel();
  private Boolean withDestination = false;


    /**
    * Constructeur de la classe et tous les actions à efféctuer si un bouton est appuyé.
    */
     public   TraiteBouton(Vue vue,  Modele modele){
        this.vue=vue;
        this.modele=modele;
   
        
        //Traite bouton Inscription
        vue.fenetrePrincipale.TraiteBoutonInscription(new ActionListener(){
         public void actionPerformed(ActionEvent e){
           vue.setFenetreInscription();
         }
        });
        
        vue.fenetreInscription.TraiteBoutonValiderInscription(new ActionListener(){
         public void actionPerformed(ActionEvent e){
           FenetreInscription f=vue.fenetreInscription;
           modele.setCompte(f.getEmail(),f.getNom(),f.getPrenom(),f.getVille(),f.getMdP(),200);

            if (modele.getCompte().getEmail()!=null) {
                l.setText("Inscription succès!");
                vue.fenetreSucces.add(l);
	      	vue.setFenetreSucces();
            } else {
                l.setText("Inscription échec!");
                vue.fenetreEchec.add(l);
                vue.setFenetreEchec();
            }
         }
        });
        
        
        vue.fenetreEchec.TraiteBoutonOkEchec(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                vue.setVisible(false);
                vue.dispose();  
                 try {
                    System.out.println("Fin de Session");
                    if(conn!=null) conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vue.class.getName()).log(Level.SEVERE, null, ex);
                }
         }
        });
        
        vue.fenetreSucces.TraiteBoutonOkSucces(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 vue.setFenetrePrincipale();
                 
         }
        });
        vue.fenetreSuccesTrajet.TraiteBoutonOkSucces(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	vue.setFenetreTronçonsTrajet();
        }
       });
        vue.fenetreSuccesParcours.TraiteBoutonOkSucces(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	vue.setFenetreTronçonsParcours();
        }
       });

        
        //Traite bouton identification
        vue.fenetrePrincipale.TraiteBoutonIdentification(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 vue.setFenetreAjoutIdentification();
         }
        });
        
        
        vue.fenetreIdentification.TraiteBoutonValiderIdentification(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                FenetreIdentification f=vue.fenetreIdentification;
                 try {
                     modele.loginCompte(f.getEmail(),f.getMdP());
                 } catch (NoAccountException ex) {
                     vue.setFenetreEchec();
                 }
            if (modele.getCompte().getEmail()!=null) {
                l.setText("Identification succès!");
                vue.fenetreSucces.add(l);
	      	vue.setFenetreSucces();
            }
            else{
                l.setText("Identification échec!");
                vue.fenetreEchec.add(l);
                vue.setFenetreEchec();
            }
         }
        });
        
        

        //Traite bouton ajout Vehicule
        vue.fenetrePrincipale.TraiteBoutonAjoutVehicule(new ActionListener(){
             public void actionPerformed(ActionEvent e){
             if(EstConnecté()){
                 vue.setFenetreAjoutVehicule();
             }
         }
        });
        
        
        vue.fenetreAjoutVehicule.TraiteBoutonValiderAjoutVehicule(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                FenetreAjoutVehicule f=vue.fenetreAjoutVehicule;
                modele.setVehicule(f.getImmatriculation(),f.getMarque(),f.getModele(),
                            EnergieUtilisee.valueOf(f.getEnergie()),f.getPuissance(),f.getPlacesAjoutVehicule());
                modele.setPossedeVehicule();
            if (modele.getVehicule().getImmatriculation()!=null) {
                l.setText("Ajout Vehicule succès!");
                vue.fenetreSucces.add(l);
	      	vue.setFenetreSucces();
            } else {
                l.setText("Ajout Vehicule échec!");
                vue.fenetreEchec.add(l);
                vue.setFenetreEchec();
            }
         }
        });
        
        
        //Traite bouton rechargement solde 
        vue.fenetrePrincipale.TraiteBoutonRechargementSolde(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                if(EstConnecté()){
                    l.setText("Solde:"+modele.getCompte().getSolde());
                    vue.fenetreRechargementSolde.add(l);
                    vue.setFenetreRechargementSolde();
                }
         }
        });
        
        vue.fenetreRechargementSolde.TraiteBoutonValiderRechargement(new ActionListener(){
             public void actionPerformed(ActionEvent e){
            	FenetreRechargementSolde f=vue.fenetreRechargementSolde;
                modele.setRechargerSolde(f.getMontant());
                l.setText("Rechargement succès!");
                vue.fenetreSucces.add(l);
                vue.setFenetreSucces();
         }
        });
        
        
        
        //Traite boutons affichage trajets, tronçons , suivi, annulation 
        vue.fenetrePrincipale.TraiteBoutonTrajet(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                if(EstConnecté()){
            	 trajets = modele.getTrajets();
            	 
            	 vue.fenetreTrajets.setTrajets(trajets);
                 vue.setFenetreTrajets();
                 
                 vue.fenetreTrajets.TraiteBoutonsTrajet(new ActionListener(){
                      public void actionPerformed(ActionEvent e){
                          //vue.setFenetreTronçonsTrajet();
                    	  String id_trajet=e.getActionCommand();
                    	  tronçons_trajet= modele.getTronçonsTrajet(id_trajet);
                     	  vue.fenetreTronçonsTrajet.setTronçons(tronçons_trajet);
                     	  vue.setFenetreTronçonsTrajet();
                     	  
                     	  vue.fenetreTronçonsTrajet.TraiteBoutonsTrajet(new ActionListener() {
                     		 public void actionPerformed(ActionEvent e) {
                     			String id_tronçon = e.getActionCommand();
                     			FenetreSuiviTrajet f=vue.fenetreSuiviTrajet;
                     			vue.setFenetreSuiviTrajet();
                     			
                     			vue.fenetreSuiviTrajet.TraiteBoutonConfirmerDépart(new ActionListener() {
                     				public void actionPerformed(ActionEvent e) {
                     					FenetreSuiviTrajet f=vue.fenetreSuiviTrajet;
                     					modele.setConfirmerDépart(id_tronçon,id_trajet);
                     	                l.setText("Confirmation Départ succès!");
                     	                vue.fenetreSuccesTrajet.add(l);
                     	                vue.setFenetreSuccesTrajet();
                     				}
                     			});
                     			
                     			vue.fenetreSuiviTrajet.TraiteBoutonConfirmerArrivée(new ActionListener() {
                     				public void actionPerformed(ActionEvent e) {
                     					FenetreSuiviTrajet f=vue.fenetreSuiviTrajet;
                     					modele.setConfirmerArrivée(id_tronçon,id_trajet);
                     	                l.setText("Confirmation Arrivée succès!");
                     	                vue.fenetreSuccesTrajet.add(l);
                     	                vue.setFenetreSuccesTrajet();
                     				}
                     			});
                     			
                     			vue.fenetreSuiviTrajet.TraiteBoutonAnnulerTronçon(new ActionListener() {
                     				public void actionPerformed(ActionEvent e) {
                     					FenetreSuiviTrajet f=vue.fenetreSuiviTrajet;
                     					modele.setAnnulerTronçon(id_tronçon,id_trajet);
                     	                l.setText("Confirmation Annulement succès!");
                     	                vue.fenetreSucces.add(l);
                     	                vue.setFenetreSucces();
                     				}
                     			});
                     		 }
                     	  });
                      }
                 });

                }  
         }
        });
        
        
        
        
        
        
        //Traite boutons affichage parcours, tronçons , suivi, paiement 
        vue.fenetrePrincipale.TraiteBoutonParcours(new ActionListener() {
             public void actionPerformed(ActionEvent e){
                 if(EstConnecté()){
            	 parcours = modele.getParcours();
            	 vue.fenetreParcours.setParcours(parcours);
                 vue.setFenetreParcours();
                 
                 vue.fenetreParcours.TraiteBoutonsParcours(new ActionListener(){
                      public void actionPerformed(ActionEvent e){
                    	  String id_parcours=e.getActionCommand();
                    	  tronçons_parcours= modele.getTronçonsParcours(id_parcours);
                     	  vue.fenetreTronçonsParcours.setTronçonsParcours(tronçons_parcours);
                     	  vue.setFenetreTronçonsParcours();
                     	  
                     	  vue.fenetreTronçonsParcours.TraiteBoutonsTronçonsParcours(new ActionListener() {
                     		 public void actionPerformed(ActionEvent e) {
                     			String id_trajet_tronçon = e.getActionCommand();
                     			String[] arr = id_trajet_tronçon.split(" ", 2);
                     			String id_trajet = arr[0]; 
                     			String id_tronçon = arr[1];
                     			
                     			FenetreTronçonsParcours f=vue.fenetreTronçonsParcours;
                     			vue.setFenetreSuiviParcours();
                     			
                     			vue.fenetreSuiviParcours.TraiteBoutonConfirmerMontée(new ActionListener() {
                     				public void actionPerformed(ActionEvent e) {
                     					FenetreSuiviParcours f=vue.fenetreSuiviParcours;
                     					modele.setConfirmerMontée(id_tronçon,id_trajet,id_parcours);
                     	                l.setText("Confirmation Montée succès!");
                     	                vue.fenetreSuccesParcours.add(l);
                     	                vue.setFenetreSuccesParcours();
                     				}
                     			});
                     			
                     			vue.fenetreSuiviParcours.TraiteBoutonConfirmerDescente(new ActionListener() {
                     				public void actionPerformed(ActionEvent e) {
                     					FenetreSuiviParcours f=vue.fenetreSuiviParcours;
                     					modele.setConfirmerDescente(id_tronçon,id_trajet,id_parcours);
                     	                l.setText("Confirmation Descente succès!");
                     	                vue.fenetreSuccesParcours.add(l);
                     	                vue.setFenetreSuccesParcours();
                     				}
                     			});
                     			
                     			
                     			vue.fenetreSuiviParcours.TraiteBoutonPaiement(new ActionListener() {
                     				public void actionPerformed(ActionEvent e) {
                     					FenetreSuiviParcours f=vue.fenetreSuiviParcours;
                     					try {
                     					modele.setPaiementTronçon(id_tronçon,id_trajet);
                     					}
                     					catch(Exception ex) {
                     	                    if(ex instanceof NoSoldeException );
                                            l.setText("Paiement tronçon échec: Vous n'avez pas assez de solde!");
                                            vue.fenetreEchec.add(l);
                                            vue.setFenetreEchec();
                     	                }
                     				}
                     			});
                     			
                     			
                     		 }
                     	  });
                      }
                 });

               }   
         }
        });

        //Traite bouton ProposeTrajet
        vue.fenetrePrincipale.TraiteBoutonProposeTrajet(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (EstConnecté()) {
                    PossedeVehicule p=new PossedeVehicule(modele.getCompte().getEmail());
                    vehiculesDB=p.getListVehicules();
                    if (vehiculesDB.isEmpty()) {
                        vue.setFenetreAjoutVehicule();
                    } else {
                        setFenetreChoisirVoiture();
                    }
		        }
            }
        });
        
        
        vue.fenetreChoisirVehicule.TraiteBoutonValiderChoisirVehicule(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                modele.choisirVehicule(combo.getSelectedItem().toString());
                vue.setProposeTrajet();
            }  
        });
        
        
        vue.fenetreProposeTrajet.TraiteBoutonValiderProposeTrajet(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                
                FenetreProposeTrajet f=vue.fenetreProposeTrajet;
                modele.setTrajet(f.getPlacesPoposeTrajet(),f.getDate(),f.getHeure());
                
                //chercher le nb de tronçon à enregistrer, et les ajouter avec la methode de la classe trajet
                int nb_tronçon=f.getVille_arrivee().size();
                int cpt=0;
                while(cpt<nb_tronçon){
                    if(cpt==0){
                    modele.getTrajet(). ajouteTronçon(f.getVille_depart().get(cpt).getText(),parseDouble(f.getLatitude_depart().get(cpt).getText()),
                                                        parseDouble(f.getLongitude_depart().get(cpt).getText()),f.getVille_arrivee().get(cpt).getText(),
                                                        parseDouble(f.getLatitude_arrivee().get(cpt).getText()),parseDouble(f.getLongitude_arrivee().get(cpt).getText()),
                                                        parseInt(f.getDuree_tronçon().get(cpt).getText()), Etat_Tronçon_Conducteur.EnAttente);
                    }else{
                    modele.getTrajet(). ajouteTronçon(f.getVille_arrivee().get(cpt-1).getText(),parseDouble(f.getLatitude_arrivee().get(cpt-1).getText()),
                                                        parseDouble(f.getLongitude_arrivee().get(cpt-1).getText()),f.getVille_arrivee().get(cpt).getText(),
                                                        parseDouble(f.getLatitude_arrivee().get(cpt).getText()),parseDouble(f.getLongitude_arrivee().get(cpt).getText()),
                                                        parseInt(f.getDuree_tronçon().get(cpt).getText()), Etat_Tronçon_Conducteur.EnAttente);                    
                    }
                    cpt++;
                }
                
                try {
                    modele.setProposeTrajet(modele.getCompte(),modele.getVehicule(),modele.getTrajet());
                    l.setText("Propose Trajet succès!");
                    vue.fenetreSucces.add(l);
                    vue.setFenetreSucces();
                } catch (Exception ex) {
                   l.setText("Echec !");
                   if(ex instanceof NoAccountException )
                        l.setText("Propose Trajet échec: Vous n'êtes pas connecté!");
                   if(ex instanceof NoVehiculeException )
                        l.setText("Propose Trajet échec: Le vehicule a déjà choisi pour ce créneau!");                    
                   if(ex instanceof NoTronçonException )
                        l.setText("Propose Trajet échec: Il n'y a pas de tronçon!");
                    if(ex instanceof TimeProblemeException )
                        l.setText("Propose Trajet échec: L'heure proposée est déjà passé!");
                    if(ex instanceof SQLException )
                        l.setText("SQLException!");
                    vue.fenetreEchec.add(l);
                    vue.setFenetreEchec();
                }
            }  
        });
        
        
         vue.fenetreProposeTrajet.TraiteBoutonAddTronçon(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
             vue.fenetreProposeTrajet.addTronçonCase();
             vue.setProposeTrajet();
            }  
        });
        
         
         // Traite Bouton Recherche Parcours
         vue.fenetrePrincipale.TraiteBoutonRechercheParcours(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                if(EstConnecté()){
                  vue.setFenetreRechercheParcours();
                }
             }
         });
         
         vue.fenetreRechercheParcours.TraiteBoutonRechercheAvecDestination(new ActionListener(){
             public void actionPerformed(ActionEvent e) {
                  vue.fenetreRechercheParcours.AjouteCasesDestination();
                  vue.setFenetreRechercheParcours();
                  withDestination = true;
             }
         });

         vue.fenetreRechercheParcours.TraiteBoutonResetRechercheParcours(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent actionEvent) {
                 vue.fenetreRechercheParcours.resetData();
                 vue.refreshPage();
             }
         });
         
         vue.fenetreRechercheParcours.TraiteBoutonValiderRechercheParcours(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 FenetreRechercheParcours f = vue.fenetreRechercheParcours;
                 f.loadSuggestions();
                 vue.refreshPage();

                 ArrayList<Suggestion> suggestions;

                 if (withDestination) {
                     suggestions = modele.rechercheParcoursAvecDest(
                             f.getVille_depart(),
                             f.getLongitude_depart(),
                             f.getLatitude_depart(),
                             f.getDate(),
                             f.getVille_arrive(),
                             f.getLongitude_arrive(),
                             f.getLatitude_arrive());

                 } else {
                     suggestions =  modele.rechercheParcoursSansDest(
                             f.getVille_depart(),
                             f.getLongitude_depart(),
                             f.getLatitude_depart(),
                             f.getDate());
                 }

                 if (suggestions.isEmpty()) {
                     f.showNoSuggestions();
                 } else {
                     f.showSuggestions(suggestions, new ActionListener() {
                         public void actionPerformed(ActionEvent e) {
                             try {
                                 modele.setParcours(f.getSelectedSuggestion());
                                 vue.setFenetrePrincipale();
                             } catch (Exception ex) {
                                 ex.printStackTrace();
                             }
                         }
                     });
                 }
                 f.finishLoadingSuggestions();
                 vue.refreshPage();
             }
         });
         
         
        //Bouton de Retour
        vue.fenetreInscription.TraiteBoutonRetourInscription(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 vue.setFenetrePrincipale();
         }
        });
        
        vue.fenetreIdentification.TraiteBoutonRetourIdentification(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 vue.setFenetrePrincipale();
         }
        });
        
        vue.fenetreProposeTrajet.TraiteBoutonRetourProposeTrajet(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 vue.setFenetrePrincipale();
         }
        });
        
        vue.fenetreAjoutVehicule.TraiteBoutonRetourAjoutVehicule(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 vue.setFenetrePrincipale();
         }
        });
        
        
        vue.fenetreChoisirVehicule.TraiteBoutonRetourChoisirVehicule(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 vue.setFenetrePrincipale();
         }
        });
        
        vue.fenetreRechargementSolde.TraiteBoutonRetourRechargement(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                vue.setFenetrePrincipale();
        }
       });
        
        vue.fenetreTrajets.TraiteBoutonRetourAffichageTrajet(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	vue.setFenetrePrincipale();
        }
       });
        
        vue.fenetreSuiviTrajet.TraiteBoutonRetourConfirmationTronçon(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	vue.setFenetreTronçonsTrajet();
        }
       });
        vue.fenetreTronçonsTrajet.TraiteBoutonRetourAffichageTrajet(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                vue.setFenetreTrajets();
        }
       });
        
        vue.fenetreParcours.TraiteBoutonRetourAffichageParcours(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                vue.setFenetrePrincipale();
        }
       });
        
        
        vue.fenetreTronçonsParcours.TraiteBoutonRetourAffichageTronçons(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                vue.setFenetreParcours();
        }
       });
        
        vue.fenetreSuiviParcours.TraiteBoutonRetourConfirmationTronçon(new ActionListener(){
            public void actionPerformed(ActionEvent e){
           	  vue.setFenetreTronçonsParcours();
        }
       });

         vue.fenetreRechercheParcours.TraiteBoutonRetourRechercheParcours(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 vue.setFenetrePrincipale();
             }
         });
        
  
}
   
     /**
      * methode verifie si l'utilisateur est connecté, affiche un écran erreur si la personne n'est pas connecté
      */
     public boolean EstConnecté(){
         if(modele.getCompte()!=null) return true;
         else{ 
            l.setText("Vous n'êtes pas connecté!");
            vue.fenetreEchec.add(l);
            vue.setFenetreEchec();
             return false;
         }
     }
     
     /**
      * Récuperer la liste de véhicules associés au compte et l'afficher.
      */
     public void setFenetreChoisirVoiture(){
         String[] myArray=new String[vehiculesDB.size()];
         vehiculesDB.toArray(myArray);
         if(combo==null){
            combo = new JComboBox(myArray);
            combo.setPreferredSize(new Dimension(100, 20));
            combo.setForeground(Color.blue);
         }
         vue.fenetreChoisirVehicule.add(combo);
         vue.setTitle("IHM - Choix d'un véhicule");
         vue.setContentPane(vue.fenetreChoisirVehicule);
         vue.refreshPage();
     }
}