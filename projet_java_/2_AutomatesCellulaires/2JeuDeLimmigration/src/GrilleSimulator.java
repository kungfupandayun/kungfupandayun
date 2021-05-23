//Équipe 58 en Teide
import gui.*;
import java.awt.Color;
import gui.Rectangle;
import gui.GUISimulator ;
public class GrilleSimulator implements gui.Simulable  {

	  public Grille grille;
	  public GUISimulator gui;
		public EventManager manager;

	  @Override
	  public void next(){
			manager.next(grille,gui);
	  }

	     @Override
		    public void restart(){
		      grille.reInit();
		      //System.out.println(balles.toString());
					for(int i=0;i<grille.ligne;i++){
						 for(int j=0;j<grille.colonne;j++) {
							 if(grille.matrice[i][j].etatPrecedant==0){
								 gui.addGraphicalElement(new Rectangle(grille.matrice[i][j].y,grille.matrice[i][j].x, Color.WHITE, Color.WHITE, 10,10));
							 }
							 else if(grille.matrice[i][j].etatPrecedant==1) {
								 gui.addGraphicalElement(new Rectangle(grille.matrice[i][j].y,grille.matrice[i][j].x,Color.decode("#C0C0C0") ,Color.decode("#C0C0C0") , 10,10));
							 }
							 else if(grille.matrice[i][j].etatPrecedant==2) {
								 gui.addGraphicalElement(new Rectangle(grille.matrice[i][j].y,grille.matrice[i][j].x,Color.decode("#696969") ,Color.decode("#696969") , 10,10));
							 }
							 else if(grille.matrice[i][j].etatPrecedant==3) {
								 gui.addGraphicalElement(new Rectangle(grille.matrice[i][j].y,grille.matrice[i][j].x, Color.BLACK, Color.BLACK, 10,10));
							 }
						 }
					 }
		    }
	  }
