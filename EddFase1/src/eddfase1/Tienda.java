
package eddfase1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
            this.listaVentanillas.insertar("VENTANILLA "+(i+1));
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
            //Ventanillas
            nombresNodos+="Ventanilla"+auxv.hashCode()+"[label=\""+auxv.nombre+"\"]"+"\n";
            //Clientes actuales de la ventanilla
            if(auxv.ocupada && auxv.clienteActual!=null){
                nombresNodos+="ClienteV"+auxv.clienteActual.hashCode()+"[shape=tab fillcolor=darkseagreen2 label=\""+auxv.clienteActual.titulo+"\nIMG C:"+auxv.clienteActual.img_color+
                        "\nIMG ByN:"+auxv.clienteActual.img_bw+"\"]"+"\n";
                
                conexiones+=String.format("ClienteV%d -> Ventanilla%d", auxv.clienteActual.hashCode(),auxv.hashCode())+"\n";
            }
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
    //Pasos de la simulación
    
    public void ejecutarPaso(){
        this.paso++;
        System.out.println("-------------------PASO "+this.paso+"-------------------");
        System.out.println("--------------------------------------------");
        //Si hay ventanillas atendiendo clientes ANTES DE ESTE PASO pueden apilar imagenes
        setApilable();
        
        ingresoVentanilla();
        apilarImagenes();
        
    }
    
    public void ingresoVentanilla(){
        
        Ventanilla aux = this.listaVentanillas.primero;
        
        while(aux!=null){
            if(!aux.ocupada && aux.clienteActual==null){//Encuentro la primera ventanilla disponible
                break;
            }
            else{
                aux=aux.siguiente;
            }
            
        }
        if(aux!=null){
        aux.ocupada=true;
        aux.clienteActual=this.cola.dequeque();
        //Quito el auntador que lo relacionaba con la cola de recepción
        aux.clienteActual.siguiente=null;
        System.out.println("EL CLIENTE "+aux.clienteActual.id+" INGRESA A LA  "+aux.nombre);
        
        }
        
    }
    
    public void apilarImagenes(){
    Ventanilla actual = this.listaVentanillas.primero;
    while(actual!=null){
        if(actual.ocupada && actual.clienteActual!=null && actual.apilable){
            //actual.apilable=false;
            actual.pila = new Pila();
            //Extrayendo las imagenes para apilar
            if(actual.clienteActual.color_res>0){//Apilo primero las imágenes de color
                String tipo="img_color";
                String nombre="IMG C";
                String id_cliente= actual.clienteActual.id;
                actual.pila.push(tipo, nombre, id_cliente);
                actual.clienteActual.color_res--;
                System.out.println("LA "+actual.nombre+" RECIBIÓ UNA IMAGEN DE "+actual.clienteActual.titulo);
            }
            else if(actual.clienteActual.bw_res>0){
                String tipo="img_bw";
                String nombre="IMG ByN";
                String id_cliente= actual.clienteActual.id;
                actual.pila.push(tipo, nombre, id_cliente);
                actual.clienteActual.bw_res--;
                System.out.println("LA "+actual.nombre+" RECIBIÓ UNA IMAGEN DE "+actual.clienteActual.titulo);
            }
            else{
                System.out.println("LA "+actual.nombre+" ENVIA LAS IMAGENES DE "+actual.clienteActual.titulo+" A SUS RESPECTIVAS COLAS DE IMPRESION");
                liberarVentanilla(actual);
            }
            //break;
        }
        
        actual=actual.siguiente;
    }
        
    }
    
    public void liberarVentanilla(Ventanilla ventanilla){
        Pila pila = ventanilla.pila;
        //Empiezo a desapilar, clasificando las imagenes
        while(pila!=null && pila.size>0){
            Imagen saliente = pila.pop();//Desapilo
        if(saliente.tipo.equals("img_color")){
            this.color.cola.enqueque(saliente);
        }
        else{
            this.bw.cola.enqueque(saliente);
        }
        }
        //Reestablesco la ventanilla para que pueda ser ocupada
        ventanilla.ocupada=false;
        ventanilla.clienteActual=null;
        ventanilla.pila=null;
        this.ingresoVentanilla();//Puede ingresar otro cliente en este mismo paso
        
    }
    
    
    public void setApilable(){
        Ventanilla aux = this.listaVentanillas.primero;
        while(aux!=null){
            if(aux.ocupada && aux.clienteActual!=null && aux.pila==null){
                aux.apilable=true;
            }
            aux=aux.siguiente;
        }
    }
}
