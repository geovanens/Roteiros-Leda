package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;
	
	public RecursiveDoubleLinkedListImpl() {
		
	}
	
	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next, RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}
	
	@Override
	public void insert(T element) {
		if (isEmpty()) {
			insertFirst(element);
		}
		else {
			insert((RecursiveDoubleLinkedListImpl<T>)this.getNext(), element);
		}
	}
	
	private void insert(RecursiveDoubleLinkedListImpl<T> list, T element) {
		if (list.isEmpty()) {
			RecursiveDoubleLinkedListImpl<T> newList = new RecursiveDoubleLinkedListImpl<>(element, new RecursiveDoubleLinkedListImpl<>(), new RecursiveDoubleLinkedListImpl<>());
			newList.setPrevious(newList);
			list.setNext(newList);
		}
		else {
			insert((RecursiveDoubleLinkedListImpl<T>)list.getNext(), element);
		}
		
	}
	


	@Override
	public void insertFirst(T element) {
		if (isEmpty()) {
			this.setData(element);
			this.setNext(new RecursiveDoubleLinkedListImpl<>());
			this.setPrevious(new RecursiveDoubleLinkedListImpl<>());
		}
		else {
			RecursiveDoubleLinkedListImpl<T> newList = new RecursiveDoubleLinkedListImpl<>(element, new RecursiveDoubleLinkedListImpl<>(), new RecursiveDoubleLinkedListImpl<>());
			newList.setNext(this);
			this.setPrevious(newList);
		}
		
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			removeFirst((RecursiveDoubleLinkedListImpl<T>)this.getNext());
		}
	}

	private void removeFirst(RecursiveDoubleLinkedListImpl<T> list) {
		RecursiveDoubleLinkedListImpl<T> next = (RecursiveDoubleLinkedListImpl<T>) list.getNext();
		if (next.isEmpty()) {
			list.setNext(next.getNext());
			next.setPrevious(list.getPrevious());
		}
		
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			
		}
	}

	
	@Override
	public void remove(T element) {
		if (contains(element)) {
			
		}
	}


	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}


	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
	
	
}
