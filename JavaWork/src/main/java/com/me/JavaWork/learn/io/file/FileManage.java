package com.me.JavaWork.learn.io.file;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class FileManage {
	private static String path = "E:\\AUTOA_IPS\\offLines";
	
	private static String path1 = "E:\\AUTOA_IPS\\offLines\\1234.xml";
	
	public static void main(String[] args) throws Exception {
//		FileManage fileManage = new FileManage();
//		//fileManage.getFileInfo("2015-12-29","2015-12-22");
//		fileManage.isFile();
//		File[] files =new FileManage().getAllFiles(path1);
//		
//		//查询文件后的结果集
//		StringBuffer dataTableContent = new StringBuffer();
//		if(files != null &&files.length > 0){
//			dataTableContent = new FileManage().list2Str(Arrays.asList(files),1);
//		}else{
//			dataTableContent.append("[]");
//		}
//		
		new FileManage().createFile();
	}
	
	
	
	
	/**
	 * 使用线程池来处理大量的数据
	 * **/
	public StringBuffer list2Str(List<File> fileList, final int nThreads) throws Exception {
		if (fileList == null || fileList.size() == 0) {
			return null;
		}
		
		StringBuffer ret = new StringBuffer();

		int size = fileList.size();
		
		//创建线程池服务，开启新的线程
		ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
		List<Future<StringBuffer>> futures = new ArrayList<Future<StringBuffer>>(nThreads);
		
		for (int i = 0; i < nThreads; i++) {
			final List<File> subList = fileList.subList(size / nThreads * i, size / nThreads * (i + 1));
			
			//callable 实现了runnable接口，但是比runnable更好用，可以有返回值，加异常等
			Callable<StringBuffer> task = new Callable<StringBuffer>() {
				@Override
				public StringBuffer call() throws Exception {
					
					StringBuffer sb = new StringBuffer();
					
					sb.append(getInfoFromXmlFiles(subList,null,null));
					
					return sb;
					
				}
			};
			futures.add(executorService.submit(task));
		}
		
		for (Future<StringBuffer> future : futures) {
			//要获future的结果，需要用get()方法；
			ret.append(future.get());
		}
		//最后要关闭线程池服务
		executorService.shutdown();
		
		return ret;
	}
	
	
	/**
	 * 处理xml文件列表，并返回成对应的数据字符串，供前台页面显示
	 * 如果是时间段查询就需要传入时间参数，否则不需要
	 * **/
	public StringBuffer getInfoFromXmlFiles(List<File> files,Date time1,Date time2){
		StringBuffer datatemp = new StringBuffer() ;
		datatemp.append("[");
		
		//读取xml文件的准备
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		Document document = null;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		int time1Index = -1;//数据是否含有time1的下标
		int time2Index = -1;//数据是否含有time2的下标
		//一天的时间（豪秒数）
		long oneDay = 86400000l;
		//符合条件的数据的序号
		int rightDataindex = 1;
		
		//查询的时间段之间的天数
		long minus1 = 0;
		//时间转化成
		String day1 = null;
		String day2 = null;
		//是否是时间段查询
		boolean timeQuery = false;
		if(time1 != null && time2 != null){
			timeQuery = true;
			day1 = dateFormat.format(time1);
			day2 = dateFormat.format(time2);
			minus1 = Math.abs(time1.getTime() - time2.getTime())/oneDay;
		}
		
		try {
			//获取路径下所有的文件
			for (int i = 0; i < files.size(); i++) {
				//每次循环开始之后，需要初始化下面两个值
				time1Index = -1;
				time2Index = -1;

				//新建文档对象
				db = dbf.newDocumentBuilder();
				//获取xml文件
				document = db.parse(files.get(i).getPath());
				//获取xml文件中元素
				Element element = (Element) document.getDocumentElement();
				
				String region = element.getAttribute("region");
				String ip = element.getAttribute("ip");
				String bras = element.getAttribute("bras");
				
				//获取时间列表
				NodeList list = document.getElementsByTagName("loop");
				//	时间列表
				List<String> dateList = new ArrayList<>();
				
				for (int j = 0; j < list.getLength(); j++) {
					element = (Element) list.item(j);
					String temp = element.getAttribute("year")+"-"+element.getAttribute("month")+"-"+element.getAttribute("day");
					Date date = dateFormat.parse(temp);
					temp = dateFormat.format(date);
					dateList.add(temp);
					if(temp.equals(day1)){
						time1Index = j;
					}
					if(temp.equals(day2)){
						time2Index = j;
					}
				}
				//计算本条数据中两天的天数差
				int minus2 = -1;
				if(timeQuery && time1Index != -1 && time2Index != -1){
					minus2 = Math.abs(time1Index - time2Index);
				}
				
				//符合条件(1:满足时间条件的，2：不需要时间判断，有多少文件就返回多少--目前的业务只有单个文件)
				if(Math.abs(minus2 - minus1) == 0 || !timeQuery){
					
					String dateStr = dateList.toString();
					dateStr = dateStr.substring(1,dateStr.length() - 1 );
					datatemp.append("['"+rightDataindex+"','123asd','"+bras+
							"','"+region+
							"','"+ip+
							"','<a  tabindex=\"0\" role=\"button\" class=\"btn btn-sm btn-primary\" data-placement=\"left\" data-toggle=\"popover\" data-trigger=\"focus\" title=\"离线时间列表\" data-content=\""+dateStr+"\">离线情况</a>"+ 
							"'],");
					rightDataindex ++;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		datatemp.append("]");
		return datatemp;
	}
	
	/**
	 * 判断某个文件是否存在
	 * */
	public boolean isExist(String path){
		File file = new File(path);
		if(file.exists()){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获取文件夹下的所有文件
	 * **/
	public File[] getAllFiles(String path){
		
		if(isExist(path)){
			File file = new File(path);
			File[] files = file.listFiles();
			if(files != null && files.length != 0){
				return files;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	
	/**
	 * 获取文件中满足条件的文件
	 * @throws ParserConfigurationException 
	 * **/
	public File[] matchFiles(File[] files) throws Exception{
		File[]  matchFiles = null;
		if(files.length > 0){
			
			return matchFiles;
		}else{
			return null;
		}
		
	}
	

	public void isFile(){
		File file =  new File("D:\\AUTOA_IPS\\offLines\\100.64.0.3.xml");
		System.out.println(file.isFile());
	}
	
	public void createFile(){
		String filePath = "d:\\debug\\372767136284928-telnet 221.182.42.26-1456121627315-txt.txt";
		File myFilePath = new File(filePath);
		if (!myFilePath.getParentFile().exists()) {
			myFilePath.getParentFile().mkdirs();
		}
		if(!myFilePath.exists()){
			try {
				myFilePath.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}

	
	
	
	
	
	
}
