package Exceptions;


public class NoSoldeException extends Exception  {
    
    public NoSoldeException(){
        super("Vous n'avez pas assez de solde !");
    }
    
}