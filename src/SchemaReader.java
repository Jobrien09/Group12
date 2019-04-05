package test;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
public class SchemaReader implements XLSXReader<Integer>{

	private String fileName;
	
	public SchemaReader(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public List<Integer> read() {
		List<Integer> schema = new ArrayList<>();
		try {
			FileInputStream excelFile = new FileInputStream(new File(fileName));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet sheet = workbook.getSheetAt(4);
			DataFormatter dataFormatter = new DataFormatter();
			
			// Getting achievement schema
			Cell cell1 = sheet.getRow(1).getCell(1);
			String exceeds = dataFormatter.formatCellValue(cell1); //exceeds
			schema.add(getRange(exceeds));
			
			Cell cell2 = sheet.getRow(2).getCell(1);
			String meets = dataFormatter.formatCellValue(cell2); //meets
			schema.add(getRange(meets));
			
			Cell cell3 = sheet.getRow(3).getCell(1);
			String marginal = dataFormatter.formatCellValue(cell3); //marginal
			schema.add(getRange(marginal));
			
			//System.out.println("E = " + exceeds + " M = " + meets + " M = " + marginal + " F = " + fails);
			
			// Getting rank schema
			Cell cell5 = sheet.getRow(1).getCell(4);
			int freshman = (int) Math.round(cell5.getNumericCellValue());
			schema.add(freshman);
			Cell cell6 = sheet.getRow(2).getCell(4);
			int sophomore = (int) Math.round(cell6.getNumericCellValue());
			schema.add(sophomore);
			Cell cell7 = sheet.getRow(3).getCell(4);
			int junior = (int) Math.round(cell7.getNumericCellValue());
			schema.add(junior);
			Cell cell8 = sheet.getRow(4).getCell(4);
			int senior = (int) Math.round(cell8.getNumericCellValue());
			schema.add(senior);
			
			//System.out.println("F = " + freshman + " S = " + sophomore + " J = " + junior + " S = " + senior);
			
			workbook.close();
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return schema;
	}
	
	private int getRange(String achievement) {
		int num = 0;
		switch (achievement) {
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
}