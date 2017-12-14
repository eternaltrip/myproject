package com.me.JavaWork.learn.io.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class CreateTempFile {
	public static void main(String[] args) throws Exception {
        File temp = File.createTempFile("pattern", ".suffix");
        temp.deleteOnExit();
        BufferedWriter out = new BufferedWriter(new FileWriter(temp));
        out.write("aString");
        System.out.println("临时文件已创建:");
        out.close();
    }
}
