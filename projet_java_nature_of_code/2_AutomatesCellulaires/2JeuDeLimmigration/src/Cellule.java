//Équipe 58 en Teide
public class Cellule {
	public int x,y;
	public int px ,py;
	public int taille;
	public int etatCellule, etatPrecedant ; //etat de la cellule à t et à t+1
  private int nbrEtats;
	//constructeur de la cellule , au début du jeu ,la cellule est à l'état 0
	public Cellule(int px ,int py, int nbrEtats) {
		this.etatCellule=0;
		this.etatPrecedant=0;
		this.taille=10;
		this.px = px;
		this.py= py;
    this.x= this.px*10;
    this.y=this.py*10;
    this.nbrEtats=nbrEtats;
	}
  //tester si une etatcellule passé en paramètre est l'état suivant de la Cellule
	public int etat (int etatcellule) {
		if((etatPrecedant+1)%nbrEtats==etatcellule) return 1;
    else return 0;
 	}
  //memoriser l'état précédant de la cellule à t
	public void memoriser() {
		etatPrecedant = etatCellule;
	}

  //changement des états des cellules à t+1
  //nbrVoisins est le nombre de voisins à l'état k+1
	public void changer(int nbrVoisins) {
    if(nbrVoisins>=3){
      etatCellule = (etatCellule +1)%nbrEtats;
    }
  }
}
