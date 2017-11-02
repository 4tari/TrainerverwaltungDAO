package exceptions;

public class NoTrainerFoundException extends Exception {
	public NoTrainerFoundException() {
		super("NoTrainerFoundException");
	}
//	public String NoTrainerFoundException(String a) {
//		if (a.length() == 0) {
//			return "No Trainer found";
//		}
//		return null;
//	}
}
