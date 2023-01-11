package at.fhtw.swen3.util.exceptions;

public abstract class BLExceptionBase extends RuntimeException{
    public BLExceptionBase(String msg) {
        super(msg);
    }
}
