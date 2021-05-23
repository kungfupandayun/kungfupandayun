//Équipe 58 en Teide
import java.util.LinkedList;
public class Grille {
	  static private int window_sizex;
	  static private int window_sizey;
	  public int ligne;
	  public int colonne ;
	  public Cellule matrice[][];
    public LinkedList<Cellule> vacantes;

	  //constructeur
	  public Grille(int window_sizex,int window_sizey) {
		  this.window_sizex=window_sizex;
	      this.window_sizey=window_sizey;
	      this.ligne=window_sizey/10 ;//nombre de lignes
	      this.colonne=window_sizex/10 ;//nombre de colonnes
	      this.matrice=new Cellule[ligne][colonne];
        this.vacantes = new LinkedList<Cellule>();
	  }

	  //construction de la grille de cellule vacantes
	  public void intialiserCellulesVacantes() {
		  for(int i=0;i<ligne;i++) {
			  for(int j=0;j<colonne;j++) {
				  this.matrice[i][j] = new Cellule (i,j,0);
			  }
		  }
	  }

    //ranger toutes les cellules vacantes dans une liste "vacantes"
    public void ajoutCellulesVacantes(){
      for(int i=2;i<ligne-2;i++) {
        for(int j=2;j<colonne-2;j++){
          if(matrice[i][j].testcelluleVacante()) vacantes.add(matrice[i][j]);
        }
      }
    }

    public void mémoriserGrille() {
      for(int i=2;i<ligne-2;i++) {
        for(int j=2;j<colonne-2;j++) {
          matrice[i][j].memoriser();
        }
      }
    }

  public void changerGrille(int K) {
		ajoutCellulesVacantes();
    for(int i=2;i<ligne-2;i++) {
      for(int j=2;j<colonne-2;j++) {
        int nb=0;
        Cellule c=matrice[i][j];
				if(!c.testcelluleVacante()){
					nb = c.testCouleur(matrice[c.px-1][c.py-1].couleurPrecedante)+c.testCouleur(matrice[c.px-1][c.py].couleurPrecedante)+c.testCouleur(matrice[c.px-1][c.py+1].couleurPrecedante)
					+c.testCouleur(matrice[c.px][c.py-1].couleurPrecedante)+c.testCouleur(matrice[c.px][c.py+1].couleurPrecedante)
					+c.testCouleur(matrice[c.px+1][c.py-1].couleurPrecedante)+c.testCouleur(matrice[c.px+1][c.py].couleurPrecedante)+c.testCouleur(matrice[c.px+1][c.py+1].couleurPrecedante);
					if(nb>K){
	                    //déménagement de la cellule
						if(vacantes.size()!=0){
						 vacantes.get(0).setCouleur(c.couleurPrecedante);
						 vacantes.remove(0);
						 //la position ancienne devient vacante
						 c.setCouleur(0);
						 this.mémoriserGrille();
						}
	        }
				}


      }
    }

	}
    public void reInit () {
      intialiserCellulesVacantes();
    }
  }
