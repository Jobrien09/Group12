import java.util.List;
import java.io.FileOutputStream;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AreaWriter {
	private static XSSFWorkbook workbook1 = RawWriter.getWorkbook();
	
	public static void writeAreaDist() {
		XSSFSheet areaSheet = workbook1.createSheet("Area");
		
		// Creating font and style for top row 
        CellStyle style = workbook1.createCellStyle();
        XSSFFont font = workbook1.createFont();
        font.setBold(true);
        style.setFont(font);

		String[] topRowTypes = {"Area", "Other", "Fails", "Marginal", "Meets", "Exceeds", "", "Average"};
        Row topRow = areaSheet.createRow(0);
        for (int i = 0; i < topRowTypes.length; i++) {
        	Cell cell = topRow.createCell(i);
        	cell.setCellValue(topRowTypes[i]);
        	cell.setCellStyle(style);
        }
        
		String[] firstColumnTypes = {"MATH", "SCIENCE", "FUNDAMENTALS", "SPECIALIZED", "TERMINAL", "CORE", "CSE", "SOCIETY"};
		int columnNum = 1;
        for (int i = 0; i < firstColumnTypes.length; i++) {
            Row column = areaSheet.createRow(columnNum);
        	Cell cell = column.createCell(0);
        	cell.setCellValue(firstColumnTypes[i]);
        	cell.setCellStyle(style);
        	columnNum++;
        }
        
        // Formatting column width
        areaSheet.autoSizeColumn(0);
  
        System.out.println("Done3");

	}
}