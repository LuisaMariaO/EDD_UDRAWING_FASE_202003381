
package eddfase1;

/**
 *
 * @author Luisa María Ortiz
 */
public class ListaAtendidos {
    Cliente primero,ordenado;
    int size;
    
    public ListaAtendidos(){
        this.primero=null;
        this.ordenado=null;
        this.size=0;
    }
    
    public void insertarFinal(Cliente nuevo){

        Cliente actual = this.primero;
        if(this.estaVacia()){
            this.primero=nuevo;
                    }
        else{
          while(actual.siguiente!=null){
              actual=actual.siguiente;
          }
          actual.siguiente=nuevo;
          
        }
        
        this.size++;

    }
    
    public boolean estaVacia(){
        return this.primero==null && this.size==0;
        
    }
    
    public void imprimir(){
        Cliente actual = this.primero;
        
        while(actual!=null){
            System.out.println(actual.titulo);
            actual=actual.siguiente;
        }
        this.primero = this.ordenado;
    }
    
    public void sortColor(){//Por inserción
        this.ordenado=null;
        Cliente actual = this.primero;
        while(actual!=null){
            Cliente siguiente=actual.siguiente;
            insertarOrdenadoC(actual);
            actual=siguiente;
            
    }
        this.primero=ordenado;
        
        //Imprimiendo los primeros 5 lugares
        System.out.println("******TOP 5 DE CLIENTES CON MAYOR CANTIDAD DE IMÁGENES A COLOR******");
        actual=this.primero;
        int contador=0;
        
        while(actual!=null){
            contador++;
            System.out.println("\n"+contador+". "+actual.titulo);
            System.out.println("Id: "+actual.id);
            System.out.println("Nombre: "+actual.nombre);
            System.out.println("Imágenes a color: "+actual.img_color);
            
            if(contador==5){break;}
            actual=actual.siguiente;
        }
        
        
    }
    public void insertarOrdenadoC(Cliente nuevo){
        if(this.ordenado==null || this.ordenado.img_color<nuevo.img_color){
            nuevo.siguiente=this.ordenado;
            this.ordenado=nuevo;
        }
        else{
            Cliente actual = this.ordenado;
            while(actual.siguiente!=null && actual.siguiente.img_color>nuevo.img_color){
                actual=actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
    }
    //Ordenando para el top de imágenes en blanco y negro
    public void sortBN(){//Por inserción
        this.ordenado=null;
        Cliente actual = this.primero;
        while(actual!=null){
            Cliente siguiente=actual.siguiente;
            insertarOrdenadoBN(actual);
            actual=siguiente;
            
    }
        this.primero=ordenado;
        
        //Imprimiendo los primeros 5 lugares
        System.out.println("******TOP 5 DE CLIENTES CON MAYOR CANTIDAD DE IMÁGENES EN BLANCO Y NEGRO******");
        actual=this.primero;
        int contador=0;
        
        while(actual!=null){
            contador++;
            System.out.println("\n"+contador+". "+actual.titulo);
            System.out.println("Id: "+actual.id);
            System.out.println("Nombre: "+actual.nombre);
            System.out.println("Imágenes en Blanco y Negro: "+actual.img_bw);
            
            if(contador==5){break;}
            actual=actual.siguiente;
        }
        
        
    }
    public void insertarOrdenadoBN(Cliente nuevo){
        if(this.ordenado==null || this.ordenado.img_bw<nuevo.img_bw){
            nuevo.siguiente=this.ordenado;
            this.ordenado=nuevo;
        }
        else{
            Cliente actual = this.ordenado;
            while(actual.siguiente!=null && actual.siguiente.img_bw>nuevo.img_bw){
                actual=actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
    }
    
    //Cliente con más pasos
    
     public void sortPasos(){//Por inserción
        this.ordenado=null;
        Cliente actual = this.primero;
        while(actual!=null){
            Cliente siguiente=actual.siguiente;
            insertarOrdenadoPasos(actual);
            actual=siguiente;
            
    }
        this.primero=ordenado;
        
        //Imprimiendo la información
        System.out.println("******CLIENTE CON MÁS PASOS REGISTRADOS******");
        actual=this.primero;
            System.out.println("\n"+actual.titulo);
            System.out.println("Id: "+actual.id);
            System.out.println("Nombre: "+actual.nombre);
            System.out.println("Pasos registrados: "+actual.pasos);
            System.out.println("Atendió: "+actual.ventanilla.nombre);
            System.out.println("Imágenes a color: "+actual.img_color);
            System.out.println("Imágenes en blanco y negro: "+actual.img_bw);
            System.out.println("Total de imágenes: "+actual.img_total);
            
      
        
    }
    public void insertarOrdenadoPasos(Cliente nuevo){
        if(this.ordenado==null || this.ordenado.pasos<nuevo.pasos){
            nuevo.siguiente=this.ordenado;
            this.ordenado=nuevo;
        }
        else{
            Cliente actual = this.ordenado;
            while(actual.siguiente!=null && actual.siguiente.pasos>nuevo.pasos){
                actual=actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
    }
    
    //Busqueda de cliente
    public void busqueda(String id){
        Cliente actual= this.primero;
        while(actual!=null){
            if(actual.id.equals(id)){
                System.out.println("******CLIENTE ENCONTRADO******");
                System.out.println("\n"+actual.titulo);
                System.out.println("Id: "+actual.id);
                System.out.println("Nombre: "+actual.nombre);
                System.out.println("Pasos registrados: "+actual.pasos);
                System.out.println("Atendió: "+actual.ventanilla.nombre);
                System.out.println("Imágenes a color: "+actual.img_color);
                System.out.println("Imágenes en blanco y negro: "+actual.img_bw);
                System.out.println("Total de imágenes: "+actual.img_total);
                break;
            }
            actual=actual.siguiente;
        }
        if(actual==null){System.out.println("CLIENTE NO ENCONTRADO");}
    }
    
    }
    
    
    

