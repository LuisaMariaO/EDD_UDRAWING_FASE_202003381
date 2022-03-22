
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

}