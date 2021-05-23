//Équipe 58 en Teide
import java.util.LinkedList;
import java.util.Random;
import java.awt.Color;

public class Boids{

  public LinkedList<Vecteur> boids;
  static int window_sizex=720;
  static int window_sizey=725;
  static private int nb;
  private int size;
  private int maxspeed=50;
  public Color couleur;



  Random random=new Random();


  public Boids(int nb_boid,int size,java.awt.Color couleur) {
      boids = new LinkedList<Vecteur>();
      this.nb=nb_boid;
      this.size=size;
      this.couleur=couleur;
      while(nb_boid>0){
        ajoutePoint();
        nb_boid--;
      }

  }

  private void ajoutePoint() {
      Vecteur p=new Vecteur(random.nextInt(window_sizex),random.nextInt(window_sizey), size,couleur);
      boids.add(p);
      p.velocity=new Vecteur(random.nextInt(30)-15,random.nextInt(30)-15);
      p.acceleration=new Vecteur(0,0);
  }



Vecteur target=new Vecteur(window_sizex/2,window_sizey/2);

public void behaviour(Vecteur b,Vecteur target,LinkedList<Vecteur> boids){
  //paramètre pour les 3 rules====>>>>(LinkedList<Vecteur> boids,int maxforce,int cover_distance)
  b.seperate(boids,10,20);
  b.cohesion(boids,7,50);
  b.align(boids,7,50);

}


  public void translate() {
    for (Vecteur b : boids){
      behaviour(b,target,boids);
      b.velocity.add_Vecteur(b.acceleration);
      b.velocity.limit(maxspeed,b.velocity);
      b.translate(b.velocity.x,b.velocity.y);
      if(b.x>window_sizex) b.x=0;
      if(b.y>window_sizey) b.y=0;
      if(b.x<0) b.x=window_sizex;
      if(b.y<0) b.y=window_sizey;
      b.acceleration.mult_Vecteur(0);
    }
  }



  public void reInit(){
    for (Vecteur b : boids)
    b.setLocation(b.original_position(b));
  }


  @Override
  public String toString(){
    String allballs="voici les points:\n";
    for (Vecteur b : boids)
    {
      allballs+="(";
      allballs+=b.toString();
      allballs+=")";
      allballs+="\n";
    }
    return allballs;
  }
}
