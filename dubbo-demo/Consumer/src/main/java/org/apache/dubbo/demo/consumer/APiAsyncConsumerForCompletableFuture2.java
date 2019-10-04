package org.apache.dubbo.demo.consumer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

import com.books.dubbo.demo.api.GreetingService;

public class APiAsyncConsumerForCompletableFuture2 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// 1.创建服务引用对象实例
		ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<GreetingService>();
		// 2.设置应用程序信息
		referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));
		// 3.设置服务注册中心
		referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));

		// 4.设置服务接口和超时时间
		referenceConfig.setInterface(GreetingService.class);
		referenceConfig.setTimeout(5000);

		// 5.设置服务分组与版本
		referenceConfig.setVersion("1.0.0");
		referenceConfig.setGroup("dubbo");

		// 6. 设置为异步
		referenceConfig.setAsync(true);

		// 7.引用服务
		GreetingService greetingService = referenceConfig.get();

		// 8.异步执行,并设置回调
		System.out.println(greetingService.sayHello("hello"));
		CompletableFuture<String> future1 = RpcContext.getContext().getCompletableFuture();
		future1.whenComplete((v, t) -> {
			if (t != null) {
				t.printStackTrace();
			} else {
				System.out.println(Thread.currentThread().getName() + " " + v);
			}

		});

		// 9.异步执行,并设置回调
		System.out.println(greetingService.sayHello("jiaduo"));
		CompletableFuture<String> future2 = RpcContext.getContext().getCompletableFuture();
		future2.whenComplete((v, t) -> {
			if (t != null) {
				t.printStackTrace();
			} else {
				System.out.println(Thread.currentThread().getName() + " " + v);
			}

		});

		// 10. 挂起线程
		Thread.currentThread().join();
	}
}