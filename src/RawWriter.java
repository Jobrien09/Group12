import java.util.List;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RawWriter {
	private static final String FILE_NAME2 = "rawList.xlsx";
	private static 	XSSFWorkbook workbook1 = new XSSFWorkbook();
	
	public static void writeRawList(List<String[]> rawList) {
		XSSFWorkbook workbook = new XSSFWorkbook();
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
		
        // Formatting column width
        for(int i=0;i<2;i++) {
        	rawSheet.autoSizeColumn(i);
        }
		
		try {
	       	FileOutputStream outputStream = new FileOutputStream(FILE_NAME2);
	       	workbook.write(outputStream);
	       	outputStream.close();
	       	workbook.close();
	    } catch (FileNotFoundException e) {
	       	e.printStackTrace();
	    } catch (IOException e) {
	       	e.printStackTrace();
	    }
		System.out.println("Done1");
	}
	
	public static void writeRawDist(List<Course> courseList) {
		// Creating font and style for top row 
        CellStyle style = workbook1.createCellStyle();
        XSSFFont font = workbook1.createFont();
        font.setBold(true);
        style.setFont(font);
        
		XSSFSheet rawSheet = workbook1.createSheet("RAW");
		int numColumns = 24;

		String[] topRowTypes = {"Course", "Other", "Fails", "Marginal", "Meets", "Exceeds"};
        Row topRow = rawSheet.createRow(0);
        for (int i = 0; i < topRowTypes.length; i++) {
        	Cell cell = topRow.createCell(i);
        	cell.setCellValue(topRowTypes[i]);
        	cell.setCellStyle(style);
        }

        int count = topRowTypes.length + 1;
        String[] topRowTypes2 = {"F", "D", "C", "B", "A"};
        for (int i = count; i < count + topRowTypes2.length; i++) {
        	Cell cell = topRow.createCell(i);
        	cell.setCellValue(topRowTypes2[i - count]);
        	cell.setCellStyle(style);
        }

        count = count + topRowTypes2.length + 1;
        String[] topRowTypes3 = {"Freshman", "Sophomore", "Junior", "Senior"};
        for (int i = count; i < count + topRowTypes3.length; i++) {
        	Cell cell = topRow.createCell(i);
        	cell.setCellValue(topRowTypes3[i - count]);
        	cell.setCellStyle(style);
        }

        count = count + topRowTypes3.length + 1;
        String [] topRowTypes4 = {"Fred", "SJ"};
        for (int i = count; i < count + topRowTypes4.length; i++) {
        	Cell cell = topRow.createCell(i);
        	cell.setCellValue(topRowTypes4[i - count]);
        	cell.setCellStyle(style);
        }

        count = count + topRowTypes4.length + 1;
        String [] topRowTypes5 = {"Fall", "Winter", "Summer", "SP"};  //SP in transcript??
        for (int i = count; i < count + topRowTypes5.length; i++) {
        	Cell cell = topRow.createCell(i);
        	cell.setCellValue(topRowTypes5[i - count]);
        	cell.setCellStyle(style);
        }

        int rowNum = 1;
        for (Course course : courseList) {
        	Row row = rawSheet.createRow(rowNum++);

        	Cell cell0 = row.createCell(0);
        	cell0.setCellValue((String) course.getName());

        	
        	Cell cell1 = row.createCell(1);
        	cell1.setCellValue((Integer) course.getNumGrades("O")); //achievement
        	Cell cell2 = row.createCell(2);
        	cell2.setCellValue(course.getNumAchievement("Fails"));
        	Cell cell3 = row.createCell(3);
        	cell3.setCellValue(course.getNumAchievement("Marginal"));
        	Cell cell4 = row.createCell(4);
        	cell4.setCellValue(course.getNumAchievement("Meets"));
        	Cell cell5 = row.createCell(5);
        	cell5.setCellValue(course.getNumAchievement("Exceeds"));
        	
        	
        	Cell cell7 = row.createCell(7);								//grade
        	cell7.setCellValue((Integer) course.getNumGrades("F"));
        	Cell cell8 = row.createCell(8);
        	cell8.setCellValue((Integer) course.getNumGrades("D"));
        	Cell cell9 = row.createCell(9);
        	cell9.setCellValue((Integer) (course.getNumGrades("C+") + course.getNumGrades("C") + course.getNumGrades("C-")));
        	Cell cell10 = row.createCell(10);
        	cell10.setCellValue((Integer) (course.getNumGrades("B+") + course.getNumGrades("B") + course.getNumGrades("B-")));
        	Cell cell11 = row.createCell(11);
        	cell11.setCellValue((Integer) (course.getNumGrades("A+") + course.getNumGrades("A") + course.getNumGrades("A-")));
        	Cell cell13 = row.createCell(13);							//rank
        	Cell cell14 = row.createCell(14);
        	Cell cell15 = row.createCell(15);
        	Cell cell16 = row.createCell(16);
        	Cell cell18 = row.createCell(18);
        	cell18.setCellValue((Integer) course.getNumCampus("FR"));	//campus
        	Cell cell19 = row.createCell(19);
        	cell19.setCellValue((Integer) course.getNumCampus("SJ"));
        	Cell cell21 = row.createCell(21);
        	cell21.setCellValue((Integer) course.getNumSemester("FA"));	//semester
        	Cell cell22 = row.createCell(22);
        	cell22.setCellValue((Integer) course.getNumSemester("WI"));
        	Cell cell23 = row.createCell(23);
        	cell23.setCellValue((Integer) course.getNumSemester("SM"));
        	Cell cell24 = row.createCell(24);
        	cell24.setCellValue((Integer) course.getNumSemester("SP"));
        }
        
        // Formatting column width
        for(int i=0;i<numColumns;i++) {
        	rawSheet.autoSizeColumn(i);
        }
        
        System.out.println("Done2");
	}
	
	public static XSSFWorkbook getWorkbook() {
		return workbook1;
	}
	
}