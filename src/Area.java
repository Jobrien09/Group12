import java.util.List;

public class Area {
	private List<Course> areaList;
	private String name;

	private int numA;
	private int numB;
	private int numC;
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
			numA += course.getNumGrades("A");
			numB += course.getNumGrades("B");
			numC += course.getNumGrades("C");
			numD += course.getNumGrades("D");
			numF += course.getNumGrades("F");
			numO += course.getNumGrades("O");
		}
		/**System.out.println(name);
		System.out.println(areaList.size());
		System.out.print(numA + " "+ numB + " "+ numC + " "+ numD + " "+ numF);
		System.out.println();**/
	}
	
	public int getNumGrades(String grade) {
		int num = 0;
		switch (grade) {
			case "A":	num = numA; break;
			case "B":	num = numB; break;
			case "C":	num = numC; break;
			case "D":	num = numD; break;
			case "F":	num = numF; break;
			case "other": num = numO; break;
			default :	System.out.println("Grade not within range4");
						return -1;
		}
		return num;
	}
	
	public int getNumAchievement(String level) {
		int num = 0; 									
		switch (level) {								
			case "Exceeds": num = numA; break;	
			case "Meets": num = numB; break;
			case "Marginal": num = numC; break;
			case "Fail": num = numD + numF; break;
			case "Other" : num = numO; break;
			default: System.out.println("level not available");
					 return -1;
		}
		return num;
	}	
}