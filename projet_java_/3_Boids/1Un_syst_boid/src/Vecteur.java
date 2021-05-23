//Équipe 58 en Teide
import java.lang.Math;
import java.util.LinkedList;
import java.awt.Color;

//class vecteur inherite le package Point
class Vecteur extends java.awt.Point{
  private int x_ori;
  private int y_ori;
  public int size;
  public Color couleur;
  public Vecteur velocity;
  public Vecteur acceleration;
  int window_sizex=720;
  int window_sizey=725;

  //Il existe 2 constructeurs de Vecteur, un pour chaque individuel,
  //l'autre pour definir la force
  public Vecteur(int x,int y,int size,Color couleur){
    super(x,y);
    x_ori=x;
    y_ori=y;
    this.size=size;
    this.couleur=couleur;

  }

  public Vecteur(int x,int y){
    super(x,y);
  }

  Vecteur original_position(Vecteur p)
  {
    p.x=x_ori;
    p.y=y_ori;
    return p;
  }

  @Override
  public String toString() {
    return "(" + x + "," + y + ")";
  }
  public void add_Vecteur(Vecteur p2){
    this.x+=p2.x;
    this.y+=p2.y;
  }

  //p1 (target - location)
  public Vecteur sub_Vecteur(Vecteur p1,Vecteur p2){
    Vecteur diff=new Vecteur((p1.x-p2.x),(p1.y-p2.y));
    return diff;
  }

  public void div_Vecteur(int factor){
    if(factor!=0){
      this.x=this.x/factor;
      this.y=this.y/factor;
    }
  }

  public void mult_Vecteur(int factor){
    this.x=this.x*factor;
    this.y=this.y*factor;
  }


  // 2 methodes pour calculer le module de distance
  public int module_dist_vect(Vecteur p){
    return (int)Math.sqrt(Math.pow((p.x-this.x),2)+Math.pow((p.y-this.y),2));
  }

  public int module_dist_vect(){
    return (int)Math.sqrt(Math.pow((this.x),2)+Math.pow((this.y),2));
  }

  //ajoute une force dans l'acceleration
  public void add_Force(Vecteur f){
    acceleration.add_Vecteur(f);
  }

  // rebondir quand la balle touche le bord de fenetre
  // public void bounce(){
  //   if(this.x>window_sizex||this.x<0) this.velocity.x=this.velocity.x*-1;
  //   if(this.y>window_sizey||this.y<0) this.velocity.y=this.velocity.y*-1;
  // }


  // definir la limit des forces
    // si la force exceeds le limit, on garde le limit pour la force
  public void limit(int value,Vecteur p){
    if(p.x>0 && p.x>value) p.x=value;
    if(p.x<0 && p.x<-(value)) p.x=-value;
    if(p.y>0 && p.y>value) p.y=value;
    if(p.y<0 && p.y<-(value)) p.y=-value;
  }

  // Seperate 1 individu de son groupe, l'evite de se combiner avec les autres
  public void seperate(LinkedList<Vecteur> boids,int maxforce,int cover_distance){
    Vecteur sum=new Vecteur(0,0);
    int count=0;
    for (Vecteur other : boids){
      int d=this.module_dist_vect(other);
      if((d>0)&&(d<cover_distance)){
        Vecteur diff=sub_Vecteur(other,this);
        sum.add_Vecteur(diff);
        count++;
      }
    }
    if(count>0){
      sum.div_Vecteur(count);
      Vecteur steer=new Vecteur(velocity.x-sum.x,velocity.y-sum.y);
      steer.limit(maxforce,steer);
      this.add_Force(steer);
    }
  }

  public void seek(Vecteur target,int maxforce){
    Vecteur desire=sub_Vecteur(target,this);
    Vecteur steer=sub_Vecteur(desire,velocity);
    steer.limit(maxforce,steer);
    this.add_Force(steer);
  }

  public void cohesion(LinkedList<Vecteur> boids,int maxforce,int cover_distance){
    Vecteur sum=new Vecteur(0,0);
    int count=0;
    for (Vecteur other : boids){
      int d=this.module_dist_vect(other);
      if((d>0)&&(d<cover_distance)){
        sum.add_Vecteur(other);
        count++;
      }
    }
    if(count>0){
      sum.div_Vecteur(count);
      System.out.println(sum.toString());
      this.seek(sum,maxforce);
    }
  }

  public void align(LinkedList<Vecteur> boids,int maxforce,int cover_distance){
    Vecteur sum=new Vecteur(0,0);
    int count=0;
    for (Vecteur other : boids){
      int d=this.module_dist_vect(other);
      if((d>0)&&(d<cover_distance)){
        sum.add_Vecteur(other.velocity);
        count++;
      }
    }
    if(count>0){
      sum.div_Vecteur(count);
      Vecteur steer=sub_Vecteur(sum,velocity);
      steer.limit(maxforce,steer);
      this.add_Force(steer);
    }
  }

}
