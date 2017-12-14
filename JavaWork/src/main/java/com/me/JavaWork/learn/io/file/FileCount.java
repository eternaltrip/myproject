package com.me.JavaWork.learn.io.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileCount {
	public static void main(String[] args) {
		
		File file = new File("E:\\music/Taylor Swift-Safe And Sound.ape");
		InputStream reader = null;
		try {
			reader = new FileInputStream(file);
			int count = 0;
			long beginTime = System.currentTimeMillis();
			while (reader.read() != -1) {
				count ++;
			}
			long endTime = System.currentTimeMillis();
			System.out.println(count+"个字节,使用reader.read()统计耗时： " +(endTime - beginTime) /1000 + "s");
			
			
			beginTime = System.currentTimeMillis();
			reader = new FileInputStream(file);
			count = 0;
			byte[] b = new byte[1024];
			while (reader.read(b) != -1) {
				//System.out.println(b.length);
				count +=b.length;
			}
			endTime = System.currentTimeMillis();
			System.out.println(count+"个字节,使用reader.read(byte[])统计耗时： " +(endTime - beginTime) /1000 + "s");
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	

}
