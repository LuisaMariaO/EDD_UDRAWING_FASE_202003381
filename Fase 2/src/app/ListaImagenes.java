
package app;

/**
 *
 * @author Luisa María Ortiz
 */
public class ListaImagenes {
  //Lista simple enlazada
    
    Imagen primero;
    int size;
    
    public ListaImagenes(){
        this.primero=null;
        this.size=0;
    }
    
    public void insertarFinal(Imagen nuevo){

        Imagen actual = this.primero;
        if(this.estaVacia()){
            this.primero=nuevo;
                    }
        else{
          while(actual.siguiente!=null){
              actual=actual.siguiente;
          }
          actual.siguiente=nuevo;
          
        }
        
        this.size++;

    }
    
    public boolean estaVacia(){
        return this.primero==null && this.size==0;
        
    }
    
   
    
  
        
        
    
    
}
