
package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Luisa María Ortiz
 */
public class ArbolAVL {
    Imagen raiz;
    String nombresNodos, conexiones, capas;
    public ArbolAVL(){
        this.raiz=null;
    }
    //Peso de un arbol o subarbol
    public int peso(Imagen img){
        if(img==null){
            return 0;
        }
        return img.peso;
    }
    
    //El mayor de dos id
    int mayor (int a, int b){
        if(a>b){
            return a;
        }
        return b;
    }
    
    //Caso 1: Rotación izquierda
    public Imagen RI(Imagen img){
        Imagen derecha = img.derecha; 
        Imagen izquierda = derecha.izquierda;
        
        derecha.izquierda=img;
        img.derecha=izquierda;
        
        //Actualizando los pesos
        img.peso=this.mayor(this.peso(img.izquierda), this.peso(img.derecha))+1;
        derecha.peso=this.mayor(this.peso(derecha.izquierda), this.peso(derecha.derecha))+1;
        return derecha;
    }
    
    //Caso 2: Rotación derecha
    public Imagen RD(Imagen img){
        Imagen izquierda = img.izquierda;
        Imagen derecha = izquierda.derecha;
        
        izquierda.derecha=img;
        img.izquierda=derecha;
        
        img.peso=this.mayor(this.peso(img.izquierda), this.peso(img.derecha))+1;
        izquierda.peso=this.mayor(this.peso(izquierda.izquierda), this.peso(izquierda.derecha))+1;
        
        return izquierda;
    }
    
    public int balancear(Imagen img){
        if(img==null){
            return 0;
        }
        return this.peso(img.izquierda)-this.peso(img.derecha);//Peso de un subarbol
    }
    
    public Imagen insertar (Imagen raiz, int id){
        //Caso base de la recursividad
        if(raiz == null){
            return new Imagen(id);
        }
        if(id<raiz.id){
            raiz.izquierda = this.insertar(raiz.izquierda, id);
        }
        else if(id > raiz.id){
            raiz.derecha = this.insertar(raiz.derecha, id);
        }
        else{
            return raiz;
        }
        
        //Actualizo el peso de los nodos anteriores
        raiz.peso=1+this.mayor(this.peso(raiz.izquierda), this.peso(raiz.derecha));
        
        //Busco el caso de balanceo si es necesario
        
        int balanceo = this.balancear(raiz);
        
        if(balanceo > 1 && id < raiz.izquierda.id){//Caso 1
            return this.RD(raiz);
        }
        if(balanceo < -1 && id > raiz.derecha.id){
            return this.RI(raiz);
        }
        //Caso 3: Rotacion Izquierda derecha
        if(balanceo > 1 && id > raiz.izquierda.id){
            raiz.izquierda = this.RI(raiz.izquierda);
            return this.RD(raiz);
            
        }
        //Caso 4: Rotacion Derecha Izquierda
        if(balanceo < -1 && id < raiz.derecha.id){
            raiz.derecha = this.RD(raiz.derecha);
            return this.RI(raiz);
        }
        
        return raiz;
    }
    
    public Imagen buscar(int id){
        return buscar(this.raiz,id);
    }

    private Imagen buscar(Imagen raiz, int id){
        if (raiz ==  null) return null;
        else if (id == raiz.id) return raiz;
        else if(id < raiz.id) return buscar(raiz.izquierda, id);
        else return buscar(raiz.derecha, id);

    }
    
    public void preOrden(){
        nombresNodos="";
        conexiones="";
        preOrden(this.raiz);

        System.out.println(nombresNodos);
        System.out.println(conexiones);
    }
    private void preOrden(Imagen raiz){
        
        if(raiz != null){
            nombresNodos+="Nodo"+raiz.hashCode()+"[label=\""+raiz.id+"\"]\n";
            if(raiz.derecha!=null){
            conexiones+="Nodo"+raiz.hashCode()+"->Nodo"+raiz.derecha.hashCode()+"\n";
        }
            if(raiz.izquierda!=null){
                conexiones+="Nodo"+raiz.hashCode()+"->Nodo"+raiz.izquierda.hashCode()+"\n";
            }
    
            preOrden(raiz.izquierda);
            preOrden(raiz.derecha);
        }
    }
    
     public void preOrden(int id){
        nombresNodos="";
        conexiones="";
        capas="";
        preOrden(this.raiz,id);

        System.out.println(nombresNodos);
        System.out.println(conexiones);
    }
    private void preOrden(Imagen raiz,int id){
        
        if(raiz != null){
            nombresNodos+="Nodo"+raiz.hashCode()+"[label=\""+raiz.id+"\"]\n";
            if(raiz.id==id){
                capas = raiz.capas.escribirDot();
                conexiones+="Nodo"+raiz.hashCode()+"->Nodo"+raiz.capas.raiz.hashCode()+"\n";
            }
            if(raiz.derecha!=null){
            conexiones+="Nodo"+raiz.hashCode()+"->Nodo"+raiz.derecha.hashCode()+"\n";
        }
            if(raiz.izquierda!=null){
                conexiones+="Nodo"+raiz.hashCode()+"->Nodo"+raiz.izquierda.hashCode()+"\n";
            }
    
            preOrden(raiz.izquierda,id);
            preOrden(raiz.derecha,id);
        }
    }
    
    public void graficar(String cliente){
        
        
        StringBuilder dot = new StringBuilder();
        
        dot.append("digraph G {\n");
        dot.append("node[ style=filled, fillcolor=coral1, shape=box];\n");
        dot.append("label=\"Árbol de Imágenes\"");
        this.preOrden();
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("}");
    try{
           
            File file = new File("Reportes de Usuario\\"+cliente);
            if(!file.exists()){file.mkdirs();}//Si no existe la carpeta, se crea
            
           
            file = new File("Reportes de Usuario\\"+cliente+"\\ArbolImagenes.dot");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(dot.toString());
            bw.close();
        ProcessBuilder pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", "Reportes de Usuario\\"+cliente+"\\ArbolImagenes.png", "Reportes de Usuario\\"+cliente+"\\ArbolImagenes.dot" );
        pbuilder.redirectErrorStream( true );
        pbuilder.start();
        }catch(Exception ex){System.out.println(ex.getMessage());}
    }
    
    public void graficar(String cliente, int id){
        StringBuilder dot = new StringBuilder();
        
        dot.append("digraph G {\n");
        dot.append("node[ style=filled, fillcolor=coral1, shape=box];\n");
        dot.append("label=\"Imagen seleccionada y árbol de capas\"");
        this.preOrden(id);
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append(capas);
        dot.append("}");
    try{
           
            File file = new File("Reportes de Usuario\\"+cliente);
            if(!file.exists()){file.mkdirs();}//Si no existe la carpeta, se crea
            
           
            file = new File("Reportes de Usuario\\"+cliente+"\\ImagenyCapas.dot");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(dot.toString());
            bw.close();
        ProcessBuilder pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", "Reportes de Usuario\\"+cliente+"\\ImagenyCapas.png", "Reportes de Usuario\\"+cliente+"\\ImagenyCapas.dot" );
        pbuilder.redirectErrorStream( true );
        pbuilder.start();
        }catch(Exception ex){System.out.println(ex.getMessage());}
    }

}
