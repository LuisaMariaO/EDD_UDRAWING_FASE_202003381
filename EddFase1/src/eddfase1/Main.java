
package eddfase1;
import java.util.Scanner;
import eddfase1.Tienda;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;



/**
 *
 * @author Luisa María Ortiz
 */
public class Main {
    static Tienda tienda = new Tienda();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("*****UDrawing Paper*****");
        System.out.println("       Bienvenido       ");
        menu();
        
    }
    
    public static void menu(){
        //Area de Scanners
        Scanner sc = new Scanner(System.in);
        String option;
        
        Scanner scruta = new Scanner(System.in);
        Scanner scnum = new Scanner(System.in);
        Scanner sub1 = new Scanner(System.in);
        
       
        
        
        int ventanillas;
        String ruta;
    
        while(true){
        
            System.out.println("----------Menú----------");
            System.out.println("1. Parámetros iniciales");
            System.out.println("2. Ejecutar paso");
            System.out.println("3. Estado en memoria de las estructuras");
            System.out.println("4. Reportes");
            System.out.println("5. Acerca de");
            System.out.println("6. Salir");
            System.out.println("Seleccione una opción: " );
            option=sc.nextLine();
            
            switch(option){
                case "1":
                    System.out.println("Ingrese el número de ventanillas en la tienda: ");
                    ventanillas=scnum.nextInt();
                    tienda=inicializarVentanillas(ventanillas);
                   
                    
                    System.out.println("Ingrese la ruta del archivo para cargar clientes: ");
                    ruta = scruta.nextLine();
                    
                    tienda=cargaMasiva(ruta);
                    
                    System.out.println("\nSiguiente acción: ");
                    System.out.println("1. Regresar al menú principal");
                    System.out.println("2. Iniciar simulacion");
                    String op1 = sub1.nextLine();
                    switch(op1){
                        case "1":
                            menu();
                            break;
                        case "2":
                            break;
                        default:
                            System.out.println("Opción inválida");
                            break;
                    }
   
                    break;
                case "2":
                    tienda.ejecutarPaso();
                    break;
                case "3":
                    tienda.graficar();
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    System.exit(0);
                default:
                    System.out.println("Opción inválida");
                
            }
        
        }
    }
    
public static Tienda inicializarVentanillas(int ventanillas){
        //Inicializando las ventanillas
        tienda.setVentanillas(ventanillas);
        

    return tienda;
}

public static Tienda cargaMasiva(String ruta){
    String id, nombre, bw, color;
    JSONParser parser = new JSONParser();
    //Creando la cola
    tienda.crearCola();
    try{
        Object obj = parser.parse(new FileReader(ruta));
        JSONObject jsonObject = (JSONObject) obj;
        
        for(int i=0; i<jsonObject.size();i++){
            JSONObject cliente = (JSONObject) jsonObject.get("Cliente"+(i+1));
            //Tomando los atributos de los clientes
            id = (String) cliente.get("id_cliente");
            nombre = (String) cliente.get("nombre_cliente");
            color = (String) cliente.get("img_color");
            bw= (String) cliente.get("img_bw");
            //Agregando el cliente a la cola
            tienda.cola.enqueque("Cliente"+(i+1), id, nombre, Integer.valueOf(color), Integer.valueOf(bw));
  
        }
        tienda.cola.imprimir();
        
    }   catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
           System.out.println(ex);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
    
    return tienda;
}

    public static void graficar(){
        
    }
}
