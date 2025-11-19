package Module2.Task1;

public class MyFileOperationException extends Exception{
    public MyFileOperationException(String message) {
        super(message);
    }

    public MyFileOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
