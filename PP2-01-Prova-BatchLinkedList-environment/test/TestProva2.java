import static org.junit.Assert.*;

import org.junit.Test;

import adt.linkedList.batch.BatchLinkedListImpl;
import util.GenericException;

public class TestProva2 {
	
	BatchLinkedListImpl<Integer> list1 = new BatchLinkedListImpl<>();
	

	@Test
	public void insert() throws GenericException {
		list1.inserirEmBatch(0, new Integer[]{1,2,4});
		assertEquals("1 2 4", list1.toStringFromHead());
		assertEquals("1 2 4", list1.toStringFromLast());
	}
	
	@Test(expected=GenericException.class)
	public void insertComErro1() throws GenericException {
		list1.inserirEmBatch(2, new Integer[]{1,2,4});
	}
	
	@Test(expected=GenericException.class)
	public void insertComErro2() throws GenericException {
		list1.inserirEmBatch(-1, new Integer[]{1,2,4});
	}
	
	@Test(expected=GenericException.class)
	public void insertComErro3() throws GenericException {
		list1.inserirEmBatch(0, null);
	}
	
	@Test
	public void remove() throws GenericException {
		list1.inserirEmBatch(0, new Integer[]{1,2,4});
		assertEquals("1 2 4", list1.toStringFromHead());
		
		list1.removerEmBatch(2, 0);
		assertEquals("1 2 4", list1.toStringFromHead());
		
		list1.removerEmBatch(1, 1);
		assertEquals("1 4", list1.toStringFromHead());
	}
	
	@Test(expected=GenericException.class)
	public void removeComErro1() throws GenericException {
		list1.removerEmBatch(0, 5);
	}
	
	@Test(expected=GenericException.class)
	public void removeComErro2() throws GenericException {
		list1.removerEmBatch(-1, 2);
	}

}
