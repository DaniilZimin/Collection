package com.company;

import com.company.exception.AddNullException;
import com.company.exception.OutOfRangeException;
import com.company.exception.RemovingNonExistingElementException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class MyListImpl<T> implements MyList<T> {

    private final int DEFAULT_CAPACITY = 10;

    public T[] strings;
    int size = 0;

    public MyListImpl() {
        this.strings = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public MyListImpl(int capacity) {
        this.strings = (T[]) new Object[capacity];
    }

    @Override
    public T add(T item) {
        checkItem(item);
        increaseArray();
        strings[size++] = item;
        return item;
    }

    @Override
    public T add(int index, T item) {
        checkItem(item);
        increaseArray();
        if (index < 0 || index > size) {
            throw new OutOfRangeException("Выход за пределы фактического количества элементов");
        }

        if (strings[index] != null) {
            System.arraycopy(strings, index, strings, index + 1, size - index);
        }
        strings[index] = item;
        size++;
        return item;
    }

    @Override
    public T set(int index, T item) {
        checkItem(item);
        checkIndex(index);
        strings[index] = item;
        return item;
    }

    @Override
    public T remove(T item) {
        for (int i = 0; i < size; i++) {
            if (strings[i].equals(item)) {
                return remove(i);
            }
        }
        throw new RemovingNonExistingElementException("Удаление не существующего элемента");
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T s = strings[index];
        if (size - 1 - index >= 0) {
            System.arraycopy(strings, index + 1, strings, index, size - 1 - index);
        }
        size--;
        return s;
    }

    @Override
    public boolean contains(T item) {
        for (int i = 0; i < size; i++) {
            if (strings[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (strings[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T item) {
        for (int i = size - 1; i >= 0; i--) {
            if (strings[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return strings[index];
    }

    @Override
    public boolean equals(MyList otherList) {
        if (this == otherList) {
            return true;
        }

        if (otherList == null) {
            return false;
        }
        if (this.size != otherList.size())
            return false;

        for (int i = 0; i < size; i++) {
            if (!Objects.equals(get(i), otherList.get(i)))
                return false;
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            strings[i] = null;
        }
    }

    @Override
    public T[] toArray() {
        return Arrays.copyOf(strings, size);
    }

    private void checkItem(T item) {
        if (item == null) {
            throw new AddNullException("Добавление пустого элемента");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new OutOfRangeException("Выход за пределы фактического количества элементов");
        }
    }

    private void increaseArray() {
        if (size >= strings.length) {
            strings = Arrays.copyOf(strings, strings.length * 2);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new StringIterator();
    }

    private class StringIterator implements Iterator<T>{
        private int currentIndex;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            return strings[currentIndex++];
        }
    }
}
