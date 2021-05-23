/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Date;

/**
 * Classe abstract de trajet et tronçon.
 */
public abstract class AbstractTrajet {

	protected int nbPlacesDisponibles;
	protected String date ;
	protected String heure ;
        protected int id_trajet;
   

    /**
     * @return the nbPlacesDisponibles
     */
	public int getNbPlacesDisponibles() {
		return nbPlacesDisponibles;
	}
    /**
     * @return the date
     */
	public String getDate() {
		return date;
	}
    /**
     * @return the heure
     */
	public String getHeure() {
		return heure;
	}

    /**
     * @param nbPlacesDisponibles : nouvelle valeur de nbPlacesDisponibles
     */
    public void setNbPlacesDisponibles(int nbPlacesDisponibles) {
        this.nbPlacesDisponibles = nbPlacesDisponibles;
    }
    /**
     * @param date : nouvelle valeur de date
     */
    public void setDate(String date) {
        this.date= date;
    }
    /**
     * @param heure : nouvelle valeur de heure
     */
    public void setHeure(String heure) {
        this.heure= heure;
    }

    /**
     * @return the id_trajet
     */
    public int getId_trajet() {
        return id_trajet;
    }

    /**
     * @param id_trajet the id_trajet to set
     */
    public void setId_trajet(int id_trajet) {
        this.id_trajet = id_trajet;
    }
}
