
package eddfase1;

/**
 *
 * @author Luisa María Ortiz
 */
public class Cliente {
String titulo, id, nombre;
int img_color, img_bw, pasos, color_res, bw_res;
Imagen img_impresa;
Cliente siguiente, anterior;
boolean atendido;//bandera
Ventanilla ventanilla;
public Cliente(String titulo, String id, String nombre, int img_color, int img_bw){
    this.titulo=titulo;
    this.id=id;
    this.nombre=nombre;
    this.img_color=img_color;
    this.img_bw=img_bw;
    //Contadores de imagenes mientras se van entregando
    this.color_res=img_color;
    this.bw_res=img_bw;
    //Contador de pasos
    this.pasos=0;
    //Apuntador
    this.siguiente=null;
    
    //Imagenes impresas por el cliente
    this.img_impresa=null;
    this.atendido=false;
    this.ventanilla=null;
    
    //Solo para su uso en la lista doble circular
    this.anterior=null;
    
}
    
}
