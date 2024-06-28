package skiplist;

import java.util.Random;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

public class SkipList<T extends Comparable<T>> implements List<T> {
    private static final int MAX_NIVEL = 16;
    private final Nodo<T> cabeza;
    private int nivelActual;
    private int size;
    private final Random random;

    private static class Nodo<T> {
        T valor;
        Nodo<T>[] adelante;

        @SuppressWarnings("unchecked")
        Nodo(int nivel, T valor) {
            adelante = new Nodo[nivel + 1];
            this.valor = valor;
        }

        @Override
        public String toString(){
            if(valor == null) return "Head";
            return valor.toString();
        }
    }

    public SkipList() {
        cabeza = new Nodo<>(MAX_NIVEL, null);
        nivelActual = 0;
        random = new Random();
    }

    @Override
    public boolean isEmpty() {
        return cabeza.adelante[0] == null;
    }


    @Override
    public int size(){
        return this.size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void add(T valor) {
        Nodo<T>[] actualizar = new Nodo[MAX_NIVEL + 1];
        Nodo<T> actual = cabeza;

        for (int i = nivelActual; i >= 0; i--) {
            while (actual.adelante[i] != null && actual.adelante[i].valor.compareTo(valor) < 0) {
                actual = actual.adelante[i];
            }
            actualizar[i] = actual;
        }

        actual = actual.adelante[0];

        if (actual == null || actual.valor.compareTo(valor) != 0) {
            int nuevoNivel = randomLevel();
            if (nuevoNivel > nivelActual) {
                for (int i = nivelActual + 1; i <= nuevoNivel; i++) {
                    actualizar[i] = cabeza;
                }
                nivelActual = nuevoNivel;
            }

            Nodo<T> nuevoNodo = new Nodo<>(nuevoNivel, valor);
            size++;
            for (int i = 0; i <= nuevoNivel; i++) {
                nuevoNodo.adelante[i] = actualizar[i].adelante[i];
                actualizar[i].adelante[i] = nuevoNodo;
            }
        }
    }

    @Override
    public T search(T valor) {
        Nodo<T> actual = cabeza;
        for (int i = nivelActual; i >= 0; i--) {
            while (actual.adelante[i] != null && actual.adelante[i].valor.compareTo(valor) < 0) {
                actual = actual.adelante[i];
            }
        }
        actual = actual.adelante[0];

        if (actual != null && actual.valor.compareTo(valor) == 0) {
            return actual.valor;
        }

        return null;
    }

    public T search(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Nodo<T> actual = cabeza.adelante[0];
        int contador = 0;

        while (actual != null && contador < index) {
            actual = actual.adelante[0];
            contador++;
        }

        if (actual == null || contador != index) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        return actual.valor;
    }
    @SuppressWarnings("unchecked")
    @Override
    public void remove(T valor) {
        Nodo<T>[] actualizar = new Nodo[MAX_NIVEL + 1];
        Nodo<T> actual = cabeza;

        for (int i = nivelActual; i >= 0; i--) {
            while (actual.adelante[i] != null && actual.adelante[i].valor.compareTo(valor) < 0) {
                actual = actual.adelante[i];
            }
            actualizar[i] = actual;
        }

        actual = actual.adelante[0];

        if (actual != null && actual.valor.compareTo(valor) == 0) {
            for (int i = 0; i <= nivelActual; i++) {
                if (actualizar[i].adelante[i] != actual) break;
                actualizar[i].adelante[i] = actual.adelante[i];
            }
            size--;

            while (nivelActual > 0 && cabeza.adelante[nivelActual] == null) {
                nivelActual--;
            }
        }
    }

    private int randomLevel() {
        int nivel = 0;
        while (random.nextInt(2) == 1 && nivel < MAX_NIVEL) {
            nivel++;
        }
        return nivel;
    }

    @Override
    public String toString() {
        String res = "[";
        Nodo<T> actual = cabeza.adelante[0];
        while(actual.adelante[0] != null){
            res += actual.toString() + ", ";
            actual = actual.adelante[0];
        }
        return res + "]";
    }

    public void display(){
        if(isEmpty()) return;
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new MultiGraph("SkipList");

        String css = "node { fill-color: black; size: 30px; text-size: 20px; text-color: white; } edge { fill-color: black; size: 2px; arrow-size: 10px, 10px; arrow-shape: arrow; }";
        graph.setAttribute("ui.stylesheet", css);
    
        createList(graph, 50, 5, 40);
        graph.display(false);
    }
    
    private void createList(Graph graph, int x, int y, int xOffset){
        Nodo<T> actual = cabeza;
        while(actual != null){
            graph.addNode(actual.toString()).setAttribute("xyz", x, y, 0);
            graph.getNode(actual.toString()).setAttribute("ui.label", actual.toString());
            x += xOffset;
            actual = actual.adelante[0];
        }

        for (int level = 0; level <= nivelActual; level++) {
            actual = cabeza.adelante[level];
            Nodo<T> prev = cabeza;
    
            while (actual != null) {
                String name = prev.toString() + " to " + actual.toString() + " lev" + level;
                if (graph.getEdge(name) == null) {
                    graph.addEdge(name, prev.toString(), actual.toString()).setAttribute("ui.label", "Level " + level);
                }
                
                prev = actual;
                actual = actual.adelante[level];
            }
        }
        
    }

}