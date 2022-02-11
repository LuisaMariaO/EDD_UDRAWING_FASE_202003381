
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

public void enqueque(Cliente nuevo){
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

public void dequeque(){
    if(this.size>0){
        this.primero=this.primero.siguiente;
    }
}

    
}
