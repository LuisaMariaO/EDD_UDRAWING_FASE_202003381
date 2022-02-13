
package eddfase1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.ZonedDateTime;

public class Tienda {
    int paso;
    Cola cola;
    Impresora color;
    Impresora bw;
    ListaVentanillas listaVentanillas;
    public Tienda(){
        this.paso=0;
        this.listaVentanillas=null;
        this.cola=null;
        //Siempre se inicializan dos impresoras
        this.color = new Impresora("Color");
        this.bw = new Impresora("B y N");
    }
    
    public void setVentanillas(int no){
        this.listaVentanillas=new ListaVentanillas();
        for(int i=0; i<no;i++){
            this.listaVentanillas.insertar("Ventanilla "+(i+1));
        }
        
        this.listaVentanillas.imprimir();
        
    }
    
    public void setImpresorass(){
        
    }
    
    public void crearCola(){
        Cola nueva = new Cola();
        this.cola=nueva;
    }
    
    public void graficar(){
        //Iniciando el grafo 
        StringBuilder dot = new StringBuilder();
        
        dot.append("digraph G {\n");
        dot.append("node[shape=box, style=\"filled\", color=azure1];\n");
 
        
        String nombresNodos="";
        String conexiones="";
        int cluster=0;
        
        //Cola recepción
        Cliente aux = this.cola.primero;
        dot.append("subgraph cluster_").append(cluster).append("{\n");
        dot.append("style=filled\n");
        dot.append("color=lightgrey;\n");
        dot.append("  edge [\n" +"    arrowhead=\"none\"\n" +"  ];\n");

        while(aux!=null){
            nombresNodos+="Cliente"+aux.hashCode()+"[label=\""+aux.titulo+"\nImg C:"+aux.img_color+"\nImg ByN:"+aux.img_bw+"\"]"+"\n";
            if(aux.siguiente!=null){
            conexiones+=String.format("Cliente%d -> Cliente%d", aux.hashCode(),aux.siguiente.hashCode())+"\n";
            }
            
            aux=aux.siguiente;
        }
        
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("label=\"Cola de recepción\""+";\n");
        dot.append("}\n");
        cluster++;
        
        //Ventanillas
        nombresNodos="";
        conexiones="";
        Ventanilla auxv = this.listaVentanillas.primero;
        dot.append("subgraph cluster_").append(cluster).append("{\n");
        dot.append("style=filled\n");
        dot.append("color=cadetblue;\n");
        

        while(auxv!=null){
            nombresNodos+="Ventanilla"+auxv.hashCode()+"[label=\""+auxv.nombre+"\"]"+"\n";
            if(auxv.siguiente!=null){
            conexiones+=String.format("Ventanilla%d -> Ventanilla%d", auxv.hashCode(),auxv.siguiente.hashCode())+"\n";
            }
            
            auxv=auxv.siguiente;
        }
        
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("label=\"Lista Ventanillas\""+";\n");
        dot.append("}\n");
        cluster++;
        
        //Impresoras
        nombresNodos="";
        conexiones="";

        dot.append("subgraph cluster_").append(cluster).append("{\n");
        dot.append("style=filled\n");
        dot.append("color=darkolivegreen1;\n");
        dot.append("  edge [\n" +"    arrowhead=\"none\"\n" +"  ];\n");
        
        nombresNodos+="Impresora"+this.color.hashCode()+"[label=\""+this.color.nombre+"\"]"+"\n";
        nombresNodos+="Impresora"+this.bw.hashCode()+"[label=\""+this.bw.nombre+"\"]"+"\n";
   
    
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("label=\"Cola impresoras\""+";\n");
        dot.append("}\n");
        
        
        //Finalizando el archivo
        
        dot.append("rankdir=LR;\n");
        dot.append("}");
        
        
        
        //Generando el archivo .dot
      
        try{
            File file = new File("Reportes\\Memoria.dot");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bwr = new BufferedWriter(fw);
            bwr.write(dot.toString());
            bwr.close();
        
        //Generando la imagen png a partir del .dot
        ProcessBuilder pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", "Reportes\\Memoria.png", 
                "Reportes\\Memoria.dot" );
        pbuilder.redirectErrorStream( true );
        pbuilder.start();
        
        }catch(IOException ex){System.out.println(ex.getMessage());}
    }
    
}
