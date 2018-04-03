package com.utils;

import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

public class WalkerClassUtils
{

	public static String fileTimeToString(FileTime fileTime)
	{
		 SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
	     String dateCreatedString = df.format(fileTime.toMillis());
	     return dateCreatedString;
	}
	
	public static Date fileTimeToDate(FileTime fileTime) 
	{
		String ftime = fileTime.toString();
		
		return StringToDate(ftime);
	}
	
	public static Date StringToDate(String string) 
	{
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddH:m:sZ");
		Date date = null;
		try {
			date = formatter.parse(string);
		} catch (ParseException e) {
			
			date = new Date();
		}
		return date;
	}
	
	/**
	 * 
	 * @param attrs
	 * @return String fileType
	 */ 
	public static String checkDocumentType(BasicFileAttributes attrs )
	{
		
		String fileType = "UNKNOWN";
		if(attrs.isOther()== true)
		{
			fileType = "OTHER";
			
		}
		else if(attrs.isDirectory() == true)
		{
			fileType = "DIRECTORY";
		}
		else if(attrs.isRegularFile() == true)
		{
			fileType = "FILE";
		}
		else if(attrs.isSymbolicLink() == true)
		{
			fileType = "SYMBOLICLINK";
		}
		 
		return fileType;
	}
}
