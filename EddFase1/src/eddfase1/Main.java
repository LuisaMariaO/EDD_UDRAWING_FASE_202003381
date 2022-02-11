
package eddfase1;
import java.util.Scanner;
import eddfase1.Tienda;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/**
 *
 * @author Luisa María Ortiz
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("*****UDrawing Paper*****");
        System.out.println("       Bienvenido       ");
        Scanner sc = new Scanner(System.in);
        String option;
        
        Tienda tienda = new Tienda();
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
                    ventanillas=sc.nextInt();
                   
                    
                    System.out.println("Ingrese la ruta del archivo para cargar clientes: ");
                    ruta = sc.nextLine();
                    tienda=inicializarTienda(tienda, ventanillas, ruta);
                    
                    break;
                case "2":
                    break;
                case "3":
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
    
public static Tienda inicializarTienda(Tienda tienda, int ventanillas, String ruta){
    return tienda;
}
}
