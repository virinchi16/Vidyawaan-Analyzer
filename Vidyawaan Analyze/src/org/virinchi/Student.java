package org.virinchi;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Student {
	String id,fn,an,sName,time,Section;
	int abs;
	public int getAbs() {
		return abs;
	}

	public String getSection() {
		return Section;
	}

	{id="0";fn="0";
	an="0";time="0";}
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
		if(this.time.startsWith(":"))
			this.abs=1;
		if(this.time.replaceAll(" ", "").length()>1)
			this.fn=this.time.replaceAll(" ", "").substring(0, 5);
		if(this.time.replaceAll(" ", "").length()>6)
			this.an=this.time.replaceAll(" ", "").substring(9);
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) throws FileNotFoundException, IOException {
		this.id = id;
		SecSet s=new SecSet();
		if(s.SecSet(this.id).equals("no")){	
			this.Section="dummy";
		}
		else
			this.Section=s.SecSet(this.id);
	}

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	public String getAn() {
		return an;
	}

	public void setAn(String an) {
		this.an = an;
	}
	
}
