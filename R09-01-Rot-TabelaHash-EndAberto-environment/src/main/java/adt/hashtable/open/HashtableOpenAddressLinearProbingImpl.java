package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void insert(T element) {
			if (isFull()) {
				throw new HashtableOverflowException();
			}
			else {
				if (element != null && !contains(element)) {
					int probe = 0;
					int hash = ((HashFunctionOpenAddress) this.getHashFunction()).hash(element, probe);
					while (this.table[hash] != null && this.table[hash] != this.deletedElement) {
						probe++;
						hash = ((HashFunctionOpenAddress) this.getHashFunction()).hash(element, probe);
						this.COLLISIONS++;
					}
					this.table[hash] = element;
					this.elements++;
				}
			}
			
			
					
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void remove(T element) {
		if (element != null && !isEmpty()) {
			int probe = 0;
			int hash = ((HashFunctionOpenAddress) this.getHashFunction()).hash(element, probe);
			while (this.table[hash] != null  && !this.table[hash].equals(element) && probe <= this.capacity()) {
				probe++;
				hash = ((HashFunctionOpenAddress) this.getHashFunction()).hash(element, probe);
			}
			if (this.table[hash] != null && this.table[hash].equals(element)) {
				this.table[hash] = this.deletedElement;
				this.elements--;
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public T search(T element) {
		if (element != null && !isEmpty()) {
			int probe = 0;
			int hash = ((HashFunctionOpenAddress) this.getHashFunction()).hash(element, probe);
			while (this.table[hash] != null  && !this.table[hash].equals(element) && probe <= this.capacity()) {
				probe++;
				hash = ((HashFunctionOpenAddress) this.getHashFunction()).hash(element, probe);
			}
			if (this.table[hash] != null && this.table[hash].equals(element)) {
				return (T) this.table[hash];
			}
		}
		return null;
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int indexOf(T element) {
		if (element != null && !isEmpty()) {
			int probe = 0;
			int hash = ((HashFunctionOpenAddress) this.getHashFunction()).hash(element, probe);
			while (this.table[hash] != null  && !this.table[hash].equals(element) && probe <= this.capacity()) {
				probe++;
				hash = ((HashFunctionOpenAddress) this.getHashFunction()).hash(element, probe);
			}
			if (this.table[hash] != null && this.table[hash].equals(element)) {
				return hash;
			}
		}
		return -1;
	}
	
	private boolean contains(T element) {
		return indexOf(element) != -1;
	}
}
