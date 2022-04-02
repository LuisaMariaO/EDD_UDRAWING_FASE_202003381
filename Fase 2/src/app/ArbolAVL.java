
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
    
    Imagen minimo(Imagen imagen)
    {
        Imagen actual = imagen;
 
        /*Encontrando el valor más a la derecha*/
        while (actual.derecha != null)
        actual = actual.derecha;
 
        return actual;
    }
    
    Imagen eliminar(int id){
        return eliminar(this.raiz,id);
    }
 
    Imagen eliminar(Imagen raiz, int id)
    {
 
        if (raiz == null)
            return raiz;
 
        //Se mueve a la izquierda si el valor a eliminar es menor que el nodo actual
        if (id < raiz.id)
            raiz.izquierda = eliminar(raiz.izquierda, id);
 
        //Se mueve a ka derecha si el valor a eliminar es mayor al nodo actual
        else if (id > raiz.id)
            raiz.derecha = eliminar(raiz.derecha, id);
 
        //Si no, la clave es igual y es el nodo a eliminar
        else
        {
 
            
            if ((raiz.izquierda == null) || (raiz.derecha == null))//Nodos hoja o con un solo hijo
            {
                Imagen temp = null;
                if (temp == raiz.izquierda)
                    temp = raiz.derecha;
                else
                    temp = raiz.derecha;
 
                // Nodo hoja
                if (temp == null)
                {
                    temp = raiz;
                    raiz = null;
                }
                else // Nodo con un solo hijo
                    raiz= temp; 
                               
            }
            else
            {
 
                //Nodo con dos hijos
                Imagen temp = minimo(raiz.izquierda);//Encuentra el mayor de los menores
 
              
                raiz.id = temp.id;
 
                
                raiz.izquierda= eliminar (raiz.izquierda, temp.id);
            }
        }
 
       
        if (raiz == null)
            return raiz;
 
        // Actualizando el peso
        raiz.peso= mayor(peso(raiz.izquierda), peso(raiz.derecha)) + 1;
 
        // Blalanceando
        int balanceo = balancear(raiz);
 
        
       
        if (balanceo > 1 && balancear(raiz.izquierda) >= 0)
            return RD(raiz);
 

        if (balanceo > 1 && balancear(raiz.izquierda) < 0)
        {
            raiz.izquierda = RI(raiz.izquierda);
            return RD(raiz);
        }
 

        if (balanceo < -1 && balancear (raiz.derecha) <= 0)
            return RI(raiz);

        if (balanceo < -1 && balancear(raiz.derecha) > 0)
        {
            raiz.derecha = RD(raiz.derecha);
            return RI(raiz);
        }
 
        return raiz;
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
     private void preOrden(Imagen raiz,Lista lista){
        if(raiz != null){
            lista.insertarFinal(raiz);
            preOrden(raiz.izquierda,lista);
            preOrden(raiz.derecha,lista);
        }
    }
    public String reporte(){
        Lista lista = new Lista();
        preOrden(this.raiz,lista);
        
        return lista.sort();
    }

}
//Lista auxiliar para ordenar en los reportes por el número de capas
class Lista{
    Imagen primera,ordenada;
    int size;
    
    public Lista(){
        this.primera=null;
        this.ordenada=null;
        this.size=0;
    }
    
     public void insertarFinal(Imagen nueva){

        Imagen actual = this.primera;
        if(this.estaVacia()){
            this.primera=nueva;
                    }
        else{
          while(actual.siguienteo!=null){
              actual=actual.siguienteo;
          }
          actual.siguienteo=nueva;
          
        }
        
        this.size++;

    }
    
    public boolean estaVacia(){
        return this.primera==null && this.size==0;
        
    }
    
     public String sort(){//Por inserción
        this.ordenada=null;
        Imagen actual = this.primera;
        while(actual!=null){
            Imagen siguiente=actual.siguienteo;
            insertarOrdenado(actual);
            actual=siguiente;
            
    }
        this.primera=ordenada;
        
        //Tabulando los primeros 5 lugares
        actual=this.primera;
        int contador=0;
        String tabla="";
        StringBuilder dot = new StringBuilder();
        dot.append("subgraph cluster_0{\ncolor=none\n");
        dot.append("label=\"Top 5 de imágenes con más número de capas\";\n");
        tabla+="Nodo[shape=none label=<\n<TABLE BORDER=\"0\" CELLSPACING=\"1\" CELLPADDING=\"1\"\n>";

        tabla+="<TR>\n";
        tabla+="<TD BGCOLOR=\"#E74C3C\">No.</TD>\n";
        tabla+="<TD BGCOLOR=\"#EC7063\">Id capa</TD>\n";
        tabla+="<TD BGCOLOR=\"#F5B7B1\">Cantidad de capas</TD>\n";
        tabla+="</TR>\n";
        while(actual!=null){
            contador++;
            tabla+="<TR>\n";
            tabla+="<TD BGCOLOR=\"#E74C3C\">"+contador+"</TD>\n";
            tabla+="<TD BGCOLOR=\"#EC7063\">"+actual.id+"</TD>\n";
            tabla+="<TD BGCOLOR=\"#F5B7B1\">"+actual.no_capas+"</TD>\n";
            tabla+="</TR>\n";
            
            if(contador==5){break;}
            actual=actual.siguienteo;
        }
        tabla+="</TABLE>>]\n}\n";
        dot.append(tabla);
        
        //Retorno los punteris a nulo
        actual=this.primera;
        while(actual!=null){
            this.primera=actual.siguienteo;
            actual=actual.siguienteo;
        }
         
        return dot.toString();
    }
    public void insertarOrdenado(Imagen nueva){
        if(this.ordenada==null || this.ordenada.no_capas<nueva.no_capas){
            nueva.siguienteo=this.ordenada;
            this.ordenada=nueva;
        }
        else{
            Imagen actual = this.ordenada;
            while(actual.siguienteo!=null && actual.siguienteo.no_capas>nueva.no_capas){
                actual=actual.siguienteo;
            }
            nueva.siguienteo = actual.siguienteo;
            actual.siguienteo = nueva;
        }
    }
}
