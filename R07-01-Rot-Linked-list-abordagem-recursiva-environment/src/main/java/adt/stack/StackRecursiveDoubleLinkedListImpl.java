package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (!isFull()) {
			if (element != null) {
				this.top.insert(element);
			}
			
		}
		else {
			throw new StackOverflowException();
		}
		
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (!isEmpty()) {
			T retorno = this.top();
			this.top.removeLast();
			return retorno;
		}
		else {
			throw new StackUnderflowException();
		}
		
	}

	@Override
	public T top() {
		if (!isEmpty()) {
			return top((RecursiveDoubleLinkedListImpl<T>) this.top);
		}
		return null;
	}
	
	private T top(RecursiveDoubleLinkedListImpl<T> list) {
		if (list.getNext().isEmpty()) {
			return list.getData();
		}
		else {
			return top((RecursiveDoubleLinkedListImpl<T>)list.getNext());
		}
	}

	@Override
	public boolean isEmpty() {
		return this.top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.top.size() == this.size;
	}
}
