
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailValidator extends Validator {

	private boolean mandatory = true;
	private final String regex = 
			"^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	private Pattern pattern = Pattern.compile(regex);
	
	public EmailValidator(boolean b) {
		this.mandatory = b;
	}

	public EmailValidator() { }

	@Override
	public boolean validate(String email) {
		Matcher matcher = pattern.matcher(email);
		return (mandatory? (!email.isEmpty() && matcher.matches()) : (email.isEmpty() || matcher.matches()));
	}

}
