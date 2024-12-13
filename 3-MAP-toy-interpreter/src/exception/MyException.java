package exception;

public class MyException extends Exception {
	private static final long serialVersionUID = 1L;

	public MyException(String myMessage) {
		super(myMessage);
	}
}
