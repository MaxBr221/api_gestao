package com.MaxBr221.GitHub.exception;

public class EventFullException extends RuntimeException{
    public EventFullException(){
        super("evento já está cheio");

    }
    public EventFullException(String messagem){
        super(messagem);
    }

}
