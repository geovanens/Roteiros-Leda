package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		} 
		else if (element != null) {
			if (isEmpty()) {
				this.head = 0;
				this.tail = 0;
				this.array[0] = element;
			}

			else {
				int capacity = this.array.length;
				this.tail = (this.tail + 1) % capacity;
				this.array[this.tail] = element;
			}

			this.elements++;

		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}

		T value = this.array[this.head];

		if (this.head == this.tail) {
			this.head = -1;
			this.tail = -1;
		} else {
			int capacity = this.array.length;
			this.head = (this.head + 1) % capacity;
		}

		this.elements--;
		return value;
	}

	@Override
	public T head() {
		if (isEmpty()) {
			return null;
		}

		return this.array[this.head];
	}

	@Override
	public boolean isEmpty() {
		return this.head == -1 && this.tail == -1;
	}

	@Override
	public boolean isFull() {
		int capacity = this.array.length;
		if (capacity == 0) {
			return true;
		}
		return (this.tail + 1) % capacity == this.head;
	}

}
