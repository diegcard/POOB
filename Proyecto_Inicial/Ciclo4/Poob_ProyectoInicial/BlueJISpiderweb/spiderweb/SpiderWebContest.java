package spiderweb;

import java.util.*;

/**
 * This class is to resolve the problem of the Spider Walk and
 * simulate its solution
 *
 * @author Sebastian Cardona
 * @author Diego Cardenas
 * @version 1.0
 */

class Node {
    public int min_dec, min_inc;
}


/**
 * This class is to resolve the problem of the Spider Walk and
 * simulate its solution
 *
 * @author Sebastian Cardona
 * @author Diego Cardenas
 * @version 1.0
 */
public class SpiderWebContest {
    private static Spiderweb spiderweb;
    //private ArrayList<Integer> posNewBridges;
    private static int numBridges;
    private static final int N = 200005;
    // INF es la distancia máxima que hay desde el centro de la telaraña hasta el extremo de un hilo
    private static final int INF = (int) 1e9;
    // seg es el segment tree que se va a usar para guardar los valores de los nodos
    private static final Node[] seg = new Node[N * 4];
    // n es el número de hilos, s es el hilo favorito de la araña
    private static int s;
    private static int n;
    private static final String[] colors = {"salmon", "lilac", "turquoise", "brown", "purple", "lightgray", "pink",
            "#2558751", "#1006253", "#2509532", "#24024029", "#14121414", "#8420971", "#117176131",
            "#5522195", "#15194115", "#122202184", "#18217172", "#14936136", "gray", "cyan", "magenta", "orange", "darkgray", "black",
            "yellow", "green", "blue", "red"};
    private static boolean isVisible = false;
    public static boolean isOk;
    /**
     * make the simulation visible
     */
    private static void makeVisible() {
        if (!isVisible) {
            spiderweb.makeVisible();
            isVisible = true;
        }
    }

    /**
     * make the simulation visible
     */
    private static void makeInvisible() {
        if (isVisible) {
            spiderweb.makeInvisible();
            isVisible = false;
        }
    }


    //save in a ArrayList the posible positions of the news bridges
    /*private void posiblePositions(int[][] bridges){
        this.posNewBridges = new ArrayList<Integer>();
        for(int i =0; i<bridges.length;i++){
            int pos = bridges[i][0];
            if(pos != 1){
                posNewBridges.add(pos-1);
            }
        }
        posNewBridges.add(bridges[bridges.length-1][0]+1);
    }*/

    // ordenar la matriz de menor a mayor respecto al primer elemento
    private static void ordenarMatrizPorPrimerElemento(int[][] matriz) {
        Arrays.sort(matriz, Comparator.comparingInt(a -> a[0]));
    }

    // ordenar la matriz de mayor a menor respecto al primer elemento
    private static void ordenarMatrizPorPrimerElementoDeMayorAMenor(ArrayList<int[]> matriz) {
        matriz.sort((a, b) -> b[0] - a[0]);
    }

    //updateMin es una funcion que se usa para actualizar el valor de un nodo
    //del segment tree por el minimo entre el actual y el valor que se le pasa
    private static int updateMin(int x, int val) {
        return Math.min(x, val);
    }

    //down es una funcion que se usa para propagar los valores de los nodos del
    //segment tree hacia sus hijos cuando se hace un update en un nodo
    private static void down(int l, int r, int nodoActual) {
        int mid = (l + r) / 2;
        if (seg[nodoActual].min_dec != INF) {
            seg[nodoActual * 2].min_dec = updateMin(seg[nodoActual * 2].min_dec, seg[nodoActual].min_dec);
            seg[nodoActual * 2 + 1].min_dec = updateMin(seg[nodoActual * 2 + 1].min_dec, seg[nodoActual].min_dec - (mid + 1 - l));
        }
        if (seg[nodoActual].min_inc != INF) {
            seg[nodoActual * 2].min_inc = updateMin(seg[nodoActual * 2].min_inc, seg[nodoActual].min_inc);
            seg[nodoActual * 2 + 1].min_inc = updateMin(seg[nodoActual * 2 + 1].min_inc, seg[nodoActual].min_inc + (mid + 1 - l));
        }
        // despues de propagar los valores se reinician los valores del nodo actual
        seg[nodoActual].min_dec = seg[nodoActual].min_inc = INF;
    }

    //build es una funcion que se usa para construir el segment tree con los
    //valores iniciales de los nodos hoja que son los hilos de la telaraña
    //en los nodos hoja, min_dec y min_inc son la distancia minima de 
    //un hilo a s sin puentes inicialmente
    private static void build(int l, int r, int nodoActual) {
        seg[nodoActual].min_dec = INF;
        seg[nodoActual].min_inc = INF;
        if (l == r) {
            seg[nodoActual].min_dec = seg[nodoActual].min_inc = Math.min(Math.abs(l - s), Math.min(Math.abs(n + l - s), Math.abs(l - n - s)));
            return;
        }
        int mid = (l + r) / 2;
        build(l, mid, nodoActual * 2);
        build(mid + 1, r, nodoActual * 2 + 1);
    }

    //modify es una funcion que se usa para modificar los valores de los nodos del segment tree
    //en un rango determinado con un valor determinado
    private static void modify(int L, int R, int l, int r, int nodoActual, int v, int type) {
        // si el rango que se esta viendo esta completamente dentro del rango que se quiere modificar se modifica el valor del nodo
        if (L <= l && R >= r) {
            if (type == 1)
                seg[nodoActual].min_dec = Math.min(seg[nodoActual].min_dec, v);
            else
                seg[nodoActual].min_inc = Math.min(seg[nodoActual].min_inc, v);
            return;
        }
        // se llama a la funcion down para propagar los valores de los nodos hacia sus hijos
        down(l, r, nodoActual);
        int mid = (l + r) / 2;
        // si el rango que se quiere modificar esta en el rango izquierdo se llama recursivamente a modify con el nodo izquierdo
        if (L <= mid) {
            modify(L, Math.min(mid, R), l, mid, nodoActual * 2, v, type);
            if (type == 1)
                v -= mid + 1 - L;
            else
                v += mid + 1 - L;
        }
        // si el rango que se quiere modificar esta en el rango derecho se llama recursivamente a modify con el nodo derecho
        if (R > mid)
            modify(Math.max(L, mid + 1), R, mid + 1, r, nodoActual * 2 + 1, v, type);
    }

    //La función smodify se utiliza para modificar el valor de un solo nodo en el árbol de segmentos
    private static void smodify(int x, int l, int r, int nodoActual, int v) {
        // si el nodo que se quiere modificar es una hoja se le asigna el valor v
        if (l == r) {
            seg[nodoActual].min_dec = seg[nodoActual].min_inc = v;
            return;
        }
        // se llama a la funcion down para propagar los valores de los nodos hacia sus hijos
        down(l, r, nodoActual);
        int mid = (l + r) / 2;
        // si el nodo que se quiere modificar esta en el rango izquierdo se llama recursivamente a smodify con el nodo izquierdo
        if (x <= mid)
            smodify(x, l, mid, nodoActual * 2, v);
            // si el nodo que se quiere modificar esta en el rango derecho se llama recursivamente a smodify con el nodo derecho
        else
            smodify(x, mid + 1, r, nodoActual * 2 + 1, v);
    }

    //La función query se utiliza para hacer una consulta en el árbol de segmentos y
    //obtener el valor minimo de un nodo en un rango determinado
    private static int query(int x, int l, int r, int nodoActual) {
        // si el rango que se esta viendo es igual al nodo que se quiere consultar se retorna el valor minimo del nodo
        if (l == r) return Math.min(seg[nodoActual].min_dec, seg[nodoActual].min_inc);
        // se llama a la funcion down para propagar los valores de los nodos hacia sus hijos
        down(l, r, nodoActual);
        int mid = (l + r) / 2;
        // si el nodo que se quiere consultar esta en el rango izquierdo se llama recursivamente a query con el nodo izquierdo
        if (x <= mid) return query(x, l, mid, nodoActual * 2);
        // si el nodo que se quiere consultar esta en el rango derecho se llama recursivamente a query con el nodo derecho
        return query(x, mid + 1, r, nodoActual * 2 + 1);
    }

    // modifica el arbol de segmentos con los puentes
    private static void segWithBridges(ArrayList<int[]> bridges) {
        for (int[] pair : bridges) {
            // se calcula el hilo inicial y el hilo siguiente
            int hiloInicial = pair[1], hiloSiguiente = pair[1] % n + 1;
            // se calcula el valor minimo entre el valor actual del nodo y el valor del nodo siguiente mas 1
            int lval = Math.min(query(hiloInicial, 1, n, 1), query(hiloSiguiente % n + 1, 1, n, 1) + 1);
            int rval = Math.min(query(hiloSiguiente, 1, n, 1), query((hiloInicial + n - 2) % n + 1, 1, n, 1) + 1);
            // se actualizan los valores de los nodos del segment tree con los valores calculados
            smodify(hiloInicial, 1, n, 1, rval);
            smodify(hiloSiguiente, 1, n, 1, lval);
            // se actualizan los valores de los nodos del segment tree con los valores calculados
            modify(hiloInicial, n, 1, n, 1, rval, 0);
            modify(1, hiloInicial, 1, n, 1, rval + n + 1 - hiloInicial, 0);
            modify(hiloSiguiente, n, 1, n, 1, lval, 0);
            modify(1, hiloSiguiente, 1, n, 1, lval + n + 1 - hiloSiguiente, 0);

            modify(1, hiloInicial, 1, n, 1, rval + hiloInicial - 1, 1);
            modify(hiloInicial, n, 1, n, 1, rval + n, 1);
            modify(1, hiloSiguiente, 1, n, 1, lval + hiloSiguiente - 1, 1);
            modify(hiloSiguiente, n, 1, n, 1, lval + n, 1);
        }
    }

    /**
     * solve the problem of the Spider Walk using segement tree
     *
     * @param strands  strands
     * @param favorite favorite strand
     * @param bridges  bridges
     * @return an array with the solution of the problem
     */
    public static int[] solve(int strands, int favorite, int[][] bridges) {
        //spiderweb = new Spiderweb(strands, favorite, bridges);
        s = favorite;
        n = strands;
        //ordenarMatrizPorPrimerElemento(bridges);
        numBridges = bridges.length;
        for (int i = 0; i < N * 4; i++) {
            seg[i] = new Node();
        }
        //build(1, n, 1);
        ArrayList<int[]> bridges1 = arrayToArrayList(bridges);
        ordenarMatrizPorPrimerElementoDeMayorAMenor(bridges1);
        build(1, n, 1);
        segWithBridges(bridges1);
        int[] solution = new int[strands];
        for (int i = 1; i <= n; ++i) {
            solution[i - 1] = query(i, 1, n, 1);
        }
        return solution;
    }


    /**
     * simulate the problem of the Spider Walk for a specific start strand
     *
     * @param strands  strands
     * @param favorite favorite strand
     * @param bridges  bridges
     * @param strand   start strand
     */
    private static void simulate(int strands, int favorite, int[][] bridges, int strand) {
        ArrayList<int[]> bridges1 = arrayToArrayList(bridges);
        ArrayList<int[]> newBridges = addBridgeWithDeterminateThreadStart(strand, bridges1, strands);
        if (newBridges != null) {
            for (int i = 0; i < newBridges.size(); i++) {
                double position = (double) (newBridges.get(i)[0] * spiderweb.getRadio()) / 23;
                spiderweb.addBridge(colors[i], (int) position, newBridges.get(i)[1]);
            }
        }
        spiderweb.spiderSit(strand);
        spiderweb.spiderWalk(true);
        isOk = spiderweb.ok();
        spiderweb.spiderWalk(false);
        isOk = spiderweb.ok();
        if (newBridges != null) {
            for (int i = 0; i < newBridges.size(); i++) {
                spiderweb.delBridge(colors[i]);
            }
        }
    }

    /**
     * simulate the problem of the Spider Walk for all the strands, so the spider start to sit in the first
     * strand, create the new bridges and walk to the favorite strand, then the spider return to the center
     * and delete the bridges that it created, then the spider start to sit in the second strand and do the same
     *
     * @param strands  strands
     * @param favorite favorite strand
     * @param bridges  bridges
     */
    public static void simulate(int strands, int favorite, int[][] bridges) {
        if(spiderweb != null) makeInvisible();
        spiderweb = new Spiderweb(strands, favorite, bridges);
        isOk = spiderweb.ok();
        makeVisible();
        s = favorite;
        n = strands;
        ordenarMatrizPorPrimerElemento(bridges);
        numBridges = bridges.length;
        for (int i = 0; i < N * 4; i++) {
            seg[i] = new Node();
        }
        build(1, n, 1);
        for (int i = 1; i <= n; i++) {
            simulate(strands, favorite, bridges, i);
        }
    }

    //convertir un arreglo a un ArrayList
    private static ArrayList<int[]> arrayToArrayList(int[][] bridge) {
        ArrayList<int[]> arrayList = new ArrayList<>();
        for (int[] element : bridge) {
            arrayList.add(element);
        }
        return arrayList;
    }

    //determina si el camino que debe hacer desde determinado strand es creciente o increciente
    private static boolean determinateThreadStart(int initStrand, ArrayList<int[]> bridges, int distance) {
        return distance == seg[initStrand].min_inc;

    }

    //hacer el arreglo paso apaso para saber sobre que strands iterar en el proceso de añadir en Dec
    private static ArrayList<Integer> pasoAPasocreatePasoAPasoDec(int initStrand, int strands) {
        ArrayList<Integer> pasoAPaso = new ArrayList<>();
        for (int inicio = initStrand; inicio >= 1; inicio--) {
            pasoAPaso.add(inicio);
        }
        for (int fin = strands; fin > initStrand; fin--) {
            pasoAPaso.add(fin);
        }
        return pasoAPaso;
    }

    //hacer el arreglo pasoApaso para saber sobre que strands iterar en el proceso de añadir en Inc
    private static ArrayList<Integer> pasoAPasocreatePasoAPasoInc(int initStrand, int strands) {
        ArrayList<Integer> pasoAPaso = new ArrayList<>();
        for (int inicio = initStrand; inicio <= strands; inicio++) {
            pasoAPaso.add(inicio);
        }
        for (int fin = 1; fin < initStrand; fin++) {
            pasoAPaso.add(fin);
        }
        return pasoAPaso;
    }

    //añadir a posNewBridges los nuevos puentes que se van poniendo una posición adelante 
    /* private void addAndSortInPosNewBridges( ArrayList<int[]> newBridges){
        if(newBridges!=null){
           for(int[] bridge:  newBridges){
               posNewBridges.add(bridge[0]+1);
           }
           Collections.sort(posNewBridges);
        }
        
    }*/

    // itera sobre todos los posibles lugares para poner los puentes por cada strand
    private static ArrayList<int[]> iterador(ArrayList<Integer> pasoAPaso, ArrayList<int[]> newBridges, int distance, int initStrand, ArrayList<int[]> bridges) {
        for (int strand : pasoAPaso) {
            for (int posible = 1; posible <= 22; posible++) { //itera sobre las posiciones posibles para añadir puentes
                // revisa que entre los puentes no se cree uno contiguo
                if (!(adyacentBridgeswithDeterminateBridges(newBridges, posible, strand) || adyacentBridges(posible, strand))) {
                    int[] ne = {posible, strand};
                    bridges.add(ne);
                    ordenarMatrizPorPrimerElementoDeMayorAMenor(bridges);
                    build(1, n, 1);
                    segWithBridges(bridges); //hago el solve con el nuevo puente
                    if (query(initStrand, 1, n, 1) >= distance) { //si con el nuevo puente no acorte el camino, borro el puente
                        bridges.remove(ne);
                    } else { //de lo contrario agrego el puente a mi solución en este intStrand y disminuto la distancia, corto la iteracion en el strand
                        newBridges.add(ne);
                        distance = query(initStrand, 1, n, 1);
                    }
                }
            }
            if (query(initStrand, 1, n, 1) == 0) {// si resulta que con el nuevo puente en strand la distancia es cero, corto iteracion sobre todos los strands
                break;
            }
            //addAndSortInPosNewBridges(newBridges);
        }
        return newBridges;
    }

    // busca los puentes que hay que añadir para que desde un hilo dado
    // llegue hasta el spot y devuelve un arrayList con esos nuevos puentes
    private static ArrayList<int[]> addBridgeWithDeterminateThreadStart(int initStrand, ArrayList<int[]> bridges, int strands) {
        ArrayList<int[]> newBridges = new ArrayList<>();
        int m = bridges.size(), n = strands;
        build(1, n, 1);
        ordenarMatrizPorPrimerElementoDeMayorAMenor(bridges);
        segWithBridges(bridges);
        int distance = query(initStrand, 1, n, 1);
        boolean camino = determinateThreadStart(initStrand, bridges, distance);
        if (distance != 0) {
            if (camino) {// añadir los puentes mínimos en increciente
                ArrayList<Integer> pasoAPaso = pasoAPasocreatePasoAPasoInc(initStrand, strands);
                newBridges = iterador(pasoAPaso, newBridges, distance, initStrand, bridges);
            } else {// añadir los puentes mínimos en dec
                ArrayList<Integer> pasoAPaso = pasoAPasocreatePasoAPasoDec(initStrand, strands);
                newBridges = iterador(pasoAPaso, newBridges, distance, initStrand, bridges);
            }
            return newBridges;
        }
        return null;
    }

    // mira si hay un puente adyacente al que quiero poner o si lo estoy sobreponiendo
    private static boolean adyacentBridges(int position, int strand) {
        int next = (strand % n) + 1;
        boolean verificador = false;
        HashMap<Integer, ArrayList<Bridge>> puentesPorLinea = spiderweb.getPuentesPorLineas();
        if (puentesPorLinea.get(strand - 1) != null) {
            for (Bridge bridges : puentesPorLinea.get(strand - 1)) {
                if (compararConMargenError(bridges.getDistance(), (double) position * spiderweb.getRadio() / 23)) {
                    verificador = true;
                    break;
                }
            }
        }
        if (puentesPorLinea.get(next - 1) != null) {
            for (Bridge bridges : puentesPorLinea.get(next - 1)) {
                if (compararConMargenError((double) bridges.getDistance(), (double) position * spiderweb.getRadio() / 23)) {
                    verificador = true;
                    break;
                }
            }
        }
        return verificador;
    }

    // dado una lista de puentes, verifica si el nuevo puente a colocar tiene un adyacente de la lista dada
    private static boolean adyacentBridgeswithDeterminateBridges(ArrayList<int[]> newBridges, int position, int strand) {
        int strand0next = (strand % n) + 1;
        boolean verificador = false;
        if (!newBridges.isEmpty()) {
            for (int[] bridge : newBridges) {
                int strand1 = bridge[0];
                int strand1next = (strand1 % n) + 1;
                int position1 = bridge[0];
                if ((strand1 == strand0next || strand1 == strand || strand1next == strand) && position == position1) {
                    verificador = true;
                    break;
                }
            }
        }
        return verificador;
    }

    //This method is used to compare two numbers with a margin of error.
    private static boolean compararConMargenError(double numero1, double numero2) {
        return Math.abs(numero1 - numero2) <= 7;
    }

}


