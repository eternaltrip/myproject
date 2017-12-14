package com.me.JavaWork.learn.io.buffered;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class BufferedTest {
	public static void main(String[] args) {
		testBufferedInputOutputStream();
		BufferedReader();
	}
	
	
	

	public static void BufferedReader(){
		String filename1 = new String("D:\\eclipseWorkSpace\\Javatest\\src\\com\\io\\reader_writer\\readerwriter.txt");
		
		FileReader fr = null ;
		BufferedReader br = null;
		try {
			File file1 = new File(filename1);
			fr = new FileReader(file1);
			br = new BufferedReader(fr);
			
			
			//方式1
//			char[] b = new char[1024];
//			int len;
//			while( (len = br.read(b)) != -1){
//				System.out.print(new String(b ,0 ,len));
//			}
//			System.out.println("结束");
			
			
			//方式2
			
			String str = null;
			while( (str = br.readLine() ) != null){
				System.out.println(str);
			}
			System.out.println("结束");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void testBufferedInputOutputStream(){
		
//		String filename1 = new String("D:\\eclipseWorkSpace\\Javatest\\src\\com\\io\\desc.txt");
//		String filename2 = new String("D:\\eclipseWorkSpace\\Javatest\\src\\com\\io\\desc_copy.txt");
		
		String filename1 = new String("io.jpg");
		String filename2 = new String("iocopy.jpg");
		FileInputStream fis = null ;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			File file1 = new File(filename1);
			File file2 = new File(filename2);
			
			fis = new FileInputStream(file1);
			fos = new FileOutputStream(file2);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
			
			byte[] b = new byte[1024];
			int len;
			while( (len = bis.read(b)) != -1){
				bos.write(b , 0 , len);
				bos.flush();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(bis !=null){
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
		
	}
}
