
package app;

/**
 *
 * @author Luisa María Ortiz

 */
public class Cliente{
    String nombre, password; //Atributos
    long dpi; //Clave para ordenar
    //Apuntadores
    Cliente anterior,siguiente; 
    Pagina izquierda, derecha;
    
    public Cliente(String nombre, String password, long dpi){
        this.nombre=nombre;
        this.password=password;
        this.dpi=dpi;
        
       this.anterior = this.siguiente = null;
       this.izquierda = this.derecha = null;
    }
    
    public Cliente(String nombre, String password, long dpi, Pagina izquierda, Pagina derecha){
        this.nombre=nombre;
        this.password=password;
        this.dpi=dpi;
        
        this.anterior = this.siguiente = null;
        this.izquierda=izquierda;
        this.derecha=derecha;
    }
    
}
