public class Course {
	private String courseNum;

	private int numA;		//A+ a- don't need
	private int numB;
	private int numC;
	private int numD;
	private int numF;
	private int numO;

	private int numSJ;
	private int numFR;

	private int numSP;
	private int numSM;
	private int numFA;
	private int numWI;
	
	public Course(String courseNum) {
		this.courseNum = courseNum;
	}

	public String getName() {
		return courseNum;
	}
	
	public void incrementCourse(String[] grade) {
		String letter = grade[2];
		String campus = grade[3];
		String semester = grade[4];
		this.incrementNumGrade(letter);
		this.incrementNumCampus(campus);
		this.incrementNumSemester(semester);
	}
	
	public void decrementCourse(String letter1, String[] grade) {
		String letter2 = grade[2];
		String campus = grade[3];
		String semester = grade[4];
		String lowLetter = findLowerGrade(letter1, letter2);
		this.decrementNumGrades(lowLetter);
		this.decrementNumCampus(campus);
		this.decrementNumSemester(semester);
	}
	
	private static String findLowerGrade(String letter1, String letter2) {
		String lowerGrade = "";
		if (Course.compareGrades(letter1, letter2) >= 0) {
			lowerGrade = letter2;
		}
		else if (Course.compareGrades(letter1, letter2) < 0) {
			lowerGrade = letter1;
		}
		return lowerGrade;
	}
	
	private void incrementNumGrade(String grade) {
		switch (grade) {
			case "A+": case "A": case "A-":	numA++; break;
			case "B+": case "B": case "B-":	numB++; break;
			case "C+": case "C": case "C-":	numC++; break;
			case "D":	numD++; break;
			case "F":	numF++; break;
			case "AUD":	numO++; break;					//provide way to count non-standard grades //ignore nonstarndard grades
			case "CTN": numO++; break;
			case "W": numO++; break;
			default :	System.out.println("Grade not within range");
		}
	}

	private void decrementNumGrades(String grade) {
		switch (grade) {
			case "A+": case "A": case "A-":	numA--; break;
			case "B+": case "B": case "B-":	numB--; break;
			case "C+": case "C": case "C-":	numC--; break;
			case "D":	numD--;	break;
			case "F":	numF--; break;
			case "AUD":	numO--; break;
			case "CTN": numO--; break;
			case "W": numO--; break;
			default :	System.out.println("Grade not within range2");
		}
	}

	public int getNumGrades(String grade) {
		int num = 0;
		switch (grade) {
			case "A":	num = numA; break;
			case "B":	num = numB; break;
			case "C":	num = numC; break;
			case "D":	num = numD; break;
			case "F":	num = numF; break;
			case "AUD":	num = numO; break;
			case "CTN": num = numO; break;
			case "W": num = numO; break;
			case "O": num = numO; break;
			default :	System.out.println("Grade not within range3");
						return -1;
		}
		return num;
	}

	private void incrementNumCampus(String campus) {
		switch(campus) {
			case "SJ": numSJ++; break;
			case "FR": numFR++; break;
			default: System.out.println("The campus does not exist");
		}
	}
	
	private void decrementNumCampus(String campus) {
		switch(campus) {
			case "SJ": numSJ--; break;
			case "FR": numFR--; break;
			default: System.out.println("The campus does not exist2");
		}
	}

	public int getNumCampus(String campus) {
		int num = 0;
		switch(campus) {
			case "SJ": num = numSJ; break;
			case "FR": num = numFR; break;
			default: System.out.println("The campus does not exist");
					 return -1;
		}
		return num;
	}

	private void incrementNumSemester(String semester) {
		switch(semester) {
			case "SP": numSP++; break;
			case "SM": numSM++; break;
			case "FA": numFA++; break;
			case "WI": numWI++; break;
			default: System.out.println("The semester does not exist");
		}
	}
	
	private void decrementNumSemester(String semester) {
		switch(semester) {
			case "SP": numSP--; break;
			case "SM": numSM--; break;
			case "FA": numFA--; break;
			case "WI": numWI--; break;
			default: System.out.println("The semester does not exist");
		}
	}

	public int getNumSemester(String semester) {
		int num = 0;
		switch(semester) {
			case "SP": num = numSP; break;
			case "SM": num = numSM; break;
			case "FA": num = numFA; break;
			case "WI": num = numWI; break;
			default: System.out.println("The semester does not exist");
					 return -1;
		}
		return num;
	}

	public int getNumAchievement(String level) {
		int num = 0; 									//assume default schema
		switch (level) {								//should read achievement schema from excel
			case "Exceeds": num = getNumGrades("A"); break;	//maybe another method to read in the schema
			case "Meets": num = getNumGrades("B"); break;
			case "Marginal": num = getNumGrades("C"); break;
			case "Fail": num = getNumGrades("D") + getNumGrades("F"); break;
			case "Other" : num = getNumGrades("AUD") + getNumGrades("CTN"); break;
			default: System.out.println("level not available");
					 return -1;
		}
		return num;
	}
	private static int compareGrades(String grade1, String grade2) {
		int g1, g2;	//ASCII
		int index = 0;												//1 = grade1 higher, 0 = same, -1 = grade2 higher
		
		if (grade1.length() > 2 || grade2.length() > 2) {
			if (grade1.length() <= 2) 
				return 1;
			else if (grade2.length() <= 2) 
				return -1;
			else 
				return 0;     //CTN ? AUD
		}

		g1 = (int) grade1.charAt(index);
		g2 = (int) grade2.charAt(index);

		if (g1 - g2 < 0)
			return 1;
		else if (g1 - g2 > 0)
			return -1;
		else {
			if (grade1.length() == 1 && grade2.length() == 1) {
				return 0;
			}
			else {
				index = index + 1;
				if (grade1.length() == 1 && grade2.length() == 2) {
					g2 = (int) grade2.charAt(index);
					if (g2 == 43)
						return 1;
					else if (g2 == 45)
						return -1;
				}
				else if (grade1.length() == 2 && grade2.length() == 1) {
					g1 = (int) grade1.charAt(index);
					if (g1 == 43)
						return -1;
					else if (g1 == 45)
						return 1;
				}
				else {
					g1 = (int) grade1.charAt(index);
					g2 = (int) grade2.charAt(index);
					if (g1 - g2 < 0)
						return 1;
					else if (g1 - g2 > 0)
						return -1;
				}
			}
		}
		return 0;
	}
}