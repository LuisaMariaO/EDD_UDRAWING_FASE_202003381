
package app;

import java.io.File;
import java.io.FileReader;
import javax.swing.JLabel;
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
public void cargarImagenes(Cliente cliente, File ruta){//VERIFICAR LA FORMA DE PARSEO
        JSONParser parser2 = new JSONParser();
     try{
        Object obj = parser2.parse(new FileReader(ruta));
        JSONArray jsonArray = (JSONArray) obj;
        
        for(int i=0; i<jsonArray.size();i++){
            JSONObject Oimagen = (JSONObject) jsonArray.get(i);
            //Inserto la imgagen en el arbol de imagenes
            int id = Math.toIntExact((long)Oimagen.get("id"));
            cliente.arbolImagenes.raiz = cliente.arbolImagenes.insertar(cliente.arbolImagenes.raiz, id);
            
            JSONArray idcapas = (JSONArray) Oimagen.get("capas");
            Imagen imagen = cliente.arbolImagenes.buscar(id);
            for(int j=0;j<idcapas.size();j++){
                Matriz matriz = cliente.arbolCapas.buscar(Long.toString((long) idcapas.get(j))).matriz;
                imagen.capas.insertar(Long.toString((long)idcapas.get(j)), matriz);
            }
  
        }
         
         JOptionPane.showMessageDialog(null, "¡Carga finalizada!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Ocurrió un error en la carga masiva", "Error", JOptionPane.ERROR_MESSAGE);
        System.out.println(e);
    }
    
}

    public void cargarAlbumes(Cliente cliente, File ruta){
     JSONParser parser = new JSONParser();
     try{
        Object obj = parser.parse(new FileReader(ruta));
        JSONArray jsonArray = (JSONArray) obj;
        
        for(int i=0; i<jsonArray.size();i++){
            JSONObject Oalbum = (JSONObject) jsonArray.get(i);
            //Inserto el album en la lista
            String nombre = (String)Oalbum.get("nombre_album");
            Album album = new Album(nombre);
            cliente.albumes.insertar(album);
            JSONArray imagenes = (JSONArray) Oalbum.get("imgs");
            
            for(int j=0;j<imagenes.size();j++){
                Imagen imagen = cliente.arbolImagenes.buscar(Math.toIntExact((long)imagenes.get(j)));
                album.imagenes.insertarFinal(imagen);
              
            }
  
        }
         
         JOptionPane.showMessageDialog(null, "¡Carga finalizada!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Ocurrió un error en la carga de álbumes", "Error", JOptionPane.ERROR_MESSAGE);
        System.out.println(e);
    }
    }
    
    public void graficarImagenes(Cliente cliente){
        cliente.arbolImagenes.graficar(String.valueOf(cliente.dpi));
    }
    
    public void graficarCapas(Cliente cliente){
        cliente.arbolCapas.graficar(String.valueOf(cliente.dpi));
    }
    public void graficarAlbumes(Cliente cliente){
        cliente.albumes.graficar(String.valueOf(cliente.dpi));
    }
    
    public void graficarImgCapas(Cliente cliente, Imagen imagen){
        cliente.arbolImagenes.graficar(String.valueOf(cliente.dpi), imagen.id);
    }
    public void generarImgPreorder(Cliente cliente, int limite,JLabel recorrido){
         Matriz imgMatriz = new Matriz("Imagen");
         imgMatriz = cliente.arbolCapas.generarPreorden(imgMatriz,limite,recorrido);
         imgMatriz.graficaApicacion(String.valueOf(cliente.dpi));
        }
    public void generarImgInorder(Cliente cliente, int limite, JLabel recorrido){
        Matriz imgMatriz = new Matriz("Imagen");
         imgMatriz = cliente.arbolCapas.generarInOrden(imgMatriz,limite,recorrido);
         imgMatriz.graficaApicacion(String.valueOf(cliente.dpi));
    }
    public void generarImgPostorder(Cliente cliente, int limite, JLabel recorrido){
        Matriz imgMatriz = new Matriz("Imagen");
         imgMatriz = cliente.arbolCapas.generarPostOrden(imgMatriz,limite,recorrido);
         imgMatriz.graficaApicacion(String.valueOf(cliente.dpi));
    }
    public void generarImg(Cliente cliente, int idimg, JLabel etiqueta){
        
        Imagen img = cliente.arbolImagenes.buscar(idimg);//Imagen que se busca
        if(img!=null){
            Matriz imgMatriz = new Matriz("Imagen");
            imgMatriz = img.capas.amplitud(imgMatriz, etiqueta);
            imgMatriz.graficaApicacion(String.valueOf(cliente.dpi));
        }
        else{
            JOptionPane.showMessageDialog(null, "No se encontró la imagen "+idimg, "Error", JOptionPane.ERROR_MESSAGE);
        }
 
    }
    
    public void generarPorCapas(Cliente cliente, String[] capas, JLabel label){
        Matriz imgMatriz = new Matriz("Imagen");
        cliente.arbolCapas.generarCapas(imgMatriz, label, capas);
        imgMatriz.graficaApicacion(String.valueOf(cliente.dpi));
    }
        
    
}