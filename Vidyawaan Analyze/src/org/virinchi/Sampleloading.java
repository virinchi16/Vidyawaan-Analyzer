package org.virinchi;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Sampleloading {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JButton showwait=new JButton("please wait...");
		JPanel panel=new JPanel();
		panel.add(showwait);
		JFrame jf=new JFrame("Loading...");
		jf.getContentPane().add(panel);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}

}
