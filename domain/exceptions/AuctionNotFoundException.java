package domain.exceptions;

public class AuctionNotFoundException extends Exception{
	private static final long serialVersionUID = -7685524846396027127L;
	public AuctionNotFoundException(String message) {
		super(message);
	}
}
