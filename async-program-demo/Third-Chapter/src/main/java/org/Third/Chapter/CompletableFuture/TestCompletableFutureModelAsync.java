package org.Third.Chapter.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class TestCompletableFutureModelAsync {
	// 0自定义线程池
	private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
	private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
			AVALIABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(5),
			new ThreadPoolExecutor.CallerRunsPolicy());

	public static CompletableFuture runAsync(Runnable runnable) {
		CompletableFuture<String> future = new CompletableFuture<String>();

		// 2.开启线程计算任务结果，并设置
		POOL_EXECUTOR.execute(() -> {

			// 2.1休眠3s，模拟任务计算
			try {
				runnable.run();
				future.complete(null);

			} catch (Exception e) {
				future.completeExceptionally(e);
			}
		});

		return future;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

		// 1.创建一个CompletableFuture对象
		CompletableFuture<String> future = runAsync(new Runnable() {

			@Override
			public void run() {

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("hello");

			}
		});

		future.whenComplete(new BiConsumer<String, Throwable>() {

			@Override
			public void accept(String t, Throwable u) {

				if (null == u) {
					System.out.println(t);
				} else {
					System.out.println(u.getLocalizedMessage());

				}
			}
		});

	}
}
