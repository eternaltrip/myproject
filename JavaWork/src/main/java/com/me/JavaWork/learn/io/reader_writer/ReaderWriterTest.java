package com.me.JavaWork.learn.io.reader_writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**（字符流）
 * 读文档中的内容要用到reader。
 * 写信息到文件中去要用writer
 * 字符流不能用来处理文件。如果强行处理，那么文件将不能正常使用（这也是为什么有字节流专门用来处理文件的原因）
 * 
 */
public class ReaderWriterTest {
	
	
	public static void main(String[] args) {
		
		System.out.println(ReaderWriterTest.class.getResource("").getPath());
		testFileReader();
		copy();
	}


	public static void testFileReader(){
		
		String pathname = ReaderWriterTest.class.getResource("").getPath() + "readerwriter.txt";
		File file = new File(pathname);
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
			char[] chars = new char[256];
			int len;
			while( (len = fileReader.read(chars)) != -1){
				String str = new String(chars , 0 , len);
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fileReader != null){
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
	
	public static void copy(){
		String pathname = ReaderWriterTest.class.getResource("").getPath();
		
		String filename1 = pathname + "readerwriter.txt";
		String filename2 = pathname + "readerwriter_copy.txt";
		
//		String filename1 =  "io.jpg";
//		String filename2 =  "iocopy.jpg";
		File file1 = new File(filename1);
		File file2 = new File(filename2);
		FileReader fileReader = null;
		FileWriter fileWriter = null;
		
		try {
			fileReader = new FileReader(file1);
			fileWriter = new FileWriter(file2);
			
			char[] chars = new char[256];
			int len;
			while( (len = fileReader.read(chars)) != -1){
				fileWriter.write(chars, 0, len);
				System.out.println(new String(chars, 0 ,len));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fileReader != null){
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fileWriter != null){
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	

}
