package test;
import java.util.List;

public class Area {
	private List<Course> list;

	private int numA, numAm, numAp;	
	private int numB, numBm, numBp;
	private int numC, numCm, numCp;
	private int numD;
	private int numF;
	private int numO;
	
	private int exceeds;
	private int meets;
	private int marginal;
	private int fails;
	
	private double totalCH;

	public Area(List<Course> list) {
		this.list = list;
		calculateArea();
	}
	
	public void calculateArea() {
		for (Course course : list) {
			numAp += course.getNumGrades("A+");
			numA += course.getNumGrades("A");
			numAm += course.getNumGrades("A-");
			numBp += course.getNumGrades("B+");
			numB += course.getNumGrades("B");
			numBm += course.getNumGrades("B-");
			numCp += course.getNumGrades("C+");
			numC += course.getNumGrades("C");
			numCm += course.getNumGrades("C-");
			numD += course.getNumGrades("D");
			numF += course.getNumGrades("F");
			numO += course.getNumGrades("O");
			totalCH += course.getCreditHours();
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
			case "O": num = numO; break;
			default : num = numO;
		}
		return num;
	}
	
	public void setNumAchievement(List<Integer> schema) {
		int iexceeds = schema.get(0);
		int imeets = schema.get(1);
		int imarginal = schema.get(2);
		int[] arrayGrades = new int[] {numAp,numA,numAm,numBp,numB,numBm,numCp,numC,numCm,numD,numF};
		
		int i = 0;
		while (i <= iexceeds) {
			exceeds += arrayGrades[i];
			i++;
		}
		while (i <= imeets) {
			meets += arrayGrades[i];
			i++;
		}
		while (i <= imarginal) {
			marginal += arrayGrades[i];
			i++;
		}
		while (i < arrayGrades.length) {
			fails += arrayGrades[i];
			i++;
		}
	}
	
	public int getNumAchievement(String level) {
		int num = 0;
		switch (level) {
			case "Exceeds": num = exceeds; break;
			case "Meets": num = meets; break;
			case "Marginal": num = marginal; break;	
			case "Fails": num = fails; break;
			default: System.out.println("level not available");
			 		 return -1;
		}
		return num;
	}
}