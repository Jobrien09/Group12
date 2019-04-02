import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Writer {
	private static final String FILE_NAME1 = "results_EE2015.xlsx";
	private static XSSFWorkbook workbook1 = RawWriter.getWorkbook();
	
	public static void writeOutput(){
		try {
	       	FileOutputStream outputStream = new FileOutputStream(FILE_NAME1);
	       	workbook1.write(outputStream);
	       	outputStream.close();
	       	workbook1.close();
	    } catch (FileNotFoundException e) {
	       	e.printStackTrace();
	    } catch (IOException e) {
	       	e.printStackTrace();
	    }	
	}
}
