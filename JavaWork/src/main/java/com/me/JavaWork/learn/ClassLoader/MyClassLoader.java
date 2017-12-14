package com.me.JavaWork.learn.ClassLoader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyClassLoader extends ClassLoader{
	public static void main(String[] args) throws IOException {
		String srcPath = args[0];
		String destDir = args[1];
		FileInputStream fileInputStream = new FileInputStream(srcPath);
		String destFileName = srcPath.substring(srcPath.lastIndexOf("\\") + 1);//获取文件名
		String destPath = destDir + "\\" + destFileName;
		
		FileOutputStream fileOutputStream = new FileOutputStream(destPath);
		cypher(fileInputStream, fileOutputStream);
		fileInputStream.close();
		fileOutputStream.close();
	}
	
	
	
	
	//简单的加密和解密
	private static void cypher(InputStream inputStream, OutputStream outputStream ) throws IOException{
		int b = -1;
		while( (b = inputStream.read() ) != -1 ){
			outputStream.write(b ^ 0xff);
		}
		
	}

//	private String classDir;
//	
//	@SuppressWarnings("deprecation")
//	@Override
//	protected Class<?> findClass(String name) throws ClassNotFoundException {
//		
//		try {
//			String classFileName = classDir + "\\" + name + ".class";
//			FileInputStream fileInputStream = new FileInputStream(classFileName);
//			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
//			cypher(fileInputStream , arrayOutputStream);
//			fileInputStream.close();
//			byte[] bytes = arrayOutputStream.toByteArray();
//			return defineClass(bytes, 0, bytes.length);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return super.findClass(name);
//	}
//	
//	
//	public MyClassLoader(){}
//	
//	public MyClassLoader(String classDir){
//		this.classDir = classDir;
//	}

}
