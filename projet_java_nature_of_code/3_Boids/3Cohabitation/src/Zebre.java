//Équipe 58 en Teide
import java.util.LinkedList;
import java.util.Random;
import java.awt.Color;
public class Zebre extends Boids{
String nom;
    public Zebre(int nb_boid,int size,Color couleur) {
        super(nb_boid,size,couleur);
        nom="zebre";
    }

@Override
  public void behaviour(Vecteur b,Vecteur target,LinkedList<Vecteur> boids){
    //paramètre pour les 3 rules====>>>>(LinkedList<Vecteur> boids,int maxforce,int cover_distance)
    b.seperate(this.boids,10,20);
    b.cohesion(this.boids,9,30);
    b.align(this.boids,9,30);

  }

  @Override
  public String toString(){
    String s=nom;
    return s;
  }
}
