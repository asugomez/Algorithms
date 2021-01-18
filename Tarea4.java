import java.util.Scanner;

/**
 * la clase Nodo no la saqué de ningún lado, pero es muy parecida a la vista en el apunte y a la tipica clase Nodo
 */
class Nodo {
    // Variable en la cual se va a guardar el valor.
    private String valor;
    // Variable para enlazar los nodos.
    private Nodo izq;
    private Nodo der;
    /**
     * Constructor que inicializamos el valor de las variables.
     */
    public void Nodo(){ //Arbol vacio
        this.valor = "";
        this.izq=null;
        this.der=null;
    }
    // Métodos get y set para los atributos.
    public String getValor() {
        return valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }
    public Nodo getNodoIzq() {
        return izq;
    }
    public void setNodoizq(Nodo hojaIzquierda) {
        this.izq = hojaIzquierda;
    }
    public Nodo getNodoDer() {
        return der;
    }
    public void setNodoDer(Nodo hojaDerecha) {
        this.der = hojaDerecha;
    }

}
/**
 * Codigo de la clase PilaArreglo sacado de:
 * https://users.dcc.uchile.cl/~bebustos/apuntes/cc3001/TDA/
 * modificado para que fuese un arreglo de nodos
 */
class Pila{
    private Nodo[] arreglo;
    private int tope;
    private int MAX_ELEM=100; // maximo numero de elementos en la pila

    public Pila()
    {
        arreglo=new Nodo[MAX_ELEM];
        tope=-1; // inicialmente la pila esta vacía
    }
    public int getTope(){return tope;}
    public Nodo[] getArreglo(){return arreglo;}

    public void apilar(Nodo x)
    {
        if (tope+1<MAX_ELEM) // si esta llena se produce OVERFLOW
        {
            tope++;
            arreglo[tope]=x;
        }
        else
        {
            MAX_ELEM=MAX_ELEM*2;
            Nodo[] nuevo_arreglo=new Nodo[MAX_ELEM];
            for (int i=0; i<arreglo.length; i++)
            {
                nuevo_arreglo[i]=arreglo[i];
            }
            tope++;
            nuevo_arreglo[tope]=x;
            arreglo=nuevo_arreglo;
        }
    }
    //retorna el elemento que se encuentre en el tope de la pila y lo elimina de ésta (pop en inglés).
    public Nodo desapilar()
    {
        if (!estaVacia()) // si esta vacia se produce UNDERFLOW
        {
            Nodo x = arreglo[tope];
            tope--;
            return x;
        }
        else{
            Nodo vacio=new Nodo();
            return vacio;
        }

    }

    public Nodo tope()
    {
        if (!estaVacia()) // si esta vacia es un error
        {
            Nodo x = arreglo[tope];
            return x;
        }
        else{
            Nodo vacio=new Nodo();
            return vacio;
        }

    }

    public boolean estaVacia()
    {
        return tope==-1;
    }
}
class Tarea4 {
    //dado un string genera un arreglo con cada letra y/o punto
    public static String[] stringAarreglo(String s){
        String[] s1 = s.split(" "); //genero un arreglo con cada letra o punto
        return s1;
    }
    //construir al árbol respectivo y retornar un puntero a su raíz.
    public static Nodo generaArbol(String recorrido) {
        String[] rec2=stringAarreglo(recorrido);
        Pila arbol=new Pila();
        int l=rec2.length;
        for(int i=0;i<l;i++){
            //si es un punto--> se apila como null
            if(rec2[i].equals(".")){
                arbol.apilar(null);
            }
            else{
                //se apila un nodo binario que tiene como hijos los ultimos dos elementos de la pila.
                Nodo letra=new Nodo();
                letra.setValor(rec2[i]);
                Nodo hijoder=arbol.desapilar();
                Nodo hijoizq=arbol.desapilar();
                letra.setNodoDer(hijoder);
                letra.setNodoizq(hijoizq);
                //apilar el nodo
                arbol.apilar(letra);
            }
        }
        return arbol.tope();
    }
    //recibe el puntero a la raíz de un árbol y retorna el string correspondiente a su recorrido en preorden
    public static String generaPreorden(Nodo raiz){
        String cadena="";
        if(raiz!=null){
            cadena=cadena + raiz.getValor();
            //si no es nula la raiz, entonces tendra hijos
            if (raiz.getNodoIzq()!=null){
                cadena=cadena+generaPreorden(raiz.getNodoIzq());
            }
            if (raiz.getNodoIzq()==null){
                cadena=cadena+".";
            }
            if (raiz.getNodoDer()!=null){
                cadena=cadena+generaPreorden(raiz.getNodoDer());
            }
            if (raiz.getNodoDer()==null){
                cadena=cadena+".";
            }
        }
        else if(raiz==null){ //Caso borde en que solo se entregue un punto y no tiene hijos
            cadena=cadena+".";
        }

        return cadena;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine(); //string de arbol en POSTORDEN
        Nodo raiz=generaArbol(s);
        String recorridoPreorden=generaPreorden(raiz); //string de arbol en PREORDEN
        System.out.println(recorridoPreorden);
    }

}
