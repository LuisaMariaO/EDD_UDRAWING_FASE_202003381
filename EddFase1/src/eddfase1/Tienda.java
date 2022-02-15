
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
    ListaEspera listaEspera;
    public Tienda(){
        this.paso=0;
        this.listaVentanillas=null;
        this.cola=null;
        //Siempre se inicializan dos impresoras
        this.color = new Impresora("Color");
        this.bw = new Impresora("B y N");
        
        this.listaEspera=null;
    }
    
    public void setVentanillas(int no){
        this.listaVentanillas=new ListaVentanillas();
        //Inicializo también la lista de espera para que solo se inicialice una vez
        this.listaEspera = new ListaEspera();
        //Inicializo las colas de impresion 
        this.color.cola=new ColaImpresion();
        this.bw.cola=new ColaImpresion();
        
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
        System.out.println("-----------------------------------------PASO "+this.paso+"-----------------------------------------");
        System.out.println("----------------------------------------------------------------------------------------");
        //Si hay ventanillas atendiendo clientes ANTES DE ESTE PASO pueden apilar imagenes
        this.setApilable();
        //Permito que imagenes ingresadas a la cola en el paso anterior se impriman a partir de el paso actual
        this.setImprimible();
        this.ingresoVentanilla();
        this.apilarImagenes();
        this.imprimir();
        
        
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
        if(aux.clienteActual!=null){
        aux.clienteActual.ventanilla=aux;//Ventanilla que lo está atendiendo
        //Quito el auntador que lo relacionaba con la cola de recepción
        aux.clienteActual.siguiente=null;
        System.out.println("EL CLIENTE "+aux.clienteActual.id+" INGRESA A LA  "+aux.nombre);
        }
        }
        
    }
    
    public void apilarImagenes(){
    Ventanilla actual = this.listaVentanillas.primero;
    while(actual!=null){
        if(actual.ocupada && actual.clienteActual!=null && actual.apilable){
            //actual.apilable=false;
            //actual.pila = new Pila();
            //Extrayendo las imagenes para apilar
            if(actual.clienteActual.color_res>0){//Apilo primero las imágenes de color
                String tipo="img_color";
                String nombre="IMG C";
                String id_cliente= actual.clienteActual.id;
                String cliente=actual.clienteActual.titulo;
                actual.pila.push(tipo, nombre, id_cliente, cliente);

                actual.clienteActual.color_res--;
                System.out.println("LA "+actual.nombre+" RECIBIÓ UNA IMAGEN DE "+actual.clienteActual.titulo);
            }
            else if(actual.clienteActual.bw_res>0){
                String tipo="img_bw";
                String nombre="IMG ByN";
                String id_cliente= actual.clienteActual.id;
                String cliente=actual.clienteActual.titulo;
                actual.pila.push(tipo, nombre, id_cliente, cliente);

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
        
        while(pila.primero!=null && pila.size>0){
            Imagen saliente = pila.pop();//Desapilo
            saliente.siguiente=null;//Elimino el puntero de la estructura anterior
        if(saliente.tipo.equals("img_color")){
            this.color.cola.enqueque(saliente);
        }
        else{//Esto no lo está haciendo :D
            this.bw.cola.enqueque(saliente);
        }
        }
        
        //Reestablesco la ventanilla para que pueda ser ocupada
        ventanilla.ocupada=false;
        this.listaEspera.insertar(ventanilla.clienteActual);//Envío el cliente actual a la lista de espera
        System.out.println("EL "+ventanilla.clienteActual.titulo+" ES ATENDIDO E INGRESA A LA LISTA DE ESPERA");
        ventanilla.clienteActual=null;
        ventanilla.pila=null;
        this.ingresoVentanilla();//Puede ingresar otro cliente en este mismo paso
        
    }
    
    
    public void setApilable(){
        Ventanilla aux = this.listaVentanillas.primero;
        while(aux!=null){
            if(aux.ocupada && aux.clienteActual!=null && aux.pila==null){
                aux.pila=new Pila();
                aux.apilable=true;
            }
            aux=aux.siguiente;
        }
    }
    
    public void setImprimible(){
        Imagen actual=this.color.cola.primero;
        while(actual!=null){
        actual.imprimible=true;
        actual=actual.siguiente;
    }
        actual=this.bw.cola.primero;
        while(actual!=null){
            actual.imprimible=true;
            actual=actual.siguiente;
        }
        
    }
    
    public void imprimir(){
        if(this.color.cola!=null && this.color.cola.primero!=null){
           Imagen impresa=this.color.cola.verPrimero();//Verifico los pasos que lleva en impresión
           if(impresa.pasos<2 && impresa.imprimible){
               impresa.pasos++;
           }
           if(impresa.pasos==2 && impresa.imprimible){
               impresa=this.color.cola.dequeque();
               //Busco el cliente al que le pertenece la imagen
               this.listaEspera.entregarImagen(impresa);//->Pendiente de revisar
               System.out.println("SE IMPRIME UNA IMAGEN A COLOR Y SE ENTREGA A "+impresa.cliente);
           }
        }
        if(this.bw.cola!=null && this.bw.cola.primero!=null){
            Imagen impresa=this.bw.cola.verPrimero();
            if(impresa.imprimible){
            impresa=this.bw.cola.dequeque();
             //Busco el cliente al que le pertenece la imagen
            this.listaEspera.entregarImagen(impresa);
            System.out.println("SE IMPRIME UNA IMAGEN A BLANCO Y NEGRO Y SE ENTREGA A "+impresa.cliente);
            }
           
            
        }
    }
}
