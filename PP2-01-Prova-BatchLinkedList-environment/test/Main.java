import adt.linkedList.batch.BatchLinkedListImpl;
import util.GenericException;

public class Main {
	
	public static void main(String[] args) throws GenericException {
		BatchLinkedListImpl<Integer> list = new BatchLinkedListImpl<>();
		list.inserirEmBatch(0, new Integer[]{1,2,4});
		
		list.inserirEmBatch(1, new Integer[]{0,10,8,5}); // 1 2 0 10 8 5 4
		list.removerEmBatch(6, 1);
		System.out.println(list.toStringFromHead());
		System.out.println(list.toStringFromLast());
	}

}
