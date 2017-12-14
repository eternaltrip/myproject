package com.me.JavaWork.learn.io.file;

import java.io.File;
import java.util.Date;

public class ModifyFileUpdateDate {
	 public static void main(String[] args) throws Exception {
	        File fileToChange = new File("C:/myjavafile.txt");
	        fileToChange.createNewFile();
	        Date filetime = new Date(fileToChange.lastModified());
	        System.out.println(filetime.toString());
	        System.out.println(fileToChange.setLastModified(System.currentTimeMillis()));
	        filetime = new Date(fileToChange.lastModified());
	        System.out.println(filetime.toString());
	    }
}
