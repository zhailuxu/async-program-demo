package org.ChapterIV.Async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableAsync // 开启异步执行
@Component // 注入该Bean到Spring容器
public class AsyncAnnotationExample {

	// @Scheduled(cron = "*/5 * * * * ?") // 每隔5秒执行一次
	public void asyncPrint1() {
		try {
			System.out.println(Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Async
	public void printMessages() {
		for (int i = 0; i < 6; i++) {
			try {
				Thread.sleep(5000);
				System.out.println(Thread.currentThread().getName() + " Message" + i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Async
	public void testException() throws Exception {
		   if (true)
			throw new Exception("error");
	}

	@Async
	public CompletableFuture<String> doSomething() {

		// 1.创建future
		CompletableFuture<String> result = new CompletableFuture<String>();
		// 2.模拟任务执行
		try {
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + "doSomething");
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.complete("done");

		// 3.返回结果
		return result;
	}
}
