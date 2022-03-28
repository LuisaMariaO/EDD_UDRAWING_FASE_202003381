
package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Luisa María Ortiz
 */
public class ArbolBB<E extends Comparable<E>>{
    Capa<E> raiz;
    String nombresNodos, conexiones;
    public ArbolBB(){
        this.raiz=null;
    }

    public void insertar(E id, Matriz matriz){
        this.raiz=insertarNodo(this.raiz,id,matriz);
    }

    private Capa<E> insertarNodo(Capa<E> raiz, E id,Matriz matriz){

        if (raiz==null){
            raiz=new Capa<E>(id,matriz);
        }
        else if (Integer.valueOf((String) id)<Integer.valueOf((String) raiz.id)){
            raiz.izquierdo=insertarNodo(raiz.izquierdo,id,matriz);
        }
        else if(Integer.valueOf((String)id) > Integer.valueOf((String)raiz.id)){
            raiz.derecho=insertarNodo(raiz.derecho, id, matriz);
        }
        //TODO: Validar la insercion de claves repetidas :D
        return raiz;
    }

    public void preOrden(){
        nombresNodos="";
        conexiones="";
        preOrden(this.raiz);

        //System.out.println(nombresNodos);
        //System.out.println(conexiones);
    }

    public void inOrden(){
        inOrden(this.raiz);
    }

    public void postOrden(){
        postOrden(this.raiz);
    }

    private void preOrden(Capa<E> raiz){
        
        if(raiz != null){
            nombresNodos+="Nodo"+raiz.hashCode()+"[label=\""+raiz.id+"\" shape=circle fillcolor=cornflowerblue]\n";
            if(raiz.derecho!=null){
            conexiones+="Nodo"+raiz.hashCode()+"->Nodo"+raiz.derecho.hashCode()+"\n";
        }
            if(raiz.izquierdo!=null){
                conexiones+="Nodo"+raiz.hashCode()+"->Nodo"+raiz.izquierdo.hashCode()+"\n";
            }
            System.out.print(raiz.id.toString()+" ");
            preOrden(raiz.izquierdo);
            preOrden(raiz.derecho);
        }
    }
    
    private void inOrden(Capa<E> raiz){
        if(raiz != null){
            inOrden(raiz.izquierdo);
            System.out.print(raiz.id.toString()+" ");
            inOrden(raiz.derecho);
        }
    }

    private void postOrden(Capa<E> raiz){
        if(raiz != null){
            postOrden(raiz.izquierdo);
            postOrden(raiz.derecho);
            System.out.print(raiz.id.toString()+" ");
        }
    }



    public Capa buscar(E id){
        return buscar(this.raiz,id);
    }

    private Capa buscar(Capa<E> raiz, E id){
        if (raiz ==  null) return null;
        else if (Integer.valueOf((String)id) == Integer.valueOf((String)raiz.id)) return raiz;
        else if(Integer.valueOf((String)id) < Integer.valueOf((String)raiz.id)) return buscar(raiz.izquierdo, id);
        else return buscar(raiz.derecho, id);

    }

    public void graficar(String cliente){
        StringBuilder dot = new StringBuilder();
        
        dot.append("digraph G {\n");
        dot.append("node[style=filled, fillcolor=cornflowerblue, shape=circle];\n");
        dot.append("label=\"Árbol de Capas\"");
        this.preOrden();
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("}");
    try{
           
            File file = new File("Reportes de Usuario\\"+cliente);
            if(!file.exists()){file.mkdirs();}//Si no existe la carpeta, se crea
            
           
            file = new File("Reportes de Usuario\\"+cliente+"\\ArbolCapas.dot");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(dot.toString());
            bw.close();
        ProcessBuilder pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", "Reportes de Usuario\\"+cliente+"\\ArbolCapas.png", "Reportes de Usuario\\"+cliente+"\\ArbolCapas.dot" );
        pbuilder.redirectErrorStream( true );
        pbuilder.start();
        }catch(Exception ex){System.out.println(ex.getMessage());}
    }
    
    public String escribirDot(){
        this.preOrden();
        return nombresNodos+conexiones;
    }
 
        
    
   

    


}