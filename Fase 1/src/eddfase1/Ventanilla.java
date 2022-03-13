
package eddfase1;

/**
 *
 * @author Luisa María Ortiz
 */

public class Ventanilla {
    String nombre;
    Ventanilla siguiente;
    boolean ocupada, apilable;
    Cliente clienteActual;
    Pila pila;
    
    
    public Ventanilla(String nombre){
        this.nombre=nombre;
        this.siguiente=null;
        //Banderas
        this.ocupada=false;
        this.apilable=false;
        
        this.clienteActual=null;
        this.pila=null; //pila de imagenes recibidas
        
    }
}
