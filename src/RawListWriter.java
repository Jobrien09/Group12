package test;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RawListWriter extends XLSXWriter<String[]>{
	
	public RawListWriter(String fileName, XSSFWorkbook workbook) {
		super(fileName, workbook);
	}
	
	@Override
	public void writeWorkbook(List<String[]> rawList) {
		XSSFWorkbook workbook = super.getWorkbook();
		XSSFSheet rawSheet = workbook.createSheet("RawList");
		
		String[] topRowTypes = {"Course", "Title"};
		Row topRow = rawSheet.createRow(0);
		for (int i = 0; i < topRowTypes.length; i++) {
			Cell cell = topRow.createCell(i);
			cell.setCellValue(topRowTypes[i]);
		}
		
		int rowNum = 1;
		for (String[] course : rawList) {
			Row row = rawSheet.createRow(rowNum++);
			Cell cell0 = row.createCell(0);
			cell0.setCellValue((String) course[0]);
			
			Cell cell1 = row.createCell(1);
			cell1.setCellValue((String) course[1]);
		}
		
        for(int i=0;i<2;i++) {
        	rawSheet.autoSizeColumn(i);
        }
		System.out.println("Done1");
	}
}
