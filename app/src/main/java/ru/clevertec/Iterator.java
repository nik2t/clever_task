package ru.clevertec;

public interface Iterator <T>{

    T next();
    boolean hasNext();
    void remove();
    void addBefore(T element);
    void addAfter(T element);
}