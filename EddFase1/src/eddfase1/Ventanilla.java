
package eddfase1;

/**
 *
 * @author Luisa María Ortiz
 */

public class Ventanilla {
    String nombre;
    Ventanilla siguiente;
    
    public Ventanilla(String nombre){
        this.nombre=nombre;
        this.siguiente=null;
    }
}
