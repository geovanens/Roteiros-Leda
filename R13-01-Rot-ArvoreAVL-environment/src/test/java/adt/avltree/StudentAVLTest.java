package adt.avltree;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import adt.bst.BSTNode;

public class StudentAVLTest {

	private AVLTree<Integer> avl;
	private BSTNode<Integer> NIL = new BSTNode<Integer>();

	@Before
	public void setUp() {
		avl = new AVLTreeImpl<>();
	}

	@Test
	public void testInit() {
		assertTrue(avl.isEmpty());
		assertEquals(0, avl.size());
		assertEquals(-1, avl.height());
		assertEquals(NIL, avl.getRoot());
	}

	@Test
	public void testInsert() {
		avl.insert(-10);
		assertEquals(1, avl.size());
		assertEquals(0, avl.height());
		assertArrayEquals(new Integer[] { -10 }, avl.preOrder());

		assertFalse(avl.isEmpty());
		assertEquals(new Integer(-10), avl.getRoot().getData());

		avl.insert(-15);
		assertEquals(2, avl.size());
		assertEquals(1, avl.height());
		assertArrayEquals(new Integer[] { -10, -15 }, avl.preOrder());

		avl.insert(20);
		assertEquals(3, avl.size());
		assertEquals(1, avl.height());
		assertArrayEquals(new Integer[] { -10, -15, 20 }, avl.preOrder());
		
	}

	@Test
	public void testRemove() {
		avl.insert(55);
		avl.insert(9);
		avl.insert(91);
		avl.insert(12);

		avl.remove(-1);
		assertEquals(4, avl.size());

		avl.remove(91);
		assertEquals(3, avl.size());
		assertArrayEquals(new Integer[] { 12, 9, 55 }, avl.preOrder());

		avl.remove(12);
		assertEquals(2, avl.size());
		assertArrayEquals(new Integer[] { 55, 9 }, avl.preOrder());

		avl.remove(9);
		avl.remove(55);
		assertEquals(NIL, avl.getRoot());
		assertTrue(avl.isEmpty());
		
	}
	
	@Test
	  public void testRemove2() {
	    avl.insert(60);
	    avl.insert(55); 
	    avl.insert(91);
	    avl.insert(9);
	    avl.insert(12);

	    avl.remove(-1);
	    assertTrue(avl.getRoot().getData().equals(60));
	    assertEquals(5, avl.size());
	    assertArrayEquals(new Integer[] { 60, 12, 9, 55, 91 }, avl.preOrder());

	    avl.remove(91);
	    assertTrue(avl.getRoot().getData().equals(12));
	    assertEquals(4, avl.size());
	    assertArrayEquals(new Integer[] { 12, 9, 60, 55 }, avl.preOrder());
	    avl.remove(12);
	    assertTrue(avl.getRoot().getData().equals(55));
	    assertArrayEquals(new Integer[] { 55, 9, 60 }, avl.preOrder());
	    avl.remove(55);
	    assertTrue(avl.getRoot().getData().equals(60));
	    assertArrayEquals(new Integer[] { 60, 9 }, avl.preOrder());
	    avl.remove(60);
	    assertTrue(avl.getRoot().getData().equals(9));
	    assertArrayEquals(new Integer[] { 9 }, avl.preOrder());
	    avl.remove(9);
	    assertArrayEquals(new Integer[] { }, avl.preOrder());
	    assertTrue(avl.isEmpty());
	  }
}
