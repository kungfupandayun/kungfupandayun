//Équipe 58 en Teide
import java.awt.Color;
import gui.Rectangle;
import gui.GUISimulator ;
public class GrilleEvent extends Event {
  public Grille grille;
  public GUISimulator gui;
  public GrilleEvent(long date, Grille grille ,GUISimulator gui){
    super(date);
    this.grille=grille;
    this.gui = gui;
  }
  public void execute(){
    gui.reset();

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
     //changer la grille
     grille.changerGrille();
     //mémoriser l'état à t
     grille.mémoriserGrille();
}
}
