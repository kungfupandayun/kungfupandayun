//Équipe 58 en Teide
import java.awt.Color ;
import gui.GUISimulator;
import java.util.Random;

public class TestGui {
	  public static void main (String[] args) {

		    //INITIALISER LA TAILLE DE FENETRE ET NBRE DE BALL
		    int window_sizex=500;
		    int window_sizey=500;

		    //DECLARATION DU TYPE

		    //créer et initialiser la grille
		    Grille grille = new Grille(window_sizex,window_sizey);
		    //créer le GuiSimultor pour l'effet graphique
		    GUISimulator gui = new GUISimulator (window_sizex ,window_sizey, Color.BLACK );
				EventManager manager = new EventManager();
		    GrilleSimulator x = new GrilleSimulator();

		    grille.ajoutCellules();
				Random random = new Random();
				for(int i=2; i<50-2 ; i++){
					for(int j=2 ; j<50-2 ; j++){
						int randomInt = random.nextInt(2);
						if(randomInt==0) grille.matrice[i][j].etatCellule=false;
						else grille.matrice[i][j].etatCellule=true ;
					}
				}


				grille.mémoriserGrille();

				manager.addEvent(new GrilleEvent(0,grille,gui));
		  //INSTANCIER LA GrilleSimulator
		    x.grille=grille;
		    x.gui=gui;
				x.manager=manager;
		    //PREPARER L'AFFICHAGE
		    gui.setSimulable(x) ;
		  }
}
