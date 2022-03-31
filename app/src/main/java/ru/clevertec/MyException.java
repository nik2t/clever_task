package ru.clevertec;

public class MyException extends Exception {

    public MyException( String message) {
        super(message);

    }
    @Override
    public String toString() {
        return "MyException{"
                + " message = " + getMessage()
                + " } ";
    }
}
