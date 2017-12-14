package com.me.JavaWork.learn.io.file;

import java.io.File;

public class SetFileReadOnly {
	 public static void main(String[] args) {
	        File file = new File("C:/java.txt");
	        if(file.exists()){
	        	System.out.println(file.setReadOnly());
	        }else{
	        	System.out.println("file is not exist");
	        }
	        
	        System.out.println(file.canWrite());
	    }
}
