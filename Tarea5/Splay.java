public class Splay {
    //rotacion simple ZIG o ZAG
    public static NodoInt RotSimple(NodoInt p, int sentido) {
        //rotacion hacia la izquierda x-->p (hijo izquierdo del padre) ZIG
        if (sentido >= 1) {
            NodoInt pp = new NodoInt(((NodoInt) p.izq).der, p.info, p.der);
            return new NodoInt(((NodoInt) p.izq).izq, ((NodoInt) p.izq).info, pp);
        }
        //rotacion hacia la derecha p<--x (hijo derecho del padre) ZAG
        else {
            NodoInt pp = new NodoInt(p.izq, p.info, ((NodoInt) p.der).izq);
            return new NodoInt(pp, ((NodoInt) p.der).info, ((NodoInt) p.der).der);
        }
    }
    //zigzag o zagzig en arbol SPLAY
    public static NodoInt RotDoble (NodoInt p, int sentido)
    {
        if (sentido <= -1)
        {
            NodoInt izquierda = new NodoInt( ((NodoInt) p.izq).izq,
                    ((NodoInt) p.izq).info, ((NodoInt) ((NodoInt) p.izq).der).izq);
            NodoInt pp = new NodoInt( ((NodoInt) ((NodoInt) p.izq).der).der,
                    p.info, p.der );
            return new NodoInt( izquierda, ((NodoInt) ((NodoInt) p.izq).der).info, pp);
        }
        else
        {
            NodoInt derecha = new NodoInt( ((NodoInt) ((NodoInt) p.der).izq).der,
                    ((NodoInt) p.der).info,   ((NodoInt) p.der).der );
            NodoInt pp = new NodoInt( p.izq,
                    p.info, ((NodoInt) ((NodoInt) p.der).izq).izq );
            return new NodoInt( pp, ((NodoInt) ((NodoInt) p.der).izq).info, derecha);
        }
    }


    //inserta el nodo y ejecuta la estrategia splaying
    public static Nodo insertar(Nodo a, double x) {
        if( a instanceof NodoExt ) {return new NodoInt( Nodo.nulo, x, Nodo.nulo );}
        //puntero al nodo interno a
        NodoInt b = (NodoInt)a;
        //si el valor a insertar es menor que la llave del nodo a
        if( x<b.info ) {
            NodoInt retorno =  new NodoInt(insertar(b.izq,x),b.info,b.der);
            //
            if(b.izq instanceof NodoExt){
                return retorno;
            }
            //zigzigs
            else if(x<((NodoInt)b.izq).info){
                //rotacion c/r al abuelo y luego c/r al padre
                return RotSimple(RotSimple(retorno,1),1);
            }
            //zigzag
            else if(x>((NodoInt)b.izq).info){
                return RotDoble(retorno,-1);
            }
            else{// if(x==((NodoInt)b.izq).info){
                return RotSimple(retorno,1);
            }
        }
        else if( x>b.info ) {
            NodoInt retorno = new NodoInt(b.izq,b.info,insertar(b.der,x));
            if(b.der instanceof NodoExt){
                return retorno;
            }
            else if(x>((NodoInt)b.der).info){
                return RotSimple(RotSimple(retorno,-1),-1);
            }

            else if(x<((NodoInt)b.der).info){
                return RotDoble(retorno,1);
            }
            else{// if(x==((NodoInt)b.der).info){
                return RotSimple(retorno,-1);
            }
        }
        else {
            return b; // ignoramos insercion si es llave repetida
        }
    }
}
