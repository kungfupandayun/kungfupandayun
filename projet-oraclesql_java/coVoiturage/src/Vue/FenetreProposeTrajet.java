package Vue;

import Modele.Tronçon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class FenetreProposeTrajet extends Fenetre {    
  private String  date;
  private String heure;
  private JTextField placesPoposeTrajet;
  private ArrayList<JTextField> ville_depart;
  private ArrayList<JTextField> ville_arrivee;  
  private ArrayList<JTextField> longitude_depart;
  private ArrayList<JTextField> longitude_arrivee;
  private ArrayList<JTextField> latitude_depart;
  private ArrayList<JTextField> latitude_arrivee;
  private ArrayList<JTextField> duree_tronçon;
  private BoutonValiderProposeTrajet boutonValiderProposeTrajet;
  private BoutonRetourProposeTrajet boutonRetourProposeTrajet;
  private BoutonAddTronçon boutonAddTronçon;
  
  private String hour[]  = new String[25];
  private String minute[]  = new String[61];
  private String jour[]  = new String[32];
  private String mois[]  = {"Mois","1","2","3","4","5","6","7","8","9","10","11","12"} ;
  private String année[]  = {"Année","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032"};
  
  private  JComboBox comboh;
  private  JComboBox combom;
  private  JComboBox comboJ;
  private  JComboBox comboM;
  private  JComboBox comboA;

  
 
    public FenetreProposeTrajet(){
        ville_depart=new ArrayList<>();
        ville_arrivee=new ArrayList<>();
        longitude_depart=new ArrayList<>();
        longitude_arrivee=new ArrayList<>();
        latitude_depart=new ArrayList<>();
        latitude_arrivee=new ArrayList<>();
        duree_tronçon=new ArrayList<>();
        

        
        setArrayListJour();
        comboJ = new JComboBox(jour);
        comboJ.setPreferredSize(new Dimension(100, 20));
        comboJ.setForeground(Color.blue);
        this.add(comboJ);
        
        comboM = new JComboBox(mois);
        comboM.setPreferredSize(new Dimension(100, 20));
        comboM.setForeground(Color.blue);
        this.add(comboM);
        
        comboA = new JComboBox(année);
        comboA.setPreferredSize(new Dimension(100, 20));
        comboA.setForeground(Color.blue);
        this.add(comboA);
        
        setArrayListHeure();
        comboh = new JComboBox(hour);
        comboh.setPreferredSize(new Dimension(100, 20));
        comboh.setForeground(Color.blue);
        this.add(comboh);
        
        setArrayListMinute();
        combom = new JComboBox(minute);
        combom.setPreferredSize(new Dimension(100, 20));
        combom.setForeground(Color.blue);
        this.add(combom);
        

        
        
//        date = new JTextField("Date (yyyy-mm-dd)");
//        date.setPreferredSize(new Dimension(250, 30));
//        this.add(date);
//
//        heure = new JTextField("Heure de départ. XX:xx [24h]");
//        heure.setPreferredSize(new Dimension(250, 30));
//        this.add(heure);

        placesPoposeTrajet = new JTextField("Places");
        placesPoposeTrajet.setPreferredSize(new Dimension(250, 30));
        this.add(placesPoposeTrajet);
        
        ville_depart.add(new JTextField("Ville de depart"));
        ville_depart.get(0).setPreferredSize(new Dimension(250, 30));
        this.add(ville_depart.get(0));

        longitude_depart.add(new JTextField("Longitude de depart"));
        longitude_depart.get(0).setPreferredSize(new Dimension(250, 30));
        this.add(longitude_depart.get(0));
        
        latitude_depart.add( new JTextField("Latitude de depart"));
        latitude_depart.get(0).setPreferredSize(new Dimension(250, 30));
        this.add(latitude_depart.get(0));
        
        ville_arrivee.add(new JTextField("Ville d'arrivee"));
        ville_arrivee.get(0).setPreferredSize(new Dimension(250, 30));
        this.add(ville_arrivee.get(0));
        
        
        longitude_arrivee.add(new JTextField("Longitude d'arrivee"));
        longitude_arrivee.get(0).setPreferredSize(new Dimension(250, 30));
        this.add(longitude_arrivee.get(0));
               
        latitude_arrivee .add(new JTextField("Latitude d'arrivee"));
        latitude_arrivee.get(0).setPreferredSize(new Dimension(250, 30));
        this.add(latitude_arrivee.get(0));
        
        duree_tronçon .add(new JTextField(" Temps de parcours estimé (en minutes)"));
        duree_tronçon.get(0).setPreferredSize(new Dimension(250, 30));
        this.add(duree_tronçon.get(0));
        
        boutonValiderProposeTrajet = new BoutonValiderProposeTrajet("Valider");
        this.add(boutonValiderProposeTrajet);

        boutonRetourProposeTrajet = new BoutonRetourProposeTrajet("Retour");
        this.add(boutonRetourProposeTrajet);
        
        boutonAddTronçon=new BoutonAddTronçon("Add Tronçon");
        this.add(boutonAddTronçon);
    }
    
    
    public void TraiteBoutonValiderProposeTrajet(ActionListener a){
        
        boutonValiderProposeTrajet.addActionListener(a);
    }
    
    public void TraiteBoutonRetourProposeTrajet(ActionListener a){
        boutonRetourProposeTrajet.addActionListener(a);
    }
    
    public void TraiteBoutonAddTronçon(ActionListener a){
        boutonAddTronçon.addActionListener(a);
    }
    
    public void addTronçonCase(){
        
        ville_arrivee.add(new JTextField("Ville d'arrivee"));
        // get l'index du dernier element de la liste
        int nb_element=ville_arrivee.size() - 1;
        ville_arrivee.get(nb_element).setPreferredSize(new Dimension(250, 30));
        this.add(ville_arrivee.get(nb_element));
        
        
        longitude_arrivee.add(new JTextField("Longitude d'arrivee"));
        longitude_arrivee.get(nb_element).setPreferredSize(new Dimension(250, 30));
        this.add(longitude_arrivee.get(nb_element));
               
        latitude_arrivee .add(new JTextField("Latitude d'arrivee"));
        latitude_arrivee.get(nb_element).setPreferredSize(new Dimension(250, 30));
        this.add(latitude_arrivee.get(nb_element));
        
        duree_tronçon .add(new JTextField(" Temps de parcours estimé (en minutes)"));
        duree_tronçon.get(nb_element).setPreferredSize(new Dimension(250, 30));
        this.add(duree_tronçon.get(nb_element));
    }
    
    
    public String getDate(){
        date=comboA.getSelectedItem().toString()+"-"+comboM.getSelectedItem().toString()+"-"+comboJ.getSelectedItem().toString();
        //System.out.println(date);
   
        return date;   
    }
     
    public String getHeure(){
        heure=comboh.getSelectedItem().toString()+":"+combom.getSelectedItem().toString();
        //System.out.println(heure);
        
        return heure;   
    }
    
    public int getPlacesPoposeTrajet(){
         return parseInt(placesPoposeTrajet.getText()); 
    }

    /**
     * @return the ville_depart
     */
    public ArrayList<JTextField> getVille_depart() {
        return ville_depart;
    }

    /**
     * @return the ville_arrivee
     */
    public ArrayList<JTextField> getVille_arrivee() {
        return ville_arrivee;
    }

    /**
     * @return the longitude_depart
     */
    public ArrayList<JTextField> getLongitude_depart() {
        return longitude_depart;
    }

    /**
     * @return the longitude_arrivee
     */
    public ArrayList<JTextField> getLongitude_arrivee() {
        return longitude_arrivee;
    }

    /**
     * @return the latitude_depart
     */
    public ArrayList<JTextField> getLatitude_depart() {
        return latitude_depart;
    }

    /**
     * @return the latitude_arrivee
     */
    public ArrayList<JTextField> getLatitude_arrivee() {
        return latitude_arrivee;
    }

    /**
     * @return the duree_tronçon
     */
    public ArrayList<JTextField> getDuree_tronçon() {
        return duree_tronçon;
    }
    
    public void setArrayListJour(){
        for(int i=0;i<=31;i++){
            if(i==0)jour[i]="Jour";
            else jour[i]=String.valueOf(i);
        }
    }
    
    public void setArrayListHeure(){
        for(int i=0;i<=24;i++){
            if(i==0)hour[i]="Heure";
            else hour[i]=String.valueOf(i-1);
        }
    }
      
    public void setArrayListMinute(){
        for(int i=0;i<=60;i++){
            if(i==0)minute[i]="Minute";
            else minute[i]=String.valueOf(i-1);
        }
    }
        
}
