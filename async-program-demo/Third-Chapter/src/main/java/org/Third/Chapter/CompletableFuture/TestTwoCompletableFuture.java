package org.Third.Chapter.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class TestTwoCompletableFuture {

	// 1.异步任务，返回future
	public static CompletableFuture<String> doSomethingOne(String encodedCompanyId) {
		// 1.1创建异步任务
		return CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {

				// 1.1.1休眠1s，模拟任务计算
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 1.1.2 解密，并返回结果
				String id = encodedCompanyId;
				return id;
			}
		});
	}

	// 2.开启异步任务，返回future
	public static CompletableFuture<String> doSomethingTwo(String companyId) {
		return CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {

				// 2.1,休眠3s，模拟计算
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 2.2 查询公司信息，转换为str，并返回
				String str = companyId + ":alibaba";
				return str;
			}
		});
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		// I，等doSomethingOne执行完毕后，接着执行doSomethingTwo
		CompletableFuture result = doSomethingOne("123").thenCompose(id -> doSomethingTwo(id));
		System.out.println(result.get());

		// II,等doSomethingOne和doSomethingTwo都完成后，使用它们的结果做一件事
		result = doSomethingOne("123").thenCombine(doSomethingTwo("456"), (one, two) -> {
			return one + " " + two;
		});
		System.out.println(result.get());

	}

}
