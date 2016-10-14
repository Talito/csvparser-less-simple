import java.io.File;
import java.io.IOException;


public class Main {
	
	/* 
	 * Try 'example2.csv' for checking invalid header name,
	 * 'example3.csv' for exceed of columns in one row,
	 * 'example4.csv' to test the given in the e-mail
	 */
	
	private static final String DEFAULT_CSV_FILE = "example3.csv";
	
	public static void main(String[] args) throws IOException {
		System.out.println("Starting...");
		CSVReader reader = new CSVReader();
		reader.parseAndValidate(new File(DEFAULT_CSV_FILE));
		System.out.println("Done.");
	}

}
