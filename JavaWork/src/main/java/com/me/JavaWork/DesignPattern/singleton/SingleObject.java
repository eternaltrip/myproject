package com.me.JavaWork.DesignPattern.singleton;


public class SingleObject {
	
	private static SingleObject instance = new SingleObject();
	private static SingleObject instanceLazy;
	//使用private修饰构造方式，这个类就不会被实例化
	private SingleObject(){}
	
	
	
	
	/**1
	 * 饿汉模式
	 * 这种方式比较常用，但容易产生垃圾对象。
		优点：没有加锁，执行效率会提高。
		缺点：类加载时就初始化，浪费内存。
		它基于 classloder 机制避免了多线程的同步问题，不过，instance 在类装载时就实例化，虽然导致类装载的原因有很多种，
		在单例模式中大多数都是调用 getInstance 方法， 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，
		这时候初始化 instance 显然没有达到 lazy loading 的效果。
	 * */
	public static SingleObject getInstance(){
		return instance;
	}
	
	/***2
	 * 懒汉模式,需要的时候才实例化，而不是一开始就实例化
	 * 但是这里不是线程安全的，所以从严格意义上说，这不是一个单例对象
	 * */
	
	public static SingleObject getInstance_Lazyloading(){
		if(instanceLazy == null){
			instanceLazy = new SingleObject();
		}
		return instanceLazy;
	}
	
	/***3
	 * 懒汉模式(线程安全的)
	 * 优点：第一次调用才初始化，避免内存浪费。
		缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率。
	 * */
	public static synchronized SingleObject getInstance_lazyloading_synchronized(){
		if(instanceLazy == null){
			instanceLazy = new SingleObject();
		}
		return instanceLazy;
	}
	
	
	/***
	 * 4 DCL
	 * 双检锁/双重校验锁（DCL，即 double-checked locking
	 * 这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
		getInstance() 的性能对应用程序很关键。
		volatile （不稳定）,
		原因：线程可以把变量保存在本地内存（比如机器的寄存器）中，而不是直接在主存中进行读写。
			这就可能造成一个线程在主存中修改了一个变量的值，而另外一个线程还继续使用它在寄存器中的变量值的拷贝，造成数据的不一致。 
			要解决这个问题，把该变量声明为volatile（不稳定的）即可，这就指示JVM，这个变量是不稳定的，每次使用它都到主存中进行读取。、
			一般说来，多任务环境下各任务间共享的标志都应该加volatile修饰。
	 */
	private volatile static SingleObject singleton;
	public static SingleObject getInstance_DCL(){
		if(singleton == null){
			synchronized(SingleObject.class){
				if(singleton == null){
					singleton = new SingleObject();
				}
			}
		}
		return singleton;
	}
	
	/**5 登记式/静态内部类
	 * 这种方式能达到双检锁方式一样的功效，但实现更简单。对静态域使用延迟初始化，应使用这种方式而不是双检锁方式。这种方式只适用于静态域的情况，双检锁方式可在实例域需要延迟初始化时使用。
	 * 
	 */
	private static class SingletonHolder{
		private static final SingleObject INSTANCE = new SingleObject();
	}
	public static final SingleObject getSingle(){
		return SingletonHolder.INSTANCE;
	}
	
	
	/**6  使用枚举
	 * 这种实现方式还没有被广泛采用，但这是实现单例模式的最佳方法。它更简洁，自动支持序列化机制，绝对防止多次实例化。
		这种方式是 Effective Java 作者 Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化。不过，由于 JDK1.5 之后才加入 enum 特性，用这种方式写不免让人感觉生疏，在实际工作中，也很少用。
		不能通过 reflection attack 来调用私有构造方法(避免了使用反射来修改属性)。
	 */
	public enum Singleton{
		INSTANCE;
		//自定方法
		public void whatevermethod(){
		}
	}
	
	
	
	
	
	
	
	public void showMess(){
		System.out.println("hi");
	}
	
	
	
	
}

