package com.me.JavaWork.learn.net;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 *获取url的头信息
 * @author yangin
 *
 */
public class GetUrlTitleInfo {
	public static void main(String[] args) throws IOException{
        URL url = new URL("http://www.runoob.com");
        URLConnection conn = url.openConnection();
        
        Map<String, List<String>> headers = conn.getHeaderFields();
        Set<String> keys = headers.keySet();
        for( String key : keys ){
            String val = conn.getHeaderField(key);
            System.out.println(key+"    "+val);
        }
        System.out.println( conn.getLastModified() );
    }
}
