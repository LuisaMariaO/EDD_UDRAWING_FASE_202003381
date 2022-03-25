
package app;

/**
 *
 * @author Luisa María Ortiz

 */
public class Cliente{
    public String nombre, password; //Atributos
    public long dpi; //Clave para ordenar
    //Apuntadores
    Cliente anterior,siguiente; 
    Pagina izquierda, derecha;
    public ArbolBB arbolCapas; //Arbol general de capas por usuario
    
    
    
    public Cliente(String nombre, String password, long dpi){
        this.nombre=nombre;
        this.password=password;
        this.dpi=dpi;
        
       this.anterior = this.siguiente = null;
       this.izquierda = this.derecha = null;
       
       this.arbolCapas=new ArbolBB();//Se inicializa su arbol de capas vacío
    }
    
    public Cliente(String nombre, String password, long dpi, Pagina izquierda, Pagina derecha){
        this.nombre=nombre;
        this.password=password;
        this.dpi=dpi;
        
        this.anterior = this.siguiente = null;
        this.izquierda=izquierda;
        this.derecha=derecha;
        
        this.arbolCapas = new ArbolBB();
    }
    
}
