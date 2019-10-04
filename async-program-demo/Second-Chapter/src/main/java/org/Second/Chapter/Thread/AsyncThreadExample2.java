package org.Second.Chapter.Thread;

/**
 * Hello world!
 *
 */
public class AsyncThreadExample2 {

	public static void doSomethingA() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--- doSomethingA---");
	}

	public static void doSomethingB() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--- doSomethingB---");

	}

	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
		// 1.开启异步单元执行任务A
		Thread thread = new Thread("threadA") {
			@Override
			public void run() {
				try {
					doSomethingA();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		//thread.setDaemon(true);
		thread.start();

		// 2.执行任务B
		doSomethingB();

		// 3.同步等待线程A运行结束
		thread.join();
		System.out.println(System.currentTimeMillis() - start);
	}
}
