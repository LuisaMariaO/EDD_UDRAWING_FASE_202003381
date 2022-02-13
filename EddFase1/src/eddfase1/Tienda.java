
package eddfase1;

public class Tienda {
    int paso;
    Cola cola;
    ListaVentanillas listaVentanillas;
    public Tienda(){
        this.paso=0;
        this.listaVentanillas=null;
        this.cola=null;
        
    }
    
    public void setVentanillas(int no){
        this.listaVentanillas=new ListaVentanillas();
        for(int i=0; i<no;i++){
            this.listaVentanillas.insertar("Ventanilla "+(i+1));
        }
        
        this.listaVentanillas.imprimir();
        
    }
    
    public void crearCola(){
        Cola nueva = new Cola();
        this.cola=nueva;
    }
    
}
