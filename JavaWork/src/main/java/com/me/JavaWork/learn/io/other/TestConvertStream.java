package com.me.JavaWork.learn.io.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * @author yangin
 *编码
 */
public class TestConvertStream {
	public static void main(String[] args) throws Exception {
//		testInputStreamReader();
		
		testSystemIn();
	}
	
	
	public static void testInputStreamReader() throws Exception{
		
		File file = new File("D:\\eclipseWorkSpace\\Javatest\\src\\com\\io\\reader_writer\\readerwriter.txt");
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis , "GBK");
		BufferedReader br = new BufferedReader(isr);
		
		
		File fileout = new File("D:\\eclipseWorkSpace\\Javatest\\src\\com\\io\\reader_writer\\readerwriter_copy.txt");
		FileOutputStream fos = new FileOutputStream(fileout);
		OutputStreamWriter osw = new OutputStreamWriter(fos , "GBK");
		BufferedWriter bw = new BufferedWriter(osw);
		
		char[] chars = new char[1024];
		int len;
		while((len = br.read(chars)) != -1){
			bw.write(chars , 0 ,len );
			bw.flush();
		}
		br.close();
		bw.close();
	}
	
	//标准的输入
	public static void testSystemIn(){
		InputStream is = System.in;
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		try {
			while(true){
				String str = br.readLine();
				if(str.equalsIgnoreCase("e")){
					br.close();
					break;
				}
				str = str.toUpperCase();
				System.out.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
		
	}
	
	

}
