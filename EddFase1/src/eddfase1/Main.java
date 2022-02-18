
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
        
        Scanner screporte = new Scanner(System.in);
        Scanner sccliente = new Scanner(System.in);
       
        
        
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
                    if(tienda.listaVentanillas==null){
                    
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
                            tienda.ejecutarPaso();
                            break;
                        default:
                            System.out.println("Opción inválida");
                            break;
                    }
                    }else{
                        System.out.println("La tienda ya está configurada, inicie de nuevo la aplicación para utilizar otra configuración.");
                    }
   
                    break;
                case "2":
                    if(tienda.listaVentanillas!=null){
                    tienda.ejecutarPaso();
                    }
                    else{
                        System.out.println("No se ha establecido la configuración de la tienda.");
                    }
                    break;
                case "3":
                    tienda.graficar();
                    break;
                case "4":
                    menuReportes();
                    break;
                case "5":
                    System.out.println(" --------------------------------------------------------------- ");
                    System.out.println("|Luisa María Ortíz Romero                                       |");
                    System.out.println("|Registro académico: 202003381                                  |");
                    System.out.println("|Estudiante de 5to Semestre de Ingeniería en Ciencias y Sistemas|");
                    System.out.println("|Github: LuisaMariaO                                            |");
                    System.out.println("|Contacto: luisamaria.ortiz17@gmail.com                         |");
                    System.out.println(" --------------------------------------------------------------- ");
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
    String nombre;
    int bw, color,id;
    JSONParser parser = new JSONParser();
    //Creando la cola
    tienda.crearCola();
    try{
        Object obj = parser.parse(new FileReader(ruta));
        JSONObject jsonObject = (JSONObject) obj;
        
        for(int i=0; i<jsonObject.size();i++){
            JSONObject cliente = (JSONObject) jsonObject.get("Cliente"+(i+1));
            //Tomando los atributos de los clientes
            
            
            nombre = (String) cliente.get("nombre_cliente");
            
            id = Math.toIntExact((long)cliente.get("id_cliente"));
            color = Math.toIntExact((long)cliente.get("img_color")) ;
            bw= Math.toIntExact((long)cliente.get("img_bw")) ;
            //Agregando el cliente a la cola
            tienda.cola.enqueque("Cliente"+(i+1), String.valueOf(id), nombre, color, bw);
  
        }
  
        
    }   catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
           System.out.println(ex);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
    
    return tienda;
}

  public static void menuReportes(){
      Scanner screporte = new Scanner(System.in);
      Scanner sccliente = new Scanner(System.in);
      System.out.println("******MENÚ DE REPORTES******");
      System.out.println("1. Top 5 de clientes con mayor cantidad de imágenes a color");
      System.out.println("2. Top 5 de clientes con mayor cantidad de imágenes en blanco y negro");
      System.out.println("3. Cliente con más pasos en el sistema");
      System.out.println("4. Reporte por cliente");
      System.out.println("Ingrese una opción: ");
      String op = screporte.nextLine();
      
      switch(op){
          case "1":
              tienda.topColor();
              break;
          case "2":
              tienda.topBN();
              break;
          case "3":
              tienda.topPasos();
              break;
          case "4":
              System.out.println("Ingrese el id del cliente que desea buscar");
              String cliente = sccliente.nextLine();
              tienda.busqueda(cliente);
              break;
          default:
              System.out.println("Opción inválida");
              break;
      }
  }
}
