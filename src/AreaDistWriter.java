package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AreaDistWriter extends XLSXWriter<Transcript>{
	private List<Integer> schema;
	
	public AreaDistWriter(String fileName, XSSFWorkbook workbook, List<Integer> schema) {
		super(fileName, workbook);
		this.schema = schema;
	}
	
	@Override
	public void writeWorkbook(List<Transcript> transcripts) {
		XSSFWorkbook workbook = super.getWorkbook();
		XSSFSheet areaSheet = workbook.createSheet("Area");

		// Creating font and style for top row 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

		String[] topRowTypes = {"Area", "", "Fails", "Marginal", "Meets", "Exceeds"};
        Row topRow = areaSheet.createRow(0);
        for (int i = 0; i < topRowTypes.length; i++) {
        	Cell cell = topRow.createCell(i);
        	cell.setCellValue(topRowTypes[i]);
        	cell.setCellStyle(style);
        }
        
        String[] areas = {"math", "science", "fundamental", "specialized", "terminal", "core", "cse", "society"};
        List<Integer> mathResults = calculateArea(areas[0], transcripts);
        List<Integer> sciResults = calculateArea(areas[1], transcripts);
        List<Integer> fundResults = calculateArea(areas[2], transcripts);
        List<Integer> specResults = calculateArea(areas[3], transcripts);
        List<Integer> termResults = calculateArea(areas[4], transcripts);
        List<Integer> coreResults = calculateArea(areas[5], transcripts);
        List<Integer> cseResults = calculateArea(areas[6], transcripts);
        List<Integer> socResults = calculateArea(areas[7], transcripts);
        
        // MATH ROW
        Row mathRow = areaSheet.createRow(1);
        Cell cell0 = mathRow.createCell(0);
        cell0.setCellValue("Math");
		Cell cell2 = mathRow.createCell(2);
		cell2.setCellValue(mathResults.get(3));
		Cell cell3 = mathRow.createCell(3);
		cell3.setCellValue(mathResults.get(2));
		Cell cell4 = mathRow.createCell(4);
		cell4.setCellValue(mathResults.get(1));
		Cell cell5 = mathRow.createCell(5);
		cell5.setCellValue(mathResults.get(0));
        
		// SCIENCE ROW
        Row sciRow = areaSheet.createRow(2);
        Cell cell01 = sciRow.createCell(0);
        cell01.setCellValue("Science");
		Cell cell21 = sciRow.createCell(2);
		cell21.setCellValue(sciResults.get(3));
		Cell cell31 = sciRow.createCell(3);
		cell31.setCellValue(sciResults.get(2));
		Cell cell41 = sciRow.createCell(4);
		cell41.setCellValue(sciResults.get(1));
		Cell cell51 = sciRow.createCell(5);
		cell51.setCellValue(sciResults.get(0));
        
		// FUNDAMENTAL ROW
        Row fundRow = areaSheet.createRow(3);
        Cell cell02 = fundRow.createCell(0);
        cell02.setCellValue("Fundamental");
		Cell cell22 = fundRow.createCell(2);
		cell22.setCellValue(fundResults.get(3));
		Cell cell32 = fundRow.createCell(3);
		cell32.setCellValue(fundResults.get(2));
		Cell cell42 = fundRow.createCell(4);
		cell42.setCellValue(fundResults.get(1));
		Cell cell52 = fundRow.createCell(5);
		cell52.setCellValue(fundResults.get(0));
        
		// SPECIALIZE ROW
        Row specRow = areaSheet.createRow(4);
        Cell cell03 = specRow.createCell(0);
        cell03.setCellValue("Specialized");
		Cell cell23 = specRow.createCell(2);
		cell23.setCellValue(specResults.get(3));
		Cell cell33 = specRow.createCell(3);
		cell33.setCellValue(specResults.get(2));
		Cell cell43 = specRow.createCell(4);
		cell43.setCellValue(specResults.get(1));
		Cell cell53 = specRow.createCell(5);
		cell53.setCellValue(specResults.get(0));
        
		// TERMIAL ROW
        Row termRow = areaSheet.createRow(5);
        Cell cell04 = termRow.createCell(0);
        cell04.setCellValue("Terminal");
		Cell cell24 = termRow.createCell(2);
		cell24.setCellValue(termResults.get(3));
		Cell cell34 = termRow.createCell(3);
		cell34.setCellValue(termResults.get(2));
		Cell cell44 = termRow.createCell(4);
		cell44.setCellValue(termResults.get(1));
		Cell cell54 = termRow.createCell(5);
		cell54.setCellValue(termResults.get(0));
        
		// CORE ROW
        Row coreRow = areaSheet.createRow(6);
        Cell cell05 = coreRow.createCell(0);
        cell05.setCellValue("Core");
		Cell cell25 = coreRow.createCell(2);
		cell25.setCellValue(coreResults.get(3));
		Cell cell35 = coreRow.createCell(3);
		cell35.setCellValue(coreResults.get(2));
		Cell cell45 = coreRow.createCell(4);
		cell45.setCellValue(coreResults.get(1));
		Cell cell55 = coreRow.createCell(5);
		cell55.setCellValue(coreResults.get(0));
        
		// CSE ROW
        Row cseRow = areaSheet.createRow(7);
        Cell cell06 = cseRow.createCell(0);
        cell06.setCellValue("CSE");
		Cell cell26 = cseRow.createCell(2);
		cell26.setCellValue(cseResults.get(3));
		Cell cell36 = cseRow.createCell(3);
		cell36.setCellValue(cseResults.get(2));
		Cell cell46 = cseRow.createCell(4);
		cell46.setCellValue(cseResults.get(1));
		Cell cell56 = cseRow.createCell(5);
		cell56.setCellValue(cseResults.get(0));
        
		// SOCIETY ROW
        Row socRow = areaSheet.createRow(8);
        Cell cell07 = socRow.createCell(0);
        cell07.setCellValue("Society");
		Cell cell27 = socRow.createCell(2);
		cell27.setCellValue(socResults.get(3));
		Cell cell37 = socRow.createCell(3);
		cell37.setCellValue(socResults.get(2));
		Cell cell47 = socRow.createCell(4);
		cell47.setCellValue(socResults.get(1));
		Cell cell57 = socRow.createCell(5);
		cell57.setCellValue(socResults.get(0));
        
        // Formatting column width
        areaSheet.autoSizeColumn(0);
        System.out.println("Done3");
	}
	
	public List<Integer> calculateArea(String area, List<Transcript> transcripts) {
		List<Integer> results = new ArrayList<>();
		int iexceeds = schema.get(0);
		int imeets = schema.get(1);
		int imarginal = schema.get(2);
        int exceeds = 0;
        int meets = 0;
        int marginal = 0;
        int fails = 0;
        
		int numAp = 0,numA= 0,numAm= 0,numBp= 0,numB= 0,numBm= 0,numCp= 0,numC= 0,numCm= 0,numD= 0,numF= 0;
		
		for(int i=0; i<transcripts.size();i++) {
	    	if(transcripts.get(i).getAreaGrade(area) == "A+"){
	    		numAp++;
	    	}else if(transcripts.get(i).getAreaGrade(area) == "A"){
	    		numA++;
	    	}else if(transcripts.get(i).getAreaGrade(area) == "A-"){
	    		numAm++;
	    	}else if(transcripts.get(i).getAreaGrade(area) == "B+"){
	    		numBp++;
	    	}else if(transcripts.get(i).getAreaGrade(area) == "B"){
	    		numB++;
	    	}else if(transcripts.get(i).getAreaGrade(area) == "B-"){
	    		numBm++;
	    	}else if(transcripts.get(i).getAreaGrade(area) == "C+"){
	    		numCp++;
	    	}else if(transcripts.get(i).getAreaGrade(area) == "C"){
	    		numC++;
	    	}else if(transcripts.get(i).getAreaGrade(area) == "D"){
	    		numD++;
	    	}else if(transcripts.get(i).getAreaGrade(area) == "F"){
	    		numF++;
	    	}
		}
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

		results.add(exceeds);
		results.add(meets);
		results.add(marginal);
		results.add(fails);
		
		return results;
	}
	
}
