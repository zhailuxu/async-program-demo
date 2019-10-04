package org.ChapterIV.TaskExecutor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestaskExecutor {

	public static void main(String arg[]) throws InterruptedException {
		// 1.创建容器上下文
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "beans.xml" });

		// 2. 获取AsyncExecutorExample实例并调用打印方法
		System.out.println(Thread.currentThread().getName() + " begin ");
		AsyncExecutorExample asyncExecutorExample = applicationContext.getBean(AsyncExecutorExample.class);
		asyncExecutorExample.printMessages();
		System.out.println(Thread.currentThread().getName() + " end ");

		// 3.关闭执行器，释放线程
		asyncExecutorExample.shutdown();

	}
}
