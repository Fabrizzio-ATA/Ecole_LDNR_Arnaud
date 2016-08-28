package ecole.Exception;

public class DAOException extends Exception{

	private static final long serialVersionUID = -2320400982170495589L;

	public DAOException() {
		super();
	}

	public DAOException(String msg, Throwable arg) {
		super(msg, arg);
	}

	public DAOException(String msg) {
		super(msg);
	}

	public DAOException(Throwable arg) {
		super(arg);
	}

}
