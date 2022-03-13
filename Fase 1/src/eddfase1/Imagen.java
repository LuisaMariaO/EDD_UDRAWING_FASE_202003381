
package eddfase1;

/**
 *
 * @author Luisa María Ortiz
 */
public class Imagen {
    Imagen siguiente;
    String tipo, nombre, id_cliente, cliente;
    int pasos;
    boolean imprimible;
    
    public Imagen(String tipo, String nombre, String id_cliente, String cliente){
        this.tipo=tipo;
        this.nombre=nombre;
        this.id_cliente=id_cliente;
        this.cliente=cliente;
        //Apuntador
        this.siguiente=null;
        
        //Contador de los pasos que lleva en impresion
        this.pasos=0;
        
        //Bandera para evitar que se imprima en el mismo paso que se recibe
        this.imprimible=false;
        
    }
    
}
