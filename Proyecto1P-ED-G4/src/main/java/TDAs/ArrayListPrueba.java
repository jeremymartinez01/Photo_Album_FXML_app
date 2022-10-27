/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

import Classes.Photo;
import java.util.Iterator;
import TDAs.ListPrueba;
/**
 *
 * @author Javier
 * @param <E>
 */
public class ArrayListPrueba<E> implements Iterable<E>, ListPrueba<E>{
    private E[] array;
    private int capacity;
    private int current;
    
    public ArrayListPrueba(){
        capacity = 10;
        array = (E[]) new Object[capacity];
        current = 0;
    }

    public ArrayListPrueba(E e) {
        capacity = 10;
        array=(E[]) new Object[capacity];
        current=0;
        this.addFirst(e);
    }
    
    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator(){
            int i = 0; 
            @Override
            public boolean hasNext() {
                return i<current && current >=0;
            }

            @Override
            public Object next() {
                return array[i++];
            }
            
        };
        return it;
    }


    @Override
    public boolean addFirst(E e) {
        if(e == null) return false;
        if(current == capacity) addCapacity();
        for(int i = current-1;i>=0;i--)
            array[i+1] = array[i];
        array[0] = e;
        current++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        if(e == null) return false;
        if(current == capacity) addCapacity();
        array[current++] = e;
        return true;
    }
    private void addCapacity()
    {
        E[] tmp = (E[])new Object[(capacity*3)/2];
        for(int i=0;i<current;i++)
            tmp[i] = array[i];//deep copy
        array = tmp;//shallow copy
        capacity = (capacity * 3)/2;
        System.out.println(capacity);
    }

    @Override
    public boolean removeLast() {
        if(isEmpty()) return false;
        array[current-1] = null;
        current --;
        if((capacity*2/3)>10 && current < (capacity*2)/3) reduceCapacity();
        return true;
    }

    @Override
    public boolean removeFirst() {
        if (isEmpty()) return false;
        array[0] = null;
        for(int i=0; i<current-1;i++){
            array[i] = array[i+1];
        }
        removeLast();
        return true;
    }
    
    private void reduceCapacity(){
        E[] tmp = (E[]) new Object[(capacity*2)/3];
        for (int i =0; i<current;i++) tmp[i]= array[i]; //deep copy
        array = tmp; //shallow copy
        capacity = capacity * 2/3;
        System.out.println(capacity);
    }

    @Override
    public boolean insert(int index, E e) {
        if(isEmpty() || e==null) return false;
        if(index>current-1 || index<0) throw new IndexOutOfBoundsException("Indice fuera del limite.");
        if(current == capacity) addCapacity();
        for(int i = index; i<current;i++ ){
            array[i+1] = array[i];
        }
        array[index]=e;
        current++;
        return true;
    }

    @Override
    public boolean set(int index, E e) {
        if(e==null || index >current-1) return false; 
        array[index] = e;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return current == 0;
    }

    @Override
    public E get(int index) {
        if (index > current -1) throw new IndexOutOfBoundsException("El indice pedido supera la capacidad del arreglo");
        return array[index];
    }

    @Override
    public boolean contains(E e) {
        for(int i=0; i<current; i++)
        {
            if(array[i].equals(e))
                return true;
        }
        return false;
    }

    public boolean remove(int index) {
        if (index > current -1) throw new IndexOutOfBoundsException("El indice pedido supera la capacidad del arreglo");
        if(index==0) return removeFirst();
        if(index == current -1) return removeLast();
        if(isEmpty()) return false;
        else{
            for (int i = index; i<=current-2;i++){
                array[i]= array[i+1];
            }
            return removeLast();
        }
    }

    public E getFirst(){
        if(isEmpty()) throw new IllegalStateException("No es posible ejecutar porque la lista esta vacia");
        return array[0];
    }

    public E getLast() {
        if(isEmpty()) throw new IllegalStateException("No es posible ejecutar porque la lista esta vacia");
        return array[current-1];
    }

    public int indexOf(E e) {
        if(e==null) new NullPointerException();
        if(isEmpty()) return -1;
        else{
            for(int i = 0;i<current-1; i++){
                if(array[i].equals(e)) return i;
            }
            return -1;
        }
    }

    @Override
    public int size() {
        return current;
    }
    
    @Override
    public String toString()
    {
        if(isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0;i<current-1;i++)//hasta el penultimo elemento lleva coma
        {
            sb.append(array[i]);
            sb.append(",");
        }    
        sb.append(array[current-1]);//agrego el ultimo elemento sin coma
        sb.append("]");
        return sb.toString();
    }


}
