import java.util.List;

public class Area {
	private List<Course> areaList;
	private String name;

	private int numA, numAm, numAp;	
	private int numB, numBm, numBp;
	private int numC, numCm, numCp;
	private int numD;
	private int numF;
	private int numO;
	
	public Area(String name, AreaList a) {
		this.name = name;
		areaList = a.getList(name);

	}
	
	public String getAreaName() {
		return name;
	}
	
	public void calculateArea() {
		for (Course course : areaList) {
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
		}
		System.out.println(name);
		System.out.println(areaList.size());
		System.out.println();
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
			default :	System.out.println("Grade not within range3");
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
			case "Other": num = numO; break;
			default: System.out.println("level not available");
					 return -1;
		}
		return num;
	}
}