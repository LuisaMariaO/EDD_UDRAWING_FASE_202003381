
package eddfase1;

/**
 *
 * @author Luisa María Ortiz
 */
public class Imagen {
    Imagen siguiente;
    String tipo, nombre, id_cliente;
    
    
    public Imagen(String tipo, String nombre, String id_cliente){
        this.tipo=tipo;
        this.nombre=nombre;
        this.id_cliente=id_cliente;
        //Apuntador
        this.siguiente=null;
        
    }
    
}
