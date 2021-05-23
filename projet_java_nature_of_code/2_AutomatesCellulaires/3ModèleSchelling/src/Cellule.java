//Équipe 58 en Teide
import java.util.LinkedList;

public class Cellule {
	public int x,y;
	public int px ,py;
	public int taille;
  public int couleurCellule , couleurPrecedante;
	//constructeur de la cellule , au début du jeu , elle est vacante
	public Cellule(int px ,int py, int couleur) {
		this.taille=10;
		this.px = px;
		this.py= py;
    this.x= this.px*10;
    this.y=this.py*10;
    this.couleurCellule=couleur;
	}

  public void setCouleur(int couleur){
    this.couleurCellule=couleur;
  }
  //tester si la cellule est vacante
  	public boolean testcelluleVacante() {
  		if(couleurPrecedante==0) return true;
      else return false;
  	}
  //memoriser l'état précédant de la cellule à t
	public void memoriser() {
		couleurPrecedante= couleurCellule;
	}
  //tester si la couleur en paramètre est différente de la couleur de ma cellules
  public int testCouleur(int couleur){
    if(couleurPrecedante!=couleur&&couleur!=0) return 1 ;
    else return 0;
  }

}
