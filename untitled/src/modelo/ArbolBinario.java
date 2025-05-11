package modelo;

import java.util.ArrayList;
import java.util.List;

public class ArbolBinario {
    private Nodo raiz;

    public Nodo getRaiz() {
        return raiz;
    }

    public void insertar(int dato) {
        raiz = insertarRec(raiz, dato);
    }

    private Nodo insertarRec(Nodo actual, int dato) {
        if (actual == null) {
            return new Nodo(dato);
        }
        if (dato < actual.dato) {
            actual.izquierdo = insertarRec(actual.izquierdo, dato);
        } else if (dato > actual.dato) {
            actual.derecho = insertarRec(actual.derecho, dato);
        }
        return actual;
    }

    public boolean buscar(int dato) {
        return buscarRec(raiz, dato);
    }

    private boolean buscarRec(Nodo actual, int dato) {
        if (actual == null) {
            return false;
        }
        if (dato == actual.dato) {
            return true;
        }
        return dato < actual.dato
                ? buscarRec(actual.izquierdo, dato)
                : buscarRec(actual.derecho, dato);
    }

    public void eliminar(int dato) {
        raiz = eliminarRec(raiz, dato);
    }

    private Nodo eliminarRec(Nodo actual, int dato) {
        if (actual == null) {
            return null;
        }

        if (dato == actual.dato) {
            if (actual.izquierdo == null) {
                return actual.derecho;
            } else if (actual.derecho == null) {
                return actual.izquierdo;
            }

            actual.dato = encontrarMinimo(actual.derecho);
            actual.derecho = eliminarRec(actual.derecho, actual.dato);

        } else if (dato < actual.dato) {
            actual.izquierdo = eliminarRec(actual.izquierdo, dato);
        } else {
            actual.derecho = eliminarRec(actual.derecho, dato);
        }
        return actual;
    }

    private int encontrarMinimo(Nodo nodo) {
        return nodo.izquierdo == null ? nodo.dato : encontrarMinimo(nodo.izquierdo);
    }

    public List<Integer> recorridoInorden() {
        List<Integer> resultado = new ArrayList<>();
        recorridoInordenRec(raiz, resultado);
        return resultado;
    }

    private void recorridoInordenRec(Nodo nodo, List<Integer> resultado) {
        if (nodo != null) {
            recorridoInordenRec(nodo.izquierdo, resultado);
            resultado.add(nodo.dato);
            recorridoInordenRec(nodo.derecho, resultado);
        }
    }

    public List<Integer> recorridoPreorden() {
        List<Integer> resultado = new ArrayList<>();
        recorridoPreordenRec(raiz, resultado);
        return resultado;
    }

    private void recorridoPreordenRec(Nodo nodo, List<Integer> resultado) {
        if (nodo != null) {
            resultado.add(nodo.dato);
            recorridoPreordenRec(nodo.izquierdo, resultado);
            recorridoPreordenRec(nodo.derecho, resultado);
        }
    }

    public List<Integer> recorridoPostorden() {
        List<Integer> resultado = new ArrayList<>();
        recorridoPostordenRec(raiz, resultado);
        return resultado;
    }

    private void recorridoPostordenRec(Nodo nodo, List<Integer> resultado) {
        if (nodo != null) {
            recorridoPostordenRec(nodo.izquierdo, resultado);
            recorridoPostordenRec(nodo.derecho, resultado);
            resultado.add(nodo.dato);
        }
    }
}
