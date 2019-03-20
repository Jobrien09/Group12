import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Read {
	private static ArrayList<Course> courseList = new ArrayList<Course>();
	private static final String FILE_NAME = "results_EE2014.xlsx";
	
	public static void main(String[] args) throws FileNotFoundException, IOException, NoSuchElementException {
		File dir = new File("\\Users\\Deon\\Documents\\transcripts");
		
		String courseLine = null;
		
		// Scanning all files in the directory, creating and incrementing all courses.
		for (File file : dir.listFiles()) {
		    Scanner s = new Scanner(file);
		    while(s.hasNextLine()) {
		    	courseLine = s.nextLine();
		    	if(!courseLine.equals("")) {
			    	Scanner lineScan = new Scanner(courseLine);
			    	String cName = lineScan.next();
			    	if(alreadyMade(cName) == false) {
			    		courseList.add(createCourse(cName));
			    	}
			    	incrementCourse(courseLine);
			    	lineScan.close();
		    	}
		    }
		    s.close();
		}
		
    	// Creating new workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        
        // Creating RAW data sheet
        XSSFSheet rawSheet = workbook.createSheet("RAW");
        
        // Creating top row of RAW data sheet
        String[] topRowTypes = new String[] {"Course", "Other", "fails", "marginal", "meets", "exceeds"};
        Row topRow = rawSheet.createRow(0);
        for(int i=0;i<topRowTypes.length;i++) {
        	Cell cell = topRow.createCell(i);
        	cell.setCellValue(topRowTypes[i]);
        }
        
        // Writing distributions to raw excel sheet
        int rowNum = 1;
        System.out.println(courseList.size());
        System.out.println(courseList.get(0).numGrades("C"));
        for(int i=0;i<courseList.size();i++) {
        	Row row = rawSheet.createRow(rowNum++);
        	// Write course name
        	Cell cell0 = row.createCell(0);
        	cell0.setCellValue((String) courseList.get(i).getName());
        	// Write course grades
        	Cell cell1 = row.createCell(1);
        	cell1.setCellValue((Integer) courseList.get(i).numGrades("O"));
        	Cell cell2 = row.createCell(2);
        	cell2.setCellValue((Integer) (courseList.get(i).numGrades("F") + courseList.get(i).numGrades("D")));
        	Cell cell3 = row.createCell(3);
        	cell3.setCellValue((Integer) courseList.get(i).numGrades("C"));
        	Cell cell4 = row.createCell(4);
        	cell4.setCellValue((Integer) courseList.get(i).numGrades("B"));
        	Cell cell5 = row.createCell(5);
        	cell5.setCellValue((Integer) courseList.get(i).numGrades("A"));
        }
        
        // Writing workbook data to file and outputting
        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
	}
	
	public static void incrementCourse (String record) {
		String cName, grade, campus, semester, discard, prev;
		double creditHours;
		
		grade = "null";
		prev = "";
		
		Scanner recordScanner = new Scanner(record);
		
		cName = recordScanner.next();
		campus = recordScanner.next();
		while(grade.equals("null")) {
			prev = recordScanner.next();
			if(recordScanner.hasNextDouble() == true) {
				grade = prev;
			}
		}
		creditHours = recordScanner.nextDouble();
		semester = recordScanner.next();
		System.out.println(cName + " " + grade + " " + campus + " " + semester + " " + creditHours);
		recordScanner.close();
		
		for(int i=0;i<courseList.size();i++) {
			if(courseList.get(i).getName().equals(cName)) {
				courseList.get(i).addGrade(grade);
				// ALSO ADD SEMESTER / CAMPUS ECT HERE BUT DIDNT ADD TO COURSE CLASS YET
			}
		}

	}
	
	public static boolean alreadyMade(String name) {
		for(int i=0;i<courseList.size();i++) {
			if(courseList.get(i).getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public static Course createCourse(String name) {
		Course newCourse = new Course(name);
		return newCourse;
	}
	
	public static ArrayList<Course> getCourseList(){
		return courseList;
	}
	// Things to do:
	// Area / areas tab, Other raw distributions (Campus, semester, ranks, ...ect), read from (and write to? or make copy of and write to that?) existing excel file, 
	// deal with equivalences somehow, how to deal with students taking a class twice, how to edit schemas, make master list of courses
	//
}
