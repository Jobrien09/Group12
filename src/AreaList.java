import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
public class AreaList {
	private List<Course> math = new ArrayList<>();
	private List<Course> science = new ArrayList<>();
	private List<Course> fundamental = new ArrayList<>();
	private List<Course> specialized = new ArrayList<>();
	private List<Course> terminal = new ArrayList<>();
	private List<Course> core = new ArrayList<>();
	private List<Course> cse = new ArrayList<>();
	private List<Course> society = new ArrayList<>();
	private List<Course> notspecified = new ArrayList<>();
	
	private static final String SAMPLE = "config.xlsx";

	public void createAreaLists(List<Course> courseList) {
		try {
			FileInputStream excelFile = new FileInputStream(new File(SAMPLE));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet sheet = workbook.getSheetAt(3);
			DataFormatter dataFormatter = new DataFormatter();
			
			Iterator<Row> it = sheet.iterator();
			Row row = it.next();
			String area = "";
			for (int rowI = 0; rowI < row.getLastCellNum(); rowI++) {
				int firstRow = 0;
				for (Row row2 : sheet) {
					Cell cell = row2.getCell(rowI);
					if (cell == null) { //column 8
						break;
					}
					String cellValue = dataFormatter.formatCellValue(cell);
					if (firstRow == 0) {	
						area = cellValue;
						firstRow++;
						continue;
					}
					for (Iterator<Course> it2 = courseList.iterator(); it2.hasNext();) {
						Course course = it2.next();
						if (course.getName().equals(cellValue)) {
							addToLists(rowI, course);
						}
					}
					checkNotSpecified(courseList);	
				}
				//System.out.println();
			}	
			workbook.close();
			
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}	
	
	private void checkNotSpecified(List<Course> courseList) {
		if (!courseList.isEmpty()) {
			for (Course course : courseList) {
				notspecified.add(course);
			}
		}	
	}
	
	private void addToLists(int rowI, Course course) {
		switch (rowI) {
			case 0: math.add(course); break;
			case 1: science.add(course); break;
			case 2: fundamental.add(course); break;
			case 3: specialized.add(course); break;
			case 4: terminal.add(course); break;
			case 5: core.add(course); break;
			case 6: cse.add(course); break;
			case 7: society.add(course); break;
		}
	}
	public List<Course> getList(String areaName) {
		List<Course> area = null;
		switch(areaName) {
			case "math": area = math; break;
			case "science": area = science; break;
			case "fundamental": area = fundamental; break;
			case "specialized": area = specialized; break;
			case "terminal": area = terminal; break;
			case "core": area = core; break;
			case "cse": area = cse; break; 
			case "society": area = society; break;
			case "notspecified": area = notspecified; break;
			default : System.out.println("No such area");
		}
		return area;
	}
}