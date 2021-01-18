/** Algoritmo de insercion en la raiz (version corregida)
*/
public class Air
  {
	public static Nodo insertar( Nodo a, double x ) {
		if( a instanceof NodoExt ) {return new NodoInt( Nodo.nulo, x, Nodo.nulo );}
		//puntero al nodo interno a
		NodoInt b = (NodoInt)a;
		//si el valor a insertar es menor que la llave del nodo a
		if( x<b.info ) {
      		  	//se interta en el subarbol izquierdo
 			NodoInt r = new NodoInt(insertar(b.izq,x),b.info,b.der);
 			//puntero interno al subarbol izquierdo
			NodoInt i = (NodoInt)r.izq;
			//retorna un nuevo nodo --> rotacion simple
			return new NodoInt(i.izq,i.info,new NodoInt(i.der,r.info,r.der));
      		  }
		//si el valor es mayor que la llave del nodo a
		else if( x>b.info ) {
      		  	//se inserta en el subarbol derecho
			NodoInt r = new NodoInt(b.izq,b.info,insertar(b.der,x));
			//puntero interno al subarbol derecho
      		NodoInt d = (NodoInt)r.der;
      		return new NodoInt(new NodoInt(r.izq,r.info,d.izq),d.info,d.der);
       		  }
		else {return b;} // ignoramos insercion si es llave repetida
	  }
  }
