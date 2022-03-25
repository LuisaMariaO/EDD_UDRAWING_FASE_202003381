
package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Luisa María Ortiz
 */
public class Matriz {
    Nodo raiz;
    boolean iR,jR;
    String id;
    String nombresNodos, conexiones,rangos,conexiones2;
    
    public Matriz(String id){
      this.raiz = new Nodo(-1,-1,"Raiz");
      //Banderas para nodos repetidos
      this.iR=this.jR=false;
      
      this.id=id;
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
    /*
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
    */
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
            nuevo = this.insertarEnColumna(nuevo, columna);
            
        }
        else{
            //Caso 4: La columna y la fila existen
            nuevo = this.insertarEnFila(nuevo, fila);
            nuevo = this.insertarEnColumna(nuevo, columna);
           
        }
        
    }
    
    //Verificar los nodos repetidos 
    
    public void graficaLogica(String cliente){
         StringBuilder dot = new StringBuilder();
        
        dot.append("digraph G {\n");
        dot.append("node[shape=box style=filled];\nsubgraph cluster_0{\n");
        nombresNodos="";
        conexiones ="";
        conexiones2="";
        rangos="{rank=same;NodoRaiz";
        
       
        
        Nodo auxf = this.raiz;
        Nodo auxc=this.raiz;
        Nodo referencia = this.raiz;
       
        Nodo aux = this.raiz; //Para graficar el resto de nodos
       boolean primero = false; 
       nombresNodos+="NodoRaiz[label=\"Raiz\" fillcolor=lightseagreen]\n";
        while(aux!=null){
            
          //Primero las cabeceras de las filas
        
            if(aux.color.equals("Col") || aux.color.equals("Row")){
               nombresNodos+="Nodo"+aux.hashCode()+"[fillcolor=white label=\""+aux.i+","+aux.j+"\" group=1];\n"; 
            
            if(!primero){
                conexiones2+="NodoRaiz->Nodo"+aux.hashCode()+";\n";
                primero=true;
            }
   
            if(aux.abajo!=null){
                conexiones+="Nodo"+aux.hashCode()+"->Nodo"+aux.abajo.hashCode()+";\n";
                        
            }
           
            }
          
    
       aux=aux.abajo;
        }
        aux=this.raiz;
        primero = false;
        //Ahora las cabeceras de las columnas
        while(aux!=null){
            if(aux.color.equals("Col") || aux.color.equals("Row")){
               nombresNodos+="Nodo"+aux.hashCode()+"[fillcolor=white label=\""+aux.i+","+aux.j+"\" group="+(aux.i+2)+"];\n"; 
               rangos+=";Nodo"+aux.hashCode()+"";
              if(aux.siguiente!=null){
                conexiones+="Nodo"+aux.hashCode()+"->Nodo"+aux.siguiente.hashCode()+"\n";
              }
                if(!primero){
                    conexiones2+="NodoRaiz->Nodo"+aux.hashCode()+";\n";
                    primero=true;
                }        
             
            }
    
            aux=aux.siguiente;
            if(aux==null){rangos+="}\n";}
  
        }
        

        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append(conexiones2);
        dot.append(rangos);
        nombresNodos="";
        conexiones="";
        conexiones2="";
        rangos="{rank=same";
        aux=this.raiz;
        while(aux!=null){
            if(!aux.color.equals("Col") && !aux.color.equals("Row") && !aux.color.equals("Raiz")){
                nombresNodos+="Nodo"+aux.hashCode()+"[label=\"\" fillcolor=\""+aux.color+"\""+ "group="+(aux.i+2)+"];\n";
            }
            if(!aux.color.equals("Raiz")){
                if(!aux.color.equals("Col")){
            if(aux.siguiente!=null){
                conexiones+="Nodo"+aux.hashCode()+"->Nodo"+aux.siguiente.hashCode()+";\n";
                 conexiones+="Nodo"+aux.hashCode()+"->Nodo"+aux.siguiente.hashCode()+"[dir=back];\n";
               
            }
            rangos+=";Nodo"+aux.hashCode();
            }
            if(aux.abajo!=null){
                if(!aux.color.equals("Row")){
                conexiones2+="Nodo"+aux.hashCode()+"->Nodo"+aux.abajo.hashCode()+";\n";
                conexiones2+="Nodo"+aux.hashCode()+"->Nodo"+aux.abajo.hashCode()+"[dir=back];\n";
                }
            }
            
            }
            aux=aux.siguiente;
            
            if(aux==null){
                rangos+="}\n";
                conexiones+=rangos;
                rangos="{rank=same";
                referencia = referencia.abajo;
                aux=referencia;
            }
        }
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append(conexiones2);
        dot.append("}\n}");
         try{
           
            File file = new File("Reportes de Usuario\\"+cliente);
            if(!file.exists()){file.mkdirs();}//Si no existe la carpeta, se crea
            
           
            file = new File("Reportes de Usuario\\"+cliente+"\\capaLogica"+this.id+".dot");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(dot.toString());
            bw.close();
        ProcessBuilder pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", "Reportes de Usuario\\"+cliente+"\\capaLogica"+this.id+".png", "Reportes de Usuario\\"+cliente+"\\capaLogica"+this.id+".dot" );
        pbuilder.redirectErrorStream( true );
        pbuilder.start();
        }catch(Exception ex){System.out.println(ex.getMessage());}

    }
}
