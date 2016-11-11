package com.nixsolutions;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class MyCollection<T> implements Collection<T> {

	private Object[] container;
	private int capacity;
	private int defaultCapacity = 10;
	private int size = -1;
	
	public MyCollection() {
		this.capacity = this.defaultCapacity;
		this.container = new Object[capacity];
	}
	
	public MyCollection(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Illegal Capacity: " + capacity);
		}
		
		this.capacity = capacity;
		this.container = new Object[capacity];
	}
	
	@Override
	public boolean add(T arg0) {
		if (this.container.length == size) {
			this.capacity += size;
			Object[] newContainer = new Object[this.capacity];
			for (int i = 0; i < this.container.length; i++) {
				newContainer[i] = this.container[i];
				this.container = newContainer;
			}
		}
		
		container[++size] = arg0;
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		 Iterator<?> e = arg0.iterator();
		 while (e.hasNext()) {
			add((T)e.next());
		}
		
		return false;
	}

	@Override
	public void clear() {
		this.container = new Object[this.defaultCapacity];
		this.capacity = this.defaultCapacity;
		this.size = -1;
	}

	@Override
	public boolean contains(Object arg0) {
		for (int i = 0; i <= this.size; i++) {
			if (this.container[i].equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		 Iterator<?> e = arg0.iterator();
		 while (e.hasNext()) {
			 if (!contains(e.next())) {
				 return false;
			 }
		 }
		 return true;
	}

	@Override
	public boolean isEmpty() {		
		return size == -1;
	}

	@Override
	public Iterator<T> iterator() {
		return new MyIterator();
	}

	@Override
	public boolean remove(Object arg0) {
		for (int i = 0; i <= this.size; i++) {
			if (this.container[i].equals(arg0)) {
				for (int j = i + 1; j <= this.size; j++) {
					this.container[j - 1] = this.container[j];
				}
				this.container[this.size--] = null;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return batchRemove(arg0, false);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return batchRemove(arg0, true);
	}
	
	private boolean batchRemove(Collection<?> arg0, boolean retain) {
		int oldSize = size;
		
		for (int i = 0; i <= size; ) {
			if (arg0.contains(container[i]) ^ retain) {
				remove(container[i]);
			} else {
				i++;
			}
		}
		
		return oldSize != size;
	}

	@Override
	public int size() {
		return this.size + 1;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(container, size + 1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] arg0) {
		if (arg0.length < size + 1) {
			return (T[]) Arrays.copyOf(container, size + 1, arg0.getClass());
		}
		System.arraycopy(container, 0, arg0, 0, size + 1);
		if (arg0.length > size + 1) {
			Arrays.fill(arg0, size + 1, arg0.length - 1, null);
		}
		return arg0;
	}

	public class MyIterator implements Iterator<T> {

		private int index = 0;
		
		@Override
		public boolean hasNext() {
			return index != size + 1;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (hasNext()) {
				return (T)MyCollection.this.container[index++];
			}
			return null;
		}
		
	}
}
