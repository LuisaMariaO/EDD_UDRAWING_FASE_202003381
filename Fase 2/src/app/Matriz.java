
package app;

/**
 *
 * @author Luisa María Ortiz
 */
public class Matriz {
    Nodo raiz;
    boolean iR,jR;
    
    public Matriz(){
      this.raiz = new Nodo(-1,-1,"Raiz");
      //Banderas para nodos repetidos
      this.iR=this.jR=false;
    }
    
    public Nodo insertarEnFila(Nodo nuevo, Nodo cabeceraFila){
        Nodo actual = cabeceraFila;
        boolean mayorEncontrado = false;
        
        while(true){
            if(actual.i > nuevo.i){
                mayorEncontrado=true;
                break;
            }
            else if(actual.siguiente != null){
                actual = actual.siguiente;
            }
            else{
                break;
            }
        }
        
        if(mayorEncontrado){
            nuevo.siguiente=actual;
            nuevo.anterior = actual.anterior;
            actual.anterior.siguiente=nuevo;
            actual.anterior=nuevo;
        }
        else{
            actual.siguiente=nuevo;//Se inserta antes que el nodo actual
            nuevo.anterior=actual;
        }
        return nuevo;
    }
    
    public Nodo insertarEnColumna(Nodo nuevo, Nodo cabeceraColumna){
        Nodo actual = cabeceraColumna;
        boolean mayorEncontrado = false;
        
        while(true){
          if(actual.j > nuevo.j){
              mayorEncontrado = true;
              break;
          }
          else if(actual.abajo != null){
              actual = actual.abajo;
          }
          else{
              break;
          }
        }
        if(mayorEncontrado){
            nuevo.abajo=actual;
            nuevo.arriba = actual.arriba;
            actual.arriba.abajo=nuevo;
            actual.arriba=nuevo;
        }
        else{
            actual.abajo=nuevo;
            nuevo.arriba=actual;
        }
        return nuevo;
    }
    
    public Nodo buscarColumna(int i){
        Nodo actual=this.raiz;
        
        while(actual!=null){
            if(actual.i == i){
                return actual;
            }
           actual=actual.siguiente;
        }
        return null;
    }
    
    public Nodo buscarFila(int j){
        Nodo actual = this.raiz;
        while(actual!=null){
            if(actual.j ==  j){
                return actual;
            }
            actual=actual.abajo;
        }
        return null;
    }
    
    public Nodo crearColumna(int i){
        return this.insertarEnFila(new Nodo(i,-1,"Col"),this.raiz);
    }
    
    public Nodo crearFila(int j){
        return this.insertarEnColumna(new Nodo (-1,j,"Row"),this.raiz);
    }
    
    public Nodo nodoExistente(int i, int j){
        this.iR=false;
        this.jR=false;
        Nodo actual = this.raiz;
        while(true){ //Busco esa posicion en j
          if(actual.j == j){
              this.jR=true;
              break;
          }
         
          else if(actual.abajo != null){
              actual = actual.abajo;
          }
          else{
              break;
          }
        }
        
        //Busco la posicion en i
          while(true){ //Busco esa posicion en j
          if(actual.i == j){
              this.iR=true;
              break;
          }
         
          else if(actual.siguiente != null){
              actual = actual.siguiente;
          }
          else{
              break;
          }
        }
         if(this.iR && this.jR){ 
         return actual;
         }
         return null;
    }
    
    public void insertarNodo(int i, int j, String color){
        Nodo nuevo = new Nodo(i,j,color);
        
        
        
        Nodo columna = this.buscarColumna(i);
        Nodo fila = this.buscarFila(j);
        
        //Caso 1: No existe ni la fila ni la columna
        if(columna == null && fila == null){
            columna = this.crearColumna(i);
            fila = this.crearFila(j);
            
            nuevo = this.insertarEnFila(nuevo, fila);
            nuevo = this.insertarEnColumna(nuevo, columna);
            
        }
        else if(columna !=null && fila==null){
            //Caso 2: La columna existe y a fila no
            fila = this.crearFila(j);
            
            nuevo = this.insertarEnFila(nuevo, fila);
            nuevo = this.insertarEnColumna(nuevo, columna);
            
        }
        else if(columna == null && fila !=null){
            //Caso 3: La columna no existe y la fila sí
            columna = this.crearColumna(i);
            
            nuevo = this.insertarEnFila(nuevo, fila);
            nuevo = this.insertarEnColumna(fila, columna);
            
        }
        else{
            //Caso 4: La columna y la fila existen
            //Verifico si ese nodo ya existe
            Nodo encontrado = this.nodoExistente(i, j);
            if(encontrado!=null){
            nuevo = this.insertarEnFila(nuevo, fila);
            nuevo = this.insertarEnColumna(nuevo, columna);
            }
            else{
                //Reemplazo el nodo
               encontrado.anterior.siguiente=nuevo;
               encontrado.siguiente.anterior=nuevo;
               encontrado.arriba.abajo=nuevo;
               encontrado.abajo.arriba=nuevo;
            }
        }
        
    }
    
    //Verificar los nodos repetidos 
}
