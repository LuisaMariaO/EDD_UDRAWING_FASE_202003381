
package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Luisa María Ortiz
 */
public class Albumes {
    //Lista circular doblemente enlazada
     Album lc;
    int size;
    String nombresNodos, conexiones, imagenes, conexionesimg;
 public Albumes(){
     this.lc=null;
     this.size=0;
 }
 
 
    public void insertar(Album nuevo){
       
        nuevo.siguiente=nuevo;
        nuevo.anterior=nuevo;

        if (this.lc!=null){//Esta lista es circular y doblemente enlazada
            nuevo.siguiente=lc.siguiente;
            lc.siguiente.anterior=nuevo;
            nuevo.anterior=lc;
            lc.siguiente=nuevo;
        }
        lc=nuevo;
        this.size++;
    }

    
    public void eliminar(Album album){
        if(album==this.lc){
                if(this.size==1){//En caso de que solo haya un cliente en espera
                    this.lc=null;
                }
                else{
            lc.siguiente.anterior=lc.anterior;
            lc.anterior.siguiente=lc.siguiente;
            this.lc=lc.siguiente;
                }

        }
        else{
            album.anterior.siguiente=album.siguiente;
            album.siguiente.anterior=album.anterior;
        }
        this.size--;
    }
    
    public void graficar(String cliente){
        this.nombresNodos="";
        this.conexiones="";
        this.imagenes="";
        this.conexionesimg="";
        
        StringBuilder dot = new StringBuilder();
        
        dot.append("digraph G {\n");
        dot.append("node[shape=box, style=filled];\n");
        dot.append("rankdir=\"LR\"\nlabel=\"Listado de álbumes\";\n");
        
        Album aux = this.lc;
        
        do{
            nombresNodos+="Nodo"+aux.hashCode()+"[label=\""+aux.nombre+"\" fillcolor=orange1];";
            if(aux.siguiente!=null){
                conexiones+="Nodo"+aux.hashCode()+"->Nodo"+aux.siguiente.hashCode()+";\n";
            }
            if(aux.anterior!=null){
                conexiones+="Nodo"+aux.hashCode()+"->Nodo"+aux.anterior.hashCode()+";\n";
            }
            
            Imagen auximg = aux.imagenes.primero;
            if(auximg!=null){
            conexiones+="Nodo"+aux.hashCode()+"->Imagen"+auximg.hashCode()+";\n";
            }
            while(auximg!=null){
                imagenes+="Imagen"+auximg.hashCode()+"[label=\"Id imagen: "+auximg.id+"\" fillcolor=olivedrab1];";
            
            
            if(auximg.siguiente!=null){
                conexiones+="Imagen"+auximg.hashCode()+"->Imagen"+auximg.siguiente.hashCode()+";\n";
            }
            if(auximg.anterior!=null){
                conexiones+="Imagen"+auximg.hashCode()+"->Imagen"+auximg.anterior.hashCode()+";\n";
            }
            auximg=auximg.siguiente;
            }
            
            aux=aux.siguiente;
        }while(aux!=this.lc);
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append(imagenes);
        dot.append(conexionesimg);
        dot.append("}");
        try{
           
            File file = new File("Reportes de Usuario\\"+cliente);
            if(!file.exists()){file.mkdirs();}//Si no existe la carpeta, se crea
            
           
            file = new File("Reportes de Usuario\\"+cliente+"\\ListaAlbumes.dot");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(dot.toString());
            bw.close();
        ProcessBuilder pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", "Reportes de Usuario\\"+cliente+"\\ListaAlbumes.png", "Reportes de Usuario\\"+cliente+"\\ListaAlbumes.dot" );
        pbuilder.redirectErrorStream( true );
        pbuilder.start();
        }catch(Exception ex){System.out.println(ex.getMessage());}
        
        
    }
    
    public void eliminarImagenes(int id){
        Album actual = this.lc;
        
        do{
            if(actual.imagenes!=null && actual.imagenes.primero!=null){//Si el álbum no está vacío
           actual.imagenes.elminar(id);
            }
           actual=actual.siguiente;//Elimino la imagen de todos los álbumes en donde se encuentre
        }while (actual!=this.lc);
        }
    
    
    }

