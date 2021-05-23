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
        for(int i=1;i<grille.ligne;i++){
            for(int j=1;j<grille.colonne;j++) {
                gui.addGraphicalElement(new Rectangle(grille.matrice[i][j].x,grille.matrice[i][j].y, Color.WHITE, Color.WHITE, 10,10));
            }
        }
	  }
}
