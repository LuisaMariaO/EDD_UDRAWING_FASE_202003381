
package app;

/**
 *
 * @author Luisa María Ortiz
 */
public class ListaClientes {
    Cliente primero,ultimo;
    int size; 
    
    public ListaClientes(){
        this.primero = null;
        this.ultimo = null;
        this.size =0;
    }
    
    boolean insertar(Cliente nuevo){//---------------------------insertar un nodo a la lista de una pagina 
        if(this.primero == null){
            this.primero = nuevo;
            this.ultimo = nuevo;
            size ++;
            return true;
        }else{
            if(this.primero == this.ultimo){ // -------------------------solo hay un nodo 
                if(nuevo.dpi < this.primero.dpi){
                    nuevo.siguiente = this.primero;
                    this.primero.anterior = nuevo;
                    this.primero.izquierda = nuevo.derecha; // -----cambia los punteros a las paginas
                    this.primero = nuevo;
                    size++; 
                    return true;
                }else if(nuevo.dpi > this.ultimo.dpi){
                    this.ultimo.siguiente = nuevo;
                    nuevo.anterior = this.ultimo;
                    this.ultimo.derecha = nuevo.izquierda; //------ cambia los punteros a las paginas 
                    this.ultimo = nuevo;
                    size++; 
                    return true;
                }else{
                   //la clave ya existe
                   
                    return false;
                }
            }else{ // ---------------------------------------------------hay mas de un nodo
                if(nuevo.dpi < this.primero.dpi){
                    nuevo.siguiente = this.primero;
                    this.primero.anterior = nuevo;
                    this.primero.izquierda = nuevo.derecha; // -----cambia los punteros a las paginas
                    this.primero = nuevo;
                    size++; 
                    return true;
                }else if(nuevo.dpi > this.ultimo.dpi){
                    this.ultimo.siguiente = nuevo;
                    nuevo.anterior = this.ultimo;
                    this.ultimo.derecha = nuevo.izquierda; //------ cambia los punteros a las paginas 
                    this.ultimo = nuevo;
                    size++; 
                    return true;
                }else{
                    Cliente pivote = this.primero;
                    while(pivote != null){
                        if(nuevo.dpi < pivote.dpi){
                            nuevo.siguiente = pivote;
                            nuevo.anterior = pivote.anterior;
                            //--------------------------- cambia los punteros a las paginas
                            pivote.izquierda = nuevo.derecha;
                            pivote.anterior.derecha = nuevo.izquierda;
                            //-----------------------------------------------------------
                            pivote.anterior.siguiente = nuevo;
                            pivote.anterior = nuevo;
                            size++;
                            return true;
                        }else if(nuevo.dpi == pivote.dpi){
                            //Ya existe ese cliente
                            return false;
                        }else{
                            pivote = pivote.siguiente;
                        }
                    }
                }
            }
        }
        return false;
    }
}
