package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class NotSpecifiedWriter extends XLSXWriter<Area>{
	
	public NotSpecifiedWriter(String fileName, XSSFWorkbook workbook) {
		super(fileName, workbook);
	}
	
	@Override
	public void writeWorkbook(List<Area> areas) {
		XSSFWorkbook workbook = super.getWorkbook();
		Sheet sheet = workbook.getSheetAt(3);
		List<Course> notspecified = areas.get(8).getList();
		int rowNum = 1;
		int columnNum = 9;
		System.out.println(notspecified.size());
		for(Course course : notspecified) {
			Row row = sheet.getRow(rowNum++);
			Cell cell = row.createCell(columnNum);
			cell.setCellValue(course.getName());
		}
	}

}
