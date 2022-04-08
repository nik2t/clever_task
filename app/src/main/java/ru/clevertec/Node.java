package ru.clevertec;

import lombok.Getter;
import lombok.Setter;

public  class Node<T>{
    @Setter
    @Getter
    T item;
    @Setter
    @Getter
    private Node<T> next;
    @Setter
    @Getter
    private Node<T> prev;

    public Node(T item, Node<T> prev, Node<T> next){
        this.item = item;
        this.prev = prev;
        this.next = next;
    }
}
