package org.Chapter5.rxjava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AsyncRpcCall2 {

	public static String rpcCall(String ip, String param) {

		System.out.println(ip + " rpcCall:" + param);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return param;

	}

	// 0自定义线程池
	private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(10, 10, 1, TimeUnit.MINUTES,
			new LinkedBlockingQueue<>(5), new ThreadPoolExecutor.CallerRunsPolicy());

	public static void main(String[] args) {

		// 1.生成ip列表
		List<String> ipList = new ArrayList<String>();
		for (int i = 1; i <= 10; ++i) {
			ipList.add("192.168.0." + i);
		}

		// 2.并发调用
		long start = System.currentTimeMillis();

		Flowable.fromArray(ipList.toArray(new String[0]))
		.flatMap(ip -> // 2.1
				Flowable.just(ip)// 2.2
						.subscribeOn(Schedulers.from(POOL_EXECUTOR))// 2.3
						.map(v -> rpcCall(v, v)))// 2.4
						.blockingSubscribe(System.out::println);// 2.5

		// 3.打印耗时
		System.out.println("cost:" + (System.currentTimeMillis() - start));
		
		//4.关闭线程池
		POOL_EXECUTOR.shutdown();
	}

}
