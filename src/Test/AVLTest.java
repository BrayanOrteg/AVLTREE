package Test;
import junit.framework.TestCase;
import model.*;

public class AVLTest extends TestCase {

    private ABB tree;

    /*
    Integrantes:
    Juan Camilo Salazar Quintero (A00381085)
    Brayan Steven Ortega García (A00380171)
    Andres Camilo Romero Ruíz (A00380637)
     */

    public void setup1() {

        tree = new ABB();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);
    }

    public void setup2() {

        tree = new ABB();
        tree.insert(10);
        tree.insert(12);
        tree.insert(11);
        tree.insert(9);

    }

    public void setup3() {

        tree = new ABB();
        tree.insert(1);
        tree.insert(2);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);

    }

    public void testInsert1(){
        setup1();
        assertEquals(tree.preorden(), "30,20,10,25,40,50,");
        tree.setOutPrints("");
    }

    public void testInsert2(){
        setup2();
        tree.insert(8);
        assertEquals(tree.preorden(), "10,9,8,11,12,");
        tree.setOutPrints("");

    }

    public void testInsert3(){
        setup3();
        tree.insert(3);
        assertEquals(tree.preorden(), "4,2,1,3,6,5,7,");
        tree.setOutPrints("");
    }

    public void testDelete1(){
        setup1();
        tree.delete(25);
        assertEquals(tree.preorden(), "30,20,10,40,50,");
        tree.setOutPrints("");
    }

    public void testDelete2(){
        setup2();
        tree.insert(8);
        tree.delete(8);
        assertEquals(tree.preorden(), "10,9,11,12,");
        tree.setOutPrints("");
    }

    public void testDelete3(){
        setup3();
        tree.insert(3);
        tree.delete(7);
        assertEquals(tree.preorden(), "4,2,1,3,6,5,");
        tree.setOutPrints("");
    }
}
