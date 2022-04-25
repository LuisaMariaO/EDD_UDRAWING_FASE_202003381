
package app;

import javax.swing.JOptionPane;

/**
 *
 * @author Luisa María Ortiz
 */
public class Pagina {
    ListaClientes claves;
    boolean raiz;
    int claves_max;
    int claves_min;
    int tamano;
    
    public Pagina(){
         claves = new ListaClientes();
       raiz = false;
       claves_max = 4; // valores establecidos por el orden del arbol
       claves_min = 2;
       tamano =0;
       
    }
    
   Object insertar_pagina(Cliente nuevo){
       if(this.claves.insertar(nuevo)){
        
           this.tamano = this.claves.size;
           
            if(this.tamano <5){
                return this;
            }else if(this.tamano == 5){
                return dividir_pag(this);//Maximo de claves superada
            } 
       }
       else{
            JOptionPane.showMessageDialog(null, "El usuario "+nuevo.dpi+" ya está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
       }
       
       return null;
   }
   
   Cliente dividir_pag(Pagina pag){
       
       Cliente temp = pag.claves.primero;
       for(int i =0; i<2; i++){
           temp = temp.siguiente;
       }
       Cliente primero = pag.claves.primero;
       Cliente segundo = pag.claves.primero.siguiente;
       Cliente tercero = temp.siguiente;
       Cliente cuarto = pag.claves.ultimo;
       
       primero.siguiente=null;
       primero.anterior = null;
       
       segundo.siguiente = null;
       segundo.anterior = null;
       
       tercero.siguiente = null;
       tercero.anterior = null;
       
       cuarto.siguiente = null;
       cuarto.anterior = null;

       temp.anterior = null;
       temp.siguiente = null;
       
       Pagina izquierda = new Pagina();
       izquierda.insertar_pagina(primero);
       izquierda.insertar_pagina(segundo);
       
       Pagina derecha = new Pagina();
       derecha.insertar_pagina(tercero);
       derecha.insertar_pagina(cuarto);
       
       temp.izquierda = izquierda;
       temp.derecha = derecha;
       
     
       return temp;
   }
   
   boolean Es_hoja(Pagina pag){
       if(pag.claves.primero.izquierda == null){
           return true;
       }
      return false;
   }
    
  
}
