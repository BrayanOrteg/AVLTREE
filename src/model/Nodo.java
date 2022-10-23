package model;

public class Nodo <T extends Comparable>{
    private T dato;
    private Nodo<T> left, rigth, parent;

    private int factorB, altura;

    public Nodo(T dato, Nodo parent, int altura) {
        this.dato = dato;
        this.left = this.rigth = null;
        this.factorB = 1;
        this.parent = parent;
        this.altura = altura;
    }

    public void setKey(T dato) {
        this.dato = dato;
    }

    public T getKey() {
        return dato;
    }

    public Nodo left() {
        return left;
    }

    public void setLeft(Nodo left) {
        this.left = left;
    }

    public Nodo rigth() {
        return rigth;
    }

    public void setRigth(Nodo rigth) {
        this.rigth = rigth;
    }

    public Nodo getParent() {
        return parent;
    }

    public void setParent(Nodo parent) {
        this.parent = parent;
    }

    public String imprimirDato() {
        String out = "null";
        if(parent!=null){
            out = ""+getKey();
        }
        return out;
    }

    public int getFactorB() {

        if(left!=null&&rigth!=null){
            return rigth.getHeight()-left.getHeight();
        }
        if(rigth==null&&left==null){
            return 0;
        }if(left==null){
            return rigth.getHeight();
        }else {
            return -left.getHeight();
        }

    }

    public int getHeight(){
        return altura;
    }

    public void setHeight(int altura){
        this.altura=altura;
    }
    public int calculateAltura(){
        if(left!=null&&rigth!=null){
            return Math.max(1+rigth.getHeight(),1+left.getHeight());
        }
        if(left==null&rigth==null){
            return 1;
        }
        if(left==null){
            return 1+rigth.getHeight();
        }else{
            return 1+ left.getHeight();
        }
    }
}
