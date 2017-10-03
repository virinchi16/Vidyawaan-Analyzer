package org.virinchi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class SecSet {
	HashMap<String, String> stu=new HashMap<>();
	public String SecSet(String S) throws FileNotFoundException, IOException{
		HSSFWorkbook wb=new HSSFWorkbook(new FileInputStream("D://Biometric/Temp/stulist.xls"));
		Sheet sheet=wb.getSheetAt(0);
		Iterator<Row> itr=sheet.rowIterator();
		while(itr.hasNext()){
			Row r=itr.next();
			stu.put(r.getCell(0).getStringCellValue(),r.getCell(1).getStringCellValue());
		}
	if(stu.get(S)==null){
		return "no";
	}
	else
		return stu.get(S);
	}
}
