package ua.homework5;

import java.util.Iterator;

public class DoubleLinkedList<T> implements Iterable<T> {
    private DoubleNode<T> headList;
    private DoubleNode<T> tailList;
    private int size;

    public boolean add(T element) {
        if (headList == null) {
            headList = new DoubleNode<>();
            headList.element = element;
            tailList = headList;
        } else {
            tailList.nextNode = new DoubleNode<>();
            tailList.nextNode.previousNode = tailList;
            tailList = tailList.nextNode;
            tailList.element = element;
        }
        size++;
        return true;
    }

    public void add(int index, T element) {
        if (index < 0 || index >= size) {
            return;
        }

        DoubleNode<T> cursor;
        DoubleNode<T> previousCursor;
        DoubleNode<T> newNode;
        if (index < size / 2) {
            cursor = headList;
            for (int i = 0; i != index; i++) {
                cursor = cursor.nextNode;
            }
        } else {
            cursor = tailList;
            for (int i = size - 1; i != index; i--) {
                cursor = cursor.previousNode;
            }
        }
        if (cursor == headList) {
            cursor.previousNode = new DoubleNode<>();
            newNode = cursor.previousNode;
            newNode.element = element;
            newNode.nextNode = cursor;
            headList = newNode;
        } else {
            previousCursor = cursor.previousNode;
            cursor.previousNode = new DoubleNode<>();
            newNode = cursor.previousNode;
            newNode.element = element;
            newNode.nextNode = cursor;
            newNode.previousNode = previousCursor;
            previousCursor.nextNode = newNode;
        }
        size++;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        if (index == 0) {
            T element = headList.element;

            if (headList == tailList) {
                headList = null;
                tailList = null;
            } else {
                headList = headList.nextNode;
                headList.previousNode = null;
            }
            size--;
            return element;
        }

        if (index == size - 1) {
            T element = tailList.element;

            if (headList == tailList) {
                headList = null;
                tailList = null;
            } else {
                tailList = tailList.previousNode;
                tailList.nextNode = null;
            }
            size--;
            return element;
        }

        DoubleNode<T> cursor;
        if (index < size / 2) {
            cursor = headList;
            for (int i = 0; i != index; i++) {
                cursor = cursor.nextNode;
            }
        } else {
            cursor = tailList;
            for (int i = size - 1; i != index; i--) {
                cursor = cursor.previousNode;
            }
        }

        cursor.previousNode.nextNode = cursor.nextNode;
        cursor.nextNode.previousNode = cursor.previousNode;
        size--;

        return cursor.element;
    }

    public boolean remove(T element) {
        DoubleNode<T> cursor = headList;
        for (int i = 0; i < size; i++) {
            if (cursor.element.equals(element)) {
                remove(i);
                return true;
            }
            cursor = cursor.nextNode;
        }
        return false;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        DoubleNode<T> cursor;
        if (index < size / 2) {
            cursor = headList;
            for (int i = 0; i != index; i++) {
                cursor = cursor.nextNode;
            }
        } else {
            cursor = tailList;
            for (int i = size - 1; i != index; i--) {
                cursor = cursor.previousNode;
            }
        }
        return cursor.element;
    }

    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            return;
        }

        DoubleNode<T> cursor;
        if (index < size / 2) {
            cursor = headList;
            for (int i = 0; i != index; i++) {
                cursor = cursor.nextNode;
            }
        } else {
            cursor = tailList;
            for (int i = size - 1; i != index; i--) {
                cursor = cursor.previousNode;
            }
        }
        cursor.element = element;
    }

    public int getsize() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    public Iterator<T> decreasingIterator() {
        return new DecItr();
    }

    private class Itr implements Iterator<T> {
        DoubleNode<T> cursor = headList;
        int counter = 0;

        @Override
        public boolean hasNext() {
            return counter < size;
        }

        @Override
        public T next() {
            if (cursor != null) {
                T element = cursor.element;
                cursor = cursor.nextNode;
                counter++;
                return element;
            }
            return null;
        }
    }

    private class DecItr implements Iterator<T> {
        DoubleNode<T> cursor = tailList;
        int counter = size;

        @Override
        public boolean hasNext() {
            return counter > 0;
        }

        @Override
        public T next() {
            if (cursor != null) {
                T element = cursor.element;
                cursor = cursor.previousNode;
                counter--;
                return element;
            }
            return null;
        }
    }

    private class DoubleNode<T> {
        T element;
        DoubleNode<T> nextNode;
        DoubleNode<T> previousNode;

        public void setElement(T element) {
            this.element = element;
        }

        public void setNextNode(DoubleNode<T> nextNode) {
            this.nextNode = nextNode;
        }

        public void setPreviousNode(DoubleNode<T> previousNode) {
            this.previousNode = previousNode;
        }

        public T getElement() {
            return element;
        }

        public DoubleNode<T> getNextNode() {
            return nextNode;
        }

        public DoubleNode<T> getPreviousNode() {
            return previousNode;
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[ ");
        DoubleNode<T> cursor = headList;
        for (int i = 0; i < size; i++) {
            str.append(cursor.element);
            if (i != size - 1) {
                str.append(", ");
            } else {
                str.append(" ]");
            }
            cursor = cursor.nextNode;
        }
        return str.toString();
    }
}
