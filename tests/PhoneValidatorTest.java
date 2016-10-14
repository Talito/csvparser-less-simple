import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PhoneValidatorTest {
	
	Validator validator = new IdNumberValidator();
	
	@Test
	public void validRowSizeShouldReturnTrue() {
		String idNumber = "3533311";
		assertTrue("Row size: " + idNumber + " is not valid", validator.validate(idNumber));
	}
	
}
