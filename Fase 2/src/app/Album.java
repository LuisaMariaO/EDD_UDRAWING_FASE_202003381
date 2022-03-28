
package app;


/**
 *
 * @author Luisa María Ortiz
 */
public class Album {
    Album anterior,siguiente;
    String nombre;
    ListaImagenes imagenes;
    
    public Album(String nombre){
        this.anterior=this.siguiente=null;
        this.nombre=nombre;
        this.imagenes=new ListaImagenes();//Lista de imagenes
    }
    
}
