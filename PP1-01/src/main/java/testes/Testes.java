package testes;

import static org.junit.Assert.*;

import org.junit.Test;

import problems.FloorCeilBinarySearch;

public class Testes {
	
	FloorCeilBinarySearch fcbs = new FloorCeilBinarySearch();
	
	Integer[] vazio = {};
	Integer[] repetidos = {1,1,1,1};
	Integer[] v = {2,4,6,8,10};

	@Test
	public void vazio1() {
		assertEquals(-1, (int) fcbs.floor(vazio, 1));
	}
	
	@Test
	public void vazio2() {
		assertEquals(-1, (int) fcbs.ceil(vazio, 1));
	}
	
	@Test
	public void t1() {
		assertEquals(1, (int) fcbs.floor(repetidos, 1));
	}
	
	@Test
	public void t2() {
		assertEquals(1, (int) fcbs.ceil(repetidos, 1));
	}
	
	@Test
	public void t4() {
		assertEquals(8, (int) fcbs.floor(v, 8));
	}
	
	@Test
	public void t5() {
		assertEquals(8, (int) fcbs.ceil(v, 8));
	}
	

}
