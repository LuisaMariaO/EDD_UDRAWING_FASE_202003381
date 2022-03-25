
package app;

import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/**
 *
 * @author Luisa María Ortiz
 */
public class Manage {
   ArbolB arbolB = new ArbolB(); //Arbol de clientes


public void cargaMasiva(File ruta){
    JSONParser parser = new JSONParser();
    try{
        Object obj = parser.parse(new FileReader(ruta));  
        JSONArray jsonArray = (JSONArray) obj;

         for(int i=0; i<jsonArray.size();i++){ 
             JSONObject cliente = (JSONObject) jsonArray.get(i);
             long dpi = Long.valueOf((String) cliente.get("dpi"));
             arbolB.insertar((String)cliente.get("nombre_cliente"),(String)cliente.get("password"),dpi);
         }
         
         JOptionPane.showMessageDialog(null, "¡Carga finalizada!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Ocurrió un error en la carga masiva", "Error", JOptionPane.ERROR_MESSAGE);
        System.out.println(e);
    }
    
}

public boolean registrar(String nombre, String password, long dpi){
    try{
    arbolB.insertar(nombre, password, dpi);
    return true;
    }catch(Exception e){return false;}
}

public void graficarClientes(){
    this.arbolB.graficar();
}
public Cliente iniciarSesion(String usuario, String password){
    long dpi=0;
    try{
        dpi = Long.valueOf(usuario);
    }catch(NumberFormatException e){JOptionPane.showMessageDialog(null, "Ingrese un usuario válido", "Error", JOptionPane.ERROR_MESSAGE);}
    
    return this.arbolB.buscar(dpi, password);
    
}

public void cargarCapas(Cliente cliente,File ruta){
    
    JSONParser parser = new JSONParser();
    try{
        Object obj = parser.parse(new FileReader(ruta));  
        JSONArray jsonArray = (JSONArray) obj;

         for(int i=0; i<jsonArray.size();i++){
            
             JSONObject capa = (JSONObject) jsonArray.get(i);
             Matriz matriz = new Matriz(String.valueOf(capa.get("id_capa")));
             JSONArray pixeles = (JSONArray) capa.get("pixeles");
             //System.out.println(matriz.id);
            for(int j=0; j<pixeles.size();j++){
                JSONObject pixel = (JSONObject) pixeles.get(j);
                matriz.insertarNodo(Math.toIntExact((long)pixel.get("columna")), Math.toIntExact((long)pixel.get("fila")), String.valueOf(pixel.get("color")));
            }
           cliente.arbolCapas.insertar(matriz.id, matriz);//Guardo la capa en el Arbol BB
         }
         
         JOptionPane.showMessageDialog(null, "¡Carga finalizada!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Ocurrió un error en la carga masiva", "Error", JOptionPane.ERROR_MESSAGE);
        System.out.println(e);
    }
}
}