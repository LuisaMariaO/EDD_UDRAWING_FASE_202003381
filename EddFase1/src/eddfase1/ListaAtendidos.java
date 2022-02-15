
package eddfase1;

/**
 *
 * @author Luisa María Ortiz
 */
public class ListaAtendidos {
    Cliente primero;
    int size;
    
    public ListaAtendidos(){
        this.primero=null;
        this.size=0;
    }
    
    public void insertarFinal(Cliente nuevo){

        Cliente actual = this.primero;
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
    
    public void imprimir(){
        Cliente actual = this.primero;
        
        while(actual!=null){
            System.out.println(actual.titulo);
            actual=actual.siguiente;
        }
    }
    
    
    
}
