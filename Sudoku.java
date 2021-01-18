import java.util.Scanner;
public class Sudoku {
    //verifica si una lista (fila,columna o matriz) es factible o no, servirá como test para el final
    public static boolean esFactible(int[] lista) {
        int l = lista.length;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                if (lista[i] != 0 && lista[i] == lista[j] && i != j)
                    return false; //si hay dos elementos iguales que no sea 0
            }
        }
        return true;
    }

    //dada una matriz, un numero n y su posición en la matriz, verifica si es factible como candidato o no
    public static boolean esCandidato(int[][] matriz, int i, int j, int n) {
        for (int k = 0; k < 9; k++) {
            //reviso las filas
            if (matriz[i][k] == n) return false;
            //reviso las columnas
            if (matriz[k][j] == n) return false;
        }
        for (int k = 0; k < 9; k++) {
            if (bloqueMatriz(matriz, i, j)[k] == n) return false;
        }
        return true;
    }

    //elimina un candidato de una lista (lo convierte a 0)
    public static int[] eliminaCandidato(int[] lista, int nro) {
        int l = lista.length;
        for (int i = 0; i < l; i++) {
            if (lista[i] == nro) lista[i] = 0;
        }
        return lista;
    }

    //dado un ind1 i, un ind2 j, retornará el bloque que le corresponde con una lista de los indices i,j de ese bloque
    public static int[] cualBloque(int ind1, int ind2) {
        int[] indices = new int[4]; //i min, i max, j min, j max
        if (ind1 == 0 || ind1 == 1 || ind1 == 2) {
            indices[0] = 0; //i min
            indices[1] = 3; //i max
        }
        if (ind1 == 3 || ind1 == 4 || ind1 == 5) {
            indices[0] = 3;
            indices[1] = 6;
        }
        if (ind1 == 6 || ind1 == 7 || ind1 == 8) {
            indices[0] = 6;
            indices[1] = 9;
        }
        if (ind2 == 0 || ind2 == 1 || ind2 == 2) {
            indices[2] = 0;
            indices[3] = 3;
        }
        if (ind2 == 3 || ind2 == 4 || ind2 == 5) {
            indices[2] = 3;
            indices[3] = 6;
        }
        if (ind2 == 6 || ind2 == 7 || ind2 == 8) {
            indices[2] = 6;
            indices[3] = 9;
        }

        return indices;
    }

    //crea una matriz de 3 dimensiones con nrs del 1 al 9
    public static int[][][] candidatos() {
        int[][][] Candidatos = new int[9][9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) Candidatos[i][j][k] = k + 1;
            }
        }
        return Candidatos;
    }

    //retorna una lista que representa al bloque de una matriz de 2d
    public static int[] bloqueMatriz(int[][] matriz, int ind1, int ind2) {
        int[] bloque = new int[9];
        //indices del bloque
        int[] listIndex = new int[4];
        for (int h = 0; h < 4; h++) {
            listIndex[h] = cualBloque(ind1, ind2)[h];
        }
        int a = listIndex[0];
        int b = listIndex[2];
        bloque[0] = matriz[a][b];
        bloque[1] = matriz[a][b + 1];
        bloque[2] = matriz[a][b + 2];
        bloque[3] = matriz[a + 1][b];
        bloque[4] = matriz[a + 1][b + 1];
        bloque[5] = matriz[a + 1][b + 2];
        bloque[6] = matriz[a + 2][b];
        bloque[7] = matriz[a + 2][b + 1];
        bloque[8] = matriz[a + 2][b + 2];
        return bloque;
    }

    //dado el candidato n que es solucion, se actualiza la matriz 3D de candidatos,i.e, se elimina como candidato de las listas
    //que esten en la misma fila, columna y bloque. (gracias al ind1 i, ind2 j)
    public static int[][][] actualiza(int[][][] matrizCandidatos, int ind1, int ind2, int n) {
        for (int p = 0; p < 9; p++) {
            eliminaCandidato(matrizCandidatos[ind1][p], n);
            eliminaCandidato(matrizCandidatos[p][ind2], n);
        }
        int[] listIndex = cualBloque(ind1, ind2);
        //se elimina de todas las listas de candidatos de ese bloque
        for (int p = listIndex[0]; p < listIndex[1]; p++) {
            for (int q = listIndex[2]; q < listIndex[3]; q++) {
                eliminaCandidato(matrizCandidatos[p][q], n);
            }
        }
        return matrizCandidatos;
    }
    //dada la matriz de candidado, una posicion(i,j) y un nro, retorna la matrizCandidatos pero con ese nro como posible candidato
    //en la fila, columna y bloque de la posicion (i,j) menos en esa posicion
    public static int[][][] desactualiza(int[][][] matrizCandidatos, int ind1, int ind2, int n){
        //se agrega a todas las filas y columas
        for (int p = 0; p < 9; p++) {
            matrizCandidatos[ind1][p][n-1]=n;
            matrizCandidatos[p][ind2][n-1]=n;
        }
        //se agrega al bloque
        int[] listIndex = cualBloque(ind1, ind2);
        //se elimina de todas las listas de candidatos de ese bloque
        for (int p = listIndex[0]; p < listIndex[1]; p++) {
            for (int q = listIndex[2]; q < listIndex[3]; q++) {
                matrizCandidatos[p][q][n-1]=n;
            }
        }
        //se elimina el que esta en esa posicion
        matrizCandidatos[ind1][ind2][n-1]=0;
        return matrizCandidatos;
    }

    //dada la matriz 3d de candidatos y una poscion, retorna cuantos candidatos posibles hay para esa casilla
    public static int cuentaCandidatos(int[][][] matrizCandidatos, int i, int j) {
        int c = 0;
        for (int k = 0; k < 9; k++) {
            if (matrizCandidatos[i][j][k] != 0) c += 1;
        }
        return c;
    }

    //dada la lista de candidatos(nro de candidatos por casilla), un numero n junto a su posición i,j
    //debe actualizar la lista de candidatos restando 1 a las filas, columnas, bloques que tengan ese n
    public static int[][] actualizaListaCan(int[][][] matrizCandidatos, int n, int i, int j) {
        int[][] listaCandidatos = new int[9][9];
        int[][][] newMatrizCandidatos = actualiza(matrizCandidatos, i, j, n);
        for (int k = 0; k < 9; k++) {
            for (int h = 0; h < 9; h++) {
                listaCandidatos[k][h] = cuentaCandidatos(newMatrizCandidatos, k, h);
            }
        }
        return listaCandidatos;
    }

    //dada una matriz de sudoku, matriz de 3d de candidatos y una matriz de 2d que cuente el nro de candidatos por casilla
    //debe ir actualizando la matriz de candidatos(3d) y del nro de candidatos(2d) cuando la casilla de la matriz de sudoku
    //sea distinta a 0. Y cuando sea 0, si solo existe 1 candidato, entonces debe rellenar la matriz de sudoku con ese elemento
    //y volver a actualizar la matriz de candidatos(3d) y el nro de candidatos(2d)
    public static int[][] Greedy(int[][] matrizSudoku, int[][][] matrizCandidatos, int[][] cuantosCandidatos) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //cuando solo haya un candidato y la casilla esta vacia
                if (cuantosCandidatos[i][j] == 1 && matrizSudoku[i][j] == 0) {
                    for (int k = 0; k < 9; k++) {
                        //busco el candidato q no es 0
                        if (matrizCandidatos[i][j][k] != 0) {
                            //se agrega a la mtriz del Sudoku
                            matrizSudoku[i][j] = matrizCandidatos[i][j][k];
                            //se actualiza la matriz de Candidatos, y la matriz que cuenta los candidatos
                            int[][][] nuevosCandidatos = actualiza(matrizCandidatos, i, j, matrizSudoku[i][j]);
                            int[][] ctosCandidatos2 = actualizaListaCan(nuevosCandidatos, matrizSudoku[i][j], i, j);
                            //recursion
                            Greedy(matrizSudoku, nuevosCandidatos, ctosCandidatos2);
                        }
                    }
                }
            }
        }
        return matrizSudoku;
    }
    public static boolean backtracking(int[][] matrizSudoku, int[][][] matrizCandidatos) {
        //codigo adaptado
        //https://medium.com/@ssaurel/build-a-sudoku-solver-in-java-part-1-c308bd511481
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for(int h=0;h<9;h++) {
                    if (esFactible(matrizSudoku[h])) {
                        if (matrizSudoku[i][j] == 0) {
                            for (int k = 0; k < 9; k++) {
                                int nro = matrizCandidatos[i][j][k];
                                //posible candidato para la casilla y q sea candidato
                                if (nro != 0 && esCandidato(matrizSudoku, i, j, nro)) {
                                    matrizSudoku[i][j] = nro;
                                    if (backtracking(matrizSudoku, matrizCandidatos)) return true;
                                    else {
                                        matrizSudoku[i][j] = 0;
                                    }
                                }
                            }
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    ////////////////////////
    ///////////////////////
    ///////////////////////

    public static void main(String[] args) {
        //leyendo la matriz inicial (input)
        Scanner sc = new Scanner(System.in);
        int matrizInicial[][] = new int[9][9]; //crea la matriz de 9x9
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) matrizInicial[i][j] = sc.nextInt();
        }
        //matriz 3D de candidatos
        int[][][] Candidatos = new int[9][9][9];
        Candidatos = candidatos();
        //matriz que dice cuantos candidatos tiene cada casilla
        int[][] listaCandidato = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) listaCandidato[i][j] = 9;
        }
        //primera pasada
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //cuando el elemento tiene un valor asignado
                if (matrizInicial[i][j] != 0) {
                    //actualiza la lista de candidatos con un solo valor posible (asignado por la matrizInicial)
                    int n = matrizInicial[i][j];
                    for (int k = 1; k < 9; k++) Candidatos[i][j][k] = 0;
                    //se actualiza la matriz de candidatos y la lista que cuenta ctos candidatos hay por casilla
                    Candidatos = actualiza(Candidatos, i, j, n);
                    Candidatos[i][j][n - 1] = n; //unico candidato posible
                    listaCandidato = actualizaListaCan(Candidatos, n, i, j);
                }
            }
        }
        //contando las casillas vacias
        int vacias=0;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++) {
                if (matrizInicial[i][j] == 0) vacias += 1;
            }
        }
        //greedy
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (matrizInicial[i][j] != 0) {
                    Candidatos = actualiza(Candidatos, i, j, matrizInicial[i][j]);
                    listaCandidato[i][j] = cuentaCandidatos(Candidatos, i, j);
                }
                else{
                    matrizInicial = Greedy(matrizInicial, Candidatos, listaCandidato);
                    Candidatos = actualiza(Candidatos, i, j, matrizInicial[i][j]);
                }
            }
        }
        //contando cuantas casillas relleno
        int vacias2=0;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++) {
                if (matrizInicial[i][j] == 0) vacias2 += 1;
            }
        }

        //backtracking
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (matrizInicial[i][j] != 0){
                    Candidatos = actualiza(Candidatos, i, j, matrizInicial[i][j]);
                }
                else{
                    backtracking(matrizInicial,Candidatos);
                }
            }
        }


        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(matrizInicial[i][j]);
                System.out.print(" ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
        System.out.print(vacias-vacias2);
    }
}



