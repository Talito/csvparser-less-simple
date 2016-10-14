
import org.apache.commons.lang.math.NumberUtils;


public class IdNumberValidator extends Validator {

	private boolean mandatory = true;
	
	public IdNumberValidator() { }
	
	public IdNumberValidator(boolean b) {
		this.mandatory = b;
	}

	@Override
	public boolean validate(String idNumber) {
		boolean validity = (NumberUtils.isNumber(idNumber) && idNumber.length() == 7);
		return (mandatory? validity : (idNumber.isEmpty() || validity));
	}

}
