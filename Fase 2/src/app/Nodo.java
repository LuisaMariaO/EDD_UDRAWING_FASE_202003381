
package app;

/**
 *
 * @author Luisa María Ortiz
 */
public class Nodo {
    int i,j;
    Nodo arriba, abajo, anterior, siguiente;
    String color;
    
    public Nodo(int i, int j, String color){
        this.arriba = this.abajo = this.anterior = this.siguiente = null;
        this.i=i;
        this.j=j;
        this.color=color;
    }
    
}
