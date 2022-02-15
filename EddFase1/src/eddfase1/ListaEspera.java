
package eddfase1;

/**
 *
 * @author Luisa María Ortiz
 */
public class ListaEspera {
    Cliente lc;
    int size;
 public ListaEspera(){
     this.lc=null;
     this.size=0;
 }
 
 
    public void insertar(Cliente nuevo){
       
        nuevo.siguiente=nuevo;
        nuevo.anterior=nuevo;

        if (this.lc!=null){//Esta lista es circular y doblemente enlazada
            nuevo.siguiente=lc.siguiente;
            lc.anterior=nuevo;
            nuevo.anterior=lc;
            lc.siguiente=nuevo;
        }
        lc=nuevo;
    }

    public void imprimir(){
        //TODO: Validar lista vacia
        Cliente actual=this.lc.siguiente;
        do{
            System.out.println(actual.toString());
            actual=actual.siguiente;
        }while(actual!=this.lc.siguiente);
    }
    public void entregarImagen(Imagen imagen){
        Cliente actual=this.lc.siguiente;
        imagen.siguiente=null;//Elimino el puntero que tenía en la cola de impresión
        do{
            if(actual.id.equals(imagen.id_cliente)){
                if(actual.img_impresa==null){
                actual.img_impresa=imagen;
                }
                else{
                    Imagen aux = actual.img_impresa;
                    while(aux!=null){
                        aux=aux.siguiente;
                    }
                    aux.siguiente=imagen;//Imagenes apuntadas entre sí que a pertenecen a un cliente 
                            
                }
            }
            actual=actual.siguiente;
        }while(actual!=this.lc.siguiente);
        
    }
}
