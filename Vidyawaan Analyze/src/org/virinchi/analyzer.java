package org.virinchi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class analyzer {
public static void main(String args[]){
	try {
		FileInputStream fis=new FileInputStream("f://file/today.xls");
		Workbook wb=new HSSFWorkbook(fis);
		Vector<String> vid=new Vector<>();
		for(int i=0;i<wb.getNumberOfSheets();i++){
			Sheet sheet=wb.getSheetAt(i);
			Iterator<Row> itr=sheet.rowIterator();
			while(itr.hasNext()){
				Row row=itr.next();
				vid.add(row.getCell(0).getStringCellValue());
			}
		}
		fis.close();
		Iterator<String> itt=vid.iterator();
		HSSFWorkbook hwb=new HSSFWorkbook();
		FileOutputStream fos=new FileOutputStream("f://javaxml/newformat.xls");
		while(itt.hasNext()){
			Sheet she=hwb.createSheet(itt.next());
			she.createRow(0).createCell(0).setCellValue("hello");
		}
//		hwb.write(fos);
		fos.close();
		JOptionPane.showMessageDialog(null, "done");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}

private static boolean patt(String str,String pat) {
	boolean b = true;
	String s[]=pat.split("_");
	int k=-1;
	@SuppressWarnings("unused")
	int n=str.length();
	for(int i=0;i<s.length;i++){
		k++;
		if(!s[i].equals("_")){
			b=str.substring(k).startsWith(s[i]) & b;
		}
	}
	return b;
}
}
