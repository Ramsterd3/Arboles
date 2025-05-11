package modelo;

public class Nodo {
    public int dato;
    public Nodo izquierdo;
    public Nodo derecho;

    public Nodo(int dato) {
        this.dato = dato;
        izquierdo = derecho = null;
    }
}