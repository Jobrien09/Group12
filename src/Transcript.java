package test;

import java.util.List;
import java.util.ArrayList;

public class Transcript {
	private ArrayList<Course> courses = new ArrayList<>();
	private ArrayList<Double> grades = new ArrayList<>();
	private static List<Course> math;
	private static List<Course> science;
	private static List<Course> fundamental;
	private static List<Course> specialized;
	private static List<Course> terminal;
	private static List<Course> core;
	private static List<Course> cse;
	private static List<Course> society;
	
	public Transcript() {
	}
	
	public void addCourse(Course course, String grade) {
		courses.add(course);
		switch (grade) {
		case "A+": 	grades.add(4.3); break;
		case "A": 	grades.add(4.0); break;
		case "A-":	grades.add(3.7); break;
		case "B+": 	grades.add(3.3); break;
		case "B": 	grades.add(3.0); break;
		case "B-":	grades.add(2.7); break;
		case "C+": 	grades.add(2.3); break;
		case "C": 	grades.add(2.0); break;
		case "D":	grades.add(1.0); break;
		case "F":	grades.add(0.0); break;
		default: courses.remove(courses.size()-1);
		}
	}
	
	public String getAreaGrade(String area) {
		double gradeIn = getAvg(area)*100;
		
		if(gradeIn == -100) {
			return "NaN";		// Returns NaN if transcript has no courses in said area
		}
		
		int numGrade = (int)gradeIn;
		String grade = "";
		
		
		if(numGrade>415) {
			grade = "A+";
		}else if(numGrade>385) {
			grade = "A";
		}else if(numGrade>355) {
			grade = "A-";
		}else if(numGrade>315) {
			grade = "B+";
		}else if(numGrade>285) {
			grade = "B";
		}else if(numGrade>255) {
			grade = "B-";
		}else if(numGrade>215) {
			grade = "C+";
		}else if(numGrade>150) {
			grade = "C";
		}else if(numGrade>50) {
			grade = "D";
		}else
			grade = "F";
		
		return grade;
	}
	
	public double getAvg(String area) {
		setArea();
		int n = 0;
		double numerator = 0.0;
		double totalCH = 0.0;
		List<Course> areaIn = null;
		
		switch(area) {
		case "math": areaIn = math; break;
		case "science": areaIn = science; break;
		case "fundamental": areaIn = fundamental; break;
		case "specialized": areaIn = specialized; break;
		case "terminal": areaIn = terminal; break;
		case "core": areaIn = core; break;
		case "cse": areaIn = cse; break;
		case "society": areaIn = society; break;
		}
		
		for(int i=0;i<areaIn.size();i++) {
			for(int j=0;j<courses.size();j++) {
				if(areaIn.get(i).getName().equals(courses.get(j).getName())) {
					totalCH += courses.get(j).getCreditHours();
					numerator += courses.get(j).getCreditHours() * grades.get(j);
					n++;
				}
			}
		}
		
		// Returns -1 if transcript has no courses in said area
		if(n==0) {
			return -1.0;
		}
		
		double avg = numerator / totalCH;
		return avg;
	}
	
	public void setArea() {
		math = AreaReader.getArea("math");
		science = AreaReader.getArea("science");
		fundamental = AreaReader.getArea("fundamental");
		specialized = AreaReader.getArea("specialized");
		terminal = AreaReader.getArea("terminal");
		core = AreaReader.getArea("core");
		cse = AreaReader.getArea("cse");
		society = AreaReader.getArea("society");
	}
	
}
