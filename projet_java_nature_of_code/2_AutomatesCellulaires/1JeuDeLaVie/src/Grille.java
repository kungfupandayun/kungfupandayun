//Équipe 58 en Teide
import java.util.Random;

public class Grille {
	  static private int window_sizex;
	  static private int window_sizey;
	  public int ligne;
	  public int colonne ;
	  public Cellule matrice[][];

	  //constructeur
	  public Grille(int window_sizex,int window_sizey) {
		  this.window_sizex=window_sizex;
	      this.window_sizey=window_sizey;
	      this.ligne=window_sizey/10 ;//nombre de lignes
	      this.colonne=window_sizex/10 ;//nombre de colonnes
	      this.matrice=new Cellule[ligne][colonne];
	  }

	  //construction de la grille
	  public void ajoutCellules() {
		  for(int i=0;i<ligne;i++) {
			  for(int j=0;j<colonne;j++) {
				  this.matrice[i][j] = new Cellule (i,j);
			  }
		  }
	  }

	  public void mémoriserGrille() {
		  for(int i=0;i<ligne;i++) {
			  for(int j=0;j<colonne;j++) {
				      matrice[i][j].memoriser();
			  }
		  }
	  }

	  public void changerGrille() {
		  for(int i=1;i<ligne-2;i++) {
			  for(int j=1;j<colonne-2;j++) {
					//compterVoisins(matrice[i][j])
					int nb;
					Cellule c=matrice[i][j];
					nb = matrice[c.px-1][c.py-1].etat()+matrice[c.px-1][c.py].etat()+matrice[c.px-1][c.py+1].etat()
					+matrice[c.px][c.py-1].etat()+matrice[c.px][c.py+1].etat()
					+matrice[c.px+1][c.py-1].etat()+matrice[c.px+1][c.py].etat()+matrice[c.px+1][c.py+1].etat();
				      matrice[i][j].changer(nb);
			  }
		  }
	  }

	  public void reInit () {
		  this.ajoutCellules();
			Random random = new Random();
			for(int i=2; i<50-2 ; i++){
				for(int j=2 ; j<50-2 ; j++){
					int randomInt = random.nextInt(2);
					if(randomInt==0) this.matrice[i][j].etatCellule=false;
					else this.matrice[i][j].etatCellule=true ;
				}
			}
	  }

}
