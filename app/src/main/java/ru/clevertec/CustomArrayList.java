package ru.clevertec;

import java.util.Vector;

public class CustomArrayList<T> implements List<T>{
    private final int INIT_SIZE = 16;
    private final int CUT_RATE = 4;
    private T[] array = (T[]) new Object[INIT_SIZE];
    private int size = 0;
    private int max_size = -1;

    public CustomArrayList(T[] array){
        addAll(array);
    }
    public CustomArrayList(){
        try {
            array = (T[]) new Object();
        }
        catch (ClassCastException e){
            e.printStackTrace();
        }
    }


    @Override
    public CustomArrayIterator<T> iterator() {
        return new CustomArrayIterator<>(array);
    }

    @Override
    public void setMaxSize(int max_size) {
        this.max_size = max_size;
        if(size >= max_size){
            for(int i = 0; i < size; i++){
                if(i > max_size-1){
                    array[i] = null;
                }
            }
        }
        else{
            resize(max_size);
        }
    }

    private void resize(int newLength) {
        try {
            T[] newArray;
            if(max_size != -1 && newLength > max_size) {
                newArray = (T[]) new Object[max_size];
            }
            else{
                newArray = (T[]) new Object[newLength];
            }
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
            size();
        }
        catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    @Override
    public T remove(int index) {
        T counter = get(index);
        for (int i = index; i < size-1; i++)
            array[i] = array[i+1];
        array[size-1] = null;
        size--;
        if (array.length > INIT_SIZE && size < array.length / CUT_RATE) {
            resize(array.length / 2);
        }
        size();
        return counter;
    }

    @Override
    public void add(T element) {
        if (size == array.length - 1 || size == array.length) {
            resize(array.length * 2);
        }
        array[size] = element;
        size();
    }

    @Override
    public void addAll(T[] array) {
        for( T element : array){
            add(element);
        }
    }

    @Override
    public T set(int index, T element) {
        array[index] = element;
        return array[index];
    }

    @Override
    public T get(int index) {
        return (T) array[index];
    }


    @Override
    public void clear() {
        int count = size;
        for(int i = 0; i < count; i++){
            remove(0);
        }
    }

    @Override
    public int find(T element) {
        int counter = 0;
        for( Object elem : array){

            if(elem == element){
                return counter;
            }
            counter++;
        }
        return -1;
    }



    @Override
    public T[] toArray() {
        return array;
    }

    @Override
    public int size() {
        int counter = 1;
        for(int i = 0; i < array.length; i++){
            if(array[i] != null){
                size = counter;
            }
            counter++;
        }

        return size;
    }

    @Override
    public void trim() {
        int counter = size;
        for(int i = 0; i < counter; i++){
            if(array[i] == null){
                remove(i);
            }

        }
        resize(size);
    }

}
