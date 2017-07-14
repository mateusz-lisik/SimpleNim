package it.lisik.experiments.SimpleNim;

public class IllegalAmountException extends RuntimeException {

    public IllegalAmountException() {
        super("Provided amount of tokens is illegal");
    }
}
