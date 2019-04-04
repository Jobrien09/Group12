public class Course {
	private String courseNum;

	private int numA, numAm, numAp;	
	private int numB, numBm, numBp;
	private int numC, numCm, numCp;
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
			case "A+": 	numAp++; break;
			case "A": 	numA++; break;
			case "A-":	numAm++; break;
			case "B+": 	numBp++; break;
			case "B": 	numB++; break;
			case "B-":	numBm++; break;
			case "C+": 	numCp++; break;
			case "C": 	numC++; break;
			case "C-":	numCm++; break;
			case "D":	numD++; break;
			case "F":	numF++; break;
			case "AUD":	numO++; break;					
			case "CTN": numO++; break;
			case "W": numO++; break;
			default : numO++;
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
			default : numO--;
		}
	}

	public int getNumGrades(String grade) {
		int num = 0;
		switch (grade) {
			case "A+":	num = numAp; break;
			case "A":	num = numA; break;
			case "A-":	num = numAm; break;
			case "B+":	num = numBp; break;
			case "B":	num = numB; break;
			case "B-":	num = numBm; break;
			case "C+":	num = numCp; break;
			case "C":	num = numC; break;
			case "C-":	num = numCm; break;
			case "D":	num = numD; break;
			case "F":	num = numF; break;
			case "AUD":	num = numO; break;
			case "CTN": num = numO; break;
			case "W": num = numO; break;
			case "O": num = numO; break;
			default : num = numO;
		}
		return num;
	}

	private void incrementNumCampus(String campus) {
		switch(campus) {
			case "SJ": numSJ++; break;
			case "FR": numFR++; break;
			default: System.out.println("The campus is not UNB");
		}
	}
	
	private void decrementNumCampus(String campus) {
		switch(campus) {
			case "SJ": numSJ--; break;
			case "FR": numFR--; break;
			default: System.out.println("The campus is not UNB 2");
		}
	}

	public int getNumCampus(String campus) {
		int num = 0;
		switch(campus) {
			case "SJ": num = numSJ; break;
			case "FR": num = numFR; break;
			default: System.out.println("The campus is not UNB");
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
		int num = 0;
		int i = 0;
		int[] arrayGrades = new int[] {numAp,numA,numAm,numBp,numB,numBm,numCp,numC,numCm,numD,numF};
		int exceeds = Schema.getExceeds();
		int meets = Schema.getMeets();
		int marginal = Schema.getMarginal();
		
		switch (level) {
			case "Exceeds": while(i<=exceeds) {
								num += arrayGrades[i];
								i++;
							} break;
			
			case "Meets": i = exceeds+1;
						  while(i<=meets) {
							  num += arrayGrades[i];
							  i++;
						  } break;
			
			case "Marginal": i = meets+1;
							while(i<=marginal) {
								num += arrayGrades[i];
								i++;
							} break;
			
			case "Fails": i = marginal+1;
							 while(i<arrayGrades.length) {
								 num += arrayGrades[i];
								 i++;
							 } break;
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