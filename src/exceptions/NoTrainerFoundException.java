package exceptions;

public class NoTrainerFoundException {

	public String NoTrainerFoundException(String a) {
		if (a.length() == 0) {
			return "No Trainer found";
		}
		return null;
	}

}
