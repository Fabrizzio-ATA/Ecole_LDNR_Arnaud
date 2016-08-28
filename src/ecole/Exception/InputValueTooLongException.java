package ecole.Exception;

public class InputValueTooLongException extends Exception {
	private static final long serialVersionUID = 3393467872972158983L;

	public InputValueTooLongException() {
		super();
	}

	public InputValueTooLongException(String msg, Throwable arg1) {
		super(msg, arg1);
	}

	public InputValueTooLongException(String msg) {
		super(msg);
	}

	public InputValueTooLongException(Throwable arg0) {
		super(arg0);
	}
	
	public InputValueTooLongException(String field, int maxLength, int length) {
		super("L'attribut " + field + " est trop long (" + length + " char) (MAX=" + maxLength + " char)");
	}
	
}
