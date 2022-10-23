package model;

public class ABB implements IAVL<Integer>{
    public Nodo<Integer> root;
    public String outPrints="";

    public ABB() {

    }

    public boolean exist(int busqueda) {
        return exist(this.root, busqueda);
    }

    private boolean exist(Nodo<Integer> n, Integer busqueda) {
        if (n == null) {
            return false;
        }
        if (n.getKey().compareTo(busqueda) == 0) {
            return true;
        } else if (busqueda.compareTo(n.getKey()) < 0) {
            return exist(n.left(), busqueda);
        } else {
            return exist(n.rigth(), busqueda);
        }

    }

    public void insert(int dato) {
        if (this.root == null) {
            this.root = new Nodo(dato, null, 1);
        } else {
            this.insert(this.root, dato);
        }
    }
    @Override
    public void insert(Nodo<Integer> parent, Integer dato) {
        if (dato.compareTo(parent.getKey()) >= 0) {
            if (parent.rigth() == null) {
                parent.setRigth(new Nodo(dato, parent, 1));
                if(parent.left()==null){
                    parent.setHeight(parent.getHeight()+1);
                }
            } else {
                parent.setHeight(parent.getHeight()+1);
                this.insert(parent.rigth(), dato);
            }
        } else {
            if (parent.left() == null) {
                parent.setLeft(new Nodo(dato, parent, 1));
                if(parent.rigth()==null){
                    parent.setHeight(parent.getHeight()+1);
                }
            } else {
                parent.setHeight(parent.getHeight()+1);
                this.insert(parent.left(), dato);
            }
        }
        Balance(parent);
    }


    public String preorden() {

        this.preorden(this.root);
        return outPrints;
    }

    private void preorden(Nodo n) {
        if (n != null) {
            outPrints+=n.getKey()+",";
            preorden(n.left());
            preorden(n.rigth());
        }
    }

    public void setOutPrints(String outPrints) {
        this.outPrints = outPrints;
    }

    private void inorden(Nodo n) {
        if (n != null) {
            inorden(n.left());
            n.imprimirDato();
            inorden(n.rigth());
        }
    }

    private void postorden(Nodo n) {
        if (n != null) {
            postorden(n.left());
            postorden(n.rigth());
            n.imprimirDato();
        }
    }
    @Override
    public Nodo<Integer> delete(Integer dato, Nodo<Integer> current){
        if(current == null){
            return null;
        }
        // encontramos el dato
        if(current.getKey().compareTo(dato) == 0){
            //1. Nodo Hoja
            if(current.left() == null && current.rigth() == null){
                if(current == root){
                    root = null;
                }
                return null;
            }
            //2. Nodo solo hijo izquierdo
            else if (current.rigth() == null){
                if(current==root){
                    root = current.left();
                    current.left().setHeight(1);
                }else{
                    current.left().setParent(current.getParent());
                }
                Balance(current.left());
                return current.left();
            }
            //3. Nodo solo hijo derecho
            else if(current.left() == null){
                if(current==root){
                    root = current.rigth();
                    current.rigth().setHeight(1);
                }else{
                    current.rigth().setParent(current.getParent());
                }
                Balance(current.rigth());
                return current.rigth();
            }
            //4. Nodo con dos hijos
            else{
                Nodo<Integer> sucesor = getSucesor(current.rigth());
                //Transferencia de valores, NUNCA de conexiones
                current.setKey(sucesor.getKey());
                //Hacer eliminación a partir de la rigth
                Nodo<Integer> subarbolDER = delete(sucesor.getKey(), current.rigth());
                current.setRigth( subarbolDER );
                Balance(current);
                return current;
            }
            // avanzamos en el arbol buscando el dato
        }else if(dato.compareTo(current.getKey()) < 0){
            Nodo<Integer> subArbolIzquierdo = delete(dato, current.left());
            current.setLeft(subArbolIzquierdo);
            return current;
        }else{
            Nodo<Integer> subArbolDerecho = delete(dato, current.rigth());
            current.setRigth(subArbolDerecho);
            return current;
        }
    }

    public Nodo<Integer> getSucesor(Nodo<Integer> current){
        if(current.left() == null){
            return current;
        }
        return getSucesor(current.left());
    }

    public Nodo<Integer> delete(Integer goal){
        return delete(goal, root);
    }


    public void inorden() {
        this.inorden(this.root);
    }

    public void postorden() {
        this.postorden(this.root);
    }
    @Override
    public void Balance(Nodo<Integer> parent){
        Nodo<Integer> q;
        if(parent.getFactorB()==2){
            //Caso A-B-C
            if(parent.rigth()!=null&&parent.rigth().getFactorB()==1) {
                leftRotate(parent);
            }else if(parent.rigth()!=null&&parent.rigth().getFactorB()==0){
                leftRotate(parent);
            }else if(parent.rigth()!=null&&parent.rigth().getFactorB()==-1){
                rigthRotate(parent.rigth());
                leftRotate(parent);
            }
        }else if(parent.getFactorB()==-2){
            //Caso D
            if(parent.left()!=null&&parent.left().getFactorB()==-1){
                rigthRotate(parent);
            }
            //Caso E
            if(parent.left()!=null&&parent.left().getFactorB()==0){
                rigthRotate(parent);
            }
            //Caso F
            if(parent.left()!=null&&parent.left().getFactorB()==1){
                leftRotate(parent.left());
                rigthRotate(parent);
            }
        }
    }
    @Override
    public void leftRotate(Nodo<Integer> x){
        Nodo<Integer> g; //abuelo
        Nodo<Integer> y = x.rigth(); //y es hijo derecho para el caso left rotate
        Nodo<Integer> b; //subarbol izquierdo de y que será hijo derecho de x

        if(x.getParent()!=null){
            g = x.getParent(); //g es abuelo de q
            y.setParent(g); //g es ahora padre de q
            if(g.rigth()==x){
                g.setRigth(y); //asignar q como hijo derecho g

            }
            if(g.left()==x){
                g.setLeft(y); //asignar q como hijo izquierdo de g

            }
        }else{
            y.setParent(null); //caso root
            root = y;
        }

        if(y.left()!=null){
            b = y.left(); //b es subarbol izquierdo de q
            x.setRigth(b); //lo replantamos como derecho de x
        }else{
            x.setRigth(null);
        }

        x.setParent(y);
        y.setLeft(x); //p es replantado como hijo izquierdo de q

        if(y.getParent()!=null){
            y.setHeight(y.getParent().getHeight()-1);
        }

        x.setHeight(x.getParent().getHeight()-1);



    }
    @Override
    public void rigthRotate(Nodo<Integer> y){

        Nodo g; //abuelo
        Nodo x = y.left(); //y es hijo derecho para el caso left rotate
        Nodo b; //subarbol izquierdo de y que será hijo derecho de x

        if(y.getParent()!=null){
            g = y.getParent(); //g es abuelo de q
            x.setParent(g); //g es ahora padre de q
            if(g.rigth()==y){
                g.setRigth(x); //asignar q como hijo derecho g
            }
            if(g.left()==y){
                g.setLeft(x); //asignar q como hijo izquierdo de g
            }
        }else{
            x.setParent(null); //caso root
            root = x;
        }

        if(x.rigth()!=null){
            b = x.rigth(); //b es subarbol izquierdo de q
            y.setLeft(b); //lo replantamos como derecho de x
        }else{
            y.setLeft(null);
        }

        y.setParent(x);
        x.setRigth(y); //p es replantado como hijo izquierdo de q

        if(x.getParent()!=null){
            x.setHeight(x.getParent().getHeight()-1);
        }

        y.setHeight(y.getParent().getHeight()-1);

    }

}
