import java.util.List;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AreaWriter {
	private static XSSFWorkbook workbook1 = RawWriter.getWorkbook();
	
	public static void writeAreaDist(List<Course> courseList) {
		XSSFSheet areaSheet = workbook1.createSheet("Area");
		List<Area> areaLists = createAreas(courseList);
	    
	    for (Area area : areaLists) {
	    	area.calculateArea();
	    }      
		
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
            Row column = areaSheet.createRow(columnNum++);
        	Cell cell = column.createCell(0);
        	cell.setCellValue(firstColumnTypes[i]);
        	cell.setCellStyle(style);
        	
            Cell cell1 = column.createCell(1);
            cell1.setCellValue((Integer) areaLists.get(i).getNumAchievement("Other"));
            Cell cell2 = column.createCell(2);
            cell2.setCellValue((Integer) areaLists.get(i).getNumAchievement("Fail"));
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
	
	public static List<Area> createAreas(List<Course> courseList) {
		AreaList a = new AreaList();
		a.createAreaLists(courseList);
		
		// Creating all areas
	    Area math = new Area("math", a);
	    Area science = new Area("science", a);
	    Area fundamental = new Area("fundamental", a);
	    Area specialized = new Area("specialized", a);
	    Area terminal = new Area("terminal", a);
	    Area core = new Area("core", a);
	    Area cse = new Area("cse", a);
	    Area society = new Area("society", a);
	    
	    // Adding areas to areaList
	    List<Area> areaLists = new ArrayList<>();
	    areaLists.add(math);
	    areaLists.add(science);
	    areaLists.add(fundamental);
	    areaLists.add(specialized);
	    areaLists.add(terminal);
	    areaLists.add(core);
	    areaLists.add(cse);
	    areaLists.add(society);
	    
	    return areaLists;
	}
}