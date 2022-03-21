
package app;

import javax.swing.JOptionPane;

/**
 *
 * @author Luisa María Ortiz
 */
public class Pagina {
    boolean hoja; //¿Es una hoja?
    int contador;
    Cliente primero;
    
    public Pagina(){
        this.primero=null;
        this.hoja=true;
        this.contador=0;
       
    }
    
    public void insertar(Cliente nuevo){
        if(this.primero==null){//Primero en la lista
            this.primero=nuevo;
            contador++;
        }
        else{
            Cliente aux = this.primero; //Para recorrer
            while (aux!=null){
                if (aux.dpi == nuevo.dpi){
                   JOptionPane.showMessageDialog(null, "El DPI "+nuevo.dpi+" ya fue registrado anteriormente", 
                           "Error", JOptionPane.ERROR_MESSAGE);
                   break;
                }else{
                    if(aux.dpi > nuevo.dpi){
                       if(aux == this.primero){//inserta al inicio
                           aux.anterior=nuevo;
                           nuevo.siguiente=aux;
                           //Ramas
                           aux.izquierda = nuevo.derecha;
                           nuevo.derecha=null;
                           
                           this.primero=nuevo;
                           this.contador++;
                           break;
                       }else{//inserta en el medio
                           nuevo.siguiente = aux;
                           //Ramas
                           aux.izquierda = nuevo.derecha;
                           nuevo.derecha = null;
                           
                           nuevo.anterior = aux.anterior;
                           aux.anterior.siguiente=nuevo;
                           aux.anterior=nuevo;
                           this.contador++;
                           break;
                       } 
                    }else if(aux.siguiente == null){//Inserta al final
                        aux.siguiente = nuevo;
                        nuevo.anterior = aux;
                        this.contador++;
                        break;
                    }
                }
                aux = aux.siguiente;
            }
        }
    }
    
    
  
}
