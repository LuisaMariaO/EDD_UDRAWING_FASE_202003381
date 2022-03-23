
package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Luisa María Ortiz
 */
public class ArbolB {
    final int orden_arbol=5;//El orden requerido para el arbol
    Pagina raiz;
    String nombresNodos="";
    String conexiones ="";
    int cuentaClaves=0;
    
    public ArbolB(){
        this.raiz = new Pagina(); //Página vacía desde la creación del árbol
    }
    
    public void insertar(String nombre, String password, long dpi){
        Cliente cliente = new Cliente(nombre, password, dpi);
        Cliente obj = insertarEnPagina(cliente, this.raiz);
        
        if(obj != null){
            //Si se retora el go significa que se creó una nueva rama que debe ser insertada en el árbol
            this.raiz = new Pagina();
            this.raiz.insertar(obj);
            this.raiz.hoja=false;
        }
    }
    
    private Cliente insertarEnPagina(Cliente nuevo, Pagina rama){
        if(rama.hoja){
            rama.insertar(nuevo);
            return (rama.contador == orden_arbol)?dividir(rama):null;//Si la pagina llega a 5 claves,se divide la rama
        }else{
            Cliente tmp = rama.primero;
            do{
                if(nuevo.dpi == tmp.dpi){
                    return null;
                }else if(nuevo.dpi < tmp.dpi){
                    Cliente obj = insertarEnPagina (nuevo, tmp.izquierda);
                    return ValidarDivision(obj,rama);
                }else if (tmp.siguiente == null){
                    Cliente obj = insertarEnPagina(nuevo, tmp.derecha);
                }
                tmp = (Cliente) tmp.siguiente;
            }while(tmp != null);
        }
        return null;
    }
    private Cliente dividir (Pagina rama){
        long val = -999;
        String name="";
        String pass="";
        Cliente tmp, Nuevo;
        Cliente aux = rama.primero;
        Pagina rderecha = new Pagina();
        Pagina rizquierda = new Pagina();
        
        int cont = 0;
        while(aux != null){
            cont++;
            if(cont < 3){
                tmp = new Cliente(aux.nombre, aux.password, aux.dpi, aux.izquierda, aux.derecha);
                rizquierda.hoja=!(tmp.derecha != null && tmp.izquierda!=null); //Si tiene ramas no es una hoja
                rizquierda.insertar(tmp);
            }else if(cont == 3){
                val = aux.dpi;
                name=aux.nombre;
                pass=aux.password;
            }else {
                tmp = new Cliente(aux.nombre, aux.password, aux.dpi, aux.izquierda, aux.derecha);
                rderecha.hoja=!(tmp.derecha!=null && tmp.izquierda !=null);
                rderecha.insertar(tmp);
            }
            aux = aux.siguiente;
        }
        Nuevo = new Cliente (name, pass, val, rizquierda, rderecha);
        return Nuevo;
    }
    private Cliente ValidarDivision (Cliente obj, Pagina rama){
        if(obj instanceof Cliente){
            rama.insertar((Cliente)obj);
            if(rama.contador == orden_arbol){
                return dividir (rama);
            }
        }
        return null;
    }
    
    public void graficar(){
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
            Cliente auxn = raiz.primero;
            nombresNodos+="Nodo"+raiz.hashCode()+"[label=\"";
             while(auxn!=null){
            nombresNodos+="<S"+cuentaClaves+">|DPI: "+auxn.dpi+"\\n Nombre: "+auxn.nombre+"\\n Password: "+auxn.password+"|";
            
            if(auxn.izquierda!=null){
                conexiones+="Nodo"+raiz.hashCode()+":S"+cuentaClaves+"->Nodo"+auxn.izquierda.hashCode()+";\n";
            }
            if(auxn.derecha!=null){
               
                conexiones+="Nodo"+raiz.hashCode()+":S"+(cuentaClaves+1)+"->Nodo"+auxn.derecha.hashCode()+";\n";
            
            }
            auxn=auxn.siguiente;
            cuentaClaves++;
            
            
            
        }
            nombresNodos+="\"];\n";
            
            auxn=raiz.primero;
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
            Cliente aux = raiz.primero;
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
    
}
