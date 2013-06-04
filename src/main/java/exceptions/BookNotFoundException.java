package exceptions;

public class BookNotFoundException extends Exception{
	private static final long serialVersionUID = 1586054100590337948L;
	public BookNotFoundException(String message) {
		super(message);
	}	
}
