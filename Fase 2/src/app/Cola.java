
package app;

/**
 *
 * @author Luisa María Ortiz
 * @param <E>
 */
public class Cola <E extends Comparable <E>> {
    Capa<E> primero;
    public int size;

public Cola(){
    this.primero=null;
}    
public void enqueque(Capa<E> nuevo){
    nuevo.siguiente=null;
    if(this.primero==null){
        this.primero=nuevo;
    
    }
    else{
      Capa actual=this.primero;
      while(actual.siguiente!=null){
          actual=actual.siguiente;
      }
      actual.siguiente=nuevo;
      
      
    }
    this.size++;
    
}

public Capa dequeque(){
    if(this.primero!=null){
        Capa saliente = this.primero;
        this.primero=this.primero.siguiente;
        saliente.siguiente=null;
        this.size--;
        return saliente;
        
    }
    else{return null;}
    
}
    
    
}
