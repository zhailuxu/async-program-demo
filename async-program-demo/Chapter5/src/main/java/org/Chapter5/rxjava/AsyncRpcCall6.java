package org.Chapter5.rxjava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AsyncRpcCall6 {

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

	public static void main(String[] args) throws InterruptedException {

		// 1.生成ip列表
		List<String> ipList = new ArrayList<String>();
		for (int i = 1; i <= 10; ++i) {
			ipList.add("192.168.0." + i);
		}

		// 2.并发调用
		Flowable.fromArray( ipList.toArray(new String[0]))
				.flatMap(ip -> //2.1
				    Flowable.just(ip)//2.2
				       .subscribeOn(Schedulers.io())//2.3
				        .map(v -> rpcCall(v, v)))//2.4
			    .subscribe(new Consumer<String>() {

					@Override
					public void accept(String t) throws Exception {
						System.out.println(Thread.currentThread().getName() + t);						
					}
				});
		
		Thread.sleep(3000);
	}

}
