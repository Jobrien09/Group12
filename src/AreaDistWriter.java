package test;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AreaDistWriter extends XLSXWriter<Area>{
	
	public AreaDistWriter(String fileName, XSSFWorkbook workbook) {
		super(fileName, workbook);
	}
	
	@Override
	public void writeWorkbook(List<Area> areaLists) {
		XSSFWorkbook workbook = super.getWorkbook();
		XSSFSheet areaSheet = workbook.createSheet("Area");

		// Creating font and style for top row 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
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
            Row column = areaSheet.createRow(columnNum++);
        	Cell cell = column.createCell(0);
        	cell.setCellValue(firstColumnTypes[i]);
        	cell.setCellStyle(style);
        	
            Cell cell1 = column.createCell(1);
            cell1.setCellValue((Integer) areaLists.get(i).getNumAchievement("Other"));
            Cell cell2 = column.createCell(2);
            cell2.setCellValue((Integer) areaLists.get(i).getNumAchievement("Fails"));
            Cell cell3 = column.createCell(3);
            cell3.setCellValue((Integer) areaLists.get(i).getNumAchievement("Marginal"));
            Cell cell4 = column.createCell(4);
            cell4.setCellValue((Integer) areaLists.get(i).getNumAchievement("Meets"));
            Cell cell5 = column.createCell(5);
            cell5.setCellValue((Integer) areaLists.get(i).getNumAchievement("Exceeds"));
        }      
        // Formatting column width
        areaSheet.autoSizeColumn(0);
        System.out.println("Done3");
	}
}
