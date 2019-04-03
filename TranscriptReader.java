import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TranscriptReader {
	private String directoryPath;
		
	//not static because it reads multiple directory. 
	private List<Course> courseList = new ArrayList<>();
	private List<String[]> rawList = new ArrayList<>();  //courseNum and CourseTitle
	private List<String[]> transcript;	
	
//	public String getCohort() { //get cohort from the directory file name from directoryPath
//		
//		return cohort;				ex: EE2014?
//	}
	
	public TranscriptReader(String dirPath) {
		this.directoryPath = dirPath;
	}
	
	private String[] readCourse (String record) {
		Scanner recordScanner = new Scanner(record);
		
		String prev = "";
		String cNum = recordScanner.next();

		String campusSection = recordScanner.next(); //FR01A
		String campus = campusSection.substring(0, 2); //FR
		String section = campusSection.substring(2); //01A
		
		String cTitle = "";
		while(!recordScanner.hasNextDouble()) {
			prev = recordScanner.next();
			cTitle += prev + " ";
		}
		cTitle = cTitle.substring(0, cTitle.length() - (prev.length() + 1));
		String letter = prev;
		
		String yearSemester = recordScanner.next();  //2013/WI
		String year = yearSemester.substring(0, 4); //2013
		String semester = yearSemester.substring(5); //WI
		
		String[] grade = {cNum, cTitle, letter, campus, semester};
		recordScanner.close();	
		return grade;
		
	}
	
	private double readCH(Readable record) {
		Scanner recordScanner1 = new Scanner(record);
		double totalCH = 0.0;
		
		while(!recordScanner1.hasNextDouble()) {
			totalCH = recordScanner1.nextDouble();
		}
		recordScanner1.close();
		return totalCH;
	}
	
	public List<String[]> getRawList() {
		return rawList;
	}
	
	public List<Course> getCourseList() {
		return courseList;
	}
	
	private void addToLists(String cNum, String cTitle) {
		if (!existsInCourseList(cNum)) {
			courseList.add(new Course(cNum));
			rawList.add(new String[] {cNum, cTitle});
		}
	}	
	
	private void addGradesToTranscript(String[] grade) {
		existsInTranscript(grade);
		transcript.add(grade);
	}
	
	private void existsInTranscript(String[] grade) {
		takeCourseTwice(grade);
	}
	
	public void createLists() {
		File dir = new File(directoryPath);
		File[] files = dir.listFiles();

		for (File f : files) {
			if (f.isFile()) {
				boolean hasErrored = false;
				BufferedReader lineReader = null;
				transcript = new ArrayList<>();

				try {
					lineReader = new BufferedReader(new FileReader(f));
                    String courseLine;
                    
                    while ((courseLine = lineReader.readLine()) != null) {
                        if(!courseLine.equals("")) {
			    			Scanner lineScan = new Scanner(courseLine);
			      			
			    			String[] grade = readCourse(courseLine);
			    			String cNum = grade[0];
			    			String cTitle = grade[1];
			    			addToLists(cNum, cTitle);
			    			addGradesToTranscript(grade);
			    			findCourseToInc(grade);
			    			lineScan.close();
                        }
                        
		    		}
				}
				catch (IOException e) {
                    System.out.println(f.getName());
                    e.printStackTrace();
                    hasErrored = true;
                }
				
                finally {
                    if (lineReader != null) {
                        try {
                            lineReader.close();
                        } catch (Exception e) {
                            if (!hasErrored)
                                System.out.println("Error has occurred");
                        }
                    }
                }
			}
		}
	}
	
	private void findCourseToInc(String[] grade) {
		for (Course course : courseList) {
			String cNum = grade[0];
			if (course.getName().equals(cNum)) {
				course.incrementCourse(grade);
				break;
			}
		}
	}
	
	private void findCourseToDec(String letter, String[] grade) {
		for (Course course : courseList) {
			String cNum = grade[0];
			if (course.getName().equals(cNum)) {
				course.decrementCourse(letter, grade);
				break;
			}
		}
	}
	
	private void takeCourseTwice(String[] grade) {
		for (String[] cGrade : transcript) {
			String cName = cGrade[0];
			String cNum = grade[0];
			if (cName.equals(cNum)) {
				String letter = cGrade[2];
				findCourseToDec(letter, grade);
				break;
			}
		}
	}
	
	private boolean existsInCourseList(String courseNum) {
		boolean status = false;
		for (Course course : courseList) {
			if (course.getName().equals(courseNum)) {
				status = true;
				break;
			}
		}
		return status;
	}
}