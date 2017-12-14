package com.me.JavaWork.learn.io.input_outputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;




/**
 * 
 *字节流  inputstream   outputstream(处理文件这类)
 *字符流 reader  writer(处理文字这种，比如网络数据传输)
 *
 */
public class InputOutPutStreamTest {
	public static void main(String[] args) {
		
		fileOutPutStreamTest();
		fileInputStreamTest();
		copyFile();
	}
	//复制文件
	public static void copyFile(){
		File file1 = new File("filename");
		File file2 = new File("copyfile");
		
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileInputStream = new FileInputStream(file1);
			fileOutputStream = new FileOutputStream(file2);
			
			byte[] bytes = new byte[20];
			int len;
			while( (len = fileInputStream.read(bytes)) != -1){
				fileOutputStream.write(bytes, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(fileInputStream != null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(fileOutputStream != null){
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//读取文件数据
	public static void fileInputStreamTest(){
		File file;
		FileInputStream fileInputStream = null ;
		try {
			file = new File("filetest.txt");
			fileInputStream = new FileInputStream(file);
			
			byte[] b = new byte[10];
			int count ;
			while( ( count = fileInputStream.read(b)) != -1){
				//方式一
//				for (int i = 0; i < count; i++) {
//					System.out.print( b[i] );
//				}
//				方式二
				System.out.println(new String(b , 0 , count));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fileInputStream != null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//写数据到文件
	public static void fileOutPutStreamTest(){
		
		//如果文件不存在就会创建，如果存在就会覆盖
		File file = new File("filetest.txt");
		FileOutputStream fileOutputStream = null;
		try {
			
			fileOutputStream = new FileOutputStream(file);
			byte[] content = new String("this is a hard question to choose whether we go").getBytes();
			fileOutputStream.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(fileOutputStream != null){
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	

}
