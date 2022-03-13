
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
