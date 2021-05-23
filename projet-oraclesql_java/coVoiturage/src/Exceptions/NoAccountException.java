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
public class NoAccountException extends Exception  {
    
    public NoAccountException(){
        super("Vous n'êtes pas identifié");
    }
    
}
