package ecole.Exception;

public class InputInvalidException extends Exception {

	private static final long serialVersionUID = -4408851590932542472L;
	public InputInvalidException(){
		super();
	}
	
	public InputInvalidException(String msg){
		super(msg);
	}
	
	public InputInvalidException(String msg, Throwable arg0){
		super(msg, arg0);
	}
	
	public InputInvalidException(Throwable arg0){
		super(arg0);
	}
}
