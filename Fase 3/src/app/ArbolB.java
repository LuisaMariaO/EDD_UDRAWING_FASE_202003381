
package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Luisa María Ortiz
 */
public class ArbolB {
    Pagina raiz;
    int orden;
    int altura;
    String nombresNodos="";
    String conexiones ="";
    int cuentaClaves=0;
    
    public ArbolB(){
        this.raiz = null;
        this.orden = 5;
        this.altura = 0;
    }
    
   void insertar(String nombre, String password, long dpi){ 
        Cliente nuevo = new Cliente(nombre, password, dpi);
        if(this.raiz == null){
            this.raiz = new Pagina();
            this.raiz.raiz = true;
            this.raiz = (Pagina)this.raiz.insertar_pagina(nuevo);
            
        }else{
            if(this.altura == 0){ // no se a dividido la raiz
                Object respuesta_insertar = this.raiz.insertar_pagina(nuevo);
                if(respuesta_insertar instanceof Pagina){ // --------------------la raiz no se dividio 
                    this.raiz = (Pagina)respuesta_insertar; 
             
                    
                }else if(respuesta_insertar instanceof Cliente){ // -----------------la raiz se dividio
                    this.altura++;
                    this.raiz = new Pagina();
                    Cliente nuevo_raiz = (Cliente)respuesta_insertar;
                    this.raiz = (Pagina)this.raiz.insertar_pagina(nuevo_raiz);
                }
                else if(respuesta_insertar==null){
                    return;
                }
            }else{//-------ya hay mas de una pagina
                Object respuesta_insertar = insertar_recorrer(nuevo,this.raiz); 
                
                if(respuesta_insertar instanceof Cliente){ //---- la raiz se dividio

                    this.altura++;
                    this.raiz = new Pagina();
                    this.raiz.insertar_pagina((Cliente) respuesta_insertar);
                }else if(respuesta_insertar==null){
                    return;
                
                }else{
       
                    this.raiz= (Pagina)respuesta_insertar;
                }
            }
        }
    }
    
    Object insertar_recorrer(Cliente nuevo, Pagina raiz_actual){ 
        if(raiz_actual.Es_hoja(raiz_actual)){
                Object respuesta_insertar = raiz_actual.insertar_pagina(nuevo);
            if(respuesta_insertar!=null){
            return respuesta_insertar;
            }
            else{
                return null;
            }
        }else{ 
        
            Object respuesta_insertar;
            if( nuevo.dpi < raiz_actual.claves.primero.dpi){ //Se inserta antes
                respuesta_insertar = insertar_recorrer(nuevo,raiz_actual.claves.primero.izquierda);
                if(respuesta_insertar instanceof Cliente){ // la pagina hija se dividio 
                   
                    return raiz_actual.insertar_pagina((Cliente)respuesta_insertar);
                }else if(respuesta_insertar==null){
                    return null;
                }else{
                    
                   
                    raiz_actual.claves.primero.izquierda = (Pagina)respuesta_insertar;
                    return raiz_actual;
                }
            }else if(nuevo.dpi > raiz_actual.claves.ultimo.dpi){ //*---- si es mayor 
                respuesta_insertar = insertar_recorrer(nuevo,raiz_actual.claves.ultimo.derecha);
                if(respuesta_insertar instanceof Cliente){ //La página se dividió
                   
                    return raiz_actual.insertar_pagina((Cliente)respuesta_insertar);
                }else{
                
                    raiz_actual.claves.ultimo.derecha = (Pagina)respuesta_insertar;
                    return raiz_actual;
                }
            }else{
             
                Cliente pivote = raiz_actual.claves.primero;
                
                while(pivote != null){
                    if(nuevo.dpi < pivote.dpi){
                        respuesta_insertar = insertar_recorrer(nuevo,pivote.izquierda);
                        if(respuesta_insertar instanceof Cliente){
                            return raiz_actual.insertar_pagina((Cliente)respuesta_insertar);
                        }else{
                            pivote.izquierda = (Pagina)respuesta_insertar;
                            pivote.anterior.derecha = (Pagina) respuesta_insertar;
                            return raiz_actual;
                        }
                    }else if(nuevo.dpi == pivote.dpi){
                        return raiz_actual;
                    }else{
                        pivote = pivote.siguiente;
                    }
                }
            }
        }
        
       return this;
    }
    
    ///--------------Metodos adicionales--------------------
    
    public void graficar(){
        this.nombresNodos="";
        this.conexiones="";
        
        StringBuilder dot = new StringBuilder();
        
        dot.append("digraph G {\n");
        dot.append("node[shape=record];\n");
        cuentaClaves=0;
        
        this.recorrido(this.raiz);
        Pagina auxp = this.raiz;
     
        
         dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("}");
        
         try{
            File file = new File("Reportes de Administrador\\clientes.dot");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(dot.toString());
            bw.close();
        ProcessBuilder pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", "Reportes de Administrador\\Clientes.png", "Reportes de Administrador\\clientes.dot" );
        pbuilder.redirectErrorStream( true );
        pbuilder.start();
        }catch(Exception ex){System.out.println(ex.getMessage());}

        
    }
    
    public void recorrido(Pagina raiz){
        if(raiz != null){
            Cliente auxn = raiz.claves.primero;
            nombresNodos+="Nodo"+raiz.hashCode()+"[label=\"";
             while(auxn!=null){
            nombresNodos+="<S"+cuentaClaves+">|DPI: "+auxn.dpi+"\\n Nombre: "+auxn.nombre+"\\n Password: "+auxn.password+"|";
            if(auxn == raiz.claves.ultimo){
               
                nombresNodos+="<S"+(cuentaClaves+1)+">";
            }
            if(auxn.izquierda!=null && auxn==raiz.claves.primero){
                conexiones+="Nodo"+raiz.hashCode()+":S"+cuentaClaves+"->Nodo"+auxn.izquierda.hashCode()+";\n";
            }
            if(auxn.derecha!=null ){
               
                conexiones+="Nodo"+raiz.hashCode()+":S"+(cuentaClaves+1)+"->Nodo"+auxn.derecha.hashCode()+";\n";
            
            }
            auxn=auxn.siguiente;
            cuentaClaves++;
            
            
            
        }
            nombresNodos+="\"];\n";
            
            auxn=raiz.claves.primero;
            while(auxn!=null){
                
                        
                recorrido(auxn.izquierda);
                recorrido(auxn.derecha);
                auxn=auxn.siguiente;
            }
        }
        
        
    }
    
    public Cliente buscar(long dpi, String password){
     return this.buscar(this.raiz, dpi, password);
    }
    
    private Cliente buscar (Pagina raiz, long dpi, String password){
        if(raiz==null){return null;} //No encontró coincidencia
        else{
            Cliente aux = raiz.claves.primero;
            while(aux!=null){
                if(aux.dpi == dpi && aux.password.equals(password)){
                    return aux;
                }
                else if(dpi < aux.dpi){
                   return  buscar(aux.izquierda,dpi, password);
                }
                else if(dpi > aux.dpi){
                    if(aux.siguiente!=null){
                        if(dpi<aux.siguiente.dpi){return buscar(aux.derecha,dpi, password);}
                    }
                    else{
                       return buscar(aux.derecha,dpi, password); 
                    }
                   
                }
                aux = aux.siguiente;
            }
        }
        return null;
    }
    
    public Cliente buscar(long dpi){
     return this.buscar(this.raiz, dpi);
    }
    
    private Cliente buscar (Pagina raiz, long dpi){
        if(raiz==null){return null;} //No encontró coincidencia
        else{
            Cliente aux = raiz.claves.primero;
            while(aux!=null){
                if(aux.dpi == dpi ){
                    return aux;
                }
                else if(dpi < aux.dpi){
                   return  buscar(aux.izquierda,dpi);
                }
                else if(dpi > aux.dpi){
                    if(aux.siguiente!=null){
                        if(dpi<aux.siguiente.dpi){return buscar(aux.derecha,dpi);}
                    }
                    else{
                       return buscar(aux.derecha,dpi); 
                    }
                   
                }
                aux = aux.siguiente;
            }
        }
        return null;
    } 
    
}
