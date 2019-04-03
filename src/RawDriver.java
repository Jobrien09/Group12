import java.util.List;
public class RawDriver {

	public static void main(String[] args) {
		TranscriptReader tr = new TranscriptReader("\\Users\\Deon\\Documents\\transcripts");
		tr.createLists();
		List<Course> courseList = tr.getCourseList();
		RawWriter.writeRawDist(courseList);
		List<String[]> rawList = tr.getRawList();
		RawWriter.writeRawList(rawList);
		AreaWriter.writeAreaDist(courseList);
		Writer.writeOutput();
	}
}