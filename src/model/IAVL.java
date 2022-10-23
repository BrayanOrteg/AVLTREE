package model;

public interface IAVL <T extends Comparable>{

    public void insert(Nodo<T> parent, T dato);

    public Nodo<T> delete(T dato, Nodo<T> current);

    public void Balance(Nodo<T> parent);

    public void leftRotate(Nodo<T> x);

    public void rigthRotate(Nodo<T> y);
}
