package org.virinchi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class StudentSectionSeparater {
	
	
	static Vector<Student> Vs=new Vector<>();
	public StudentSectionSeparater(Vector<Student> Sv) throws IOException{
		Vs=Sv;
		then();
	}
	public static void then() throws IOException {
		JButton showwait=new JButton("Please Wait ... last organizing");
		JPanel panel=new JPanel();
		panel.add(showwait);
		JFrame jf=new JFrame("LENDI _ R&D ");
		jf.getContentPane().add(panel);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		Comparator<Student> comp=new StudentComparator();
		Collections.sort(Vs, comp);
		Iterator<Student> str=Vs.iterator();
		HSSFWorkbook wb=new HSSFWorkbook();
		Sheet sheet;
		while(str.hasNext()){
			Student ss=(Student) str.next();
			if(wb.getSheet(ss.getSection())== null){
				sheet=wb.createSheet(ss.getSection());
			}
			else
				sheet=wb.getSheet(ss.getSection());
			Row row=sheet.createRow(sheet.getLastRowNum()+1);
			row.createCell(0).setCellValue(ss.getId());
			row.createCell(1).setCellValue(ss.getsName());
			if(!ss.getFn().equals("0"))
				row.createCell(2).setCellValue(ss.getFn().toString());
			if(!ss.getAn().equals("0"))
				row.createCell(3).setCellValue(ss.getAn().toString());
		}
		FileOutputStream fos=new FileOutputStream("D://Biometric/DayWiseAttendance/completeformatatt.xls");
		wb.write(fos);
		jf.setVisible(false);
		fos.close();
	}
}
