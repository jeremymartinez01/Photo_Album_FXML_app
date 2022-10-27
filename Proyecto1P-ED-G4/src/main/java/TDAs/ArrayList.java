package TDAs;

import java.util.Iterator;

public class ArrayList<E> implements List<E>,Iterable<E>{
    
    private int capacity = 100;
    private E[] elements = null;
    private int effectiveSize = 0;
    
    public ArrayList () {
        this.elements = (E[]) new Object[capacity];
        this.effectiveSize = 0;
    }

    @Override
    public boolean addFirst(E e) {
        if (e == null) {
            return false;
        } else if (isEmpty()) {
            elements[effectiveSize++] = e;
            return true;
        } else if (isFull()) {
            addCapacity();
        }
        // desplazamiento o bit shifting 
        for (int i = effectiveSize - 1; i >=0; i--) {
            elements[i+1] = elements[i]; 
        }
        elements[0] = e;
        effectiveSize++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E removeLast() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        return effectiveSize;
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    @Override
    public void clear() {
        effectiveSize = 0;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean isFull() {
        return effectiveSize == capacity;
    }

    private void addCapacity() {
        E[] nuevo =  (E[]) new Object [capacity * 2];
        for (int i=0; i<elements.length; i++) {
            nuevo[i] = elements[i];
        }
        elements = nuevo;
        capacity *= 2;
    }
    
    @Override
    public Iterator<E> iterator() {
        
        Iterator<E> it = new Iterator<E>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor < effectiveSize;
            }

            @Override
            public E next() {
                E e = elements[cursor];
                cursor++;
                return e;
            }
        };
        return it;
    }
    
}
