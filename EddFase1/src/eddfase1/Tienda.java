
package eddfase1;
import java.util.Vector;
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
    ListaAtendidos listaAtendidos;
    //Vectores para generar nombres y apellidos aleatorios
    String nombres[]={"Luisa","María","Olga","Lidia","Belén","Ana","Manuela","Teresa","Alejandra","Rosa","Amanda","Amalia","Brenda","Josefina",
    "Linda","Rosaura","Zulma","Dania","Clara","Julia","Julieta","Carmen","Lourdes","Fátima","Soledad","Hermenegildo","Roberto","Pedro","Esteban",
    "Cornelio","Ezequiel","Daniel","Isaías","Mateo","Marcos","Juan","Lucas","Santiago","Judas","José","Álvaro","Joel","Dante","Salomón","David",
    "Iván","Jesús","Francisco","Valentín","Albin"};
    
    String apellidos[]={"Ortíz","Romero","Ramírez","Rafael","Franco","Valenzuela","Castillo","Botrán","del Cid","Campollo","Gutiérrez","Paiz","Osorio",
    "Giammatei","Cabrera","Árbenz","Estrada","Túchez","Salazar","Batres","Álvarez","Herrera","Herrarte","Muñoz","Véliz","Gárcía","Santos","Vargas",
    "Velásquez","Rodas","Samayoa","Solares","Alvarado","Mayén","Camas","Puac","Quel","Escalante","Bolaños","Lima","Delgado","Quevedo","Monterroso",
    "Guzman","Bran","Melgar","Morales","Rosales","Zetino","de Leon","Collado"};
    
    public Tienda(){
        this.paso=0;
        this.listaVentanillas=null;
        this.cola=null;
        //Siempre se inicializan dos impresoras
        this.color = new Impresora("Color");
        this.bw = new Impresora("B y N");
        
        this.listaEspera=null;
        this.listaAtendidos=new ListaAtendidos();
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
        
        //this.listaVentanillas.imprimir();
        
        
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
        
        //Clientes atendidos
        nombresNodos="";
        conexiones="";
        Cliente auxi = this.listaAtendidos.primero;
        dot.append("subgraph cluster_").append(cluster).append("{\n");
        dot.append("style=filled\n");
        dot.append("color=	cornflowerblue;\n");


        while(auxi!=null){
            nombresNodos+="Cliente"+auxi.hashCode()+"[label=\""+auxi.titulo+"\nNombre: "+auxi.nombre+"\nAtendió: "+auxi.ventanilla.nombre+"\nImg impresas: "+auxi.img_total+"\nPasos totales: "+auxi.pasos+"\"]"+"\n";
            if(auxi.siguiente!=null){
            conexiones+=String.format("Cliente%d -> Cliente%d", auxi.hashCode(),auxi.siguiente.hashCode())+"\n";
            }
            
            auxi=auxi.siguiente;
        }
        
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("label=\"Clientes atendidos\""+";\n");
        dot.append("}\n");
        cluster++;
        
        //Clientes en espera
        nombresNodos="";
        conexiones="";
        Cliente cl = this.listaEspera.lc.siguiente;
        dot.append("subgraph cluster_").append(cluster).append("{\n");
        dot.append("style=filled\n");
        dot.append("color=coral;\n");
        
        if(this.listaEspera.lc!=null){
        do{
            nombresNodos+="Cliente"+(cl.hashCode()+1)+"[label=\""+cl.titulo+"\nNombre: "+cl.nombre+"\nImg C: "+cl.img_color+"\nImg ByN: "+cl.img_bw+"\"]"+"\n";
            if(cl.siguiente!=null && cl.anterior!=null){
            //Imagenes de cada cliente
            Imagen actual=cl.img_impresa;
            if(cl.img_impresa!=null){
                if(actual.tipo.equals("img_color")){
                nombresNodos+="Imagen"+(actual.hashCode()+1)+"[shape=signature fillcolor=darksalmon label=\"IMG COLOR\"]"+"\n";}
                else{
                    nombresNodos+="Imagen"+(actual.hashCode()+1)+"[shape=signature fillcolor=gray52 label=\"IMG ByN\"]"+"\n";
                }
                conexiones+=String.format("Cliente%d -> Imagen%d", cl.hashCode()+1,actual.hashCode()+1)+"\n";  
                
                while(actual!=null){
                    if(actual.tipo.equals("img_color")){
                        nombresNodos+="Imagen"+(actual.hashCode()+1)+"[shape=signature fillcolor=darksalmon label=\"IMG COLOR\"]"+"\n";}
                    else{
                    nombresNodos+="Imagen"+(actual.hashCode()+1)+"[shape=signature fillcolor=gray52 label=\"IMG ByN\"]"+"\n";
                }
                    if(actual.siguiente!=null){
                    conexiones+=String.format("Imagen%d -> Imagen%d", actual.hashCode()+1,actual.siguiente.hashCode()+1)+"\n";  
                }
              actual=actual.siguiente;
            }
            }
            
            conexiones+=String.format("Cliente%d -> Cliente%d", cl.hashCode()+1,cl.siguiente.hashCode()+1)+"\n";
            conexiones+=String.format("Cliente%d -> Cliente%d", cl.hashCode()+1,cl.anterior.hashCode()+1)+"\n";
            }

            cl=cl.siguiente;
        }while(cl!=this.listaEspera.lc.siguiente);
        
        }
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("label=\"Lista de Espera\""+";\n");
        dot.append("}\n");
        cluster++;
        
        
        
        //Impresoras
        nombresNodos="";
        conexiones="";

        dot.append("subgraph cluster_").append(cluster).append("{\n");
        dot.append("style=filled\n");
        dot.append("color=darkolivegreen1;\n");
        dot.append("  edge [\n" +"    arrowhead=\"none\"\n" +"  ];\n");
        //Impresora a color
        nombresNodos+="Impresora"+this.color.hashCode()+"[label=\""+this.color.nombre+"\"]"+"\n";
        
        if(this.color.cola.primero!=null){
            Imagen actual = this.color.cola.primero;
            nombresNodos+="Imagen"+(actual.hashCode()+1)+"[shape=signature fillcolor=darksalmon label=\"IMG COLOR\"]"+"\n";
            conexiones+=String.format("Impresora%d -> Imagen%d", this.color.hashCode(),actual.hashCode()+1)+"\n";
            
            while(actual!=null){
              nombresNodos+="Imagen"+(actual.hashCode()+1)+"[shape=signature fillcolor=darksalmon label=\"IMG COLOR\"]"+"\n"; 
              if(actual.siguiente!=null){
                conexiones+=String.format("Imagen%d -> Imagen%d", actual.hashCode()+1,actual.siguiente.hashCode()+1)+"\n";  
              }
              actual=actual.siguiente;
            }
            
        }
        //Impresora blanco y negro
        nombresNodos+="Impresora"+this.bw.hashCode()+"[label=\""+this.bw.nombre+"\"]"+"\n";
        
         if(this.bw.cola.primero!=null){
            Imagen actual = this.bw.cola.primero;
            nombresNodos+="Imagen"+(actual.hashCode()+1)+"[shape=signature fillcolor=gray52 label=\"IMG ByN\"]"+"\n";
            conexiones+=String.format("Impresora%d -> Imagen%d", this.bw.hashCode(),actual.hashCode()+1)+"\n";
            
            while(actual!=null){
              nombresNodos+="Imagen"+(actual.hashCode()+1)+"[shape=signature fillcolor=gray52 label=\"IMG ByN\"]"+"\n"; 
              if(actual.siguiente!=null){
                conexiones+=String.format("Imagen%d -> Imagen%d", actual.hashCode()+1,actual.siguiente.hashCode()+1)+"\n";  
              }
              actual=actual.siguiente;
            }
            
        }
   
    
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("label=\"Cola impresoras\""+";\n");
        dot.append("}\n");
        cluster++;
        
        
        //Pilas de imágenes en ventanilla
        nombresNodos="";
        conexiones="";
        Ventanilla auxv = this.listaVentanillas.primero;
        dot.append("subgraph cluster_").append(cluster).append("{\n");
        dot.append("style=filled\n");
        dot.append("color=gold2;\n");
        dot.append("  edge [\n" +"    arrowhead=\"none\"\n" +"  ];\n");

        while(auxv!=null){    
            //Ventanillas
            nombresNodos+="Ventanilla"+(auxv.hashCode()+1)+"[label=\""+auxv.nombre+"\"]"+"\n";
            //Pila de imágenes en ventanilla
            if(auxv.pila!=null){
                Imagen imagen=auxv.pila.primero;
                if(imagen.tipo.equals("img_color")){
                nombresNodos+="Imagen"+imagen.hashCode()+"[shape=signature fillcolor=floralwhite label=\"IMG COLOR\"]"+"\n";}
                else{
                    nombresNodos+="Imagen"+imagen.hashCode()+"[shape=signature fillcolor=floralwhite label=\"IMG ByN\"]"+"\n";} 
                
                conexiones+=String.format("Ventanilla%d -> Imagen%d", auxv.hashCode()+1,imagen.hashCode())+"\n";
                //Agrego las pilas de imágenes de cada ventanilla
            //imagen = imagen.siguiente;
            while(imagen!=null){
                if(imagen.tipo.equals("img_color")){
                nombresNodos+="Imagen"+imagen.hashCode()+"[shape=signature fillcolor=floralwhite label=\"IMG COLOR\"]"+"\n";}
                else{
                    nombresNodos+="Imagen"+imagen.hashCode()+"[shape=signature fillcolor=floralwhite label=\"IMG ByN\"]"+"\n";} 
                if(imagen.siguiente!=null){
                    conexiones+=String.format("Imagen%d -> Imagen%d", imagen.hashCode(),imagen.siguiente.hashCode())+"\n";
                }
                imagen=imagen.siguiente;
            }
            
            }
            
            
            
            if(auxv.siguiente!=null){
            //conexiones+=String.format("Ventanilla%d -> Ventanilla%d", auxv.hashCode()+1,auxv.siguiente.hashCode()+1)+"\n";
            }
            
            auxv=auxv.siguiente;
        }
        
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("label=\"Pila de imágenes en cada ventanilla\""+";\n");
        dot.append("}\n");
        cluster++;
        
        //Ventanillas
        nombresNodos="";
        conexiones="";
        auxv = this.listaVentanillas.primero;
        dot.append("subgraph cluster_").append(cluster).append("{\n");
        dot.append("style=filled\n");
        dot.append("color=cadetblue;\n");
        

        while(auxv!=null){    
            //Ventanillas
            nombresNodos+="Ventanilla"+auxv.hashCode()+"[label=\""+auxv.nombre+"\"]"+"\n";
            //Clientes actuales de la ventanilla
            if(auxv.ocupada && auxv.clienteActual!=null){
                nombresNodos+="ClienteV"+auxv.clienteActual.hashCode()+"[shape=tab fillcolor=darkseagreen2 label=\""+auxv.clienteActual.titulo+"\nNombre: "+
                        auxv.clienteActual.nombre+"\nIMG C:"+auxv.clienteActual.img_color+
                        "\nIMG ByN:"+auxv.clienteActual.img_bw+"\"]"+"\n";
                
                conexiones+=String.format("ClienteV%d -> Ventanilla%d", auxv.clienteActual.hashCode(),auxv.hashCode())+"\n";
                //Agrego las pilas de imágenes de cada ventanilla
       
            }
            if(auxv.siguiente!=null){
            conexiones+=String.format("Ventanilla%d -> Ventanilla%d", auxv.hashCode(),auxv.siguiente.hashCode())+"\n";
            }
            
            auxv=auxv.siguiente;
        }
        
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("label=\"Lista Ventanillas (Se muestra el cliente que está atendiendo)\""+";\n");
        dot.append("}\n");
        cluster++;
        
        
        
        
        //Cola recepción
        nombresNodos="";
        conexiones="";
        Cliente aux = this.cola.primero;
        dot.append("subgraph cluster_").append(cluster).append("{\n");
        dot.append("style=filled\n");
        dot.append("color=lightgrey;\n");
        dot.append("  edge [\n" +"    arrowhead=\"none\"\n" +"  ];\n");

        while(aux!=null){
            nombresNodos+="Cliente"+aux.hashCode()+"[label=\""+aux.titulo+"\nNombre: "+aux.nombre+"\nImg C: "+aux.img_color+"\nImg ByN: "+aux.img_bw+"\"]"+"\n";
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
        //Busco clientes que ya hayan recibido todas sus imágenes y los saco de la lista de espera
        this.setAtendido();
        //Genero los clientes aleatorios
        this.generarClientes();
        this.ingresoVentanilla();
        
        this.apilarImagenes();
        
        //Después de ingresar a ventanilla, sumo este paso a los clientes en la cola de recepción
        this.cola.cuentaPaso();
        
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
        aux.clienteActual.pasos++;//Contador de pasos
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
                actual.clienteActual.pasos++;//Un paso más al cliente
                System.out.println("LA "+actual.nombre+" RECIBIÓ UNA IMAGEN DE "+actual.clienteActual.titulo);
            }
            else if(actual.clienteActual.bw_res>0){
                String tipo="img_bw";
                String nombre="IMG ByN";
                String id_cliente= actual.clienteActual.id;
                String cliente=actual.clienteActual.titulo;
                actual.pila.push(tipo, nombre, id_cliente, cliente);

                actual.clienteActual.bw_res--;
                actual.clienteActual.pasos++;
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
        ventanilla.clienteActual.pasos++;//Un paso más en el sistema
        System.out.println("EL "+ventanilla.clienteActual.titulo+" ES ATENDIDO E INGRESA A LA LISTA DE ESPERA");
        
        ventanilla.clienteActual=null;
        ventanilla.pila=null;
        this.ingresoVentanilla();//Puede ingresar otro cliente en este mismo paso
        
    }
    public void pasoCola(){
        
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
               this.listaEspera.entregarImagen(impresa);
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
        this.listaEspera.contarPasos();
    }
    public void setAtendido(){
      
      if(this.listaEspera.lc!=null){
          Cliente actual=this.listaEspera.lc;
        do{
            if(actual.img_contador==actual.img_total && actual.atendido){
                //actual.pasos++;
                System.out.println("EL "+actual.titulo+" YA POSEE TODAS SUS IMÁGENES IMPRESAS Y SALE DE LA EMPRESA REGISTRANDO UN"
                        + " TIEMPO TOTAL DE "+actual.pasos+" PASOS");
                this.listaEspera.sacar(actual);
                //Elimino los punteros que lo relacionan con la lista de espera
                actual.siguiente=null;
                actual.anterior=null;
                this.listaAtendidos.insertarFinal(actual);
                break;
            }
            actual=actual.siguiente;
        }while(actual!=this.listaEspera.lc);
    }
    }
    
    public void generarClientes(){
        int cantidad = (int)(Math.random()*4);//0 a 3 cilentes aleatorios
        int bwn, colorn;
            for(int i=0;i<cantidad;i++){
               bwn= (int)(Math.random()*5);//0 a 4 imágenes en blanco y negro
               if(bwn==0){//Para evitar que hayan clientse con 0 imágenes, se verifica primero
                   colorn = (int) (Math.random()*4+1);//1 a 4 imágenes a color
               }
               else{
                   colorn = (int) (Math.random()*(5-bwn));//0 a las imágenes restantes para completar 4 imágenes 
               }
               
               String nombre = this.nombres[(int)(Math.random()*49)]+" "+this.apellidos[(int)(Math.random()*49)];
               int id = Integer.valueOf(this.cola.ultimo)+1;
               this.cola.enqueque("Cliente"+String.valueOf(id), String.valueOf(id), nombre, colorn, bwn);
                System.out.println("EL cliente"+id+" ("+nombre+ ") HA INGRESADO A LA COLA DE RECEPCIÓN");
            }
        
        
    }
    
    //Reportes
    
    public void topColor(){
        this.listaAtendidos.sortColor();
    
    }
    
    public void topBN(){this.listaAtendidos.sortBN();}
    public void topPasos(){this.listaAtendidos.sortPasos();}
    public void busqueda(String id){this.listaAtendidos.busqueda(id);}
    
}
