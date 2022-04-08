package ru.clevertec;



public interface List <T> extends Iterable<T>{
    Iterator<T> iterator();
    void setMaxSize(int maxSize);
    void add( T element);
    void addAll(T[] array);
    T set(int index, T element);
    T remove(int index);
    void clear();
    int find(T element);
    T get(int index);
    T[] toArray();
    int size();
    void trim();

}
