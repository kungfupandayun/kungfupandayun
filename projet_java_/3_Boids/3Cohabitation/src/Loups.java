//Équipe 58 en Teide
import java.util.LinkedList;
import java.util.Random;
import java.awt.Color;
public class Loups extends Boids{
String nom;
    public Loups(int nb_boid,int size,Color couleur) {
        super(nb_boid,size,couleur);
        nom="loups";
    }

@Override
  public void behaviour(Vecteur b,Vecteur target,LinkedList<Vecteur> boids){
    //paramètre pour les 3 rules====>>>>(LinkedList<Vecteur> boids,int maxforce,int cover_distance)
    b.seperate(this.boids,10,30);
    b.cohesion(this.boids,5,50);
    b.align(this.boids,5,50);

  }

  @Override
  public String toString(){
    String s=nom;
    return s;
  }
}
