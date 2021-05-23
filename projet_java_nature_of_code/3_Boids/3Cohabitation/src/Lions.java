//Équipe 58 en Teide
import java.util.LinkedList;
import java.util.Random;
import java.awt.Color;
public class Lions extends Boids{
String nom;
    public Lions(int nb_boid,int size,Color couleur) {
        super(nb_boid,size,couleur);
        nom="Lions";
    }

@Override
  public void behaviour(Vecteur b,Vecteur target,LinkedList<Vecteur> boids){
    //(LinkedList<Vecteur> boids,int maxforce,int cover_distance)
    b.seperate(this.boids,30,200);
    b.cohesion(this.boids,0,0);
    b.align(this.boids,0,0);

  }

  @Override
  public String toString(){
    String s=nom;
    return s;
  }
}
