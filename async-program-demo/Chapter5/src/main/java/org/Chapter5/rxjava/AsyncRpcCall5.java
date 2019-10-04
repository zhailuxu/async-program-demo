package org.Chapter5.rxjava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

//

public class AsyncRpcCall5 {

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

		//1.
		long start = System.currentTimeMillis();
		Flowable.fromCallable(() -> {//1.1
			Thread.sleep(1000); // 1.2模拟耗时的操作
			return "Done" +Thread.currentThread().getName();
		})
		.subscribeOn(Schedulers.io())//1.3
		  .observeOn(Schedulers.single())//1.4
		  .subscribe(new Consumer<String>() {

			@Override
			public void accept(String t) throws Exception {
				System.out.println(Thread.currentThread().getName() + t);
				
			}
		}, Throwable::printStackTrace);//1.5
		
		//2.
		System.out.println("cost:" + (System.currentTimeMillis() - start));
		
		//3.
		Thread.sleep(2000000); // 等待流结束
	}
}
