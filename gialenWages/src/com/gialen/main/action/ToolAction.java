package com.gialen.main.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.directwebremoting.io.FileTransfer;

public class ToolAction {

	
	public static String getThisYear(){
		SimpleDateFormat smdt = new SimpleDateFormat("yyyy");
		return smdt.format(new Date());
	}
	
	public static String getThisMonth() {
		SimpleDateFormat smdt = new SimpleDateFormat("yyyyMM");
		return smdt.format(new Date());
	}
	
	public static String getThisDate() {
		SimpleDateFormat smdt = new SimpleDateFormat("yyyy.MM.dd");
		return smdt.format(new Date());
	}
	
	
	
	
	
	
	//File upfiles
	public static String upload(InputStream upfiles){
		
		
		//good系文件路劲
		 //= new File(good);
		String fileName = null;
		BufferedInputStream bin;
		java.io.BufferedOutputStream bout;
		//upfiles;
		try {
			//bin =  new BufferedInputStream(new java.io.FileInputStream(upfiles));
			bin =  new BufferedInputStream(upfiles);
			//FileTransfer f = upfiles;
			fileName = "D:/"+new Random().nextInt(10000)+".xls";
			bout = new BufferedOutputStream(new java.io.FileOutputStream(fileName));
		
			int tmp=0;
			while(tmp!=-1){
				tmp = bin.read();
				bout.write(tmp);
			}
			bout.flush();
			bout.close();
			bin.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName; 
	}

	public static List<Date> getMyFormatDate(String operatingStartDate,
			String operatingEndDate) {
		
		SimpleDateFormat smdt  = new SimpleDateFormat("yyyy.MM.dd");
		
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = smdt.parse(operatingStartDate);
			endDate = smdt.parse(operatingEndDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<Date> dateList = new ArrayList<Date>();
		dateList.add(startDate);
		dateList.add(endDate);
		return dateList;
	}	
}
