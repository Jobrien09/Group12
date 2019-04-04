package test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Schema {
	private static final String SAMPLE = "config.xlsx";
	private static String exceeds = "A-";
	private static String meets = "B-";
	private static String marginal = "C";
	private static String fails = "F";
	private static int freshman;
	private static int sophomore;
	private static int junior;
	private static int senior;
	
	
//	private String fileName;
//	public Schema(String fileName) {
//		this.fileName = fileName;
//	}
//	
	public static void read() {
		try {
			FileInputStream excelFile = new FileInputStream(new File(SAMPLE));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet sheet = workbook.getSheetAt(4);
			DataFormatter dataFormatter = new DataFormatter();
			
			// Getting achievement schema
			Cell cell1 = sheet.getRow(1).getCell(1);
			exceeds = dataFormatter.formatCellValue(cell1);
			Cell cell2 = sheet.getRow(2).getCell(1);
			meets = dataFormatter.formatCellValue(cell2);
			Cell cell3 = sheet.getRow(3).getCell(1);
			marginal = dataFormatter.formatCellValue(cell3);
			Cell cell4 = sheet.getRow(4).getCell(1);
			fails = dataFormatter.formatCellValue(cell4);
			
			//System.out.println("E = " + exceeds + " M = " + meets + " M = " + marginal + " F = " + fails);
			
			// Getting rank schema
			Cell cell5 = sheet.getRow(1).getCell(4);
			freshman = (int) Math.round(cell5.getNumericCellValue());
			Cell cell6 = sheet.getRow(2).getCell(4);
			sophomore = (int) Math.round(cell6.getNumericCellValue());
			Cell cell7 = sheet.getRow(3).getCell(4);
			junior = (int) Math.round(cell7.getNumericCellValue());
			Cell cell8 = sheet.getRow(4).getCell(4);
			senior = (int) Math.round(cell8.getNumericCellValue());
			
			//System.out.println("F = " + freshman + " S = " + sophomore + " J = " + junior + " S = " + senior);
			
			workbook.close();
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static int getExceeds() {
		int num = 0;
		switch (exceeds) {
			case "A+":	num = 0; break;
			case "A":	num = 1; break;
			case "A-":	num = 2; break;
			case "B+":	num = 3; break;
			case "B":	num = 4; break;
			case "B-":	num = 5; break;
			case "C+":	num = 6; break;
			case "C":	num = 7; break;
			case "C-":	num = 8; break;
			case "D":	num = 9; break;
			case "F":	num = 10; break;
			default :	System.out.println("Grade not within range5");
						return -1;
		}
		return num;
	}
	
	public static int getMeets() {
		int num = 0;
		switch (meets) {
			case "A+":	num = 0; break;
			case "A":	num = 1; break;
			case "A-":	num = 2; break;
			case "B+":	num = 3; break;
			case "B":	num = 4; break;
			case "B-":	num = 5; break;
			case "C+":	num = 6; break;
			case "C":	num = 7; break;
			case "C-":	num = 8; break;
			case "D":	num = 9; break;
			case "F":	num = 10; break;
			default :	System.out.println("Grade not within range5");
						return -1;
		}
		return num;
	}
	
	public static int getMarginal() {
		int num = 0;
		switch (marginal) {
			case "A+":	num = 0; break;
			case "A":	num = 1; break;
			case "A-":	num = 2; break;
			case "B+":	num = 3; break;
			case "B":	num = 4; break;
			case "B-":	num = 5; break;
			case "C+":	num = 6; break;
			case "C":	num = 7; break;
			case "C-":	num = 8; break;
			case "D":	num = 9; break;
			case "F":	num = 10; break;
			default :	System.out.println("Grade not within range5");
						return -1;
		}
		return num;
	}
	
	public static int getFreshman() {
		return freshman;
	}
	
	public static int getSophomore() {
		return sophomore;
	}
	
	public static int getJunior() {
		return junior;
	}
	
	public static int getSenior() {
		return senior;
	}

}