package splaytree;

public class Test {
  public static void main(String[] args) {
    SplayTree<Integer> splayTree = new SplayTree<Integer>();

        splayTree.insert(10);
        splayTree.insert(5);
        splayTree.insert(15);
        splayTree.insert(7);
        splayTree.insert(12);

        splayTree.display();
        splayTree.search(5);
        splayTree.display();
        splayTree.remove(12);
        splayTree.display();
        System.out.println("Árbol Splay después de insertar:");
        System.out.println(splayTree);
        
  }
}