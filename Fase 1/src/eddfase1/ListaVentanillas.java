
package eddfase1;

/**
 *
 * @author Luisa María Ortiz
 */
public class ListaVentanillas {
 Ventanilla primero;
 int size;
 
public ListaVentanillas(){
    this.primero=null;
    this.size=0;
    
}

public void insertar(String nombre){
    Ventanilla nueva = new Ventanilla(nombre);
    if(this.primero==null && this.size==0){
        this.primero=nueva;
        
    }
    else{
      Ventanilla actual=this.primero;
      while(actual.siguiente!=null){
          actual=actual.siguiente;
      }
      actual.siguiente=nueva;
      
    }
    
   this.size++; 
    
}

public void imprimir(){
    Ventanilla actual = this.primero;
    
    while(actual!=null){
        System.out.println(actual.nombre);
        actual=actual.siguiente;
    }
}


}
