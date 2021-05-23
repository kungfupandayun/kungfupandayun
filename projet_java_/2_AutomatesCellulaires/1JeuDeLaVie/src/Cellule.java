//Équipe 58 en Teide
public class Cellule {
	public int x,y;
	public int px ,py;
	public int taille;
	public boolean etatCellule, etatPrecedant ; //etat de la cellule à t et à t+1
	//constructeur de la cellule , au début du jeu , elle est morte
	public Cellule(int px ,int py) {
		this.etatCellule=false;
		this.etatPrecedant=false;
		this.taille=10;
		this.px = px;
		this.py= py;
    this.x= this.px*10;
    this.y=this.py*10;
	}

	//examiner l'état d'une cellule
	public int etat () {
		if(etatPrecedant==true) return 1; //cellule vivante
		else return 0; //cellule morte
	}

	//déclarer la cellule comme vivante
	public void celluleVivante() {
		this.etatCellule =true;
	}

	//memoriser l'état précédant de la cellule à t
	public void memoriser() {
		etatPrecedant = etatCellule;
	}

	//changement des états des cellules à t+1
	public void changer(int nbrVoisins) {
		if(this.etatPrecedant == false) { //la cellule est morte
			if (nbrVoisins==3) this.etatCellule=true; //la cellule renaît
		}
		else { //la cellule est vivante
			if (nbrVoisins==2||nbrVoisins==3)   this.etatCellule=this.etatPrecedant; //la cellule reste vivante
			else this.etatCellule=false; //la cellule meurt
		}
	}



}
