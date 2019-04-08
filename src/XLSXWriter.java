package test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class XLSXWriter<T>{
	
	private String fileName;
	private XSSFWorkbook workbook;
	
	public abstract void writeWorkbook(List<T> list);
	
	public XLSXWriter(String fileName, XSSFWorkbook workbook) {
		this.fileName = fileName;
		this.workbook = workbook;
	}
	
	public XSSFWorkbook getWorkbook() {
		return workbook;
	}
	
	public void writeToExcel(){
		try {
	       	FileOutputStream outputStream = new FileOutputStream(fileName);
	       	workbook.write(outputStream);
	       	outputStream.close();
	       	workbook.close();
	    } catch (FileNotFoundException e) {
	       	e.printStackTrace();
	    } catch (IOException e) {
	       	e.printStackTrace();
	    }	
	}

}
