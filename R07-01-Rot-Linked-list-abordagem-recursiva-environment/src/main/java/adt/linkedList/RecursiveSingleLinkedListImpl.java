package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.getData() == null;
	}

	@Override
	public int size() {
		return this.size(this, 0);
	}
	
	private int size(RecursiveSingleLinkedListImpl<T> list, int size) {
		if (list.isEmpty()) {
			return size;
		}
		return size(list.getNext(), size+1);
	}

	@Override
	public T search(T element) {
		return search(this, element);
	}
	
	private T search(RecursiveSingleLinkedListImpl<T> list, T element) {
		if (list.isEmpty()) {
			return null;
		}
		if (list.getData() == element) {
			return element;
		}
		return search(list.getNext(), element);
	}
	
	@Override
	public void insert(T element) {
		insert(this, element);
	}
	
	private void insert(RecursiveSingleLinkedListImpl<T> list, T element) {
		if (list.isEmpty()) {
			list.setData(element);
			list.setNext(new RecursiveSingleLinkedListImpl<>());
		}
		else {
			insert(list.getNext(), element);
		}
		
	}

	/**
	 * Verifica se um elemento esta contido na lista
	 * @param element o elemento a ser procurado
	 * @return true se o elemento existe na lista ou falso caso contrario. 
	 */
	protected boolean contains(T element) {
		return search(element) != null;
	}

	@Override
	public void remove(T element) {
		if (contains(element)) {
			remove(this, element);
		}
	}
	
	private void remove(RecursiveSingleLinkedListImpl<T> list, T element) {
		RecursiveSingleLinkedListImpl<T> next = list.getNext();
		if (next.getData() == element) {
			list.setNext(next.getNext());
		}
		else {
			remove(list.getNext(), element);
		}
		
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[this.size()];
		return toArray(this, array, 0);
	}

	private T[] toArray(RecursiveSingleLinkedListImpl<T> list, T[] array, int index) {
		if (list.isEmpty()) {
			return array;
		}
		
		array[index] = list.getData();
		return toArray(list.getNext(), array, index+1);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
