package org.Third.Chapter.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

//		// 

public class TestFutureException2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		// 1.创建一个CompletableFuture对象
		CompletableFuture<String> future = new CompletableFuture<String>();

		// 2.开启线程计算任务结果，并设置
		new Thread(() -> {

			// 2.1休眠3s，模拟任务计算
			try {
				// 2.1.1 抛出异常
				if (true) {
					throw new RuntimeException("excetion test");
				}
				// 2.1.2设置正常结果
				future.complete("ok");
			} catch (Exception e) {
				// 2.1.3 设置异常结果
				future.completeExceptionally(e);
			}
			// 2.2设置计算结果到future
			System.out.println("----" + Thread.currentThread().getName() + " set future result----");

		}, "thread-1").start();

		// 3.等待计算结果
		// System.out.println(future.get());
		System.out.println(future.exceptionally(t -> "default").get());// 默认值
	}
}
