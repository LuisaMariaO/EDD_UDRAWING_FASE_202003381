
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
    String nombresNodos, conexiones,rangos,conexiones2,tabla;
    
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

    public Nodo nodoExistente(Nodo nuevo){
        Nodo aux = this.raiz;
        Nodo referencia = this.raiz;
        while(aux!=null){
            if(aux.i==nuevo.i && aux.j==nuevo.j){
                return nuevo;
            }
            aux=aux.siguiente;
            if(aux==null){
                if(referencia.abajo!=null){
                    referencia=referencia.abajo;
                    aux=referencia;
                }
            }
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
            nuevo = this.insertarEnColumna(nuevo, columna);
            
        }
        else{
            //Caso 4: La columna y la fila existen
            
            //Verifico si ya existe el nodo en la matriz
            Nodo buscado = this.nodoExistente(nuevo);
            if(buscado==null){//Si el nodo no existe, lo inserto
            nuevo = this.insertarEnFila(nuevo, fila);
            nuevo = this.insertarEnColumna(nuevo, columna);
            }
            else{
                //Reemplazo los apuntadores hacia el nodo existente y los reemplazo por el nodo nuevo
                if(buscado.arriba!=null){
                buscado.arriba.abajo=nuevo;
                nuevo.arriba=buscado.arriba;
                }
                if(buscado.abajo!=null){
                buscado.abajo.arriba=nuevo;
                nuevo.abajo=buscado.abajo;
                }
                if(buscado.siguiente!=null){
                buscado.siguiente.anterior=nuevo;
                nuevo.siguiente=buscado.siguiente;
                }
                if(buscado.anterior!=null){
                buscado.anterior.siguiente=nuevo;
                nuevo.anterior=buscado.anterior;
                }
            }
           
        }
        
    }
    
    //Verificar los nodos repetidos 
    
    public void graficaLogica(String cliente){
         StringBuilder dot = new StringBuilder();
        
        dot.append("digraph G {\n");
        dot.append("node[shape=box style=filled];\nsubgraph cluster_0{\ncolor=none;\n");
        nombresNodos="";
        conexiones ="";
        conexiones2="";
        rangos="{rank=same;NodoRaiz";
        
       
        
        
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
    
    public void graficaApicacion(String cliente){
        StringBuilder dot = new StringBuilder();
        tabla="";
        boolean primera=true;
        dot.append("digraph G {\n");
        dot.append("Nodo[shape=none, margin=0,label=<\n");
        dot.append("<TABLE BORDER=\"0\" CELLBORDER=\"0\" CELLSPACING=\"0\">\n");
        Nodo aux = this.raiz;
        Nodo referencia=this.raiz;
        int contador=0;
        
        while(aux!=null){
            if(!aux.color.equals("Raiz") && !aux.color.equals("Row") && !aux.color.equals("Col")){  
            contador++;
            while(contador<aux.i){
                tabla+="<TD BGCOLOR=\"#FFFFFF\"></TD>\n";//Llenando con color blanco los espacios sin color
                contador++;
            }
            tabla+="<TD BGCOLOR=\""+aux.color+"\"></TD>\n";
            }
           
            aux=aux.siguiente;
            if(aux==null){
                if(!primera){
                tabla+="</TR>\n";
               
                }
                else{
                    primera=false;
                }
                if(referencia.abajo!=null){
                    referencia=referencia.abajo;
                    aux=referencia;
                    tabla+="<TR>\n";
                    contador=0;
                }
            }
        }
        dot.append(tabla);
        dot.append("</TABLE>>];\n}");
        try{
           
            File file = new File("Reportes de Usuario\\"+cliente);
            if(!file.exists()){file.mkdirs();}//Si no existe la carpeta, se crea
            
           
            file = new File("Reportes de Usuario\\"+cliente+"\\ImagenApp.dot");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(dot.toString());
            bw.close();
        ProcessBuilder pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", "Reportes de Usuario\\"+cliente+"\\ImagenApp.png", "Reportes de Usuario\\"+cliente+"\\ImagenApp.dot" );
        pbuilder.redirectErrorStream( true );
        pbuilder.start();
        }catch(Exception ex){System.out.println(ex.getMessage());}
    }
}
