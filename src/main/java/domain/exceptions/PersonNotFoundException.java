package domain.exceptions;

public class PersonNotFoundException extends Exception{

	private static final long serialVersionUID = 4425494498316899717L;
	public PersonNotFoundException(String message) {
		super(message);
	}
}
