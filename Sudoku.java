import java.util.Scanner;
//un test puede ser que las columnas sumen 1+2+3+4+5+6+7+8+9

public class Sudoku {
    //verifica si una lista (fila,columna o matriz) es factible o no, servirá para el final
    public boolean esFactible(int[] lista) {
        int l = lista.length;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                if (lista[i] != 0 && lista[i] == lista[j] && i != j)
                    return false; //si hay dos elementos iguales que no sea 0
            }
        }
        return true;
    }
    /*
int[] prueba=[0,1,2,3,4,5,6,7,8];
int[] prueba2=[1,2,3,4,5,6,6,7,9];
assert esFactible(prueba)==true;
assert esFactible(prueba2)==false;
*/

    //dado un nro, verifica si puede ser candidato
    public boolean esCandidato(int [] fila, int[] columna, int[] matriz,  int nro){
        int l =fila.length;
        for(int i=0;i<l;i++){
            if (fila[i]==nro) return false; //el nro candidato ya se encuentra en la lista, entonces no es factible
            if (columna[i]==nro) return  false;
            if (matriz[i]==nro) return  false;
        }
        return true; //nro candidato no esta en la lista
    }

    int[] cand=[1,2,3,4,5,6,7,8,9];
    //sera mejor guardar 9 filas, 9 columnas y 9 matrices?
    //dada una matriz las guarda como columnas en forma de lista
    public static void columna(int[][] matriz){
        for(int i=0;i<9;i++)
            //como hago para pasar de fila a columna
    }
    //dada una matriz guarda los 9 bloques en lista
    public static void bloque(int[][] matriz){
        for(int i=0;i<9;i++)
        //como hago para pasar de fila a columna
    }
//la matriz solucion debe ser la matriz inicial en un comienzo y luego ir cambiando las posibles soluciones
    //una soluciones muuy lenta sería tener una lista con todos los candidatos para cada elemento (81)
DEFINIR FILA, COLUMNA Y MATRIZ

    /* recorrer la matriz inicial y probar en cada casilla si el elemento del 1 al 9 es candidatos y si es factible. Cuando solo
    quede un candidato,, entocnes agregarlo a la matriz solucion
    proobar primero con el 1 en todos los elementos, luego con el 2.... y asi
    la funcion es factible le deben entregar las 3 listas (fila, columna y matriz)

    podemos guardar 9 matrices (una matriz para cada solucion con respecto a un nro, y uego juntarlas todas)
     */
//funcion seleccion (de candidatos)
    //funcion de factibilidad: si no es factible se elimina de la lista de candidatos
    //podemos hacer una lista del 1 al 9 para cada elemento de la matriz de 9x9 e ir eliminando los elementos:
    //1. los elementos q estan en la matriz inicial
    //2. los que no son candidatos (lista Candidatos)
    //una lista de candidatos para el elemento [i][j], en un ciclo veo cuales son mis candidatos (gracias a funcion es factible)
    //avanzo al siguiente elemento de la fila, dps de la columna y voy eliminando (o rellenando) candidatos.
    //inicio el ciclo denuevo hasta que la lista de candidatos para todos los elementos [i][j] sea 1
    //ojo: la primera pasada debe eliminar altiro los elemtnos que no son factible por la matriz inicial
    //y en la matriz de candidatos, los elementos que nos entreg por la matriz inicial que sea solo 1 elemento y no una lista
//funcion que pase de la matriz a filas, a columnas y a otra lista q sinmule la matriz de 3x3
    /*
Greedy
while (S no sea una solución y C ≠ Ø) C: candidatos a solucones
        x=seleccion(C)
        C=C - {x}
        if (S u {x}) es factible):
            S=S U {x}
*/


    public static void main (String[] args) {
        //la matriz guardara cada fila primero
        Scanner sc = new Scanner(System.in);
        int matrizInicial[][] = new int[9][9]; //crea la matriz de 9x9
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) matrizInicial[i][j] = sc.nextInt();
        }

    }


}
