package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}
	
	@Override
	public void insert(T element) {
		if (isEmpty()) {
			this.setData(element);
			this.setNext(new RecursiveDoubleLinkedListImpl<>());
			this.previous = new RecursiveDoubleLinkedListImpl<>();
		}
		else {
			insert((RecursiveDoubleLinkedListImpl<T>)this, element);
		}
	}
	
	private void insert(RecursiveDoubleLinkedListImpl<T> list, T element) {
		RecursiveDoubleLinkedListImpl<T> next = (RecursiveDoubleLinkedListImpl<T>) list.getNext();
		if (next.isEmpty()) {
			RecursiveDoubleLinkedListImpl<T> newList = new RecursiveDoubleLinkedListImpl<>();
			newList.setData(element);
			newList.setNext(next);
			newList.setPrevious(list);
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
			RecursiveDoubleLinkedListImpl<T> newList = new RecursiveDoubleLinkedListImpl<>();
			newList.setData(this.getData());
			newList.setNext(this.getNext());
			newList.setPrevious(this);
			this.setData(element);
			this.setNext(newList);
		}
		
	}

	@Override
	public void remove(T element) {
		if (contains(element)) {
			if (this.getData() == element) {
				removeFirst();
			}
			else {
				remove(this, element);
			}
			
		}
	}
	
	private void remove(RecursiveDoubleLinkedListImpl<T> list, T element) {
		RecursiveDoubleLinkedListImpl<T> next = (RecursiveDoubleLinkedListImpl<T>) list.getNext();
		if (next.getData() == element) {
			list.setNext(next.getNext());
			((RecursiveDoubleLinkedListImpl<T>) next.getNext()).setPrevious(list);
			
		}
		else {
			remove(next, element);
		}
		
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			RecursiveDoubleLinkedListImpl<T> next = (RecursiveDoubleLinkedListImpl<T>) this.getNext();
			this.setData(next.getData());
			this.setNext(next.getNext());
		}
	}


	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (this.getNext().isEmpty()) {
				this.setData(null);
			}
			else {
				removeLast((RecursiveDoubleLinkedListImpl<T>) this);
			}
		}
	}

	private void removeLast(RecursiveDoubleLinkedListImpl<T> list) {
		RecursiveDoubleLinkedListImpl<T> next = (RecursiveDoubleLinkedListImpl<T>) list.getNext();
		if (next.isEmpty()) {
			RecursiveDoubleLinkedListImpl<T> prev = list.getPrevious();
			prev.setNext(new RecursiveDoubleLinkedListImpl<>());
		}
		else {
			removeLast((RecursiveDoubleLinkedListImpl<T>)list.getNext());
		}
		
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
}
