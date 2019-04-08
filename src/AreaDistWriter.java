package test;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AreaDistWriter extends XLSXWriter<Transcript>{
	private List<Integer> schema;
	
	public AreaDistWriter(String fileName, XSSFWorkbook workbook, List<Integer> schema) {
		super(fileName, workbook);
		this.schema = schema;
	}
	
	@Override
	public void writeWorkbook(List<Transcript> transcripts) {
		XSSFWorkbook workbook = super.getWorkbook();
		XSSFSheet areaSheet = workbook.createSheet("Area");

		// Creating font and style for top row 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

		String[] topRowTypes = {"Area", "", "Fails", "Marginal", "Meets", "Exceeds"};
        Row topRow = areaSheet.createRow(0);
        for (int i = 0; i < topRowTypes.length; i++) {
        	Cell cell = topRow.createCell(i);
        	cell.setCellValue(topRowTypes[i]);
        	cell.setCellStyle(style);
        }
        
		String[] firstColumnTypes = {"MATH", "SCIENCE", "FUNDAMENTALS", "SPECIALIZED", "TERMINAL", "CORE", "CSE", "SOCIETY"};
		int columnNum = 1;
		
        for (int i = 0; i < firstColumnTypes.length; i++) {
            Row column = areaSheet.createRow(columnNum++);
        	Cell cell = column.createCell(0);
        	cell.setCellValue(firstColumnTypes[i]);
        	cell.setCellStyle(style);
        }   
        
        // Formatting column width
        areaSheet.autoSizeColumn(0);
        System.out.println("Done3");
	}
	
	public void calculateArea() {
		
	}
}
