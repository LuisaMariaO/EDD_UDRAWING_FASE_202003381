
package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Luisa María Ortiz
 */
public class ArbolBB<E extends Comparable<E>>{
    Capa<E> raiz;
    String nombresNodos, conexiones,textoRecorrido,hojas;
    Matriz img;
    int no_capas,contador;
    int limite,recorrido;
    public ArbolBB(){
        this.raiz=null;
        this.no_capas=0;
    }

    public void insertar(E id, Matriz matriz){
        this.raiz=insertarNodo(this.raiz,id,matriz);
    }

    private Capa<E> insertarNodo(Capa<E> raiz, E id,Matriz matriz){

        if (raiz==null){
            this.no_capas++;
            raiz=new Capa<E>(id,matriz);
        }
        else if (Integer.valueOf((String) id)<Integer.valueOf((String) raiz.id)){
            raiz.izquierdo=insertarNodo(raiz.izquierdo,id,matriz);
        }
        else if(Integer.valueOf((String)id) > Integer.valueOf((String)raiz.id)){
            raiz.derecho=insertarNodo(raiz.derecho, id, matriz);
        }
        //TODO: Validar la insercion de claves repetidas :D
        
        return raiz;
    }

    public void preOrden(){
        nombresNodos="";
        conexiones="";
        preOrden(this.raiz);

        //System.out.println(nombresNodos);
        //System.out.println(conexiones);
    }

 

    private void preOrden(Capa<E> raiz){
        
        if(raiz != null){
            nombresNodos+="Nodo"+raiz.hashCode()+"[label=\""+raiz.id+"\" shape=circle fillcolor=cornflowerblue]\n";
            if(raiz.derecho!=null){
            conexiones+="Nodo"+raiz.hashCode()+"->Nodo"+raiz.derecho.hashCode()+"\n";
        }
            if(raiz.izquierdo!=null){
                conexiones+="Nodo"+raiz.hashCode()+"->Nodo"+raiz.izquierdo.hashCode()+"\n";
            }
            System.out.print(raiz.id.toString()+" ");
            preOrden(raiz.izquierdo);
            preOrden(raiz.derecho);
        }
    }
    
 



    public Capa buscar(E id){
        return buscar(this.raiz,id);
    }

    private Capa buscar(Capa<E> raiz, E id){
        if (raiz ==  null) return null;
        else if (Integer.valueOf((String)id) == Integer.valueOf((String)raiz.id)) return raiz;
        else if(Integer.valueOf((String)id) < Integer.valueOf((String)raiz.id)) return buscar(raiz.izquierdo, id);
        else return buscar(raiz.derecho, id);

    }

    public void graficar(String cliente){
        StringBuilder dot = new StringBuilder();
        
        dot.append("digraph G {\n");
        dot.append("node[style=filled, fillcolor=cornflowerblue, shape=circle];\n");
        dot.append("label=\"Árbol de Capas\"");
        this.preOrden();
        
        dot.append(nombresNodos);
        dot.append(conexiones);
        dot.append("}");
    try{
           
            File file = new File("Reportes de Usuario\\"+cliente);
            if(!file.exists()){file.mkdirs();}//Si no existe la carpeta, se crea
            
           
            file = new File("Reportes de Usuario\\"+cliente+"\\ArbolCapas.dot");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(dot.toString());
            bw.close();
        ProcessBuilder pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", "Reportes de Usuario\\"+cliente+"\\ArbolCapas.png", "Reportes de Usuario\\"+cliente+"\\ArbolCapas.dot" );
        pbuilder.redirectErrorStream( true );
        pbuilder.start();
        }catch(Exception ex){System.out.println(ex.getMessage());}
    }
    
    public String escribirDot(){
        this.preOrden();
        return nombresNodos+conexiones;
    }
 
        
    public Matriz generarPreorden(Matriz imgMatriz, int limite,JLabel labelRecorrido){
        this.img=imgMatriz;
        this.limite=limite;
        this.recorrido=0;
        this.textoRecorrido="Recorrido Preorder= ";
        generarPreOrden(this.raiz,imgMatriz);
        labelRecorrido.setText(this.textoRecorrido);
        return imgMatriz;
        
    }
    private void generarPreOrden(Capa<E> raiz, Matriz imgMatriz){
        if(this.recorrido==this.limite){
            return;
        }
        if(raiz!=null){
        this.recorrido++;
        this.textoRecorrido+=raiz.id+" ";
        Nodo aux = raiz.matriz.raiz;
        Nodo referencia = raiz.matriz.raiz;
        while(aux!=null){
            if(!aux.color.equals("Raiz") && !aux.color.equals("Col") && !aux.color.equals("Row")){
                imgMatriz.insertarNodo(aux.i, aux.j, aux.color);
            }
            aux=aux.siguiente;
            if(aux==null){
                if(referencia.abajo!=null){
                    referencia=referencia.abajo;
                    aux=referencia;
                }
            }
        }
        
        generarPreOrden(raiz.izquierdo,imgMatriz);
        generarPreOrden(raiz.derecho,imgMatriz);
        }
        
        
        
    
    }
   
    public Matriz generarInOrden(Matriz imgMatriz, int limite,JLabel labelRecorrido){
        this.img=imgMatriz;
        this.limite=limite;
        this.recorrido=0;
        this.textoRecorrido="Recorrido Inorder= ";
        generarInOrden(this.raiz,imgMatriz);
        labelRecorrido.setText(this.textoRecorrido);
        return imgMatriz;
        
    }
    private void generarInOrden(Capa<E> raiz, Matriz imgMatriz){
        
        if(raiz!=null){
        generarInOrden(raiz.izquierdo,imgMatriz);
        if(this.recorrido==this.limite){
            return;
        }
        this.recorrido++;
        this.textoRecorrido+=raiz.id+" ";
        Nodo aux = raiz.matriz.raiz;
        Nodo referencia = raiz.matriz.raiz;
        while(aux!=null){
            if(!aux.color.equals("Raiz") && !aux.color.equals("Col") && !aux.color.equals("Row")){
                imgMatriz.insertarNodo(aux.i, aux.j, aux.color);
            }
            aux=aux.siguiente;
            if(aux==null){
                if(referencia.abajo!=null){
                    referencia=referencia.abajo;
                    aux=referencia;
                }
            }
        }
        
        
        generarInOrden(raiz.derecho,imgMatriz);
        }
        
        
        
    
    }
    
    public Matriz generarPostOrden(Matriz imgMatriz, int limite,JLabel labelRecorrido){
        this.img=imgMatriz;
        this.limite=limite;
        this.recorrido=0;
        this.textoRecorrido="Recorrido Postorder= ";
        generarPostOrden(this.raiz,imgMatriz);
        labelRecorrido.setText(this.textoRecorrido);
        return imgMatriz;
        
    }
    private void generarPostOrden(Capa<E> raiz, Matriz imgMatriz){
        
        if(raiz!=null){
        generarPostOrden(raiz.izquierdo,imgMatriz);
        generarPostOrden(raiz.derecho,imgMatriz);
        if(this.recorrido==this.limite){
            return;
        }
        this.recorrido++;
        this.textoRecorrido+=raiz.id+" ";
        Nodo aux = raiz.matriz.raiz;
        Nodo referencia = raiz.matriz.raiz;
        while(aux!=null){
            if(!aux.color.equals("Raiz") && !aux.color.equals("Col") && !aux.color.equals("Row")){
                imgMatriz.insertarNodo(aux.i, aux.j, aux.color);
            }
            aux=aux.siguiente;
            if(aux==null){
                if(referencia.abajo!=null){
                    referencia=referencia.abajo;
                    aux=referencia;
                }
            }
        }
        
        
   
        }

    }
    
    public Matriz amplitud(Matriz imgMatriz, JLabel recorrido){
       this.img=imgMatriz;
       this.textoRecorrido="Recorrido en amplitud del árbol de capas= ";
       amplitud(this.raiz,imgMatriz);
       recorrido.setText(this.textoRecorrido);
       return imgMatriz;
    }
    
    private void amplitud(Capa<E> raiz, Matriz imgMatriz){
        
            Capa capa =raiz;
            Cola cola = new Cola();//Cola auxiliar para realizar el recorrido
            Capa tmp = null;
            if(capa != null){
                cola.enqueque(capa);
            while(cola.size>0){
                tmp=cola.dequeque();
                this.textoRecorrido+=tmp.id+" ";
                Nodo aux = tmp.matriz.raiz;
                Nodo referencia = tmp.matriz.raiz;
                while(aux!=null){
                if(!aux.color.equals("Raiz") && !aux.color.equals("Col") && !aux.color.equals("Row")){
                    imgMatriz.insertarNodo(aux.i, aux.j, aux.color);
                }
                aux=aux.siguiente;
                if(aux==null){
                    if(referencia.abajo!=null){
                        referencia=referencia.abajo;
                        aux=referencia;
                    }
                }
                }
               if(tmp.izquierdo!=null){
                   cola.enqueque(tmp.izquierdo);
               }
               if(tmp.derecho!=null){
                   cola.enqueque(tmp.derecho);
               }
            }    
            }
        }
    
    public Matriz generarCapas(Matriz imgMatriz, JLabel label, String[]capas){
        this.img=imgMatriz;
        this.textoRecorrido="Capas graficadas: ";
        for(String c : capas){
            Capa<E> capa = this.buscar((E) c);
            if(capa!=null){
                generarCapas(imgMatriz,capa);
            }
            else{
                 JOptionPane.showMessageDialog(null, "No se encontró la capa "+c, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        label.setText(this.textoRecorrido);
        return imgMatriz;
    }
    
    private void generarCapas(Matriz imgMatriz,Capa capa){
            this.textoRecorrido+=capa.id+" ";
                Nodo aux = capa.matriz.raiz;
                Nodo referencia = capa.matriz.raiz;
                while(aux!=null){
                if(!aux.color.equals("Raiz") && !aux.color.equals("Col") && !aux.color.equals("Row")){
                    imgMatriz.insertarNodo(aux.i, aux.j, aux.color);
                }
                aux=aux.siguiente;
                if(aux==null){
                    if(referencia.abajo!=null){
                        referencia=referencia.abajo;
                        aux=referencia;
                    }
                }
                }
    }
    
    public String reporteHojas(){
        String tabla="";
        StringBuilder dot = new StringBuilder();
        dot.append("subgraph cluster_1{\ncolor=none\n");
        dot.append("label=\"Capas que son hoja\";\n");
        tabla+="Nodo1[shape=none label=<\n<TABLE BORDER=\"0\" CELLSPACING=\"1\" CELLPADDING=\"1\"\n>";

        tabla+="<TR>\n";
        tabla+="<TD BGCOLOR=\"#A569BD\">No.</TD>\n";
        tabla+="<TD BGCOLOR=\"#C39BD3\">Id capa</TD>\n";
        
        tabla+="</TR>\n";
        this.hojas="";
        contador=0;
        
        
        dot.append(tabla);
        encontrarHojas(this.raiz);
        dot.append(this.hojas);
        dot.append("</TABLE>>]\n}\n");
   
        return dot.toString();
        
    }
    
    public void encontrarHojas(Capa raiz){
        if(raiz!=null){
            if(raiz.izquierdo==null && raiz.derecho==null){
                this.contador++;
                this.hojas+="<TR>\n";
                this.hojas+="<TD BGCOLOR=\"#A569BD\">"+contador+"</TD>\n";
                this.hojas+="<TD BGCOLOR=\"#C39BD3\">"+raiz.id+"</TD>\n";
                this.hojas+="</TR>\n";
            }
            
            encontrarHojas(raiz.izquierdo);
            encontrarHojas(raiz.derecho);
        }
    }
    public String reportarProfundidad(){
        String tabla="";
        StringBuilder dot = new StringBuilder();
        dot.append("subgraph cluster_2{\ncolor=none\n");
        dot.append("label=\"Profundidad del árbol de capas\";\n");
        tabla+="Nodo2[shape=none label=<\n<TABLE BORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"0\"\n>";

        tabla+="<TR>\n";
        tabla+="<TD BGCOLOR=\"#2980B9\">Profundidad</TD>\n";
        tabla+="</TR>\n";
        
        tabla+="<TR>\n";
        tabla+="<TD BGCOLOR=\"#2980B9\">"+encontrarProfundidad(this.raiz)+"</TD>\n";
        tabla+="</TR>\n";
        dot.append(tabla);
        dot.append("</TABLE>>]\n}\n");
       
        return dot.toString();
    }
    private int encontrarProfundidad(Capa raiz){
	if(raiz == null){
		return 0;
	}
	int nizquierda = encontrarProfundidad(raiz.izquierdo);
	int nderecha = encontrarProfundidad(raiz.derecho);
        //Retorna el nivel mayor +1
        if(nizquierda >nderecha){
            return nizquierda+1;
        }
        else{
            return nderecha+1;
        }
	
}
    
    public String reportarRecorrido(){
        this.nombresNodos="";
       String tabla="";
        StringBuilder dot = new StringBuilder();
        dot.append("subgraph cluster_3{\ncolor=none\n");
        dot.append("label=\"Recorridos de árbol de capas\";\n");
        tabla+="Nodo3[shape=none label=<\n<TABLE BORDER=\"0\" CELLSPACING=\"1\" CELLPADDING=\"1\">\n";

        tabla+="<TR>\n";
        tabla+="<TD BGCOLOR=\"#F4D03F\">Preorden</TD>\n";
        tabla+="</TR>\n";

        
        this.nombresNodos+="<TR>\n";
        this.nombresNodos+="<TD BGCOLOR=\"#F4D03F\"><FONT COLOR=\"#F4D03F\">Preorden</FONT></TD>";
        rpreOrden(this.raiz);
        this.nombresNodos+="</TR>\n";
        dot.append(tabla);
        dot.append(this.nombresNodos);
        dot.append("</TABLE>>]\n\n");
        //Para inorden 
        this.nombresNodos="";
        tabla="";
        
        tabla+="Nodo4[shape=none label=<\n<TABLE BORDER=\"0\" CELLSPACING=\"1\" CELLPADDING=\"1\">\n";

        tabla+="<TR>\n";
        tabla+="<TD BGCOLOR=\"#58D68D\">Inorden</TD>\n";
        tabla+="</TR>\n";
       
        
        this.nombresNodos+="<TR>\n";
         this.nombresNodos+="<TD BGCOLOR=\"#58D68D\"><FONT COLOR=\"#58D68D\">Inorden</FONT></TD>";
        inOrden(this.raiz);
        this.nombresNodos+="</TR>\n";
        dot.append(tabla);
        dot.append(this.nombresNodos);
        dot.append("</TABLE>>]\n\n");
        
        //Para post orden
        this.nombresNodos="";
        tabla="";
        tabla+="Nodo5[shape=none label=<\n<TABLE BORDER=\"0\" CELLSPACING=\"1\" CELLPADDING=\"1\">\n";

        tabla+="<TR>\n";
        tabla+="<TD BGCOLOR=\"#1ABC9C\">Postorden</TD>\n";
        tabla+="</TR>\n";
        
        
        this.nombresNodos+="<TR>\n";
        this.nombresNodos+="<TD BGCOLOR=\"#1ABC9C\"><FONT COLOR=\"#1ABC9C\">Postorden</FONT></TD>";
        postOrden(this.raiz);
        this.nombresNodos+="</TR>\n";
        dot.append(tabla);
        dot.append(this.nombresNodos);
        dot.append("</TABLE>>]\n}\n");
        
     
        return dot.toString(); 
    }
    

    public void inOrden(){
        inOrden(this.raiz);
    }

    public void postOrden(){
        postOrden(this.raiz);
    }
    
    
    private void rpreOrden(Capa<E> raiz){
        if(raiz!=null){
   
        this.nombresNodos+="<TD BGCOLOR=\"#F4D03F\">"+raiz.id+"</TD>";
        rpreOrden(raiz.izquierdo);
        rpreOrden(raiz.derecho);
        }
    }
       private void inOrden(Capa<E> raiz){
        if(raiz != null){
            inOrden(raiz.izquierdo);
            this.nombresNodos+="<TD BGCOLOR=\"#58D68D\">"+raiz.id+"</TD>";
            inOrden(raiz.derecho);
        }
    }

    private void postOrden(Capa<E> raiz){
        if(raiz != null){
            postOrden(raiz.izquierdo);
            postOrden(raiz.derecho);
            this.nombresNodos+="<TD BGCOLOR=\"#1ABC9C\">"+raiz.id+"</TD>";
        }
    }
    

}