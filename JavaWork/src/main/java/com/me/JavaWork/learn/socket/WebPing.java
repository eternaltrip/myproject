package com.me.JavaWork.learn.socket;

import java.net.InetAddress;
import java.net.Socket;

/**
 * 演示了如何使用 net.Socket 类的 getInetAddress() 方法来连接到指定主机
 * @author yangin
 *
 */
public class WebPing {
	   public static void main(String[] args) {
	        try {
	            InetAddress addr;
	            Socket sock = new Socket("www.runoob.com", 80);
	            addr = sock.getInetAddress();
	            System.out.println("连接到 " + addr);
	            sock.close();
	        } catch (java.io.IOException e) {
	            System.out.println("无法连接 " + args[0]);
	            System.out.println(e);
	        }
	    }
}
