package test;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class TestDriver {

	public static void main(String[] args) {
		XSSFWorkbook workbook1 = new XSSFWorkbook();
		XSSFWorkbook workbook2 = new XSSFWorkbook();
		
		String dirPath = "C:\\Users\\Deon\\Documents\\transcripts all";
		String outfileName1 = "rawList.xlsx";
		String outfileName2 = "results_EE2015.xlsx";
		String infileName3 = "config.xlsx";
		
		//read transcripts and return 2 lists
		TranscriptReader tr = new TranscriptReader(dirPath);
		List<Transcript> transcripts = tr.read();		
		List<String[]> rawList = tr.getRawList();
		List<Course> courseList = tr.sortCourseList();
		
		//read equivalence tab and return updated raw list with equivalences
		XLSXReader<String[]> er = new RawEquivalenceReader(infileName3, rawList);
		rawList = er.read();
		
		XLSXReader<Course> er2 = new CourseEquivalenceReader(infileName3, courseList);
		courseList = er2.read();
		courseList = tr.sortCourseList();
		
		//write raw list to excel
		XLSXWriter<String[]> w1 = new RawListWriter(outfileName1, workbook1);
		w1.writeWorkbook(rawList);
		w1.writeToExcel();
		
		//write raw distribution to the workbook
		XLSXReader<Integer> sr = new SchemaReader(infileName3);
		List<Integer> schema = sr.read();	
		
		//read areas tab and return calculated area distribution list
		AreaReader ar = new AreaReader(infileName3, courseList);		
		List<Area> areaLists = ar.read();
		
		XLSXWriter<Course> w2 = new RawDistWriter(outfileName2, workbook2, schema);
		w2.writeWorkbook(courseList);	
		
		//Write area distribution to area tab
		AreaDistWriter w3 = new AreaDistWriter(outfileName2, workbook2, schema);	
		w3.writeWorkbook(transcripts);
		w3.writeToExcel();			//close just once
	}
}