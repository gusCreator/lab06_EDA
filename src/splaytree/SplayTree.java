package splaytree;

public class SplayTree<E extends Comparable<E>> implements Tree<E> {

  private Node<E> root;
  private int size;

  public SplayTree(){
    root = null;
  }
  
  public void insert(E data) {
    if (root == null) {
        root = new Node<E>(data);
        size++;
        return;
    }
    root = splay(root, data);
    int cmp = data.compareTo(root.data);
    if (cmp == 0) {
        return;
    }

    Node<E> newNode = new Node<>(data);
    size++;
    if (cmp < 0) {
        newNode.left = root.left;
        newNode.right = root;
        root.left = null;
    } else {
        newNode.right = root.right;
        newNode.left = root;
        root.right = null;
    }
    root = newNode;
  }

  public void remove(E data) {
    if (root == null) {
        return;
    }

    root = splay(root, data);

    int cmp = data.compareTo(root.data);
    if (cmp != 0) {
        return;
    }

    if (root.left == null) {
        root = root.right;
    } else {
        Node<E> maxLeft = root.left;
        while (maxLeft.right != null) {
            maxLeft = maxLeft.right;
        }
        root.left = splay(root.left, maxLeft.data);
        root.left.right = root.right;
        root = root.left;
    }
}

  public E search(E data) {
    if (root == null) {
        return null;
    }

    root = splay(root, data);

    int cmp = data.compareTo(root.data);
    if (cmp != 0) {
        return null;
    }

    return root.data;
}
  public int size(){
    return size;
  }

  public boolean isEmpty(){
    return root == null;
  }

  private Node<E> splay(Node<E> root, E data) {
      if (root == null) return null;

      int cmp1 = data.compareTo(root.data);
      if (cmp1 < 0) {
        if (root.left == null) return root;
        int cmp2 = data.compareTo(root.left.data);
        if (cmp2 < 0) {
          root.left.left = splay(root.left.left, data);
          root = rotateRight(root);
        } else if (cmp2 > 0) {
          root.left.right = splay(root.left.right, data);
          if (root.left.right != null) {
              root.left = rotateLeft(root.left);
          }
        }
        return (root.left == null) ? root : rotateRight(root);
      } else if (cmp1 > 0) {
        if (root.right == null) return root;
        int cmp2 = data.compareTo(root.right.data);
        if (cmp2 < 0) {
          root.right.left = splay(root.right.left, data);
          if (root.right.left != null) {
              root.right = rotateRight(root.right);
          }
        } else if (cmp2 > 0) {
          root.right.right = splay(root.right.right, data);
          root = rotateLeft(root);
        }
        return (root.right == null) ? root : rotateLeft(root);
      } else {
          return root;
      }
    }

    private Node<E> rotateRight(Node<E> h) {
        Node<E> x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }

    private Node<E> rotateLeft(Node<E> h) {
        Node<E> x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }

  private static class Node<E> {
    E data;
    Node<E> left, right;

    public Node(E dato){
      this.data = dato;
      this.left = this.right = null;
    }

    @Override
    public String toString(){
      return data.toString();
    }

  }
  @Override
  public String toString(){
    return toString(root);
  }

  private String toString(Node<E> actual){
    if(actual == null) return "";
    return toString(actual.left) + " " + actual.toString() + " " + toString(actual.right);
   }
}