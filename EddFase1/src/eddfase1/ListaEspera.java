
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
        this.size++;
    }

    public void imprimir(){
        Cliente actual=this.lc;
        if(actual!=null){
        do{
            System.out.println(actual.titulo);
            actual=actual.siguiente;
        }while(actual!=this.lc);
        }
    }
    public void entregarImagen(Imagen imagen){
        Cliente actual=this.lc;
        imagen.siguiente=null;//Elimino el puntero que tenía en la cola de impresión
        do{
            if(actual.id.equals(imagen.id_cliente)){
                if(actual.img_impresa==null){
                actual.img_impresa=imagen;
                }
                else{
                    Imagen aux = actual.img_impresa;
                    while(aux.siguiente!=null){
                        aux=aux.siguiente;
                    }
                    aux.siguiente=imagen;//Imagenes apuntadas entre sí que a pertenecen a un cliente 
                            
                }
                actual.atendido=true;
                
                actual.img_contador++;
                break;
            }
            actual=actual.siguiente;
        }while(actual!=this.lc);
        
    }
    public void sacar(Cliente cliente){
        if(cliente==this.lc){
                if(this.size==1){//En caso de que solo haya un cliente en espera
                    this.lc=null;
                }
                else{
            lc.siguiente.anterior=lc.anterior;
            lc.anterior.siguiente=lc.siguiente;
            this.lc=lc.siguiente;
                }

        }
        else{
            cliente.anterior.siguiente=cliente.siguiente;
            cliente.siguiente.anterior=cliente.anterior;
        }
        this.size--;
    }
}
