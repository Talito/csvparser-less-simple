
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;


public class PhoneValidator extends Validator {

	/* No need of 'mandatory' field since we implicitly handle it 
	 * with the try-catch clausule in 'validate' method */
	
	private PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

	@Override
	public boolean validate(String number) {
		PhoneNumber phoneNumber;
		try {
			phoneNumber = phoneNumberUtil.parse(number, "PL");
			return phoneNumberUtil.isValidNumber(phoneNumber);
		} catch (NumberParseException e) {
			//e.printStackTrace();
			return (number.isEmpty()? true : false);
		}
	}

}
