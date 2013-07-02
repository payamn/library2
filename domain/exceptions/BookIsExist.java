package domain.exceptions;

public class BookIsExist extends Exception{
	private static final long serialVersionUID = 737834010710016204L;
	public BookIsExist(String message) {
		super(message);
	}	
}
