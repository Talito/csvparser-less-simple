

public class ValidatorFactory {

	public static Validator getValidator(FIELD type) {
		switch(type) {
		case PHONE_NUMBER:
			return new PhoneValidator();
		case EMAIL_ADDRESS:
			return new EmailValidator();
		case ID_NUMBER:
			return new IdNumberValidator();
		case PHONE_NUMBER_OPTIONAL:
			return new PhoneValidator();
		case EMAIL_ADDRESS_OPTIONAL:
			return new EmailValidator(false);
		case ID_NUMBER_OPTIONAL:
			return new IdNumberValidator(false);
		}
		return null;
	}
}
