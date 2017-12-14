package com.me.JavaWork.learn.io.other;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.me.JavaWork.learn.Pojo;


/**
 * 对象流，可以把对象写出或者读入。（*一般传输会选择json。所以这种用到的不多）
 * @author yangin
 *
 */
public class TestObjectStream {
	public static void main(String[] args) throws Exception {
		testOjectOut();
		testObjectIn();
	}
	
	
	/**
	 * 把对象写出去
	 * @throws Exception
	 */
	public static void testOjectOut() throws Exception{
		Pojo pojo = new Pojo("test",12);
		
		FileOutputStream fos = new FileOutputStream("objectTest.tmp");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(pojo);
		
		oos.writeBoolean(true);
		oos.writeLong(11111111111111l);
		oos.flush();
		oos.close();
	}

	public static void testObjectIn()  throws Exception{
		FileInputStream fis = new FileInputStream("objectTest.tmp");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Pojo pojo = (Pojo) ois.readObject();
		System.out.println(pojo.toString());
		System.out.println(ois.readLong());
		System.out.println(ois.readBoolean());
		
	}
}
