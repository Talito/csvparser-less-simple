import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class CSVReader {
	
	private Validator validator;
	private Map<Integer, FIELD> headersMap = new HashMap<Integer, FIELD>();
	
	private static final Logger logger = Logger.getLogger(CSVReader.class.getName());
	
	public void parseAndValidate(File file) {
		CSVParser parser;
		try {
			parser = CSVParser.parse(
					file, Charset.defaultCharset(), CSVFormat.RFC4180);
	
			/* HEADER PARSING */
			Iterator<CSVRecord> parserIterator = parser.iterator();
			CSVRecord headers = parserIterator.next();
			int headerSize = headers.size();
			for (int i = 0; i < headerSize; i++) {
				if (!checkHeaderName(headers.get(i))) {
					logger.log(Level.SEVERE, 
							"Invalid header name " + headers.get(i) + " at column " + i + ".\nFinalizing program...");
					System.exit(-1);
				}
				addHeaderToMap(i, headers.get(i));
			}
			
			/* ROWS PARSING */
			while (parserIterator.hasNext()) {
				CSVRecord row = parserIterator.next();
				int rowSize = row.size();
				long col = parser.getCurrentLineNumber();
				if (rowSize > headerSize) { 
					System.err.println("Row size for " + parser.getCurrentLineNumber() + " is wrong. Must be equal to " + headerSize + "."); 
				}
				for (int i = 0; i < headerSize; i++) {
					FIELD field = headersMap.get(Integer.valueOf(i));
					validator = ValidatorFactory.getValidator(field);
					String cell = row.get(i);
					if (!validator.validate(cell)) {
						printInvalidCellError(i+1, col, field);
					}
				}
				if (rowSize > headerSize) { 
					System.err.println((rowSize - headerSize) + " more unexpected columns were not analyzed at row " + col + ".");
				}
			}
		} catch (IOException e) {
			System.err.println("Could not open the CSV file.");
			logger.log(Level.SEVERE, e.toString());
		}
	}
	
	private void printInvalidCellError(int col, long row, FIELD field) {
		System.err.println("Invalid value at column " + col + ", row " + row + ". Invalid " + field + ".");
	}

	private boolean checkHeaderName(String headerName) {
		try {
			return (FIELD.valueOf(headerName) != null);
		} catch (IllegalArgumentException e) {
			logger.log(Level.FINE, e.toString());
			return false;
		}
	}

	private void addHeaderToMap(int key, String header) {
		switch(header) {
		case "PHONE_NUMBER":
			headersMap.put(key, FIELD.PHONE_NUMBER);
			break;
		case "PHONE_NUMBER_OPTIONAL":
			headersMap.put(key, FIELD.PHONE_NUMBER_OPTIONAL);
			break;
		case "EMAIL_ADDRESS":
			headersMap.put(key, FIELD.EMAIL_ADDRESS);
			break;
		case "EMAIL_ADDRESS_OPTIONAL":
			headersMap.put(key, FIELD.EMAIL_ADDRESS_OPTIONAL);
			break;
		case "ID_NUMBER":
			headersMap.put(key, FIELD.ID_NUMBER);
			break;
		case "ID_NUMBER_OPTIONAL":
			headersMap.put(key, FIELD.ID_NUMBER_OPTIONAL);
		}
	}
	
}
