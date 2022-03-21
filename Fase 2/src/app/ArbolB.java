
package app;

/**
 *
 * @author Luisa María Ortiz
 */
public class ArbolB {
    final int orden_arbol=5;//El orden requerido para el arbol
    Pagina raiz;
    
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
}
