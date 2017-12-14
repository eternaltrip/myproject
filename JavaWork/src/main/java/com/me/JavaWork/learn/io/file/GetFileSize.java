package com.me.JavaWork.learn.io.file;

import java.io.File;

public class GetFileSize {
	public static long getFileSize(String filename) {
		File file = new File(filename);
		if (!file.exists() || !file.isFile()) {
			System.out.println("文件不存在");
			return -1;
		}
		return file.length();
	}

	public static void main(String[] args) {
		long size = getFileSize("c:/java.txt");
		System.out.println("java.txt�ļ���СΪ: " + size);
	}
}
