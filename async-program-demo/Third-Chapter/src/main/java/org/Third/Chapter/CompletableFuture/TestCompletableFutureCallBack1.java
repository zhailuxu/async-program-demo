package org.Third.Chapter.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

//又返回结果的回调
public class TestCompletableFutureCallBack1 {
	// 0.创建线程池
	private static final ThreadPoolExecutor bizPoolExecutor = new ThreadPoolExecutor(8, 8, 1, TimeUnit.MINUTES,
			new LinkedBlockingQueue<>(10));

	// I thenRun不能访问oneFuture的结果
	public static void thenRun() throws InterruptedException, ExecutionException {
		// 1.创建异步任务，并返回future
		CompletableFuture<String> oneFuture = CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {

				// 1.1休眠2s，模拟任务计算
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 1.2返回计算结果
				return "hello";
			}
		});
		// 2.在future上施加事件，当future计算完成后回调该事件，并返回新future
		CompletableFuture twoFuture = oneFuture.thenRun(new Runnable() {

			@Override
			public void run() {
				// 2.1.1当oneFuture任务计算完成后做一件事情
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
				System.out.println("---after oneFuture over doSomething---");
			}
		});

		// 3.同步等待twoFuture对应的任务完成，返回结果固定为null
		System.out.println(twoFuture.get());

	}

	// II thenRun不能访问oneFuture的结果
	public static void thenAccept() throws InterruptedException, ExecutionException {
		// 1.创建异步任务，并返回future
		CompletableFuture<String> oneFuture = CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {

				// 1.1休眠2s，模拟任务计算
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// 1.2返回计算结果
				return "hello";
			}
		});
		// 2.在future上施加事件，当future计算完成后回调该事件，并返回新future
		CompletableFuture twoFuture = oneFuture.thenAccept(new Consumer<String>() {

			@Override
			public void accept(String t) {
				// 2.1.1对oneFuture返回的结果进行加工
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("---after oneFuture over doSomething---" + t);
			}
		});

		// 3.在future上施加事件，当future计算完成后回调该事件，并返回新future
		CompletableFuture threeFuture = oneFuture.thenAccept(new Consumer<String>() {

			@Override
			public void accept(String t) {
				// 2.1.1对oneFuture返回的结果进行加工
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("---after oneFuture over doSomething---" + t);
			}
		});

		// 3.同步等待twoFuture对应的任务完成，返回结果固定为null
		System.out.println(twoFuture.get());

	}

	// III thenRun不能访问oneFuture的结果
	public static void thenRunAsync() throws InterruptedException, ExecutionException {
		// 1.创建异步任务，并返回future
		CompletableFuture<String> oneFuture = CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {

				// 1.1休眠2s，模拟任务计算
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// 1.2返回计算结果
				return "hello";
			}
		});
		// 2.在future上施加事件，当future计算完成后回调该事件，并返回新future
		CompletableFuture twoFuture = oneFuture.thenRunAsync(new Runnable() {

			@Override
			public void run() {
				// 2.1.1当oneFuture任务计算完成后做一件事情
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("---after oneFuture over doSomething---");
			}
		});

		// 3.同步等待twoFuture对应的任务完成，返回结果固定为null
		System.out.println(twoFuture.get());

	}

	// IV thenRun不能访问oneFuture的结果
	public static void thenAcceptAsync() throws InterruptedException, ExecutionException {
		// 1.创建异步任务，并返回future
		CompletableFuture<String> oneFuture = CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {

				// 1.1休眠2s，模拟任务计算
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// 1.2返回计算结果
				return "hello";
			}
		});
		// 2.在future上施加事件，当future计算完成后回调该事件，并返回新future
		CompletableFuture twoFuture = oneFuture.thenAcceptAsync(new Consumer<String>() {

			@Override
			public void accept(String t) {
				// 2.1.1对oneFuture返回的结果进行加工
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("---after oneFuture over doSomething---" + t);
			}
		});

		// 3.同步等待twoFuture对应的任务完成，返回结果固定为null
		System.out.println(twoFuture.get());

	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		// 1.thenRun test
		// thenRun();

		// 2.thenAccept test
		thenAccept();

		// 3.thenRunAsync test
		// thenRunAsync();

		// 4.thenAcceptAsync test;
		// thenAcceptAsync();
	}

}
