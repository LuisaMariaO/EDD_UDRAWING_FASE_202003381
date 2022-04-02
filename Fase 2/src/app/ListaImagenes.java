
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
    
    public void elminar(int id){
        Imagen actual = this.primero;
        if(this.primero.id == id){
            if(this.size>1){
            this.primero=this.primero.siguiente;
            }
            else{
                this.primero=null;
            }
            this.size--;
        }
        else{
            while(actual!=null){
                if(actual.siguiente!=null){
                     if(actual.siguiente.id==id){
                     if(actual.siguiente.siguiente!=null){    
                    actual.siguiente=actual.siguiente.siguiente;
                     }
                     else{
                         actual.siguiente=null;
                     }
                     this.size--;
                    break;
                }
                }
               
                actual=actual.siguiente;
            }
        }
        
    }
    
   
    
  
        
        
    
    
}
