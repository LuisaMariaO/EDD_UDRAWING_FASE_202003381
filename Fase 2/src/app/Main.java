
package app;
import interfaz.Login;

/**
 *
 * @author Luisa María Ortiz
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Manage manage = new Manage();//Las estructuras principales se inicializan desde ahora
        Login login = new Login(manage);
        login.setVisible(true);
    }
    
}
