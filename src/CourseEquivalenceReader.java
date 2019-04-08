package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CourseEquivalenceReader implements XLSXReader<Course>{

	private String fileName;
	private List<Course> courseList;
	
	public CourseEquivalenceReader(String fileName, List<Course> courseList) {
		this.fileName = fileName;
		this.courseList = courseList;
	}
	
	//reads Equivalence tab and change any course number that exists in the tab to master course number
	@Override
	public List<Course> read() {
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
	
	private void changeCourseNum(List<Course> courseList, String courseNum, String masterCourseNum) {
		for (Course course : courseList) {
			String cNum = course.getName();
			if (cNum.equals(courseNum)) {
				Course mCourse = existsInCourseList(courseList, masterCourseNum);
				if (mCourse == null)
					course.setName(masterCourseNum);
				else {
					combineCourses(course, mCourse);
					courseList.remove(course);
					}
				break;
			}
		}	
	}
	
	private Course existsInCourseList(List<Course> courseList, String masterCourseNum) {
		Course masterCourse = null;
		for (Course course : courseList) {
			if (course.getName().equals(masterCourseNum)) {
				masterCourse = course;
			}
		}
		return masterCourse;
	}
	
	private void combineCourses(Course course, Course masterCourseNum) {
		String[] letterGrades = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D", "F"};
		for (String grade : letterGrades) {
			int num = course.getNumGrades(grade);
			masterCourseNum.incrementNumGrade(grade, num);
		}
		
		String[] achievement = {"Exceeds", "Meets", "Marginal", "Fails"};
		for (String level : achievement) {
			int num = course.getNumAchievement(level);
			masterCourseNum.incrementNumAchievement(level, num);
		}
		
		String[] campuses = {"SJ", "FR"};
		for (String campus : campuses) {
			int num = course.getNumCampus(campus);
			masterCourseNum.incrementNumCampus(campus, num);
		}
		
		String[] semesters = {"SP", "SM", "FA", "WI"};
		for (String semester : semesters) {
			int num = course.getNumSemester(semester);
			masterCourseNum.incrementNumSemester(semester, num);
		}
	}	
}