package org.Third.Chapter.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class TestCompletableFutureWhenComplete {
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

		// 1.创建一个CompletableFuture对象
		CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {
				// 1.1模拟异步任务执行
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 1.2返回计算结果
				return "hello,jiaduo";
			}
		});

		// 2.添加回调函数
		future.whenComplete(new BiConsumer<String, Throwable>() {

			@Override
			public void accept(String t, Throwable u) {
				// 2.1如果没有异常，打印异步任务结果
				if (null == u) {
					System.out.println("1");
				} else {
					// 2.2打印异常信息
					System.out.println(u.getLocalizedMessage());
				}
			}
		});

		// 2.添加回调函数
		future.whenComplete(new BiConsumer<String, Throwable>() {

			@Override
			public void accept(String t, Throwable u) {
				// 2.1如果没有异常，打印异步任务结果
				if (null == u) {
					System.out.println("2");
				} else {
					// 2.2打印异常信息
					System.out.println(u.getLocalizedMessage());

				}
			}
		});

		// 3.挂起当前线程，等待异步任务执行完毕
		Thread.currentThread().join();
	}
}
