/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bst;

import java.util.HashSet;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author BRKsCosta
 */
public class IntegerBSTTest {

    private final IntegerBST integer = new IntegerBST();

    public IntegerBSTTest() {
    }

    @Before
    public void setUp() {
        integer.insert(4);
        integer.insert(2);
        integer.insert(8);
        integer.insert(1);
        integer.insert(3);
        integer.insert(6);
        integer.insert(5);
        integer.insert(10);
        integer.insert(9);
        integer.insert(14);

    }

    @Test
    public void test_exists_after_insert() {

        integer.insert(7);
        assertEquals("Esperado 7", true, integer.exists(7));
        integer.insert(14);
        assertEquals("Esperado 14", true, integer.exists(14));
        integer.insert(3);
        assertEquals("Esperado 7", true, integer.exists(3));

    }

    @Test
    public void test_not_exists_after_remove() {
        integer.remove(7);
        assertEquals("Devera não existir 7", false, integer.exists(7));
        integer.remove(14);
        assertEquals("Devera não existir 14", false, integer.exists(14));
        integer.remove(3);
        assertEquals("Devera não existir 3", false, integer.exists(3));
    }

    private boolean equals(Iterable<Integer> atual, Integer... expected) {
        int i = 0;
        int count = 0;

        for (int a : atual) {
            if (a != expected[i++]) {
                return false;
            }
            count++;
        }

        return count == expected.length;

    }

    @Test
    public void test_inorder_from_several_trees() {
        Iterable<Integer> initialOrder = integer.inOrder();
        assertEquals("Elemento não inOrder", true, equals(initialOrder, 1, 2, 3, 4, 5, 6, 8, 9, 10, 14));

        integer.insert(17);
        Iterable<Integer> insert17 = integer.inOrder();
        assertEquals("Elemento não inOrder", true, equals(insert17, 1, 2, 3, 4, 5, 6, 8, 9, 10, 14, 17));

        integer.remove(4);
        Iterable<Integer> remove4 = integer.inOrder();
        assertEquals("Elemento não inOrder", true, equals(remove4, 1, 2, 3, 5, 6, 8, 9, 10, 14, 17));

    }

    @Test
    public void test_breathOrder() {

        Iterable<Integer> initialOrder = integer.breadthOrder();
        //System.out.println("Aaaaaa " + initialOrder);
        assertEquals("Elemento não breathOrder", true, equals(initialOrder, 4, 2, 8, 1, 3, 6, 10, 5, 9, 14));

        integer.remove(4);
        Iterable<Integer> remove4 = integer.breadthOrder();
        assertEquals("Elemento não breathOrder", true, equals(remove4, 5, 2, 8, 1, 3, 6, 10, 9, 14));

        integer.remove(5);
        Iterable<Integer> remove5 = integer.breadthOrder();
        assertEquals("Elemento não breathOrder", true, equals(remove5, 6, 2, 8, 1, 3, 10, 9, 14));

    }

    @Test(expected = EmptyContainerException.class)
    public void test_check_sum_empty_exception() {
        for (Integer value : integer.inOrder()) {
            integer.remove(value);
        }

        integer.sum();
    }

    @Test
    public void test_sum_initialTree_insert_remove() {

        int sumInitialTree = integer.sum();
        assertEquals("Elemento não inOrder", sumInitialTree, 62);

        integer.insert(0);

        int insert0 = integer.sum();
        assertEquals("Elemento não inOrder", insert0, 62);

        integer.insert(7);
        int insert7 = integer.sum();
        assertEquals("Elemento não inOrder", insert7, 69);

        integer.remove(5);
        int removed5 = integer.sum();
        assertEquals("Elemento não inOrder", removed5, 64);

    }

    @Test(expected = EmptyContainerException.class)
    public void test_exception() {
        for (Integer values : integer.inOrder()) {
            integer.remove(values);
        }

        integer.sumInternals();
    }

    @Test
    public void test_sum_isInternal() {

        int sumInitialTree = integer.sumInternals();
        assertEquals("Elemento não inOrder", sumInitialTree, 26);

        integer.insert(15);
        int insert15 = integer.sumInternals();
        assertEquals("Elemento não inOrder", insert15, 40);

        integer.remove(4);
        int removed4 = integer.sumInternals();
        assertEquals("Elemento não inOrder", removed4, 34);
    }

    @Test(expected = EmptyContainerException.class)
    public void test_countGreatherThan_except() {
        for (Integer values : integer.inOrder()) {
            integer.remove(values);
        }

        integer.greaterThan(1);
    }

    @Test
    public void test_countGretherThan() {
        int greather1 = integer.countGreaterThan(1);
        assertEquals("Test countGreatherThan", 9, greather1);

    }

    @Test(expected = EmptyContainerException.class)
    public void test_greatherThan_except() {

        for (Integer values : integer.inOrder()) {
            integer.remove(values);
        }

        integer.greaterThan(1);
    }

    @Test
    public void test_greatherThan() {

        HashSet<Integer> myArr = new HashSet<>();
            
        Set<Integer> greatherX = integer.greaterThan(14);
        myArr.addAll(greatherX);
        
        greatherX.forEach((values) -> {
            myArr.add(values);
        });
        
        assertEquals("A lista deverá ser ", myArr, greatherX);

    }

}
