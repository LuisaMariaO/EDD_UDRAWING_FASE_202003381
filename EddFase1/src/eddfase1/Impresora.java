/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eddfase1;

/**
 *
 * @author Luisa María Ortiz
 */
public class Impresora {
    Impresora siguiente;
    String nombre;
    //Cola de impresion
    ColaImpresion cola;
  public Impresora(String nombre){
      this.siguiente=null;
      this.nombre=nombre;
      
      this.cola=null;
  }
    
}
