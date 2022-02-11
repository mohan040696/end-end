package com.resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class DataDriven extends GenericLibrary{
	
	public static HashMap<String, String> storevalue = new HashMap<String, String>();

	public static List<HashMap<String, String>> readDataExcel(String filepath, String sheetname) {

		List<HashMap<String, String>> mydata = new ArrayList<>();
		try {
			FileInputStream file = new FileInputStream(filepath);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(sheetname);
			Row row = sheet.getRow(0);
			for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
				Row currentrow = sheet.getRow(i);
				HashMap<String, String> currenthash = new HashMap<String, String>();
				for (int j = 0; j < currentrow.getPhysicalNumberOfCells(); j++) {
					Cell currentcel = currentrow.getCell(j);
					if (currentcel != null) {
						DataFormatter formatter = new DataFormatter(); // creating formatter using the default local
						String j_username = formatter.formatCellValue(currentcel);
						currenthash.put(row.getCell(j).getStringCellValue(), currentcel.getStringCellValue());
					}
				}
				mydata.add(currenthash);
			}
			file.close();
		} catch (Exception e) {

		}
		return mydata;
	}
public static int getColNum(String filepath , String sheetname,String colName) {
	int colnum = 0;
	try {
		FileInputStream input = new FileInputStream(filepath);
		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet sheet = workbook.getSheet(sheetname);
		Row row = sheet.getRow(0);
		for(int i = 0;i<=row.getPhysicalNumberOfCells();i++) {
			Cell getCurrentCell = row.getCell(i);
			if(getCurrentCell.getStringCellValue().equalsIgnoreCase(colName)) {
				colnum = getCurrentCell.getColumnIndex();
				break;
			}
		}
		input.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return colnum;

}
	public static void updateToExcel(String filepath, String sheetname, int rownum,String colName, String value) {
		try {
			FileInputStream input = new FileInputStream(filepath);
			XSSFWorkbook workbook = new XSSFWorkbook(input);
			XSSFSheet sheet = workbook.getSheet(sheetname);
			Cell cell = null;
			cell = sheet.getRow(rownum).getCell(getColNum(filepath, sheetname,colName));
			cell.setCellValue(value);
			input.close();
			FileOutputStream output = new FileOutputStream(filepath);
			workbook.write(output);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
