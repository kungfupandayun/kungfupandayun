//Équipe 58 en Teide
import java.awt.Color;
import gui.Rectangle;
import gui.GUISimulator ;
public class GrilleEvent extends Event {
  public Grille grille;
  public GUISimulator gui;
  public int K ;
  public GrilleEvent(long date, Grille grille ,GUISimulator gui){
    super(date);
    this.grille=grille;
    this.gui = gui;
  }
  public void execute(){
    gui.reset();
     for(int i=2;i<grille.ligne-2;i++){
        for(int j=2;j<grille.colonne-2;j++) {
          if(grille.matrice[i][j].couleurPrecedante==0){
            gui.addGraphicalElement(new Rectangle(grille.matrice[i][j].y,grille.matrice[i][j].x, Color.BLACK, Color.BLACK, 10,10));
          }
          else if(grille.matrice[i][j].couleurPrecedante==1){
            gui.addGraphicalElement(new Rectangle(grille.matrice[i][j].y,grille.matrice[i][j].x, Color.GREEN, Color.GREEN, 10,10));
          }
          else if(grille.matrice[i][j].couleurPrecedante==2){
            gui.addGraphicalElement(new Rectangle(grille.matrice[i][j].y,grille.matrice[i][j].x, Color.RED, Color.RED, 10,10));
          }
          else if(grille.matrice[i][j].couleurPrecedante==3){
            gui.addGraphicalElement(new Rectangle(grille.matrice[i][j].y,grille.matrice[i][j].x, Color.WHITE, Color.WHITE, 10,10));
          }
        }
      }

     //fixer le seuil K
     K=2;
     //changer la grille
     grille.changerGrille(K);

   }
}
