package splaytree;

public class Test {
  public static void main(String[] args) {
    SplayTree<Integer> splayTree = new SplayTree<Integer>();

        // Insertando elementos en el árbol
        splayTree.insert(10);
        splayTree.insert(5);
        splayTree.insert(15);
        splayTree.insert(7);
        splayTree.insert(12);

        // Imprimiendo el árbol para verificar la estructura después de insertar
        System.out.println("Árbol Splay después de insertar:");
        System.out.println(splayTree);
        System.out.println();
  }
}