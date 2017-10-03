package org.virinchi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class AttendenceAnalyzer2 {
	public static void main(String args[]) {
		JButton showwait=new JButton("Please Wait I am writing to Book");
		JPanel panel=new JPanel();
		panel.add(showwait);
		JFrame jf=new JFrame("LENDI _ R&D ");
		jf.getContentPane().add(panel);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		FileInputStream fs;
		try {
			fs = new FileInputStream("D://Biometric/Att/alldata.xls");
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			Sheet s1=wb.getSheetAt(0);
			Iterator<Row> itr=s1.iterator();
			Student S;
			Vector<Student> v=new Vector<>();
			while(itr.hasNext()){
				System.out.println("1");
				S=new Student();
				Row r=itr.next();
				String str=s1.getRow(r.getRowNum()).getCell(1).toString();
				String id=new StringBuffer(new StringBuffer(str).reverse().substring(0, 10).toString()).reverse().toString();
				String name=new StringBuffer(new StringBuffer(str).reverse().substring(10).toString()).reverse().toString();
				S.setsName(name);
				S.setId(id);
				S.setTime(s1.getRow(r.getRowNum()).getCell(2).toString());
				v.add(S);
			}
			
			FileOutputStream fo=new FileOutputStream("D://Biometric/Att/Dept_wise.xls");
			HSSFWorkbook hwb=new HSSFWorkbook();
			Sheet cse=hwb.createSheet("CSE");
			Sheet mech=hwb.createSheet("MECH");
			Sheet ece=hwb.createSheet("ECE");
			Sheet eee=hwb.createSheet("EEE");
			Iterator<Student> it=v.iterator();
			Vector<Student> Cse=new Vector<Student>();
			Vector<Student> Mech=new Vector<Student>();
			Vector<Student> Eee=new Vector<Student>();
			Vector<Student> Ece=new Vector<Student>();
			Vector<Student> all=new Vector<>();
			int c=0,e=0,ee=0,m=0;
			int computers = 0,electricals = 0,electronics = 0,mechanics = 0;
			Row r;
			while(it.hasNext())
			{
				Student Ss=it.next();
				StringBuffer sb=new StringBuffer(Ss.getId());
				char D=sb.charAt(7);
				String id=new String(sb);
				if(D=='5'){
					r=cse.createRow(c);
					r.createCell(0).setCellValue(id);
					if(Ss.getAbs()==1){
						computers++;
					}
					r.createCell(1).setCellValue(Ss.getsName().replaceAll(" ", ""));
					r.createCell(2).setCellValue(Ss.getFn());
					r.createCell(3).setCellValue(Ss.getAn());
					Cse.add(Ss);
					c++;
				}
				if(D=='4'){
					r=ece.createRow(e);
					r.createCell(0).setCellValue(id);
					if(Ss.getAbs()==1){
						electronics++;
					}
					r.createCell(1).setCellValue(Ss.getsName().replaceAll(" ", ""));
					r.createCell(2).setCellValue(Ss.getFn());
					r.createCell(3).setCellValue(Ss.getAn());
					Ece.add(Ss);
					e++;
				}
				if(D=='2'){
					r=eee.createRow(ee);
					r.createCell(0).setCellValue(id);
					if(Ss.getAbs()==1){
						electricals++;
					}
					r.createCell(1).setCellValue(Ss.getsName().replaceAll(" ", ""));
					r.createCell(2).setCellValue(Ss.getFn());
					r.createCell(3).setCellValue(Ss.getAn());
					Eee.add(Ss);
					ee++;
				}
				if(D=='3'){
					r=mech.createRow(m);
					r.createCell(0).setCellValue(id);
					if(Ss.getAbs()==1){
						mechanics++;
					}
					r.createCell(1).setCellValue(Ss.getsName().replaceAll(" ", ""));
					r.createCell(2).setCellValue(Ss.getFn());
					r.createCell(3).setCellValue(Ss.getAn());
					Mech.add(Ss);
					m++;
				}
			}
			all.addAll(Cse);
			all.addAll(Ece);
			all.addAll(Mech);
			all.addAll(Eee);
			hwb.write(fo);
			fo.close();
			jf.setVisible(false);
			JOptionPane.showMessageDialog(null, "V-File Processings have sucessfully processed and stored to files");
			JOptionPane.showMessageDialog(null, "CSE-"+computers+"/"+c+" ECE-"+electronics+"/"+e+" EEE-"+electricals+"/"+ee+" Mech-"+mechanics+"/"+m, "Absents on "+java.time.LocalDate.now(), 0);
			StudentSectionSeparater ssd=new StudentSectionSeparater(v);
		}
		catch (Exception e) {
			jf.setVisible(false);
			/*JOptionPane.showMessageDialog(null, "V-tech Says "+e.getStackTrace());*/
			e.printStackTrace();
		}
		
	}
}
