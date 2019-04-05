package test;
import java.util.List;

import java.io.FileOutputStream;
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
public class AreaReader implements XLSXReader<Area>{
	private List<Course> math = new ArrayList<>();
	private List<Course> science = new ArrayList<>();
	private List<Course> fundamental = new ArrayList<>();
	private List<Course> specialized = new ArrayList<>();
	private List<Course> terminal = new ArrayList<>();
	private List<Course> core = new ArrayList<>();
	private List<Course> cse = new ArrayList<>();
	private List<Course> society = new ArrayList<>();
	private List<Course> specified = new ArrayList<>();
	private List<Course> notspecified = new ArrayList<>();
	
	private List<Course> courseList;
	
	private String fileName;
	public AreaReader(String fileName, List<Course> courseList) {
		this.fileName = fileName;
		this.courseList = courseList;
	}
	
	@Override
	public List<Area> read() {
		try {
			FileInputStream excelFile = new FileInputStream(new File(fileName));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet sheet = workbook.getSheetAt(3);
			DataFormatter dataFormatter = new DataFormatter();
			
			Iterator<Row> it = sheet.iterator();
			Row row = it.next();
			String areaName = "";
			for (int rowI = 0; rowI < row.getLastCellNum(); rowI++) {
				int firstRow = 0;
				for (Row row2 : sheet) {
					Cell cell = row2.getCell(rowI);
					if (cell == null) {
						break;
					}
					String cellValue = dataFormatter.formatCellValue(cell);
					if (firstRow == 0) {
						areaName = cellValue;
						firstRow++;
						continue;
					}
					classify(courseList, areaName, cellValue);
				}
			}
			checkNotSpecified(courseList);
			writeNotSpecified(workbook, sheet);
			
			FileOutputStream outputStream = new FileOutputStream(fileName);
			workbook.write(outputStream);
			excelFile.close();
			workbook.close();
			outputStream.close();
			
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		List<Area> areaLists = calculateAreaLists();
		return areaLists;
	}
	
	//classifies the courses
	private void classify(List<Course> courseList, String areaName, String courseNum) {
		for (Iterator<Course> it = courseList.iterator(); it.hasNext();) {
			Course course = it.next();
			if (course.getName().equals(courseNum)) {
				addToLists(areaName, course);
				specified.add(course);	
			}
		}	
	}
	
	//adds any not specified courses to notspecified list 
	private void checkNotSpecified(List<Course> courseList) {
		for(int i=0;i<courseList.size();i++) {
			for(int j=0;j<specified.size();j++) {
				if(courseList.get(i) == specified.get(j)) {
					break;
				}
				if(j == specified.size()-1) {
					notspecified.add(courseList.get(i));
				}
			}
		}
	}
	
	//adds the course to a list where it belong
	private void addToLists(String areaName, Course course) {
		switch (areaName.toLowerCase()) {
			case "math": math.add(course); break;
			case "science": science.add(course); break;
			case "fundamentals": fundamental.add(course); break;
			case "specialized": specialized.add(course); break;
			case "terminal": terminal.add(course); break;
			case "core": core.add(course); break;
			case "cse": cse.add(course); break;
			case "society": society.add(course); break;
		}
	}
	
	//calculates the area distribution
	private List<Area> calculateAreaLists() {
		// Creating all areas
	    Area math = new Area(this.math);
	    Area science = new Area(this.science);
	    Area fundamental = new Area(this.fundamental);
	    Area specialized = new Area(this.specialized);
	    Area terminal = new Area(this.terminal);
	    Area core = new Area(this.core);
	    Area cse = new Area(this.cse);
	    Area society = new Area(this.society);
	    
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
	
	private void writeNotSpecified(Workbook workbook, Sheet sheet) {
		int rowNum = 1;
		int columnNum = 9;
		for(Course course : notspecified) {
			Row row = sheet.getRow(rowNum++);
			if (row == null) {
				row = sheet.createRow(rowNum-1);
			}
			Cell cell = row.createCell(columnNum);
			cell.setCellValue(course.getName());
//			if(rowNum<200) {
//				Row row = sheet.getRow(rowNum++);
//				Cell cell = row.createCell(columnNum);
//				cell.setCellValue(course.getName());
//			} else {
//				Row row = sheet.createRow(rowNum++);
//				Cell cell = row.createCell(columnNum);
//				cell.setCellValue(course.getName());
//			}
		}
	}
}