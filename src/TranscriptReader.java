package test;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class TranscriptReader{
	private String directoryPath;

	private List<Course> courseList = new ArrayList<>();
	private List<String[]> rawList = new ArrayList<>();
	private List<String[]> transcript;
	private List<Transcript> transcripts = new ArrayList<>();
	private Transcript trans;
	
	public TranscriptReader(String dirPath) {
		this.directoryPath = dirPath;
	}
	
	//reads transcripts and creates Course, Raw, and Transcript list
	public List<Transcript> read() {
		File dir = new File(directoryPath);
		File[] files = dir.listFiles();

		for (File f : files) {
			if (f.isFile()) {
				boolean hasErrored = false;
				BufferedReader lineReader = null;
				transcript = new ArrayList<>();
				trans = new Transcript();
				transcripts.add(trans);
				try {
					lineReader = new BufferedReader(new FileReader(f));
                    String courseLine;             
                    while ((courseLine = lineReader.readLine()) != null) {
                        if(!courseLine.equals("")) {
			    			createLists(courseLine);
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
		return transcripts;
	}
	
	//creates lists
	private void createLists(String courseLine) {
		Scanner lineScan = new Scanner(courseLine);		
		String[] grade = readCourse(courseLine);
		String cNum = grade[0];
		String cTitle = grade[1];
		String letterGrade = grade[2];
		String creditHours = grade[5];
		addToLists(cNum, cTitle, creditHours, letterGrade);
		addCourseToTranscript(grade);
		findCourseToInc(grade);
		lineScan.close();
	}
	
	//reads the line of a transcript
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
		String creditH = recordScanner.next();  //change this to double later when calculating ch
		
		String yearSemester = recordScanner.next();  //2013/WI
		String year = "";
		String semester = "";
		if (yearSemester.length() == 7) {
			year = yearSemester.substring(0, 4); //2013
			semester = yearSemester.substring(5); //WI
		}
		
		String[] grade = {cNum, cTitle, letter, campus, semester, creditH};
		recordScanner.close();	
		return grade;
	}
	
	//returns sorted raw list
	public List<String[]> getRawList() {
		Collections.sort(rawList, new Comparator<String[]>() {
			public int compare (String[] x1, String[] x2) {
				int n1 = x1[0].length();
				int n2 = x2[0].length();
				int min = Math.min(n1, n2);
			
				for (int i = 0; i < min; i++) {
					int x1_ch = (int)x1[0].charAt(i);
					int x2_ch = (int)x2[0].charAt(i);
				
					if (x1_ch > x2_ch)
						return 1;
					else if (x1_ch < x2_ch)
						return -1;
				}
			
				if (n1 > n2)
					return 1;
				else if (n1 < n2)
					return -1;
				return 0;	
			}
		});
		return rawList;
	}
	
	public List<Course> sortCourseList() {
		Collections.sort(courseList, new Comparator<Course>() {
			public int compare (Course c1, Course c2) {
				int n1 = c1.getName().length();
				int n2 = c2.getName().length();
				int min = Math.min(n1, n2);
			
				for (int i = 0; i < min; i++) {
					int x1_ch = (int)c1.getName().charAt(i);
					int x2_ch = (int)c2.getName().charAt(i);
				
					if (x1_ch > x2_ch)
						return 1;
					else if (x1_ch < x2_ch)
						return -1;
				}
			
				if (n1 > n2)
					return 1;
				else if (n1 < n2)
					return -1;
				return 0;	
			}
		});
		return courseList;
	}
		
	//returns course list
	public List<Course> getCourseList() {
		return courseList;
	}
	
	//adds the course into Course list and Raw list
	private void addToLists(String cNum, String cTitle, String creditHours, String grade) {
		if (!existsInCourseList(cNum)) {
			Course course = new Course(cNum);
			course.setCreditHours(creditHours);
			courseList.add(course);
//			trans.addCourse(course, grade);
			rawList.add(new String[] {cNum, cTitle});
		}
	}
	
	//checks if the course exists in Course list
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
	
	private void addCourseToTranscript(String[] grade) {
		if (transcript.isEmpty())
			transcript.add(grade);
		else
			takeSameCourse(grade);
	}
	//checks if the course was already taken once
	private void takeSameCourse(String[] grade) {
		String[] cGrade = transcript.get(transcript.size() - 1);
		String cName = cGrade[0];
		String cNum = grade[0];
		if (cName.equals(cNum)) {
			String letter1 = cGrade[2];
			String letter2 = grade[2];
			String lowLetter = Course.findLowerGrade(letter1, letter2);
			String[] lowGrade, highGrade;
			if (lowLetter.equals(letter1)) {
				lowGrade = cGrade;
				transcript.remove(lowGrade);
				highGrade = grade;
				transcript.add(highGrade);
			} else {
				lowGrade = grade;
			}				
			findCourseToDec(lowGrade);
		}
		else
			transcript.add(grade);
	}
	
	//increments the course distribution
	private void findCourseToInc(String[] grade) {
		for (Course course : courseList) {
			String cNum = grade[0];
			if (course.getName().equals(cNum)) {
				course.incrementCourse(grade);
				break;
			}
		}
	}
	
	//decrements the course distribution
	private void findCourseToDec(String[] grade) {
		for (Course course : courseList) {
			String cNum = grade[0];
			if (course.getName().equals(cNum)) {
				course.decrementCourse(grade);
				break;
			}
		}
	}
}