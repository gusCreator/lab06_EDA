package splaytree;
public interface Tree<E> {
    void insert(E element);
    void remove(E element);
    int size();
    boolean isEmpty();
    E search(E element);
}

