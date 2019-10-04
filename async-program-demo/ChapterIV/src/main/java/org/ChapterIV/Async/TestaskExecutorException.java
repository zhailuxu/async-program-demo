package org.ChapterIV.Async;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestaskExecutorException {

	public static void main(String arg[]) throws Exception {
		// 1.创建容器上下文
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "beans-annotation.xml" });

		// 2. 获取AsyncExecutorExample实例并调用打印方法
		System.out.println(Thread.currentThread().getName() + " begin ");
		AsyncAnnotationExample asyncCommentExample = applicationContext.getBean(AsyncAnnotationExample.class);
		asyncCommentExample.testException();
		System.out.println(Thread.currentThread().getName() + " end ");
	}
}
