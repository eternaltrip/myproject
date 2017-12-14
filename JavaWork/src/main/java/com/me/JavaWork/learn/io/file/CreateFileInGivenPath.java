package com.me.JavaWork.learn.io.file;

import java.io.File;

public class CreateFileInGivenPath {
	public static void main(String[] args) throws Exception {
        File file = null;
        File dir = new File("C:/");
        file = File.createTempFile
        ("JavaTemp", ".javatemp", dir);
        System.out.println(file.getPath());
    }
}
