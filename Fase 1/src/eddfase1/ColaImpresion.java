
package eddfase1;

/**
 *
 * @author Luisa María Ortiz
 */
public class ColaImpresion {
    Imagen primero;
    int size;
    
    public ColaImpresion(){
        this.primero=null;
        this.size=0;
    }
    
    public void enqueque(Imagen nueva){

    if(this.primero==null && this.size==0){
        this.primero=nueva;
        
    }
    else{
      Imagen actual=this.primero;
      while(actual.siguiente!=null){
          actual=actual.siguiente;
      }
      actual.siguiente=nueva;
      
    }
    
   this.size++; 
    
}

public Imagen dequeque(){
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

public Imagen verPrimero(){
        return this.primero;
}
    
}
