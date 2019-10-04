package org.Third.Chapter.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestCompletableFutureCallBack {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

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
		CompletableFuture<String> twoFuture = oneFuture.thenApply(new Function<String, String>() {

			// 2.1对步骤1计算结果基础上进行计算，这里t为步骤1返回的hello
			@Override
			public String apply(String t) {
				// 2.1.1对oneFuture返回的结果进行加工
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 2.1.2返回加工后结果
				return t + " jiduo";
			}
		});

		// 3.同步等待twoFuture对应的任务完成，并获取结果
		System.out.println(twoFuture.get());

	}

}
