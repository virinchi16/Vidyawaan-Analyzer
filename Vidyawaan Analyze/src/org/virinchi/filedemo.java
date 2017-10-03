package org.virinchi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class filedemo {
	public static void main(String args[]) throws IOException {
		HashMap<String, String> hm=new HashMap<>();
		JButton showwait=new JButton("please wait while I connect to Vidyawan... and Get Data");
		JPanel panel=new JPanel();
		panel.add(showwait);
		JFrame jf=new JFrame("Loading...");
		jf.getContentPane().add(panel);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		LocalDate d=LocalDate.now();
		String url="http://vidyawaan.nic.in/vidyawaan/vidya_stu_attendence_report.jsp";
		HSSFWorkbook hb=new HSSFWorkbook();
		hm.put("fromdate", "2017-07-22");
		hm.put("todate", "2017-07-22");
		hm.put("orgid", "2");
		hm.put("instcode","6536");
		hm.put("ctype", "S");
		hm.put("example_length", "-1");
		Sheet sheet=hb.createSheet("dataextracted");
		/*try {*/
			Document doc= Jsoup.connect(url).data(hm).get();
			jf.setVisible(false);
			showwait=new JButton("please wait i am writting to database");
			jf=new JFrame("Writing");
			jf.getContentPane().add(panel);
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.pack();
			jf.setLocationRelativeTo(null);
			jf.setVisible(true);
			System.out.println("connected");
			for (Element table : doc.select("table")) {
		        for(Element row : table.select("tr")){
		        	Row r=sheet.createRow(sheet.getLastRowNum()+1);
		        	int i=0;
		        	for(Element tds : row.select("td")){
		        		if(i==0){
		        			r.createCell(0).setCellValue(tds.text());
		        			//System.out.println(i+"+"+tds.text());
		        		}
		        		if(i==1){
		        			r.createCell(1).setCellValue(tds.text());
		        			//System.out.println(i+"+"+tds.text());
		        		}
		        		if(i==2){
		        			r.createCell(2).setCellValue(tds.text());
		        			//System.out.println(i+"+"+tds.text());
		        		}
		        		i++;
		        	}
		        }
		    }
			System.out.println("here1"+sheet.getRow(0));
			System.out.println("here2"+sheet.getRow(1));
			System.out.println("here3"+sheet.getRow(2));
			File f=new File("d://Biometric//Att");
			if(!f.exists()){
				f.mkdir();
			}
			hb.write(new FileOutputStream("d://Biometric/Att/AllData.xls"));
			jf.setVisible(false);
			//JOptionPane.showMessageDialog(null, "fetched");
			AttendenceAnalyzer2 callatt=new AttendenceAnalyzer2();
			callatt.main(null);
		/*} *//*catch (IOException e) {
			jf.setVisible(false);
			JOptionPane.showMessageDialog(null, "V-tech Says "+e.getMessage());
		} catch (Exception e) {
			jf.setVisible(false);
			JOptionPane.showMessageDialog(null, "V-tech Says "+e.getMessage());
		}*/
	}

}
