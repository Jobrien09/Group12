package test;
import java.util.List;
import java.util.Iterator;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EquivalenceReader{

	private static String fileName;
	
	public EquivalenceReader(String fileName) {
		this.fileName = fileName;
	}
	
	//reads Equivalence tab and change any course number that exists in the tab to master course number
	public static List<Course> read(List<Course> courseList) {
		try {
			FileInputStream excelFile = new FileInputStream(new File(fileName));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet sheet = workbook.getSheetAt(2);
			DataFormatter dataFormatter = new DataFormatter();
			
			Iterator<Row> it = sheet.iterator();
			Row row = it.next();
			String masterCourseNum = "";
			for (int rowI = 0; rowI < row.getLastCellNum(); rowI++) {
				int firstRow = 0;
				for (Row row2 : sheet) {
					Cell cell = row2.getCell(rowI);
					String cellValue = dataFormatter.formatCellValue(cell);
					if (firstRow == 0) {
						masterCourseNum = cellValue;
						firstRow++;
						continue;
					}
					changeCourseNum(courseList, cellValue, masterCourseNum);			
				}
			}
			workbook.close();
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return courseList;
	}
	
	private static void changeCourseNum(List<Course> courseList, String courseNum, String masterCourseNum) {
		for (Course course : courseList) {
			String cNum = course.getName();
			if (cNum.equals(courseNum)) {
				
				break;
			}
		}	
	}
}