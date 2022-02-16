
package eddfase1;

/**
 *
 * @author Luisa María Ortiz
 */
public class Cola {
Cliente primero;
int size;

public Cola(){
    this.primero=null;
    this.size=0;
}

public void enqueque(String titulo, String id, String nombre, int img_color, int img_bw){
    Cliente nuevo = new Cliente(titulo, id, nombre, img_color, img_bw);
    if(this.primero==null && this.size==0){
        this.primero=nuevo;
        
    }
    else{
      Cliente actual=this.primero;
      while(actual.siguiente!=null){
          actual=actual.siguiente;
      }
      actual.siguiente=nuevo;
      
    }
    
   this.size++; 
    
}

public Cliente dequeque(){
    if(this.size>0){
        Cliente saliente = this.primero;
        this.primero=this.primero.siguiente;
        this.size--;
        return saliente;
        
    }
    else{return null;}
    
}
public void imprimir(){
    Cliente actual = this.primero;
    
    while(actual!=null){
        System.out.println(actual.nombre);
        actual=actual.siguiente;
    }
}

public void cuentaPaso(){//Cuenta pasos de los clientes que están en la cola de recepción
    Cliente actual = this.primero;
    while(actual!=null){
        actual.pasos++;
        actual=actual.siguiente;
    }
}
    
}
