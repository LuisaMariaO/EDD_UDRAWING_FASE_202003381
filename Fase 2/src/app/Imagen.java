
package app;

/**
 *
 * @author Luisa María Ortiz
 */
public class Imagen {
    int id, peso;
    Imagen izquierda, derecha;
    ArbolBB capas;
    //Punteros para la los albumes
    Imagen siguiente, anterior;
    
    Imagen(int id){
        this.id=id;
        this.peso=1;
        
        this.izquierda=this.derecha=null;
        this.capas=new ArbolBB();
        
        this.siguiente=this.anterior=null;
    }
    
    
}
