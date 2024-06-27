package skiplist;
public interface List<T> {
    void add(T elemento);
    T search(int indice);
    T search(T elemento);
    void remove(T elemento);
    int size();
    boolean isEmpty();
}