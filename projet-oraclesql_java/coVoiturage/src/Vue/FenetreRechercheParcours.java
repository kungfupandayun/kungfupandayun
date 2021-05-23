
package Vue;

import Modele.Suggestion;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pojia
 */
public class FenetreRechercheParcours extends Fenetre {
    private JTextField ville_depart;
    private JTextField longitude_depart;
    private JTextField latitude_depart;
    private JTextField date;
    private JTextField ville_arrive;
    private JTextField longitude_arrive;
    private JTextField latitude_arrive;
    private JLabel noSuggestion;
    private JTable suggestionTable;
    private JScrollPane sp;
    private int selectedSuggestion;
    private BoutonValiderRechercheParcours  boutonValiderRechercheParcours;
    private BoutonRetourRechercheParcours  boutonRetourRechercheParcours;
    private BoutonRechercheAvecDestination boutonRechercheAvecDestination;
    private BoutonSelectRechercheParcours boutonSelectRechercheParcours;
    private BoutonResetRechercheParcours boutonResetRechercheParcours;

    public FenetreRechercheParcours(){
        date = new JTextField("Date (yyyy-mm-dd)");
        date.setPreferredSize(new Dimension(250, 30));
        this.add(date);
        
        ville_depart = new JTextField("Ville de depart");
        ville_depart.setPreferredSize(new Dimension(250, 30));
        this.add(ville_depart);
        
        longitude_depart = new JTextField("Longitude de depart");
        longitude_depart.setPreferredSize(new Dimension(250, 30));
        this.add(longitude_depart);
        
        latitude_depart = new JTextField("Latitude de depart");
        latitude_depart.setPreferredSize(new Dimension(250, 30));
        this.add(latitude_depart);

        boutonRechercheAvecDestination = new BoutonRechercheAvecDestination("Ajoute la destination");
        this.add(boutonRechercheAvecDestination);
        
        boutonValiderRechercheParcours = new BoutonValiderRechercheParcours("Valider");
        this.add(boutonValiderRechercheParcours);
        
        boutonRetourRechercheParcours = new BoutonRetourRechercheParcours("Retour");
        this.add(boutonRetourRechercheParcours);

        boutonResetRechercheParcours = new BoutonResetRechercheParcours("Reset");
        this.add(boutonResetRechercheParcours);
    }

    public void resetData() {
        date.setText("Date (yyyy-mm-dd)");
        ville_depart.setText("Ville de depart");
        longitude_depart.setText("Longitude de depart");
        latitude_depart.setText("Latitude de depart");

        if (ville_arrive != null) {
            ville_arrive.setText("Ville d'arrivée");
            longitude_arrive.setText("Longitude d'arrivée");
            latitude_arrive.setText("Latitude d'arrivée");
        }

        if (sp != null) {
            this.remove(boutonSelectRechercheParcours);
            this.remove(sp);
        }

        if (noSuggestion != null) {
            this.remove(noSuggestion);
        }
    }
    public void loadSuggestions() {
        boutonValiderRechercheParcours.setText("Loading...");
    }

    public void finishLoadingSuggestions() {
        boutonValiderRechercheParcours.setText("Valider");
    }

    public void TraiteBoutonRetourRechercheParcours(ActionListener a){
        boutonRetourRechercheParcours.addActionListener(a);
    }
    
        
    public void TraiteBoutonRechercheAvecDestination(ActionListener a){
        boutonRechercheAvecDestination.addActionListener(a);
    }

    public void TraiteBoutonValiderRechercheParcours(ActionListener a){
        boutonValiderRechercheParcours.addActionListener(a);
    }

    public void TraiteBoutonResetRechercheParcours(ActionListener a){
        boutonResetRechercheParcours.addActionListener(a);
    }

    
    public void AjouteCasesDestination(){
        if(ville_arrive==null){
            this.remove(boutonRechercheAvecDestination);
            this.remove(boutonValiderRechercheParcours);
            this.remove(boutonRetourRechercheParcours);
            this.remove(boutonResetRechercheParcours);

            ville_arrive = new JTextField("Ville d'arrivée");
            ville_arrive.setPreferredSize(new Dimension(250, 30));
            this.add(ville_arrive);

            longitude_arrive = new JTextField("Longitude d'arrivée");
            longitude_arrive.setPreferredSize(new Dimension(250, 30));
            this.add(longitude_arrive);

            latitude_arrive = new JTextField("Latitude d'arrivée");
            latitude_arrive.setPreferredSize(new Dimension(250, 30));
            this.add(latitude_arrive);

            this.add(boutonRechercheAvecDestination);
            this.add(boutonValiderRechercheParcours);
            this.add(boutonRetourRechercheParcours);
            this.add(boutonResetRechercheParcours);
        }
    }

    public void showNoSuggestions() {
        if (noSuggestion == null) {
            noSuggestion = new JLabel("Aucune suggestion disponible");
            this.add(noSuggestion);
        }
    }

    public void showSuggestions(ArrayList<Suggestion> suggestions, ActionListener selectButtonAction) {
        String column[] = {"DEPART", "ARRIVEE", "STOP", "HEURE DEPART", "HEURE ARRIVEE",
                "DUREE", "DISTANCE", "PRIX", "MARQUE", "MODELE", "ENERGIE"};

        DefaultTableModel tableModel = new DefaultTableModel(column, 0);

        for (Suggestion suggestion: suggestions) {
            String stop;
            if (suggestion.isWithStop()) {
                stop = suggestion.getPlaceStop();
            } else {
                stop = "No Stop";
            }

            String dureeTrajet = String.format("%dh%dm", suggestion.getDureeTrajet() / 60, suggestion.getDureeTrajet() % 60);
            Object[] data = {
                    suggestion.getPlaceDépart(),
                    suggestion.getPlaceArriveé(),
                    stop,
                    suggestion.getHeureDépart(),
                    suggestion.getHeureArrivée(),
                    dureeTrajet,
                    String.format("%.2fm", suggestion.getDistance()),
                    String.format("%.2f EUR", suggestion.getPrix()),
                    suggestion.getMarque(),
                    suggestion.getModele(),
                    suggestion.getEnergieUtilisee()
            };
            tableModel.addRow(data);

        }
        suggestionTable = new JTable(tableModel);
        suggestionTable.setBounds(30,40,400,100);
        suggestionTable.setRowSelectionAllowed(true);

        ListSelectionModel select= suggestionTable.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                selectedSuggestion = suggestionTable.getSelectedRow();
                System.out.println("Table element selected is: " + selectedSuggestion);
            }
        });

        sp = new JScrollPane(suggestionTable);
        sp.setPreferredSize(new Dimension(900, 150));
        boutonSelectRechercheParcours = new BoutonSelectRechercheParcours("Select");
        boutonSelectRechercheParcours.addActionListener(selectButtonAction);
        this.add(sp);
        this.add(boutonSelectRechercheParcours);
    }
    
    public String getDate(){
        System.out.println(date.getText());
        return date.getText();   
    }
    
    public String getVille_depart(){
        System.out.println(ville_depart.getText());
        return ville_depart.getText();   
    }
    
    public Double getLongitude_depart(){
        System.out.println(longitude_depart.getText());
        return Double.parseDouble(longitude_depart.getText());   
    }
    
    public Double getLatitude_depart(){
        System.out.println(latitude_depart.getText());
        return Double.parseDouble(latitude_depart.getText());   
    }

    public String getVille_arrive() {
        return ville_arrive.getText();
    }

    public Double getLongitude_arrive() {
        return Double.parseDouble(longitude_arrive.getText());
    }

    public Double getLatitude_arrive() {
        return Double.parseDouble(latitude_arrive.getText());
    }

    public int getSelectedSuggestion() {
        return selectedSuggestion;
    }
}
