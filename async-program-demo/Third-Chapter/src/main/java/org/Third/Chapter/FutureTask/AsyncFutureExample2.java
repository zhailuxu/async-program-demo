package org.Third.Chapter.FutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class AsyncFutureExample2 {

	public static String doSomethingA() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--- doSomethingA---");

		return "TaskAResult";
	}

	public static String doSomethingB() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--- doSomethingB---");
		return "TaskBResult";

	}

	// 0自定义线程池
	private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
	private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
			AVALIABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(5),
			new ThreadPoolExecutor.CallerRunsPolicy());

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		long start = System.currentTimeMillis();

		// 1.创建future任务
		FutureTask<String> futureTask = new FutureTask<String>(() -> {
			String result = null;
			try {
				result = doSomethingA();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		});

		// 2.开启异步单元执行任务A
		POOL_EXECUTOR.execute(futureTask);

		// 3.执行任务B
		String taskBResult = doSomethingB();

		// 4.同步等待线程A运行结束
		String taskAResult = futureTask.get();
		// 5.打印两个任务执行结果
		System.out.println(taskAResult + " " + taskBResult);
		System.out.println(System.currentTimeMillis() - start);
	}
}
