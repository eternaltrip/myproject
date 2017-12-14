package com.me.JavaWork.learn.ClassLoader;


public class ClassLoaderTest {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		/**
		 *  1， BootStrap 主要的类加载器，所有的类和类加载器都是基于此运行。加载JRE/lib/rt.jar
			2，ExtClassLoader 加载JRE/lib/ext/*.jar
			3，AppClassLoader 加载 classpath指定的所有jar或目录
			优先级问题：如果在1级中已经有了，那么就从这里加载。如果1中没有2中有，那么就从2中加载，如果只有3中有，那么就从3中间加载
			级别越高，加载优先级越高
		 */
		
		//ClassLoader.getSystemClassLoader().loadClass("com.ClassLoader.ClassLoaderTest");
		//System.out.println(System.class.getClassLoader().getClass());
		
		System.out.println("类加载器的层级关系");
		ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
		while (classLoader != null) {
			System.out.println(classLoader.getClass());
			classLoader = classLoader.getParent();
		}
		System.out.println(classLoader);//这里是最顶级的类加载器了
		
		//System.out.println(new ClassLoaderAttachment().toString());
		
		//使用自己的加载器进行加载
//		Class clazz = new MyClassLoader("testlib").loadClass("ClassLoaderAttachment");
//		Date d1 = (Date) clazz.newInstance();
//		System.out.println(d1); 
		
	}

}



