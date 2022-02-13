
package eddfase1;

/**
 *
 * @author Luisa María Ortiz
 */
public class Cliente {
String titulo, id, nombre;
int img_color, img_bw, pasos;

Cliente siguiente;

public Cliente(String titulo, String id, String nombre, int img_color, int img_bw){
    this.titulo=titulo;
    this.id=id;
    this.nombre=nombre;
    this.img_color=img_color;
    this.img_bw=img_bw;
    //Contador de pasos
    this.pasos=0;
    //Apuntador
    this.siguiente=null;
}
    
}
