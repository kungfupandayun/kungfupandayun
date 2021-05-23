/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author pojia
 */
public class NoTronçonException extends Exception{
    public NoTronçonException(){
        super("Trajet sera abandonné parce que aucune Tronçon était proposé!");
    }
    
}
