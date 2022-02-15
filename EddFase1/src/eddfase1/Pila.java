
package eddfase1;

/**
 *
 * @author Luisa María Ortiz
 */
public class Pila {
    
Imagen primero;
int size;

public Pila(){
    this.primero=null;
    this.size=0;
}

public void push(String tipo, String nombre, String id_cliente, String cliente){//Inserta al inicio
    Imagen nuevo = new Imagen(tipo, nombre, id_cliente, cliente);
    if(this.primero==null && this.size==0){
        this.primero=nuevo;
        
    }
    else{
        
        nuevo.siguiente=this.primero;
        this.primero=nuevo;
    }
    
   this.size++; 
    
}

public Imagen pop(){
    if(this.size>0){
        Imagen saliente = this.primero;
        this.primero=this.primero.siguiente;
        this.size--;
        return saliente;
    }
    else{return null;}
}
public void imprimir(){
    Imagen actual = this.primero;
    
    while(actual!=null){
        System.out.println(actual.nombre);
        actual=actual.siguiente;
    }
}

    
}
