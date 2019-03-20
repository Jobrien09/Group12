
public class Course {

	private String courseName;
	private int[] numA = new int[3]; // Index 0 is A+, index 1 A, Index 2 A-, ect...
	private int[] numB = new int[3];
	private int[] numC = new int[3];
	private int numD;
	private int numF;
	private int numO;
	
	public Course(String courseName) {
		this.courseName = courseName;
	}
	
	public void addGrade(String grade) {
		switch (grade) {
			case "A+":	numA[0]++;
						break;
			case "A":	numA[1]++;
						break;
			case "A-":	numA[2]++;
						break;
			case "B+":	numB[0]++;
						break;
			case "B":	numB[1]++;
						break;
			case "B-":	numB[2]++;
						break;
			case "C+":	numC[0]++;
						break;
			case "C":	numC[1]++;
						break;
			case "C-":	numC[2]++;
						break;
			case "D":	numD++;
						break;
			case "F":	numF++;
						break;
			case "O":	numO++;
						break;
			default :	System.out.println("Grade not within range");
						break;
		}
	}
	
	public int numGrades(String grade) {
		switch (grade) {		
			case "A":	return numA[0] + numA[1] + numA[2];
					
			case "B":	return numB[0] + numB[1] + numB[2];
			
			case "C":	return numC[0] + numC[1] + numC[2];

			case "D":	return numD;

			case "F":	return numF;

			case "O":	return numO;

			default :	System.out.println("Grade not within range");
						return -1;
		}		
	}
	
	public String getName() {
		return courseName;
	}
	
}