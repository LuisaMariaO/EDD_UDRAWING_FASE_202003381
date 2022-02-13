
package eddfase1;

/**
 *
 * @author Luisa María Ortiz
 */
public class ColaImpresion {
    Impresora primero;
    int size;
    
    public ColaImpresion(){
        this.primero=null;
        this.size=0;
    }
    
    public void push(String impresora){
    Impresora nueva = new Impresora(impresora);
    if(this.primero==null && this.size==0){
        this.primero=nueva;
        
    }
    else{
        nueva.siguiente=this.primero;
        this.primero=nueva;
      
    }
    
   this.size++; 
    
}

public void pop(){
    if(this.size>0){
        this.primero=this.primero.siguiente;
    }
}
public void imprimir(){
    Impresora actual = this.primero;
    
    while(actual!=null){
        System.out.println(actual.nombre);
        actual=actual.siguiente;
    }
}
    
}
