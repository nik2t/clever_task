package ru.clevertec;

public class CustomArrayIterator<T> implements Iterator<T> {

        private int index = 0;
        private T[] array;

        public CustomArrayIterator(T[] array) {
            this.array = array;
        }

        @Override
        public T next() {
            return (T) array[index++];
        }

        @Override
        public boolean hasNext() {
            if (index < array.length && array[index] != null) {
                return true;
            }
            return false;
        }

        @Override
        public void remove() {
            for (int i = index; i < array.length-1; i++)
                array[i] = array[i + 1];
            array[array.length-1] = null;
        }

        @Override
        public void addBefore(T element) {
            for (int i = array.length - 2; i >= index; i--) {
                if (i == index) {
                    array[i + 1] = array[i];
                    array[i] = element;
                } else {
                    array[i + 1] = array[i];
                }
            }

        }

        @Override
        public void addAfter(T element) {
            if (array.length - 1 == index) {
                array[index + 1] = element;

            } else {
                for (int i = array.length - 2; i > index; i--) {
                    if (i == index + 1) {
                        array[i + 1] = array[i];
                        array[i] = element;
                    } else {
                        array[i + 1] = array[i];
                    }
                }
            }

        }
}
