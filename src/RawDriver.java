import java.util.List;
public class RawDriver {

	public static void main(String[] args) {
		TranscriptReader tr = new TranscriptReader("/home1/ugrads/msong5/second/CS2043/transcript/");
		tr.createLists();
		List<Course> courseList = tr.getCourseList();
		RawWriter.writeRawDist(courseList);
		List<String[]> rawList = tr.getRawList();
		RawWriter.writeRawList(rawList);
	}

}
