
package app;

/**
 *
 * @author Luisa María Ortiz
 */
public class Imagen {
    int id, peso,no_capas;
    Imagen izquierda, derecha;
    ArbolBB capas;
    //Punteros para la los albumes
    Imagen siguiente, anterior;
    //Punteros para ser ordenadas por el número de capas
    Imagen siguienteo,anterioro;
    
    Imagen(int id){
        this.id=id;
        this.peso=1;
        
        this.izquierda=this.derecha=null;
        this.capas=new ArbolBB();
        
        this.siguiente=this.anterior=null;
        this.no_capas=0;
        
        this.siguienteo=this.anterioro=null;
    }
    
    
}
