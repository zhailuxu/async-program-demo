package org.Chapter5.rxjava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

//

public class AsyncRpcCall4 {

	public static String rpcCall(String ip, String param) {

		System.out.println(Thread.currentThread().getName() + " " + ip + " rpcCall:" + param);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return param;

	}

	public static void main(String[] args) throws InterruptedException {

		// 1.生成ip列表
		List<String> ipList = new ArrayList<String>();
		for (int i = 1; i <= 10; ++i) {
			ipList.add("192.168.0." + i);
		}

		// 2.顺序调用
		Flowable.fromArray(ipList.toArray(new String[0])).observeOn(Schedulers.io())// 2.1切换到IO线程执行
				.map(v -> rpcCall(v, v))// 2.2映射结果
				.subscribe(System.out::println);// 2.3订阅

		//3.
		System.out.println("main execute over and wait");
		Thread.currentThread().join();// 挂起main函数所在线程
	}
}
