
package app;

/**
 *
 * @author Luisa María Ortiz
 */
public class ArbolBB<E extends Comparable<E>>{
    Capa<E> raiz;
    String nombresNodos, conexiones;
    public ArbolBB(){
        this.raiz=null;
    }

    public void insertar(E id, Matriz matriz){
        raiz=insertarNodo(raiz,id,matriz);
    }

    private Capa<E> insertarNodo(Capa<E> raiz, E id,Matriz matriz){

        if (raiz==null){
            raiz=new Capa<E>(id,matriz);
        }
        else if (id.compareTo(raiz.id)< 0)
            raiz.izquierdo=insertarNodo(raiz.izquierdo,id,matriz);
        else if(id.compareTo(raiz.id)> 0)
            raiz.derecho=insertarNodo(raiz.derecho, id, matriz);
        //TODO: Validar la insercion de claves repetidas :D
        return raiz;
    }

    public void preOrden(){
        preOrden(this.raiz);
    }

    public void inOrden(){
        inOrden(this.raiz);
    }

    public void postOrden(){
        postOrden(this.raiz);
    }

    private void preOrden(Capa<E> raiz){
        if(raiz != null){
            System.out.print(raiz.id.toString());
            preOrden(raiz.izquierdo);
            preOrden(raiz.derecho);
        }
    }
    
    private void inOrden(Capa<E> raiz){
        if(raiz != null){
            preOrden(raiz.izquierdo);
            System.out.print(raiz.id.toString());
            preOrden(raiz.derecho);
        }
    }

    private void postOrden(Capa<E> raiz){
        if(raiz != null){
            preOrden(raiz.izquierdo);
            preOrden(raiz.derecho);
            System.out.print(raiz.id.toString());
        }
    }



    public Capa buscar(E id){
        return buscar(this.raiz,id);
    }

    private Capa buscar(Capa<E> raiz, E id){
        if (raiz ==  null) return null;
        else if (id.compareTo(raiz.id) == 0) return raiz;
        //si el resultado del metodo comparteTo es negativo significa que la raiz es mayor
        else if(id.compareTo(raiz.id) < 0) return buscar(raiz.izquierdo, id);
        //si el resultado del metodo comparteTo es  positivo significa que la raiz es menor
        else return buscar(raiz.derecho, id);

    }

   

    


}