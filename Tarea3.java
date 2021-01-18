import org.jetbrains.annotations.NotNull;
import sun.rmi.log.LogInputStream;

import java.util.Scanner;

class Nodo {
    //http://codigolibre.weebly.com/blog/listas-simples-en-java
    // Variable en la cual se va a guardar el valor.
    private int valor;
    // Variable para enlazar los nodos.
    private Nodo siguiente;
    // Métodos get y set para los atributos.
    public void Nodo(){
        this.valor = 0;
        this.siguiente = null;
    }


    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

}
class listaEnlazada {
    //http://codigolibre.weebly.com/blog/listas-simples-en-java
    // Puntero que indica el inicio de la lista o conocida tambien
    // como cabeza de la lista.
    private Nodo inicio;
    // Variable para registrar el tamaño de la lista.
    private int tamano;
    /**
     * Constructor por defecto.
     */
    public listaEnlazada listaEnlazada() {
        inicio = null;
        tamano = 0;
        return null;
    }
    public int getTamanio(){
        return tamano;
    }
    public Nodo getInicio() {
        return inicio;
    }
    /**
     * Consulta si la lista esta vacia.
     *
     * @return true si el primer nodo (inicio), no apunta a otro nodo.
     */
    public boolean esVacia() {
        return inicio == null;
    }
    public void agregarAlInicio(int valor){
        // Define un nuevo nodo.
        Nodo nuevo = new Nodo();
        // Agrega al valor al nodo.
        nuevo.setValor(valor);
        // Consulta si la lista esta vacia.
        if (esVacia()) {
            // Inicializa la lista agregando como inicio al nuevo nodo.
            inicio = nuevo;
            // Caso contrario va agregando los nodos al inicio de la lista.
        } else{
            // Une el nuevo nodo con la lista existente.
            nuevo.setSiguiente(inicio);
            // Renombra al nuevo nodo como el inicio de la lista.
            inicio = nuevo;
        }
        // Incrementa el contador de tamaño de la lista.
        tamano++;
    }
    public void agregarAlFinal(int valor) {
        // Define un nuevo nodo.
        Nodo nuevo = new Nodo();
        // Agrega al valor al nodo.
        nuevo.setValor(valor);
        // Consulta si la lista esta vacia.
        if (esVacia()) {
            // Inicializa la lista agregando como inicio al nuevo nodo.
            inicio = nuevo;
            // Caso contrario recorre la lista hasta llegar al ultimo nodo
            //y agrega el nuevo.
        } else {
            // Crea ua copia de la lista.
            Nodo aux = inicio;
            // Recorre la lista hasta llegar al ultimo nodo.
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            // Agrega el nuevo nodo al final de la lista.
            //aux.setValor(nuevo.getValor());
            aux.setSiguiente(nuevo);
        }
        // Incrementa el contador de tamaño de la lista
        tamano++;
    }
    public boolean buscar(int referencia){
        // Crea una copia de la lista.
        Nodo aux = inicio;
        // Bandera para indicar si el valor existe.
        boolean encontrado = false;
        // Recorre la lista hasta encontrar el elemento o hasta
        // llegar al final de la lista.
        while(aux != null && encontrado != true){
            // Consulta si el valor del nodo es igual al de referencia.
            if (referencia == aux.getValor()){
                // Canbia el valor de la bandera.
                encontrado = true;
            }
            else{
                // Avansa al siguiente. nodo.
                aux = aux.getSiguiente();
            }
        }
        // Retorna el resultado de la bandera.
        return encontrado;
    }
    public void removerPorReferencia(int referencia){
        // Consulta si el valor de referencia existe en la lista.
        if (buscar(referencia)) {
            // Consulta si el nodo a eliminar es el pirmero
            if (inicio.getValor() == referencia) {
                // El primer nodo apunta al siguiente.
                inicio = inicio.getSiguiente();
            } else{
                // Crea ua copia de la lista.
                Nodo aux = inicio;
                // Recorre la lista hasta llegar al nodo anterior
                // al de referencia.
                while(aux.getSiguiente().getValor() != referencia){
                    aux = aux.getSiguiente();
                }
                // Guarda el nodo siguiente del nodo a eliminar.
                Nodo siguiente = aux.getSiguiente().getSiguiente();
                // Enlaza el nodo anterior al de eliminar con el
                // sguiente despues de el.
                aux.setSiguiente(siguiente);
            }
            // Disminuye el contador de tamaño de la lista.
            tamano--;
        }
    }
    //////////////////////
    //yo hice esta funcion
    public listaEnlazada reverse(){
        int t=this.getTamanio();
        Nodo aux1=this.getInicio();
        listaEnlazada lista1= new listaEnlazada();
        while(t>0 && aux1!=null ){
            Nodo curso=aux1;
            lista1.agregarAlInicio(curso.getValor());
            aux1=aux1.getSiguiente();
        }
        return lista1;
    }
}

class Tarea3 {
    //convierte una lista enlazada de enteros a un arreglo de enteror
    public static int[] listaAarreglo(listaEnlazada lista){
        int n=lista.getTamanio();
        int[] arreglo=new int[n];
        if(lista.esVacia()){
            return arreglo; //retorna arreglo vacio
        }
        else{
            Nodo aux=lista.getInicio();
            int i=0;
            while(aux!=null &&i<n){
                arreglo[i]=aux.getValor();
                i++;
                aux=aux.getSiguiente();
            }
        }
        return arreglo;

    }

    //devolvera una lista enlazada con los cursos para los que un determinado curso es requisito
    public static listaEnlazada cursoEsRequisito(@NotNull Nodo curso, @NotNull listaEnlazada[] listaCursosyRequisito){
        int l=listaCursosyRequisito.length;
        listaEnlazada[] listaCursosyRequisito2=listaCursosyRequisito;
        listaEnlazada esRequisito=new listaEnlazada();
        int valor=curso.getValor();
        esRequisito.agregarAlInicio(valor);//el primer nodo serña el curso
        //esRequisito.agregarAlInicio(valor);//el inicio de la lista es el curso del cual se vera de qué es requisito
        for(int i=0;i<l;i++){
            listaEnlazada lista=listaCursosyRequisito2[i]; //recorrera todas las listas enlazadas
            Nodo aux=lista.getInicio();
            while(aux!=null){
                if (aux.getValor() == valor) { //si el curso es igual al de la lista
                    esRequisito.agregarAlFinal(listaCursosyRequisito[i].getInicio().getValor()); //se agrega la cabeza de la lista enlazada (para ese curso es requisito)
                }
                aux = aux.getSiguiente();
            }
        }
        return esRequisito;
    }

    //dada un arreglo que contiene listas enlazadas con la información del curso y sus requisitos
    //retorna un arreglo de listas enlazadas en que la cabeza es el curso y los nodos siguientes son los cursos en lo q es requisito
    public static listaEnlazada[] listaEsRequisito(@NotNull listaEnlazada[] cursoYnroreq){
        int n=cursoYnroreq.length;
        listaEnlazada[] cursos_es_REQUISITO=new listaEnlazada[n];
        for(int i=0;i<n;i++){
            Nodo curso=cursoYnroreq[i].getInicio();
            listaEnlazada curso_es_requi=new listaEnlazada();
            curso_es_requi=cursoEsRequisito(curso, cursoYnroreq);
            cursos_es_REQUISITO[i]=curso_es_requi;
        }
        return cursos_es_REQUISITO;
    }

    //dada una matriz que señala el curso y el nro de requisitos para cada curso, retornar un arreglo de lista enlazada
    // según el nro de requisitos. Ej: en lista[0] estan todos los cursos q no tienen requisitos
    public static listaEnlazada[] numDeRequisito(@NotNull int[][] cursoYnroreq){
        int m=0; //será el maximo nro de requisitos
        int l=cursoYnroreq.length; //retorna el nro de filas
        for(int i=0;i<l;i++){
            if(cursoYnroreq[i][1]>m) m=cursoYnroreq[i][1];
        }
        listaEnlazada[] cursosPorNroReq=new listaEnlazada[m+1]; //arreglo de listas enlazadas (segun requisitos)
        for(int i=0;i<m+1;i++){
            listaEnlazada listaAux=new listaEnlazada();
            cursosPorNroReq[i]=listaAux; //el arreglo contiene inicialmente puras listas vacias
        }
        for(int i=0;i<l;i++){
            Nodo curso=new Nodo();
            if(curso.getValor()>=0) {
                curso.setValor(cursoYnroreq[i][0]); //nro del curso
                int nroReq = cursoYnroreq[i][1]; //nro de requisitos de ese curso
                if(nroReq>-1) cursosPorNroReq[nroReq].agregarAlInicio(curso.getValor());
            }
        }
        return cursosPorNroReq;
    }

    //imprime aquel curso sin requisitos
    public static void imprime(@NotNull Nodo curso){
        System.out.println(curso.getValor());
    }

    //dadp un valor y la matriz de int[][] que lleva el nro del curso y el nro de requisitos
    //retorna la posición en que se encuentra ese valor
    public static int indice(int curso,int[][] curso_y_nroReq){
        int l=curso_y_nroReq.length;
        int ind=-1;
        for(int i=0;i<l;i++){
            if(curso_y_nroReq[i][0]==curso) ind=i;
        }
        return ind;
    }

    //dado un curso1 sin requisitos, una matriz que dice el curso y su nro de requisitos y un arreglo de listas en donde sale el curso con todos
    //sus requisitos --> retornara una arreglo de lista enlazada que contiene los cursos segun su nro de requisitos
    public static listaEnlazada[] actualizaLista(Nodo curso1, int[][] curso_y_numReq,listaEnlazada[] listaCursosyReq){
        int n=listaCursosyReq.length;
        listaEnlazada req_a_curso1=cursoEsRequisito(curso1,listaCursosyReq); //cursos que tienen de requisito a curso1
        req_a_curso1.removerPorReferencia(curso1.getValor()); //elimino el primer nodo que es el curso1
        int[] req_a_curso2 = listaAarreglo(req_a_curso1); //pasa a un arreglo para q sea mas facil hacer un for
        int t = req_a_curso2.length ; //nro de cursos que tienen de requisito a curso1
        //los indices que tienen los cursos que tienen de requisito a curso 1
        int[] indices = new int[t];
        for (int i = 0; i < t; i++) {
            int curso = req_a_curso2[i];
            indices[i] = indice(curso, curso_y_numReq);
        }
        for(int i: indices){
            curso_y_numReq[i][1]-=1; //le resto un requisito a los cursos que tienen de requisito a curso 1
        }
        listaEnlazada[] cursos_segun_req=new listaEnlazada[n];
        cursos_segun_req=numDeRequisito(curso_y_numReq);
        return cursos_segun_req;

    }

    public static void cursosPorOrden(){
        Scanner sc = new Scanner(System.in);
        String n1=sc.nextLine(); //nro de cursos
        int n=Integer.parseInt(n1);
        int[][] nro_requisitos=new int[n][2]; //señala el curso y el nro de requisitos
        listaEnlazada[] cursosYreq= new listaEnlazada[n]; //arreglo de listas que tienen sus requisitos
        for (int i = 0; i < n; i++) { //leo todas las lineas
            String s = sc.nextLine(); //'1 2 11 3'
            String[] s1 = s.split(" ");//'12113'
            int l = s1.length;
            nro_requisitos[i][1] = l - 1; //nro de cursos que son requisito para el curso (no debe contar al primer curso)
            int[] listanro = new int[l];  //lista del curso con sus requisitos
            listaEnlazada listaCursos = new listaEnlazada();
            for (int j = 0; j < l; j++) {
                //hacer lista enlazada segun el nro requistos
                listanro[j] = Integer.parseInt(s1[j]); //lista del curso con sus requisitos
                if (j == 0) { //el primer curso (principal)
                    nro_requisitos[i][0] = listanro[j];
                    Nodo curso = new Nodo();
                    curso.setValor(nro_requisitos[i][j]);
                    listaCursos.agregarAlInicio(curso.getValor()); //el inicio de la lista sera el curso
                }
                else {
                    Nodo requisito = new Nodo();
                    requisito.setValor(listanro[j]);
                    listaCursos.agregarAlFinal(requisito.getValor()); //agrega los cursos que son requisito a la lista enlazada
                }
            }
            cursosYreq[i]=listaCursos;
        }
        listaEnlazada[] cursos_es_requisito=new listaEnlazada[n];
        cursos_es_requisito=listaEsRequisito(cursosYreq); //un arreglo de listas con todos los cursos en que un curso1 es requisito
        //--------------------------------//
        //Imprimiendo los cursos sin requisitos
        listaEnlazada[] nroRequisitosPorCurso=new listaEnlazada[n];
        nroRequisitosPorCurso=numDeRequisito(nro_requisitos); //arreglo de ListaEnlazada que guarda los cursos segun el nro de requisitos
        //debe parar cuando no haya ningun curso por tomar
        while (nroRequisitosPorCurso[0].getTamanio()!=0) {
            Nodo aux = nroRequisitosPorCurso[0].getInicio(); //el primer curso sin requisito
            imprime(aux);
            listaEnlazada[] nroReq2=actualizaLista(aux,nro_requisitos,cursosYreq);
            nroRequisitosPorCurso=nroReq2;
        }
    }
    public static void main(String[] args){
        cursosPorOrden();
    }

}

