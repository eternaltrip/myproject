package com.me.JavaWork.learn.io.other;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 打印输出流
 * @author yangin
 *
 */

public class TestPrinterWriter {

	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream(new File("filename"));
		PrintStream ps = new PrintStream(fos);
		System.setOut(ps);//如果启用了打印输出，那么控制台就不会显示了
		
		for (int i = 0; i < 300; i++) {
			System.out.print(i+ " ") ;
			if(i % 50 == 0){
				System.out.println();
			}
		}
		
		ps.close();
	}

}
