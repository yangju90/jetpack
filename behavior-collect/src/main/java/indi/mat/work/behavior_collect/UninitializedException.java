package indi.mat.work.behavior_collect;

public class UninitializedException extends RuntimeException {
    public UninitializedException() {
        super();
    }

    public UninitializedException(String message) {
        super(message);
    }

    public UninitializedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UninitializedException(Throwable cause) {
        super(cause);
    }
}
