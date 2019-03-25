import java.util.Iterator;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class TestReader {
	public static final String SAMPLE = "results_EE2014.xlsx";
	public static void main(String[] args) throws IOException {
		try {
			FileInputStream excelFile = new FileInputStream(new File(SAMPLE));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet sheet = workbook.getSheetAt(0);
			DataFormatter dataFormatter = new DataFormatter();
			
			Iterator<Row> it = sheet.iterator();
			Row row = it.next();
			for (int rowI = ; rowI < row.getLastCellNum(); rowI++) {
				for (Row row2 : sheet) {
					Cell cell = row2.getCell(rowI);
					String cellValue = dataFormatter.formatCellValue(cell);
					System.out.println(cellValue);				
				}
				System.out.println();
			}
//			for (Row row: sheet) {
//				for(Cell cell : row) {
//					String cellValue = dataFormatter.formatCellValue(cell);
//					System.out.println(cellValue);
//				}
//				System.out.println();
//			}
			workbook.close();
			
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
