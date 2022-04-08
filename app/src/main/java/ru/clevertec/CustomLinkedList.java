package ru.clevertec;


import lombok.Getter;
import lombok.Setter;

public class CustomLinkedList<T> implements List<T>{
    @Getter
    @Setter
    private Node<T> fstNode;
    @Getter
    @Setter
    private Node<T> lstNode;
    @Getter
    @Setter
    private int size = 0;
    @Getter
    @Setter
    private int max_size = -1;

    public CustomLinkedList(){
        fstNode = new Node<T>(null, null, lstNode);
        lstNode = new Node<T>( null, fstNode,null);

    }

    public CustomLinkedList(Node<T> fstNode, Node<T> lstNode, int size, int max_size){
        this.max_size=max_size;
        this.size=size;
        this.lstNode=lstNode;
        this.fstNode=fstNode;

    }


    @Override
    public CustomLinkedIterator<T> iterator() {
        return new CustomLinkedIterator<>();
    }

    @Override
    public void setMaxSize(int max_size) {
        this.max_size = max_size;
        if(size > max_size){
            size = max_size;
            Node<T> pointer;
            int counter = -1;
            for(pointer = fstNode; pointer != null; pointer = pointer.getNext()){
                if(counter == size-1){
                    pointer.setNext(lstNode);
                    lstNode.setPrev(pointer);
                }
                counter++;
            }
        }
    }

    @Override
    public void add(T element) {
        if(size < max_size || max_size == -1) {
            Node<T> counter = new Node<T>(element, lstNode.getPrev(), lstNode);
            lstNode.getPrev().setNext(counter);
            lstNode.setPrev(counter);
            size++;
        }
        else{
            System.out.println("List overflow!");
        }
    }
    public void printLinkList(){
        Node<T> p;
        for(p = fstNode; p!=null; p=p.getNext()){
            System.out.print(p.item+"--->");
        }
        System.out.println();
    }

    @Override
    public void addAll(T[] array) {
        for( T element : array){
            add(element);
        }
    }

    @Override
    public T set(int index, T element) {
        T item = get(index);
        Node<T> pointer;
        int counter = -1;
        for(pointer = fstNode; pointer != null; pointer = pointer.getNext()){
            if(counter == index && index < size){
                pointer.setItem(element);
            }
            counter++;
        }
        return item;
    }

    @Override
    public T remove(int index) {
        Node<T> pointer;
        int counter = -1;
        for(pointer = fstNode; pointer!=null; pointer=pointer.getNext()){
            if(counter == index && index < size){
                pointer.getPrev().setNext(pointer.getNext());
                pointer.getNext().setPrev(pointer.getPrev());
                size--;
                return pointer.getItem();
            }
            counter++;
        }

        return null;
    }

    @Override
    public void clear() {
        fstNode = new Node<T>(null, null, lstNode);
        lstNode = new Node<T>( null, fstNode,null);
    }

    @Override
    public int find(T element) {
        Node<T> pointer;
        int index = -1;
        for(pointer = fstNode; pointer!=null; pointer=pointer.getNext()){
            if(pointer.getItem() == element){
                return index;
            }
            index++;
        }
        System.out.println("Element not found!(find)");
        return -1;
    }

    @Override
    public T get(int index) {
        Node<T> pointer;
        int counter = -1;
        for(pointer = fstNode; pointer!=null; pointer=pointer.getNext()){

            if(counter == index && index < size){
                return pointer.getItem();
            }
            counter++;
        }
        System.out.println("Element not found!(get)");
        return null;
    }

    @Override
    public T[] toArray() {
        T[] arr = (T[]) new Object[size];
        int counter = -1;
        Node<T> pointer;
        for(pointer = fstNode; pointer!=null; pointer=pointer.getNext()) {
            if(counter != -1 && counter != size) {
                arr[counter] = pointer.getItem();
            }
            counter++;
        }
        return arr;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void trim() {
        Node<T> pointer;
        int counter = -1;
        for(pointer = fstNode; pointer!=null; pointer=pointer.getNext()){

            if(counter != -1 && counter != size && pointer.getItem() == null){
                remove(counter);
                counter--;
            }
            counter++;
        }
    }


    public class CustomLinkedIterator<T> implements Iterator<T> {
        private int index = 0;
        private Node pointer;

        public CustomLinkedIterator() {
            pointer = getFstNode();
            index = 0;
        }

        @Override
        public T next() {
            pointer = pointer.getNext();
            index++;
            return (T) pointer.getItem();
        }

        @Override
        public boolean hasNext() {
            if (index < getSize() && pointer.getNext() != null) {
                return true;
            }
            return false;
        }

        @Override
        public void remove() {
            if(pointer != fstNode) {
                pointer.getPrev().setNext(pointer.getNext());
                pointer.getNext().setPrev(pointer.getPrev());
                setSize(getSize() - 1);
            }
            else{
                System.out.println("Pointer on the head.");
            }
        }

        @Override
        public void addBefore(T element) {
            if(pointer != fstNode) {
                Node<T> counter = new Node<>(element, pointer.getPrev(), pointer);
                pointer.getPrev().setNext(counter);
                pointer.setPrev(counter);
                setSize(getSize() + 1);
            }
            else{
                System.out.println("Pointer on the head.");
            }
        }

        @Override
        public void addAfter(T element) {
            if(pointer != fstNode) {
                Node<T> counter = new Node<>(element, pointer, pointer.getNext());
                pointer.getNext().setPrev(counter);
                pointer.setNext(counter);
                setSize(getSize() + 1);
            }
            else{
                System.out.println("Pointer on the head.");
            }
        }
    }
}
