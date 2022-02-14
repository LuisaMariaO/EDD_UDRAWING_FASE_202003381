
package eddfase1;

/**
 *
 * @author Luisa María Ortiz
 */

public class Ventanilla {
    String nombre;
    Ventanilla siguiente;
    boolean ocupada;
    Cliente clienteActual;
    
    
    public Ventanilla(String nombre){
        this.nombre=nombre;
        this.siguiente=null;
        this.ocupada=false;
        this.clienteActual=null;
        
    }
}
