package test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class TestDriver {

	public static void main(String[] args) throws IOException {
		
		String dirPath = "/home1/ugrads/dbest/cohort_1";
		String outfileName1 = "rawList.xlsx";
		String outfileName2 = "results_EE2015.xlsx";
		String infileName3 = "config.xlsx";
		
		XSSFWorkbook workbook1 = new XSSFWorkbook();
		XSSFWorkbook workbook2 = new XSSFWorkbook();
		
		//read transcripts and return 2 lists
		TranscriptReader tr = new TranscriptReader(dirPath);
		tr.read();		
		List<String[]> rawList = tr.getRawList();
		List<Course> courseList = tr.getCourseList();
		
		//read equivalence tab and return updated raw list with equivalences
		EquivalenceReader er = new EquivalenceReader(infileName3);
		courseList = er.read(courseList);
		
		//write raw list to excel
		XLSXWriter<String[]> w1 = new RawListWriter(outfileName1, workbook1);
		w1.writeWorkbook(rawList);
		w1.writeToExcel();
		
		//write raw distribution to the workbook
		XLSXWriter<Course> w2 = new RawDistWriter(outfileName2, workbook2);
		w2.writeWorkbook(courseList);		
		
		//read areas tab and return calculated area distribution list
		XLSXReader<Area, Course> ar = new AreaReader(infileName3);
		List<Area> areaLists = ar.read(courseList);
		
		XLSXWriter<Area> w3 = new AreaDistWriter(outfileName2, workbook2);	
		w3.writeWorkbook(areaLists);
		w3.writeToExcel();
		
	}
}