package test;
import java.util.List;
import java.util.Iterator;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RawEquivalenceReader implements XLSXReader<String[]>{

	private String fileName;
	private List<String[]> rawList;
	
	public RawEquivalenceReader(String fileName, List<String[]> rawList) {
		this.fileName = fileName;
		this.rawList = rawList;
	}
	
	//reads Equivalence tab and change any course number that exists in the tab to master course number
	@Override
	public List<String[]> read() {
		try {
			FileInputStream excelFile = new FileInputStream(new File(fileName));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet sheet = workbook.getSheetAt(2);
			DataFormatter dataFormatter = new DataFormatter();
			
			Iterator<Row> it = sheet.iterator();
			Row row = it.next();
			String masterCourseNum = "";
			for (int rowI = 0; rowI < row.getLastCellNum(); rowI++) {
				int firstRow = 0;
				for (Row row2 : sheet) {
					Cell cell = row2.getCell(rowI);
					String cellValue = dataFormatter.formatCellValue(cell);
					if (firstRow == 0) {
						masterCourseNum = cellValue;
						firstRow++;
						continue;
					}
					changeCourseNum(rawList, cellValue, masterCourseNum);			
				}
			}
			workbook.close();
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return rawList;
	}
	
	private void changeCourseNum(List<String[]> rawList, String courseNum, String masterCourseNum) {
		for (String[] course : rawList) {
			String cNum = course[0];
			if (cNum.equals(courseNum)) {
				if (!existsInRawList(rawList, masterCourseNum))
					course[0] = masterCourseNum;
				else 
					rawList.remove(course);
				
				break;
			}
		}	
	}
	
	private boolean existsInRawList(List<String[]> rawList, String masterCourseNum) {
		boolean flag = false;
		for (String[] course : rawList) {
			if (course[0].equals(masterCourseNum)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}