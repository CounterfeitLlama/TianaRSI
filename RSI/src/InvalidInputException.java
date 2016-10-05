public class InvalidInputException extends Throwable {
	  public InvalidInputException() {
		  this("Invalid input, probably a string when it should be an int");
	  }
	  
	  public InvalidInputException(String message) {
		  super(message);
	  }
	  
	  public InvalidInputException(String message, Throwable cause) {
		  super(message, cause);
	  }
	  
	  public InvalidInputException(Throwable cause) {
		  super(cause);
	  }
}