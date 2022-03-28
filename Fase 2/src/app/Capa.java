
package app;

/**
 *
 * @author Luisa María Ortiz
 * @param <E>
 */
public class Capa <E> {
    public Matriz matriz;
    public E id;
    Capa <E> izquierdo, derecho;
    public Capa (E id,Matriz matriz){
       this.id=id;
       this.matriz=matriz;
       this.izquierdo=this.derecho=null;
       
    }
    
}
